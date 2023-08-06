import java.rmi.RemoteException;

public interface Calculator
                extends java.rmi.Remote {

        /*
         * Take an integer and push to the top of the stack
         * 
         * Args:
         * Val (int): value to push to stack
         */
        void pushValue(int Val, String clientID) throws RemoteException;

        /*
         * Pop all operands and apply input operator to operands.
         * Result is push to stack.
         * 
         * Args:
         * operator (String): one of [max, min, lcd, gcd]
         * 
         * Throw:
         * RemoteException: invalid operator is provided
         */
        public void pushOperation(String operator, String clientID)
                        throws java.rmi.RemoteException;

        /*
         * Pop the top of the stack and return to client
         * 
         * Return:
         * Integer value stored at head
         * 
         * Throw:
         * Remote Exception: if stack is empty
         */
        public int pop(String clientID)
                        throws java.rmi.RemoteException;

        /* Return if the stack is empty */
        boolean isEmpty(String clientID) throws RemoteException;

        /*
         * Perform a pop after a wait
         * 
         * Return:
         * Integer value stored at head
         * 
         * Throw:
         * Remote Exception: if stack is empty
         */
        int delayPop(int millis, String clientID) throws RemoteException;
}
