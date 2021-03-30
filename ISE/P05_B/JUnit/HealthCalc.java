// Production code for ISE worksheet 5B.

public class HealthCalc
{
    /**
     * Determines a rating for ultraviolet radiation risk, based on a real-
     * valued UV index. Ratings below zero are invalid, in which case the 
     * submodule returns "-". Otherwise, if the index is below 3, the rating 
     * is "low", then up to 6 for "moderate", up to 8 for "high", and up to 11 
     * for "very high". Any rating over 11 is "extreme".
     */
    public static String uvRating(double index)
    {
        String rating;
        if(index < 0.0)
        {
            rating = "-";
        }
        else if(index <= 3.0)
        {
            rating = "low";
        }
        else if(index <= 6.0)
        {
            rating = "medium";
        }
        else if(index <= 8.0)
        {
            rating = "high";
        }
        else if(index <= 11.0)
        {
            rating = "very high";
        }
        else
        {
            rating = "extreme";
        }
        return rating;
    }
}