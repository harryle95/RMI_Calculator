MYPATH=$(pwd)/test/multi/test2

java CalculatorClient -d < "${MYPATH}/test0"
java CalculatorClient -d < "${MYPATH}/test11" 
java CalculatorClient -d < "${MYPATH}/test12" 
java CalculatorClient -d < "${MYPATH}/test13" | diff -wEB "${MYPATH}/output13" -