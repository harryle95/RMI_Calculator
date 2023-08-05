compile:
	javac *.java 

registry:
	rmiregistry &

server: compile
	rmic CalculatorImplementation
	java CalculatorServer & 

run: compile 
	java CalculatorClient 

shell: compile 
	java CalculatorClient

clean: 
	python3 cleanUp.py
	rm -rf *.class 