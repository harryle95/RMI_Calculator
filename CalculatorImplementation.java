import java.rmi.RemoteException;
import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorImplementation extends java.rmi.server.UnicastRemoteObject
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
        if (isEmpty()) return;
        if (operands.size()==1) return;
        int result;
        if (operator.equalsIgnoreCase("min"))
            result = Operation.min(operands);
        else if (operator.equalsIgnoreCase("max"))
            result = Operation.max(operands);
        else if (operator.equalsIgnoreCase("gcd"))
            result = Operation.gcd(operands);
        else if (operator.equalsIgnoreCase("lcm"))
            result = Operation.lcm(operands);
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

}