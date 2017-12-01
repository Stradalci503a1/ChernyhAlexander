import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

public class Calculator {

    static double calculate(String expression) {
        if (expression == null) {
            return Double.NaN;
        }
        if (!isCorrectBrackets(expression)) {
            return Double.NaN;
        }
        Stack<Character> stack = new Stack<>();
        ArrayList<String> postfixExpression = new ArrayList<>();
        Integer headOfWord = null;
        for (int it = 0; it < expression.length(); it++) {
            int symbolPriority = getPriority(expression.charAt(it));
            switch (symbolPriority) {
                case 0: {
                    if (Character.isDigit(expression.charAt(it))) {
                        if (headOfWord == null) {
                            headOfWord = it;
                        }
                        if (it == expression.length() - 1 || !Character.isDigit(expression.charAt(it + 1))) {
                            postfixExpression.add(expression.substring(headOfWord, it + 1));
                            headOfWord = null;
                        }
                    } else return Double.NaN;
                }
                break;

                case 1: {
                    stack.push(expression.charAt(it));
                }
                break;

                case 2: {
                    while (stack.peek() != '(') {
                        postfixExpression.add(stack.pop().toString());
                    }
                    stack.pop();
                }
                break;
                default: {
                    while ((!stack.empty()) && (getPriority(stack.peek()) >= symbolPriority)) {
                        postfixExpression.add(stack.pop().toString());
                    }
                    stack.push(expression.charAt(it));
                }
                break;
            }
        }
        while (!stack.empty()) {
            postfixExpression.add(stack.pop().toString());
        }
        return calculatePostfix(postfixExpression);
    }

    static double calculatePostfix(ArrayList<String> expression) {
        Stack<Double> stack = new Stack<>();
        double secondNumber;
        for (String it : expression) {
            if (isNumber(it)) {
                stack.add(Double.parseDouble(it));
            } else if (stack.size() < 2) {
                return Double.NaN;
            } else {
                switch (it) {
                    case "*": {
                        secondNumber = stack.pop();
                        stack.push(stack.pop() * secondNumber);
                    }
                    break;

                    case "/": {
                        secondNumber = stack.pop();
                        if (secondNumber == 0) {
                            return Double.NaN;
                        }
                        stack.push(stack.pop() / secondNumber);
                    }
                    break;

                    case "-": {
                        secondNumber = stack.pop();
                        stack.push(stack.pop() - secondNumber);
                    }
                    break;

                    case "+": {
                        secondNumber = stack.pop();
                        stack.push(stack.pop() + secondNumber);
                    }
                    break;

                }
            }
        }
        if (stack.empty()) {
            return Double.NaN;
        } else {
            return stack.peek();
        }
    }

    private static boolean isNumber(String string) {

        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int getPriority(char symbol) {
        if (symbol == '(') {
            return 1;
        }

        if (symbol == ')') {
            return 2;
        }

        if (symbol == '+' || symbol == '-') {
            return 3;
        }

        if (symbol == '*' || symbol == '/') {
            return 4;
        } else {
            return 0;
        }
    }

    public static boolean isCorrectBrackets(String str) {
        int count = 0;
        for (char symbol : str.toCharArray()) {
            if (symbol == '(') {
                count++;
            }
            if (symbol == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }
}

