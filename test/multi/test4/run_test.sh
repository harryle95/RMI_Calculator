PWD=$(pwd)/test/multi/test4

java CalculatorClient -d < "${PWD}/test0"
java CalculatorClient -d < "${PWD}/test11" | diff -wEB "${PWD}/output11" -
java CalculatorClient -d < "${PWD}/test12" | diff -wEB "${PWD}/output12" -
java CalculatorClient -d < "${PWD}/test13" | diff -wEB "${PWD}/output13" -