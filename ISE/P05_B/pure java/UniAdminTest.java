/**
 * UniAdminTest
 */
public class UniAdminTest {
    
    public static void main(String[] args) {
        testCalcGrade();
    }

    private static void testCalcGrade()
    {

        assert UniAdmin.calcGrade(-25).equals("") : "mark < 0 error";
        assert UniAdmin.calcGrade(23).equals("F") : "0 < mark < 50 error";
        assert UniAdmin.calcGrade(52).equals("5") : "50 <= mark < 60 error";
        assert UniAdmin.calcGrade(68).equals("6") : "60 <= mark < 70 error";
        assert UniAdmin.calcGrade(74).equals("7") : "70 <= mark < 80 error";
        assert UniAdmin.calcGrade(81).equals("8") : "80 <= mark < 90 error";
        assert UniAdmin.calcGrade(95).equals("7") : "90 <= mark < 100 error";
        assert UniAdmin.calcGrade(100).equals("10") : "mark = 100 error";
        assert UniAdmin.calcGrade(105).equals("") : "mark > 100 error";
        
    }
    
}