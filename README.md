## How to run the program: 

### Run the rmiregistry:

```
make registry
```

### Run the server: 

```
make server 
```

### Run the client in shell mode (interractive input)

```
make shell 
```

### CleanUp 

Kill current background process running rmi registry and server and delete class files (sudo privilege is required) 

```
make clean 
```

### Interractive Shell

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


## Run tests: 

Before running any test, ensure that the rmiregistry and calculator server are up and running. Run the following commands: 

```
make registry 
make server 
```

To run all tests: 

```
make test
```

## How the directory is structured

`Server`, `Client` and `Calculator` interface and implementation classes are located in the root directory. `Operation` class defines
the implementation of the four used operations. `TestOperation` performs unit testing with standard library modules. `JUnitTest` is not used
because I use `Intellij` and wasn't sure if that is allowed for GradeScope. The `test` folder defines the test cases for integration testing, with `test/single` the cases for a single client, and `test/client` the cases for multiple clients. Each test folder has a `run_test.sh` bash script which orchestrate the testing. 

For more information on design decision, see [here](doc/DESIGN.md).

For more information testing, see [here](doc/TESTING.md)