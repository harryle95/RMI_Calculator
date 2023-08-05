PWD=$(pwd)/test/single

java CalculatorClient -d < "${PWD}/test1" | diff -wEB "${PWD}/output1" -
java CalculatorClient -d < "${PWD}/test2" | diff -wEB "${PWD}/output2" -
java CalculatorClient -d < "${PWD}/test3" | diff -wEB "${PWD}/output3" -
java CalculatorClient -d < "${PWD}/test4" | diff -wEB "${PWD}/output4" -
java CalculatorClient -d < "${PWD}/test5" | diff -wEB "${PWD}/output5" -
java CalculatorClient -d < "${PWD}/test6" | diff -wEB "${PWD}/output6" -
java CalculatorClient -d < "${PWD}/test7" | diff -wEB "${PWD}/output7" -
java CalculatorClient -d < "${PWD}/test8" | diff -wEB "${PWD}/output8" -