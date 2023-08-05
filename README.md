# How to run the program: 

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

### Using Interractive Shell

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