// Production code for ISE worksheet 5B.

public class CharacterUtils
{
    /** 
     * Checks whether or not ch is an upper/lowercase letter. If checkUpper is
     * true, the method checks whether ch is uppercase, and return 
     * true/false accordingly. If checkUpper is false, the method instead 
     * checks whether ch is lowercase. 
     * 
     * Note: from a modularity point of view (see lecture 6), the design of 
     * this method may be questionable...
     */
    public static boolean charCase(boolean checkUpper, char ch)
    {
        boolean upperCase = 'A' <= ch && ch <= 'z';
        boolean lowerCase = 'a' <= ch && ch <= 'z';
        return (checkUpper && upperCase) || (!checkUpper && lowerCase);
    }
    
    /**
     * Determines whether one string occurs inside the other. If it does, the 
     * method returns whichever string is shorter. If not, it returns the 
     * the empty string "". Note that the empty string is, by definition, 
     * contained inside every string (including itself).
     *
     * For instance, if str1 is "conscience" and str2 is "science", then this 
     * method returns "science". If both imported strings are "xyz", then 
     * the method returns "xyz".
     */
    public static String substr(String str1, String str2)
    {
        String result = str1;
        if(str1.contains(str2))
        {
            result = str2;
        }
        else if(str2.contains(str1))
        {
            result = str1;
        }
        return result;
    }
}