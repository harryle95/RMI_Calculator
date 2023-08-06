## General Overview

Test cases are provided in the folder `test`, with `input` files being executed by a client, and `output` being the expected behaviour. In each test directory, there is a `run_test.sh` file which orchestrates the testing. The `single` directory includes testing done on single clients, and `multi` directory includes testing done on multiple concurrent clients. 

### Before runnning any of the tests below: 

Clean up (kill previous rmiregistry and server) - sudo priviledge is needed:

```
make clean
```

Run `rmiregistry`:

```
make registry 
```

Run server: 

```
make server
```

## User Acceptance Testing 

UAT is done by running the program in shell mode. 

- Single client: run `make shell` in the repo's root directory and follow the prompt below. The output should match with what is seen here. Note that the `clear()` statement pop all values in the current stack and may raise an exception if remote stack is empty and the output will look as shown. If the remote stack has any value, there will not be anything displayed after the call. 

```
>>>clear() 
Runtime exception: java.rmi.ServerException: RemoteException occurred in server thread; nested exception is: 
        java.rmi.RemoteException: Stack is Empty
>>>isEmpty()
true
>>>pushValue(100)
>>>pushValue(1000)
>>>pushOperation(gcd)
>>>pop()
100
>>>isEmpty()
true
>>>exit()
```

- Multiple clients on shared stack - open four terminals, each running `make shell`. We will run the following commands sequentially. In this test, the stack is shared (t1 pushes the value 1000, t2 pushes the value 100 and t3 pop the result of gcd which is correct - 100). After this sequence of event, the stack is empty, and `t4 isEmpty()` returns `true`.
  
Terminal 1
```
>>>clear()
Runtime exception: java.rmi.ServerException: RemoteException occurred in server thread; nested exception is: 
        java.rmi.RemoteException: Stack is Empty
>>>pushValue(1000)
>>>exit()
```

Terminal 2
```
>>>pushValue(100)
>>>exit()
```

Terminal 3
```
>>>pushOperation(gcd)
>>>pop()
100
>>>exit()
```

Terminal 4
```
>>>isEmpty()
true
>>>exit()
```


## Unit Tests

Unit tests are performed on the stack-based methods:

- `int max(Stack<Integer> stack)`
- `int min(Stack<Integer> stack)`
- `int gcd(Stack<Integer> stack)`
- `int lcm(Stack<Integer> stack)`

which are the public methods in `Operation.java`. The test inputs and expected outputs are stored in `TestOperation.java`. I was not using `JUnitTest` because I was not sure whether the package is compatible with gradescope and whether I will need to commit the `JAR` file. 


## Integeration Tests

Integration test is done by testing the client as a whole. The strategy of the test is to test using input files specifying the commands that a client will invoke and test with expected output (see [the Examples section in DESIGN](DESIGN.md) for more information).

### Single Client 

Testing on single clients is done by running. Too see how single testing is performed, refered to [the bashscript](../test/single/run_test.sh).

```
make int_test_single
```

### Multiple Clients - shared stack 

For testing multiple clients with shared stack, I assume that the operations are atomic, and hence the sequence of concurrent events is serialisation. In that case, the behaviour is tested by running in a sequential order the operations for each client (specified by `input<id>`). The test cases for multiple clients are stored in `test/multi` for tests 1 to 5. To run the test: 

```
make int_test_multiple_shared
```

### Multiple Clients - private stack 

For testing multiple clients with private stack, each client is ran with clientID provided. To simulate the non-deterministic setting, the clients are ran in background modes, as specified in [the last example of DESIGN](DESIGN.md).