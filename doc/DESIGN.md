
## Server 

Server implements a standard server code, creating an `rmi` service (Calculator) with a public interface and 
registering the service on `rmiregistry` that can be accessed remotely. For ease of testing, hostname is bound to 
localhost and the program listens to port 1099 (default `rmiregistry` port) for incoming requests.

## Calculator Implementation

The implementation maintains a `Map<String, Stack<Int>>` StackPool. Each client host is allocated a private stack, which can be 
indexed from StackPool using `clientID`. The interface methods are modified to take `String clientID` as an additional parameters
to enable operation operation on private stacks. The implentation makes use of the utlity class `Operation` which implements stack-based 
operations `max, min, lcm, gcd`. The methods of `Operations` are unit-tested. 

## Client

Client is simply a shell program with limited functionalities but supports invocation of remote methods. Any runtime exceptions, both by calling a valid method such as popping an empty stack, or by invalid input (not part of acceptable commands) will be thrown but will not cause `System.exit(-1)`. Depending on `option` argument (see `Running Client` section below), the error message will be displayed to stdout. 

### Acceptable commands

Acceptable inputs to perform matching remote procedures are:

- `pushValue(int)`: push int value to matching remote stack.
- `pushOperation(String)`: push matching operations to remote stack, which pops all values and push the result of operation on all values. Acceptable operation strings are `max, min, lcm, gcd` that can be entered with or without quotation marks `"`. 
- `pop()`: pop the top of the matching remote stack and return to client. 
- `delayPop(int)`: server will perform a pop after sleeping on current thread for int value milliseconds. 
- `isEmpty()`: return the boolean result if whether the matching remote stack is empty. 

Additional useful methods that are not part of the interface includes: 

- `exit()`: exit current shell 
- `clear()`: empty the matching remote stack. Equivalent to pushing a max operation then pop. 

### Running Client

After compiling, client can be run using this syntax: 

`java CalculatorClient [option] [clientID]`

where `[]` denotes input being optional. 

Valid input option is:
`-d` which runs the program in debug mode. In non-debug mode (shell) mode, inputs are prompted after `>>>` and results and error messages are displayed to `stdout`. Debug mode is only for testing and will only print to `stdout` the output of `pop(), delayPop(), isEmpty()` operations. If `option` is not provided, the program will run in shell mode. 

`clientID` uniquely identifies clients and is used to identify the stack allocated to the client on server. If `clientID` is not provided, the default value of `clientID` is `DEFAULT`. 

### Examples:

To run in shell mode using shared stack with `clientID = DEFAULT`:

```
make shell
```

To run in shell mode with provided `clientID`: 

```
java CalculatorClient clientID
```

To use input from a `inputFile` and direct the output to `outputFile` using a specified `clientID`: 

```
java CalculatorClient -d clientID < inputFile > outputFile
```

To test the program using `inputFile` and `expectedOutput` using a specified `clientID`: 

```
java CalculatorClient -d clientID < inputFile | diff -eWB expectedOutput -
```
To test multiple clients concurrent execution, each with sequence of operations specified in `inputFile1, inputFile2, ...` and expected behaviours specified in `outputFile1, outputFile2, ...`, we can run the following: 

```
java CalculatorClient -d clientID1 < inputFile1 > log1 & 
java CalculatorClient -d clientID2 < inputFile2 > log2 & 
java CalculatorClient -d clientID3 < inputFile3 > log3 & 
java CalculatorClient -d clientID4 < inputFile4 > log4 & 

# Sleep to wait for synchronisation
sleep(10) 

# Compare output 
diff -wEB log1 outputFile1 
diff -wEB log2 outputFile2
diff -wEB log3 outputFile3
diff -wEB log4 outputFile4
```

