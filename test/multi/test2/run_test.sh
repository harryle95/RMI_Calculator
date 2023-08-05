PWD=$(pwd)/test/multi/test2

java CalculatorClient -d < "${PWD}/test0"
java CalculatorClient -d < "${PWD}/test11" 
java CalculatorClient -d < "${PWD}/test12" 
java CalculatorClient -d < "${PWD}/test13" | diff -wEB "${PWD}/output13" -