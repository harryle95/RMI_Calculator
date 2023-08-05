import java.util.Arrays;
import java.util.Stack;


/* Run unit tests for predefined test cases.
 * Test the four basic operations - min, max, lcm, gcd
 */
public class TestOperation {
    Stack<Integer> singleton;
    Stack<Integer> testStack1;
    Stack<Integer> testStack2;
    Stack<Integer> testStack3;
    Stack<Integer> testStack4;

    Stack<Integer> testStack5;
    public TestOperation(){
        singleton = new Stack<>();
        singleton.push(10);

        testStack1 = new Stack<>();
        testStack1.addAll(Arrays.asList(15, 7, 36, 17, 102));

        testStack2 = new Stack<>();
        testStack2.addAll(Arrays.asList(68, 24, 100, 3, 42, 56, 12));

        testStack3 = new Stack<>();
        testStack3.addAll(Arrays.asList(100, 15, 65, 55, 115));

        testStack4 = new Stack<>();
        testStack4.addAll(Arrays.asList(10, 15, 33, 13));

        testStack5 = new Stack<>();
        testStack5.addAll(Arrays.asList(180, 72, 89, 123));
    }

    public static void testMax( Stack<Integer> inputStack, int expected){
        Stack<Integer> copyStack = new Stack<>();
        copyStack.addAll(inputStack);
        int output = Operation.max(copyStack);
        assert(expected == output ): "Max fails. Output: " + output + ". Expected: " + expected;
    }

    public static void testMin( Stack<Integer> inputStack, int expected){
        Stack<Integer> copyStack = new Stack<>();
        copyStack.addAll(inputStack);
        int output = Operation.min(copyStack);
        assert(expected == output ): "Min fails. Output: " + output + ". Expected: " + expected;
    }

    public static void testGCD( Stack<Integer> inputStack, int expected){
        Stack<Integer> copyStack = new Stack<>();
        copyStack.addAll(inputStack);
        int output = Operation.gcd(copyStack);
        assert(expected == output): "GCD fails. Output: " + output + ". Expected: " + expected;
    }

    public static void testLCM( Stack<Integer> inputStack, int expected){
        Stack<Integer> copyStack = new Stack<>();
        copyStack.addAll(inputStack);
        int output = Operation.lcm(copyStack);
        assert(expected == output ): "LCM fails. Output: " + output + ". Expected: " + expected;
    }

    public static void main(String[] args){
        TestOperation test = new TestOperation();
        testMax(test.singleton, 10);
        testMin(test.singleton, 10);
        testGCD(test.singleton, 10);
        testLCM(test.singleton, 10);

        testMax(test.testStack1, 102);
        testMin(test.testStack1, 7);
        testGCD(test.testStack1, 1);
        testLCM(test.testStack1, 21420);

        testMax(test.testStack2, 100);
        testMin(test.testStack2, 3);
        testGCD(test.testStack2, 1);
        testLCM(test.testStack2, 71400);

        testMax(test.testStack3, 115);
        testMin(test.testStack3, 15);
        testGCD(test.testStack3, 5);
        testLCM(test.testStack3, 986700);

        testMax(test.testStack4, 33);
        testMin(test.testStack4, 10);
        testGCD(test.testStack4, 1);
        testLCM(test.testStack4, 4290);

        testMax(test.testStack5, 180);
        testMin(test.testStack5, 72);
        testGCD(test.testStack5, 1);
        testLCM(test.testStack5, 1313640);
    }
}
