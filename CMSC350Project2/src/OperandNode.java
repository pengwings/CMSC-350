/**
 * OperandNode.java
 * Brian Yu
 * 4/12/2020
 * This class inherits node class and defines node for operands.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OperandNode extends Node {
    private int value;
    private Node operator, left, right;
    private static int registerCounter;
    private String register;

    public OperandNode(int value) { //constructor for leaf nodes
        this.value = value;
    }

    public OperandNode(Node operator, Node left, Node right) { //constructor for internal nodes
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.register = "R" + registerCounter;
        registerCounter++;
    }

    public String getRegister() {   //returns register where expression is stored
        return register;
    }

    public static void resetRegisterCounter() {  //resets register counter after each expression has been converted
        registerCounter = 0;
    }

    @Override
    public String toString() {  //returns string representation of expression tree
        if (operator == null) {
            return Integer.toString(value);
        } else {
            String leftValue = left.toString();
            String rightValue = right.toString();
            return "(" + leftValue + " " + operator.toString() + " " + rightValue + ")";
        }
    }

    public void writeOutput(File outputFile) {  //prints three address line on output file for each expression
        try {
            FileWriter fileWriter = new FileWriter(outputFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String leftValue = left.toString();
            String rightValue = right.toString();
            if(left.getRegister()!=null && right.getRegister()==null) {
                printWriter.println(operator.getRegister() + " " + this.register + " " + left.getRegister() + " " + rightValue);
            } else if (left.getRegister()==null && right.getRegister()!= null) {
                printWriter.println(operator.getRegister() + " " + this.register + " " + leftValue + " " + right.getRegister());
            } else if (left.getRegister()!=null && right.getRegister()!= null) {
                printWriter.println(operator.getRegister() + " " + this.register + " " + left.getRegister() + " " + right.getRegister());
            } else {
                printWriter.println(operator.getRegister() + " " + this.register + " " + leftValue + " " + rightValue);
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("File output failed.");
        }
    }

}
