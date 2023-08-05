
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorClientShell {
    private static final String pattern = "([^\\(]+)\\(([^\\)]*)\\)";
    private static String method;

    private static String argument;
    private static void parseInput(String input){
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        if (matcher.find()) {
            method = matcher.group(1).trim();
            argument = matcher.group(2).trim();
            argument = argument.replace("\"", "").replace("'","");
        } else {
            System.out.println("Error: invalid input. Input must be method(argument)");
        }
    }

    private static void executeInput(Calculator c){
        if (method.equalsIgnoreCase("pushValue")){
            try{
                int val = Integer.parseInt(argument);
                c.pushValue(val);
            }catch (NumberFormatException nfe) {
                System.out.println("Invalid argument for pushValue: " + argument);
            }catch (RemoteException re){
                System.out.println("Remote Exception: "+re);
            }
        }else if(method.equalsIgnoreCase("pushOperation")){
            try{
                c.pushOperation(argument);
            }catch (RemoteException re){
                System.out.println("Invalid operation: " + argument);
            }
        }else if(method.equalsIgnoreCase("pop")){
            try{
                System.out.println(c.pop());
            }catch (RemoteException re){
                System.out.println("Empty stack");
            }
        }else if (method.equalsIgnoreCase("delayPop")){
            try{
                int val = Integer.parseInt(argument);
                System.out.println(c.delayPop(val));
            }catch (NumberFormatException nfe){
                System.out.println("Invalid argument for delayPop: "+argument);
            }catch (RemoteException re){
                System.out.println("Empty stack");
            }
        }else if (method.equalsIgnoreCase("isEmpty")){
            try{
                System.out.println(c.isEmpty());
            }catch (RemoteException re){
                System.out.println("RemoteException: " + re);
            }
        }else{
            System.out.println("Invalid method");
        }
    }

    public static void main(String[] args) {

        try {
            Calculator c = (Calculator)
                    Naming.lookup(
                            "rmi://localhost/CalculatorService");

            while (true) {
                Scanner stdin = new Scanner(System.in);
                System.out.print(">>");
                String input = stdin.nextLine().trim();
                parseInput(input);
                executeInput(c);
            }
        }
        catch (MalformedURLException murle) {
            System.out.println();
            System.out.println(
                    "MalformedURLException");
            System.out.println(murle);
        }
        catch (RemoteException re) {
            System.out.println();
            System.out.println(
                    "RemoteException");
            System.out.println(re);
        }
        catch (NotBoundException nbe) {
            System.out.println();
            System.out.println(
                    "NotBoundException");
            System.out.println(nbe);
        }
        catch (
                java.lang.ArithmeticException
                        ae) {
            System.out.println();
            System.out.println(
                    "java.lang.ArithmeticException");
            System.out.println(ae);
        }
    }
} 