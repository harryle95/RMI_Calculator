## How to run the program: 

- Run the rmiregistry:

```
make registry
```

- Run the server: 

```
make server 
```

- Run the client in shell mode (interractive input)

```
make shell 
```

- CleanUp - kill current background process running rmi registry and server and delete class files (sudo privilege is required) 

```
make clean 
```

## How to Interractive Shell

Setup the server with `make registry` followed by `make server` then `make shell`. Simply type in the method to perform the execution.

```
>>pushValue(100)
>>pushValue(200)
>>pop()
200
>>pop()
100
>>pop()
Empty stack
>>exit()
```

To free all resources, call `make clean`. Sudo priviledge is required

## How the directory is structured

`Server`, `Client` and `Calculator` interface and implementation classes are located in the root directory. `Operation` class defines
the implementation of the four used operations. `TestOperation` performs unit testing with standard library modules. `JUnitTest` is not used
because I use `Intellij` and wasn't sure if that is allowed for GradeScope. The `test` folder define the test cases for integration testing, with `test/single` the cases for a single client, and `test/client` the cases for multiple clients. Each test folder has a `run_test.sh` bash script which orchestrate the testing. 

## How to run tests: 

Before running any test, ensure that the rmiregistry and calculator server are up and running. Run the following commands: 

```
make registry 
make server 
```

To run all tests: 

```
make test
```

### Unit Test:

Unit Test tests the four operations - max, min, gcd, lcm. If any of the unit test fails, assertion error will be raised which prints the error message to the terminal. To run unit tests: 

```
make unit_test
```

### Integration Test: 

Integration tests are divided into two cases, `single` where there is only one client, and `multiple` where there are multiple clients using the calculator service. For `multiple`, since this program does not handle race condition, I assume a specific order of operations to serialise the testing cases. If any of the test fails, a diff error will be displayed to stdout. To run integration tests: 

```
make integration_test_single 
make integration_test_multiple
```
