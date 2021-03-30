import java.util.*;

/** 
 * Production code for ISE (ISAD1000) Worksheet 7. 
 */
public class ConUtilities
{
    /** 
     * Outputs a given real number, rounded to a particular number of decimal 
     * places. If decimalPlaces is zero or negative, the number is rounded to 
     * zero decimal places, and no decimal point is printed. If the number has 
     * fewer decimal places than required, zeros will be added to the end to 
     * make up the difference.
     *
     * @param num The number to round.
     * @param decimanPlaces The number of decimal places to display.
     */
    public static void printReal(double num, int decimalPlaces)
    {
        double magnitude;
        double roundedNum;
        
        if(decimalPlaces > 0)
        {
            magnitude = Math.pow(10.0, (double)decimalPlaces);        
            roundedNum = Math.round(num * magnitude) / magnitude;
            System.out.printf("%." + decimalPlaces + "f\n", roundedNum);
        }
        else
        {
            System.out.println((int)Math.round(num));
        }
    }

    /** 
     * Attempts to read a valid, non-negative integer from the user. If the 
     * user inputs a sequence of digits, the submodule interprets this as an 
     * integer and exports it. If the user enters anything else besides a 
     * sequence of digits, or nothing at all, then the value -1 is exported. 
     *
     * In the course of asking for input, this submodule outputs the string 
     * "Enter a valid integer:". This happens irrespective of anything else.
     *
     * (We could, of course, do this much more simply using Scanner, but what
     * the hell. It's nice to see how it's done from (almost) scratch!)
     *
     * @return The integer read from the user, or -1 if no valid non-negative
     * integer was entered.
     */
    public static int readValidInt()
    {
        String input;
        int number = 0;        
        int i = 0;
        boolean valid = true;
        char ch;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a valid integer: ");
        input = sc.nextLine();

        if(input.length() == 0)
        {
            number = -1;
        }
        else
        {
            // Read an integer digit-by-digit. For each digit, we first 
            // multiply what the number we already have (which starts off at 
            // zero) by 10. Then we simply add the value of the digit, and move
            // onto the next digit.
        
            while(valid && i < input.length())
            {
                ch = input.charAt(i);
                if(Character.isDigit(ch))
                {
                    // Process the character only if it's a digit.
                    number = (number * 10) + (int)(ch - '0');
                }
                else
                {
                    // If not a digit, then we abort.
                    valid = false;
                    number = -1;
                }
                i++;
            }
        }
        
        return number;
    }
    
    /**
     * Inputs two integers from the user, and outputs the maximum. If the input
     * does not consist of two integers as expected, then the method outputs 
     * "error" instead of the answer. If it's the first number that's invalid, 
     * then the submodule does not ask for a second.
     */     
    public static void consoleMax()
    {
        int num1, num2, maximum;
        Scanner input = new Scanner(System.in);
        
        try
        {    
            System.out.print("Enter first integer: ");
            num1 = input.nextInt();
            
            System.out.print("Enter second integer: " );
            num2 = input.nextInt();
            
            maximum = num1;
            if(num2 > num1)
            {
                maximum = num2;
            }
            
            System.out.println("The maximum is " + maximum);
        }
        catch(InputMismatchException e)
        {
            System.out.println("error");
        }
    }
    
    /**
     * Reads a line of text from the user and checks whether it contains
     * inChar. First, the method outputs the prompt "Enter text:". If the 
     * entered text contains inChar, the method then outputs the string 
     * "contains X" (where X is the value of inChar). Otherwise, the method 
     * outputs the string "does not contain X". The submodule also exports the
     * result -- true if inChar was found, or false otherwise.
     *
     * @param inChar The character to search for.
     * @return True if inChar was found in the input, or false otherwise.
     */
    public static boolean containsChar(char inChar)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();
        
        boolean found = (input.indexOf(inChar) >= 0);
        
        if(found)
        {
            System.out.println("contains " + inChar);
        }
        else
        {
            System.out.println("does not contain " + inChar);
        }
        
        return found;
    }
}
