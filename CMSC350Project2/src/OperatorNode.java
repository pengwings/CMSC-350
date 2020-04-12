/**
 * OperatorNode.java
 * Brian Yu
 * 4/12/2020
 * This class inherits node class and defines node for operators.
 */
public class OperatorNode extends Node {
    private String operator;

    public OperatorNode(String operator) {
        this.operator = operator;
    }
    //returns appropriate instruction based on operator
    public String getRegister() {
        switch(this.operator) {
            case "+":
                return "Add";
            case "-":
                return "Sub";
            case "*":
                return "Mul";
            case "/":
                return "Div";
            default:
                return "Invalid operator";
        }
    }

    @Override
    public String toString() {
        return this.operator;
    }

}
