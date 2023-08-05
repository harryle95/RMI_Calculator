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

### Interractive Shell

Shell handles basic input with tokens separated by " " and "\n". 

- If token is an integer, perform a pushValue method. 
- If token is a valid operator (gcd, lcm, max, min), perform the corresponding pushOperation
- If token is pop, perform a pop and print the result to stdin
- If token is isEmpty, print the output to stdin 
