import java.rmi.RemoteException;
import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorImplementation
        extends
        java.rmi.server.UnicastRemoteObject
        implements Calculator {
    
    public Stack<Integer> operands;

    public CalculatorImplementation()
            throws RemoteException {
        super();
        operands = new Stack<Integer>();
    }


    @Override
    public void pushValue(int Val) throws RemoteException {
        operands.push(Val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        if (isEmpty()) throw new RemoteException("Stack is empty");
        if (operands.size()==1) return;
        int result;
        if (operator.equalsIgnoreCase("min"))
            result = min();
        else if (operator.equalsIgnoreCase("max"))
            result = max();
        else if (operator.equalsIgnoreCase("gcd"))
            result = gcd();
        else if (operator.equalsIgnoreCase("lcd"))
            result = lcd();
        else
            throw new RemoteException("Invalid operator: " + operator);
        pushValue(result);
    }

    @Override
    public int pop() throws RemoteException {
        try {
            return operands.pop();
        }catch (EmptyStackException e){
            throw new RemoteException("Stack is Empty");
        }
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return operands.isEmpty();
    }

    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return pop();
    }


    private int max(){
        int result = operands.pop();
        int temp;
        while (!(operands.isEmpty())){
            temp = operands.pop();
            if (temp >= result){
                result = temp;
            }
        }
        return result;
    }

    private int min(){
        int result = operands.pop();
        int temp;
        while (!(operands.isEmpty())){
            temp = operands.pop();
            if (temp >= result){
                result = temp;
            }
        }
        return result;
    }

    private int gcd(int first, int second){
        if (second == 0) return first;
        return gcd(second, first%second);
    }

    private int gcd(){
        int result = operands.pop();
        while (!(operands.isEmpty()))
            result = gcd(result, operands.pop());
        return result;
    }

    private int lcd(int first, int second){
        return first*second/gcd(first, second);
    }

    private int lcd(){
        int result = operands.pop();
        while(!(operands.isEmpty()))
            result = lcd(result, operands.pop());
        return result;
    }
}