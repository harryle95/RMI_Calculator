import java.rmi.Naming;

public class CalculatorServer {

    /* Server constructor, bind image to localhost */
    public CalculatorServer() {
        try {
            Calculator c = new CalculatorImplementation();
            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new CalculatorServer();
    }
}