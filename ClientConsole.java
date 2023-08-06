import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientConsole {
    private final int EXIT_SUCCESS = 0;

    private final int EXIT_FAILURE = 1;

    private boolean EXIT_PROGRAM = false;
    private final String PATTERN = "([^\\(]+)\\(([^\\)]*)\\)";
    private String method;
    private String argument;

    private final boolean verbose;

    public ClientConsole(boolean verbose) {
        this.verbose = verbose;
    }

    /*
     * Parse raw input from stdin into operator, operand format.
     * Valid input includes:
     * pushValue(int) - interface pushValue
     * pushOperation(operator) - interface pushOperation
     * pop() - interface pop
     * delayPop(int) - interface delayPop
     * isEmpty() - interface isEmpty
     * exit() - exit current shell/terminal program
     * clear() - clean allocated stack
     * 
     * Example:
     * "pop()" -> operator: "pop", operand: "null"
     * "pushValue(10)" -> operator: "pushValue", operand: "10"
     *
     * Return:
     * error code (Integer) - 0: input parsed successfully. -1: invalid input
     *
     * Args:
     * input (String) - raw input. Input must either be blank or
     */
    private int parseInput(String input) {
        /* Handle null input and blank lines */
        if (input == null)
            return EXIT_FAILURE;
        if (input.isEmpty())
            return EXIT_FAILURE;

        /* Split method into operator and operand */
        Pattern regexPattern = Pattern.compile(PATTERN);
        Matcher matcher = regexPattern.matcher(input);

        if (matcher.find()) {
            method = matcher.group(1).trim();
            argument = matcher.group(2).trim();
            argument = argument.replace("\"", "").replace("'", "");
            return EXIT_SUCCESS;
        } else {
            if (verbose)
                System.out.println("Error: invalid input. Input must be method(argument)");
            return EXIT_FAILURE;
        }
    }

    /*
     * Execute input on a stub c with client identifier ID
     * 
     * Args:
     * c (Calculator) - stub to execute command remotely
     * clientID (String) - used to identified stack allocated for client
     */
    private void executeInput(Calculator c, String clientID) {
        if (method.equalsIgnoreCase("pushValue")) {
            try {
                int val = Integer.parseInt(argument);
                c.pushValue(val, clientID);
            } catch (NumberFormatException nfe) {
                if (verbose)
                    System.out.println("Invalid argument for pushValue: " + argument);
            } catch (RemoteException re) {
                if (verbose)
                    System.out.println("Remote Exception: " + re);
            }
        } else if (method.equalsIgnoreCase("pushOperation")) {
            try {
                c.pushOperation(argument, clientID);
            } catch (RemoteException re) {
                if (verbose)
                    System.out.println("Invalid operation: " + argument);
            }
        } else if (method.equalsIgnoreCase("pop")) {
            try {
                System.out.println(c.pop(clientID));
            } catch (RemoteException re) {
                if (verbose)
                    System.out.println("Empty stack");
            }
        } else if (method.equalsIgnoreCase("delayPop")) {
            try {
                int val = Integer.parseInt(argument);
                System.out.println(c.delayPop(val, clientID));
            } catch (NumberFormatException nfe) {
                if (verbose)
                    System.out.println("Invalid argument for delayPop: " + argument);
            } catch (RemoteException re) {
                if (verbose)
                    System.out.println("Empty stack");
            }
        } else if (method.equalsIgnoreCase("isEmpty")) {
            try {
                System.out.println(c.isEmpty(clientID));
            } catch (RemoteException re) {
                if (verbose)
                    System.out.println("RemoteException: " + re);
            }
        } else if (method.equalsIgnoreCase("exit")) {
            EXIT_PROGRAM = true;
        } else if (method.equalsIgnoreCase("clear")) {
            try {
                c.pushOperation("max", clientID);
                c.pop(clientID);
            } catch (Exception e) {
                if (verbose)
                    System.out.println("Runtime exception: " + e);
            }
        } else {
            if (verbose)
                System.out.println("Invalid method");
        }
    }

    /*
     * Console run. Prompt for user input in interactive shell in a REPL format.
     * To exit the program, type exit()
     * 
     * Args:
     * c (Calculator): stub to execute command remotely
     * clientID (String): used to identy the stack allocated to the current client
     */
    public void run(Calculator c, String clientID) {
        String token;
        Scanner stdin = new Scanner(System.in);
        int status;
        while (true) {
            if (verbose)
                System.out.print(">>>");
            token = stdin.nextLine().trim();
            status = parseInput(token);
            if (status == EXIT_SUCCESS)
                executeInput(c, clientID);
            if (EXIT_PROGRAM)
                break;
        }
    }
}
