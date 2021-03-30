// Production code for ISE worksheet 5B.

public class UniAdmin
{
    /**
     * Calculates a grade, given a mark. For marks less than 50, the grade is 
     * "F". For marks from 50 to 59, the grade is "5". For 60 to 69, the grade
     * is "6", and so on up to "10". If the mark is invalid, calcGrade will 
     * export the empty string "".
     */
    public static String calcGrade(int mark)
    {
        String grade = "";
        if(mark < 50)
        {
            grade = "F";
        }
        else if(mark == 100)
        {
            grade = "10";
        }
        else
        {
            grade = "" + ((mark + 1) / 10);
        }
        return grade;
    }
}