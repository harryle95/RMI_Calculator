 
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException;
import java.util.Scanner;
import java.util.Arrays;

public class CalculatorClientShell { 
 
    public static void main(String[] args) { 
        try { 
            Calculator c = (Calculator)
                           Naming.lookup(
                 "rmi://localhost/CalculatorService");

            while (true){
                Scanner stdin = new Scanner(System.in);
                System.out.print(">>");
                String input = stdin.nextLine();
                String token;
                String[] buffer = input.split(" ");
                if (buffer.length == 0) continue;
                int operand;

                for (int i = 0; i < buffer.length; i++){
                    token = buffer[i];
                    try {
                        operand = Integer.parseInt(token);
                        c.pushValue(operand);
                    }
                    catch (NumberFormatException e){
                        try {
                            if (token.equalsIgnoreCase("max")||
                                    token.equalsIgnoreCase("min")||
                                    token.equalsIgnoreCase("lcd")||
                                    token.equalsIgnoreCase("gcd")){
                                c.pushOperation(token);
                            }else if (token.equalsIgnoreCase("pop")){
                                try {
                                    System.out.println(c.pop());
                                } catch (RemoteException re_exp){
                                    System.out.println("Empty stack");
                                }
                            }else if (token.equalsIgnoreCase("isEmpty")){
                                System.out.println(c.isEmpty());
                            }else
                                System.out.println("Invalid command");
                        }
                        catch (RemoteException re_exp){
                            System.out.println("Invalid operator: "+ token);
                        }
                    }
                }
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