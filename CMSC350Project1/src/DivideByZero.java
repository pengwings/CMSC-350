/**
 * DivideByZero.java
 * Brian Yu
 * 3/29/2020
 * This class defines custom exception DivideByZero for the case where the calculator attempts to divide by zero
 */
public class DivideByZero extends Exception {
    public DivideByZero() {
        super("Cannot divide by 0");
    }
}
