compile:
	javac *.java 

registry:
	rmiregistry &

server: compile registry
	rmic CalculatorImplementation
	java CalculatorServer & 
	clear 

run: compile 
	java CalculatorClient 

shell: compile 
	java CalculatorClient

clean: 
	python3 cleanUp.py
	rm -rf *.class 