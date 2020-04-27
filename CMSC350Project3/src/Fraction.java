/**
 * Fraction.java
 * Brian Yu
 * 4/26/2020
 * This class defines a Fraction object comprised of both the String representation of a fraction and its decimal value
 */
public class Fraction implements Comparable<Fraction>{
    private String fractionString;
    private double value;
    //constructor for Fraction object which calculates its decimal value based on the String input
    public Fraction(String fraction) {
        this.fractionString = fraction;
        this.value = calculateDouble(fractionString);
    }
    //private method to calculate decimal value of fraction
    private double calculateDouble(String fraction) {
        String[] inputFraction = fraction.split("/");
        double dividend = Double.parseDouble(inputFraction[0]);
        double divisor = Double.parseDouble(inputFraction[1]);
        return dividend/divisor;
    }
    //Overriden compareTo method from Comparable interface that compares Fraction objects based on their decimal value
    //returns 0 if equal, -1 if input is greater and 1 if input is smaller
    @Override
    public int compareTo(Fraction input) {
        if(this.value < input.value) {
            return -1;
        } else if(this.value > input.value) {
            return 1;
        }
        return 0;
    }
    //method to return string representing Fraction object
    public String toString() {
        return fractionString;
    }
}
