# How to run the program: 

- Run the server: 

```
make server 
```

- Run the client: 

```
java CalculatorClient 
```

- Run Interractive Shell: 

```
make shell
```

- CleanUp - kill current background rmi registry and delete class files: 

```
make clean 
```

### Using Interractive Shell

Setup the server with `make server` then `make shell`. Simply type in the method to perform the execution.

```
>>pushValue(100)
>>pushValue(200)
>>pop()
200
>>pop()
100
>>pop()
Empty stack
>>

```