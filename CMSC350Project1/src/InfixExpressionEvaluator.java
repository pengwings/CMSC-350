/**
 * InfixExpressionEvaluator.java
 * Brian Yu
 * 3/29/2020
 * This class parses the input string into array and sorts each element of the array into the appropriate stack.  The program then
 * uses the algorithm to perform the appropriate operation using the appropriate stack to return the result of the in-fix
 * expression.
 */
import javax.swing.*;
import java.util.Stack;

public class InfixExpressionEvaluator {
    //creates two stacks per evaluator instance
    Stack<Integer> operandStack = new Stack<>();
    Stack<String> operatorStack = new Stack<>();
    //this method contains the bulk of the algorithm, parses the input string into the appropriate stacks and performs the operations in the appropriate order
    public int calculateExpression(String input) {
        //Splits the input string by any non-digit character, trims the spaces, but also takes in the delimiter using lookahead and lookbehind regex expression
        String[] operandsAndOperators = input.trim().split("((?<=\\D)|(?=\\D))");
        String operators = "+-*/";
        for(int j=0; j<operandsAndOperators.length; j++) {
            //checks the first character of the string to see if it is a digit
            if(Character.isDigit(operandsAndOperators[j].charAt(0))){
                operandStack.push(Integer.parseInt(operandsAndOperators[j]));
            } else if(operandsAndOperators[j].equals("(")) {
                operatorStack.push(operandsAndOperators[j]);
            } else if(operandsAndOperators[j].equals(")")) {
                while(!(operatorStack.peek().equals("("))) {
                    operandStack.push(performOperation(operandStack, operatorStack));
                    }
                //pops out remaining left paranthesis
                operatorStack.pop();
            } else if(operators.contains(operandsAndOperators[j])) {
                while(!operatorStack.empty() && (operatorStack.peek().equals("*") || operatorStack.peek().equals("/"))) {
                    operandStack.push(performOperation(operandStack, operatorStack));
                }
                operatorStack.push(operandsAndOperators[j]);
            }
        }
        while(!operatorStack.empty()) {
            operandStack.push(performOperation(operandStack, operatorStack));
        }
        return operandStack.peek();
    }
    //this method pops two operands from the operand stack and one operator from the operator stack and then performs the appropriate operation
    private int performOperation(Stack<Integer> operandStack, Stack<String> operatorStack) {
        int result = 0;
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        String operator = operatorStack.pop();
        switch(operator) {
            case "+":
                result = leftOperand + rightOperand;
                break;
            case "-":
                result = leftOperand - rightOperand;
                break;
            case "*":
                result = leftOperand * rightOperand;
                break;
            //the divide case checks to see if the rightOperand (divisor) is 0 and throws custom exception if it is
            case "/":
                try {
                    if(rightOperand == 0) {
                        throw new DivideByZero();
                    }
                    result = leftOperand / rightOperand;
                } catch(DivideByZero e) {
                    JOptionPane.showMessageDialog(null, "Cannot divide by 0.");
                }
                break;
        }
        return result;
    }
}
