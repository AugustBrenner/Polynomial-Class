/******************************************************************************
 *
 * A Polynomial models a one variable polynomial expression of the form :
 * axn + bxn-1  + cxn-2  + …   + gx1  +  h x0  .
 * This class tests the polynomial class
 *
 * @note
 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   September 11th, 2013
 ******************************************************************************/

import java.util.Scanner;

public class PolynomialTest {

    public static void main(String [] args){
        //instantiate variables to use
        int index;
        double value;

        // instantiate scanner object
        Scanner input = new Scanner(System.in);

        // creates one Polynomial using value provided by user
        System.out.println("Please enter a double value to instantiate the first polynomial of a polynomial array");
        Polynomial polynomial = new Polynomial(input.nextDouble());
        System.out.printf("%s\n\n", polynomial.toString());

        // changes two coefficients of this Polynomial with user provided values and prints the Polynomial
        for(int i = 0; i < 2; i++){
            System.out.println("please select the index from 0 to 5 of the coefficient you want to change");
            index = input.nextInt();
            System.out.println("please enter the double value that you would like to alter it by");
            value = input.nextDouble();
            polynomial.add_to_coeff(value, index);
        }
        System.out.printf("%s\n\n", polynomial.toString());

        // sets two coefficients of this Polynomial with user provided values and prints the Polynomial
        for(int i = 0; i < 2; i++){
            System.out.println("please select the index from 0 to 5 of the coefficient you want to set");
            index = input.nextInt();
            System.out.println("please enter the double value that you would like to set it to");
            value = input.nextDouble();
            polynomial.assign_coeff(value, index);
        }
        System.out.printf("%s\n\n", polynomial.toString());

        // Evaluates this polynomial for a value x, provided by the user.  Result is printed.
        System.out.println("please enter the double value of x you wish to use to evaluate this polynomial");
        System.out.printf("Polynomial(x) = %f\n\n", polynomial.eval(input.nextDouble()));

        // Creates a new Polynomial using the copy constructor and prints this Polynomial
        System.out.println("A wild Polynomial has appeared");
        Polynomial wildPolynomial = new Polynomial(polynomial);
        System.out.printf("wildPolynomial: %s    |    polynomial: %s\n\n",
                wildPolynomial.toString(), polynomial.toString());

        // Creates a clone of this new Polynomial, and prints out the coefficients using the coefficient method
        System.out.println("Ditto use Transform!");
        Polynomial ditto = wildPolynomial.clone();
        index = ditto.length() - 1;
        String output = String.format("%fx^%d", ditto.coefficient(index), index);
        for(int i = index - 1; i >= 0; i--){
            output += String.format(" + %fx^%d", ditto.coefficient(i), i);
        }
        System.out.printf("Ditto: %s\n\n", output);

        // Ask the user to enter a value for K, then call next_term
        System.out.println("please enter an integer index value to call the next nonzero term");
        System.out.printf("The next term is %f\n\n", ditto.next_term(input.nextInt()));

        // Print out both Polynomials and their sum
        System.out.printf("wildPolynomial: %s    |    ditto: %s\nPolynomial Sum: %s\n\n",
                wildPolynomial.toString(), ditto.toString(), Polynomial.add(wildPolynomial, ditto).toString());

        // Say Good By
        System.out.print("Good By!");
    }
}
