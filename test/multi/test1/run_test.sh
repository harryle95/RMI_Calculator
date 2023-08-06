MYPATH=$(pwd)/test/multi/test1

java CalculatorClient -d < "${MYPATH}/test0"
java CalculatorClient -d < "${MYPATH}/test11" 
java CalculatorClient -d < "${MYPATH}/test12" | diff -wEB "${MYPATH}/output12" -
java CalculatorClient -d < "${MYPATH}/test13" | diff -wEB "${MYPATH}/output13" -