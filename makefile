compile:
	javac *.java 

registry:
	rmiregistry &

server: compile registry
	rmic CalculatorImplementation &
	java CalculatorServer & 


clean: 
	python3 cleanUp.py
	rm -rf *.class 