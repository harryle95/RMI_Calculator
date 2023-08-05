public interface Calculator 
            extends java.rmi.Remote { 

    /* Take an integer and push to the top of the stack
     * 
     * Args:
     *  Val (int): value to push to stack
     */
    public void pushValue(int Val)
            throws java.rmi.RemoteException;

    /* Pop all operands and apply input operator to operands.
     * Result is push to stack.
     * 
     * Args:
     *  operator (String): one of [max, min, lcd, gcd]
     * 
     * Throw:
     *  RemoteException: invalid operator is provided
     */
    public void pushOperation(String operator)
            throws java.rmi.RemoteException;

    /* Pop the top of the stack and return to client
     * 
     * Return:
     *  Integer value stored at head
     * 
     * Throw: 
     *  Remote Exception: if stack is empty
     */
    public int pop()
            throws java.rmi.RemoteException;

    /* Return if the stack is empty */
    public boolean isEmpty()
            throws java.rmi.RemoteException;

    /* Perform a pop after a wait 
     * 
     * Return:
     *  Integer value stored at head
     * 
     * Throw: 
     *  Remote Exception: if stack is empty
    */
    public int delayPop(int millis)
            throws java.rmi.RemoteException;
    
} 
