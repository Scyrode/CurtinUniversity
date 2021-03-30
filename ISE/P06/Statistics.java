import java.util.Scanner;

/**
 * This is a program for performing one of a list of simple statistical 
 * operations on an array of numbers.
 *
 * This class is part of ISE (ISAD1000) Practical Worksheet 6 (Modularity).
 *
 * The code here uses arrays and looping, so make sure you're up-to-date with
 * OOPD!
 */
public class Statistics
{
    // You may not have seen declarations like this before. Take a guess as to
    // what they are!
    static double varianceResult;
    static int sumLength;
    static double[] sumList;

    public static void main(String[] args)
    {
        String operation;
        int listLength;
        double[] list;
        double result;
        
        Scanner input = new Scanner(System.in);
        
        // Input array length
        System.out.print("Enter length of list: ");
        listLength = input.nextInt();
        
        // Create new array and input each element
        list = new double[listLength]; 
        for(int i = 0; i < listLength; i++)
        {
            System.out.print("Enter real number: ");
            list[i] = input.nextDouble();
        }
        input.nextLine(); // Skip the newline after the last value read.
        
        System.out.print("Select a calculation to perform: ");
        operation = input.nextLine();
        
        // Determine which operation was chosen, and perform it.
        if(operation.equals("min"))
        {
            result = minmax(listLength, list, false);
        }
        else if(operation.equals("max"))
        {
            result = minmax(listLength, list, true);
        }
        else if(operation.equals("sum"))
        {   
            sumLength = listLength;
            sumList = list;
            result = sum();
        }
        else if(operation.equals("mean"))
        {
            sumLength = listLength;
            sumList = list;
            result = mean();
        }
        else if(operation.equals("variance"))
        {
            variance(listLength, list);
            result = varianceResult;
        }
        else if(operation.equals("stddev"))
        {
            result = stddev(listLength, list);
        }
        else if(operation.equals("geommean"))
        {
            result = product(1, listLength, list);
        }
        else
        {
            System.out.println("Unrecognised operation \"" + operation + "\".");
            result = 0.0;
        }
        System.out.println("Result = " + result);
    }
    
    /**
     * Calculates the sum of the numbers in the sumList variable.
     * @return The sum.
     */
    public static double sum()
    {
        double result = 0.0;
        for(int i = 0; i < sumLength; i++)
        {
            result += sumList[i];
        }
        return result;
    }
    
    
    /**
     * Calculates the mean (average) of the numbers in the sumList variable.
     * @return The mean.
     */
    public static double mean()
    {
        double sum = 0.0;
        for(int i = 0; i < sumLength; i++)
        {
            sum += sumList[i];
        }
        return sum / (double)sumLength;
    }
    
    /**
     * Calculates the variance of a list of numbers. Stores the result in the 
     * varianceResult variable.
     * @param length The list length.
     * @param list The list of numbers.
     */
    public static void variance(int length, double[] list)
    {
        double average;
        double difference;
        double sumSquares = 0.0;
        
        sumLength = length;
        sumList = list;
        average = mean();
        
        for(int i = 0; i < length; i++)
        {
            difference = list[i] - average;
            sumSquares += difference * difference;
        }
        
        varianceResult = sumSquares / ((int)length - 1);
    }
    
    /**
     * Calculates the standard deviation of a list of numbers. 
     * @param length The list length.
     * @param list The list of numbers.
     * @return The standard deviation.
     */
    public static double stddev(int length, double[] list)
    {
        variance(length, list);        
        return Math.sqrt(varianceResult);
    }
    
    /** 
     * Determines either the lowest or highest of a list of numbers.
     * @param length The list length.
     * @param list The list of numbers.
     * @param max Whether to determine the maximum or minimum. If max is true,
     * the maximum is found; otherwise, the minimum is found.
     * @return The maximum or minimum.
     */
    public static double minmax(int length, double[] list, boolean max)
    {
        double element;
        double result = list[0];
        
        if(max)
        {
            // Find the highest value in the list.
            for(int i = 1; i < length; i++)
            {
                element = list[i];
                if(result < element) 
                {
                    // If the next element is higher than the maximum so far, 
                    // update the maximum.
                    result = element;
                }
            }
        }
        else
        {
            // Find the lowest value in the list.
            for(int i = 1; i < length; i++)
            {
                element = list[i];
                if(result > element) 
                {
                    // If the next element is lower than the minimum so far, 
                    // update the minimum.
                    result = element;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Calculates the product of a list of numbers, and optionally performs an
     * additional operation.
     * @param op An extra operation to perform. If op is 1, we calculate the 
     * "geometric mean". If op is 2, we find the log of the product. Otherwise,
     * we just return the raw product.
     * @param length The list length.
     * @param list The list of numbers.
     * @return The product, or log of the product, or geometric mean.
     */
    public static double product(int op, int length, double[] list)
    {
        double result;
        double product = 1.0;
        for(int i = 0; i < length; i++)
        {
            product *= list[i];
        }
        
        switch(op)
        {
            case 1:
                result = Math.pow(product, 1.0 / (double)length);
                break;
                
            case 2:
                result = Math.log(product);
                break;
                
            default:
                result = product;            
        }
        return result;
    }
}