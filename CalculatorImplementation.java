import java.rmi.RemoteException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class CalculatorImplementation extends java.rmi.server.UnicastRemoteObject
        implements Calculator {

    public Map<String, Stack<Integer>> StackPool;

    public CalculatorImplementation()
            throws RemoteException {
        super();
        StackPool = new HashMap<>();
    }

    private Stack<Integer> getStack(String clientID) {
        if (!(StackPool.containsKey(clientID))) {
            StackPool.put(clientID, new Stack<Integer>());
        }
        return StackPool.get(clientID);
    }

    @Override
    public void pushValue(int Val, String clientID) throws RemoteException {
        Stack<Integer> clientStack = getStack(clientID);
        clientStack.push(Val);
    }

    @Override
    public void pushOperation(String operator, String clientID) throws RemoteException {
        if (isEmpty(clientID))
            return;
        Stack<Integer> clientStack = getStack(clientID);
        if (clientStack.size() == 1)
            return;
        int result;
        if (operator.equalsIgnoreCase("min"))
            result = Operation.min(clientStack);
        else if (operator.equalsIgnoreCase("max"))
            result = Operation.max(clientStack);
        else if (operator.equalsIgnoreCase("gcd"))
            result = Operation.gcd(clientStack);
        else if (operator.equalsIgnoreCase("lcm"))
            result = Operation.lcm(clientStack);
        else
            throw new RemoteException("Invalid operator: " + operator);
        pushValue(result, clientID);
    }

    @Override
    public int pop(String clientID) throws RemoteException {
        Stack<Integer> clientStack = getStack(clientID);
        try {
            return clientStack.pop();
        } catch (EmptyStackException e) {
            throw new RemoteException("Stack is Empty");
        }
    }

    @Override
    public boolean isEmpty(String clientID) throws RemoteException {
        Stack<Integer> clientStack = getStack(clientID);
        return clientStack.isEmpty();
    }

    @Override
    public int delayPop(int millis, String clientID) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return pop(clientID);
    }

}