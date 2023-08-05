public interface Calculator 
            extends java.rmi.Remote { 

    /* Take an integer and push to the top of the stack */
    public void pushValue(int Val)
            throws java.rmi.RemoteException;

    /* Push and operator and return results. Also pop all operands on stack */
    public void pushOperation(String operator)
            throws java.rmi.RemoteException;

    /* Pop the top of the stack and return to client */
    public int pop()
            throws java.rmi.RemoteException;

    /* Return if the stack is empty */
    public boolean isEmpty()
            throws java.rmi.RemoteException;

    /* Wait milliseconds before popping */
    public int delayPop(int millis)
            throws java.rmi.RemoteException;
    
} 
