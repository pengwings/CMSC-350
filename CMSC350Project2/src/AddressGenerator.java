/**
 * AddressGenerator.java
 * Brian Yu
 * 4/12/2020
 * This class constructs a GUI that allows users to convert postfix expression to infix expression and generate three address instruction file.
 */
import java.io.File;
import java.util.Stack;

public class AddressGenerator {
    //creates operator and operand stacks
    Stack<OperatorNode> operatorStack = new Stack<>();
    Stack<OperandNode> operandStack = new Stack<>();

    public String generateExpression(String input, File output) throws RuntimeException {
        String[] operandsAndOperators = input.trim().split("((?<=\\D)|(?=\\D))");
        String operators = "+-*/";
        for(int i=0; i<operandsAndOperators.length; i++) {
            if(operandsAndOperators[i].equals(" ")) {
                //catches and ignores spaces
            } else if(Character.isDigit(operandsAndOperators[i].charAt(0))) { //puts integers in operand stack
                operandStack.push(new OperandNode(Integer.parseInt(operandsAndOperators[i])));
            } else if(operators.contains(operandsAndOperators[i])) {
                if (operandStack.size()>=2) { //pops out two operands to create tree once operator is hit if there are at least 2 operands in the operand stack
                    OperandNode rightNode = operandStack.pop();
                    OperandNode leftNode = operandStack.pop();
                    operandStack.push(new OperandNode(new OperatorNode(operandsAndOperators[i]), leftNode, rightNode));
                    operandStack.peek().writeOutput(output);
                } else { //else puts operator into operator stack
                    operatorStack.push(new OperatorNode(operandsAndOperators[i]));
                }
            } else {  //throws exception if invalid character encountered
                throw new RuntimeException();
            }
        } //returns infix expression constructed through string representation of expression tree
        return operandStack.pop().toString();
    }
}
