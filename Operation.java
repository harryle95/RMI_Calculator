import java.util.Stack;

public final class Operation {
    public static int max(Stack<Integer> operands){
        int result = operands.pop();
        int temp;
        while (!(operands.isEmpty())){
            temp = operands.pop();
            if (temp >= result){
                result = temp;
            }
        }
        return result;
    }

    public static int min(Stack<Integer> operands){
        int result = operands.pop();
        int temp;
        while (!(operands.isEmpty())){
            temp = operands.pop();
            if (temp <= result){
                result = temp;
            }
        }
        return result;
    }

    private static int gcd(int first, int second){
        if (second == 0) return first;
        return gcd(second, first % second);
    }

    public static int gcd(Stack<Integer> operands){
        int result = operands.pop();
        while (!(operands.isEmpty()))
            result = gcd(result, operands.pop());
        return result;
    }

    private static int lcm(int first, int second){
        return (first*second)/gcd(first, second);
    }

    public static int lcm(Stack<Integer> operands){
        int result = operands.pop();
        while(!(operands.isEmpty()))
            result = lcm(result, operands.pop());
        return result;
    }
}
