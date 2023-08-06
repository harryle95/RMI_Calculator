MYPATH=$(pwd)/test/multi/test7

java CalculatorClient -d 0 < "${MYPATH}/test0" > "${MYPATH}/log0" &
java CalculatorClient -d 1 < "${MYPATH}/test1" > "${MYPATH}/log1" &
java CalculatorClient -d 2 < "${MYPATH}/test2" > "${MYPATH}/log2" &
java CalculatorClient -d 3 < "${MYPATH}/test3" > "${MYPATH}/log3" &

# Sleep to wait for all processes to finish
sleep 3

diff -wEB "${MYPATH}/output0" "${MYPATH}/log0"
diff -wEB "${MYPATH}/output1" "${MYPATH}/log1"
diff -wEB "${MYPATH}/output2" "${MYPATH}/log2"
diff -wEB "${MYPATH}/output3" "${MYPATH}/log3"
