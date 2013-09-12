/******************************************************************************
 *
 * A Polynomial models a one variable polynomial expression of the form :
 * axn + bxn-1  + cxn-2  + …   + gx1  +  h x0  .
 * An object of type polynomial will store the coefficients of the
 * expression in an array.
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
 *   September 10th, 2013
 ******************************************************************************/

public class Polynomial implements Cloneable
{

    private double[ ] polynomial;
    private static final int INITIAL_CAPACITY = 6;


    /**
     * Initialize an empty bag with an initial capacity of 6.  Note that the
     * addItem method works efficiently (without needing more
     * memory) until this capacity is reached.
     * @param - none
     * @postcondition
     *   new 5th order Polynomial object is ready for use
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for:
     *   new double[6].
     **/
    public Polynomial()
    {
        polynomial = new double[INITIAL_CAPACITY];
    }


    /**
     * Initialize the first coefficient of the zero order polynomial
     * to the desired double value.
     * @param zeroOrderCoefficient
     *   the zero order coefficient
     * @postcondition
     *   new 5th order Polynomial object is ready for use with
     *   Polynomial[0] = zeroOrderCoefficient.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for:
     *   new double[6].
     **/
    public Polynomial(double zeroOrderCoefficient)
    {
        polynomial = new double[INITIAL_CAPACITY];
        polynomial[0] = zeroOrderCoefficient;
    }


    /**
     * Initialize a Polynomial object from a Polynomial source.
     * @param - source
     * @precondition
     *   source is not null.
     * @postcondition
     *   new 5th order Polynomial object is ready for use
     *   with same values as the source polynomial
     * @exception IllegalArgumentException
     *   Indicates that source is null.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for:
     *   new double[6].
     **/
    public Polynomial(Polynomial source)
    {
        if (source == null)
            throw new IllegalArgumentException
                    ("the source Polynomial is null");
        polynomial = new double[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++){
            polynomial[i] = (source.coefficient(i));
        }
    }


    /**
     * Initialize a Polynomial object from a Polynomial source.
     * @param - none
     * @return
     *   The return value is a copy of this Polynomial. Subsequent changes to the
     *   copy will not affect the original, nor vice versa.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for creating the clone.
     **/
    public Polynomial clone( )
    {  // Clone an Polynomial object.
        Polynomial answer;

        try
        {
            answer = (Polynomial) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException
                    ("This class does not implement Cloneable");
        }

        answer.polynomial = polynomial.clone( );

        return answer;
    }


    /**
     * returns the length of the polynomial array
     * @precondition
     *   none
     * @postcondition
     *   the polynomial remains unchanged
     * @return
     *   the polynomial object length;
     **/
     public int length(){
         return polynomial.length;
     }


    /**
     * increases the coefficient of x^k by amount
     * @param ammount
     *   the ammount the coefficient is increased by
     * @param k
     *   the exponent of the coefficient affected.
     * @precondition
     *   0 <= k <= polynomial.length
     * @postcondition
     *   the coefficeint of x^k is increased by ammount.
     * @exception
     *   IllegalArgumentException returned if coefficient
     *   is
     * @note
     **/
    public void add_to_coeff(double ammount, int k)
    {
        if (k < 0 || k >= polynomial.length)
            throw new IllegalArgumentException();
        polynomial[k] += ammount;
    }


    /**
     * assigns new coefficient as coefficient of x^k
     * @precondition
     *   0 <= k <= polynomial.length
     * @postcondition
     *   the coefficeint of x^k is equal to new_coefficient.
     * @exception
     *   IllegalArgumentException returned if coefficient
     *   is
     **/
    public void assign_coeff(double new_coefficient, int k)
    {
        if (k < 0 || k >= polynomial.length)
            throw new IllegalArgumentException();
        polynomial[k] = new_coefficient;
    }


    /**
     * sets all coefficients equal to 0.
     * @postcondition
     *   all coefficients are equal to 0.
     **/
    public void clear()
    {
        for(int i = 0; i < polynomial.length; i++){
            polynomial[i] = 0.0;
        }
    }


    /**
     * returns coefficient for index x
     * @param k
     * @exception
     *   IllegalArgumentException returned if coefficient
     *   is
     * @Postcondition:
     *   object has not changed
     * @return
     *   coefficient at index k
     */
    public double coefficient(int k)
    {
        if (k < 0 || k >= polynomial.length)
            throw new IllegalArgumentException();
        return polynomial[k];
    }

    /**
     * returns the next non-zero coefficient after the xk term
     * @param k
     * @exception
     *   IllegalArgumentException returned if coefficient
     *   is
     * @precondition 0 <= k < polynomial.length - 1
     * @postcondition  the object has not changed
     * @return  a coefficient value if a non-zero coefficient exist above xk,  otherwise -1 is returned
     */
    public double next_term(int k)
    {
        double output = -1;
        if (k < 0 || k >= polynomial.length - 1)
            throw new IllegalArgumentException();
        boolean flag = false;
        int i = k + 1;
        while(i < polynomial.length && !flag ){
            if (polynomial[i] != 0){
                output = polynomial[i];
                flag = true;
            }
            i++;
        }
        return output;

    }


    /**
     * evaluates the polynomial for a given value of x
     * @param x
     * @precondition None
     * @postcondition  he object has not changed
     * @return  the result of evaluating the polynomial for x
     */
    public double eval(double x){
        double output = 0;
        for (int i = 0; i < polynomial.length; i++){
            output += polynomial[i] * Math.pow(x, i);
        }
        return output;
    }


    /**
     * returns a new Polynomial which is the sum of the two parameters
     * @param obj1
     * @param obj2
     * @exception
     *   IllegalArgumentException returned if coefficient
     *   is
     * @precondition obj1 and obj2 are non-null
     * @postcondition  the object has not changed
     * @return  new Polynomial (coefficients have been calculating by summing
     * like coefficients of two operands
     */
    public static Polynomial add( Polynomial obj1,  Polynomial obj2){
        if (obj1 == null || obj2 == null)
            throw new IllegalArgumentException();
        Polynomial largerObj, smallerObj;
        if (obj1.length() >= obj2.length()){
            largerObj = obj1;
            smallerObj = obj2;
        }else{
            largerObj = obj2;
            smallerObj = obj1;
        }
        Polynomial output = new Polynomial(largerObj);
        for(int i = 0; i < smallerObj.length(); i++){
            output.add_to_coeff(smallerObj.coefficient(i), i);
        }
        return output;
    }


    /**
     * creates a string representation of this Polynomial object
     * @precondition None
     * @postcondition  the object has not changed
     * @return  a string representation of the Polynomial object
     * @overrides Object class toString
     */
    @Override
    public String toString() {
        int index = polynomial.length - 1;
        String output = String.format("%fx^%d", polynomial[index], index);
        for(int i = index - 1; i >= 0; i--){
            output += String.format(" + %fx^%d", polynomial[i], i);
        }
        return output;
    }
}