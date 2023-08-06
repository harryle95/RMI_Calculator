MYPATH=$(pwd)/test/single

java CalculatorClient -d < "${MYPATH}/test1" | diff -wEB "${MYPATH}/output1" -
java CalculatorClient -d < "${MYPATH}/test2" | diff -wEB "${MYPATH}/output2" -
java CalculatorClient -d < "${MYPATH}/test3" | diff -wEB "${MYPATH}/output3" -
java CalculatorClient -d < "${MYPATH}/test4" | diff -wEB "${MYPATH}/output4" -
java CalculatorClient -d < "${MYPATH}/test5" | diff -wEB "${MYPATH}/output5" -
java CalculatorClient -d < "${MYPATH}/test6" | diff -wEB "${MYPATH}/output6" -
java CalculatorClient -d < "${MYPATH}/test7" | diff -wEB "${MYPATH}/output7" -
java CalculatorClient -d < "${MYPATH}/test8" | diff -wEB "${MYPATH}/output8" -