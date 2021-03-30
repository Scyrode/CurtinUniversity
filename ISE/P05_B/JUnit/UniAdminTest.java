/**
 * UniAdminTest
 */

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UniAdminTest {

    @Test
    public void testCalcGrade()
    {

        assertEquals("mark < 0 error", "", UniAdmin.calcGrade(-25));
        assertEquals("0 < mark < 50 error", "F", UniAdmin.calcGrade(23));
        assertEquals("50 <= mark < 60 error", "5", UniAdmin.calcGrade(52));
        assertEquals("60 <= mark < 70 error", "6", UniAdmin.calcGrade(68));
        assertEquals("70 <= mark < 80 error", "7", UniAdmin.calcGrade(74));
        assertEquals("80 <= mark < 90 error", "8", UniAdmin.calcGrade(81));
        assertEquals("90 <= mark < 100 error", "9", UniAdmin.calcGrade(95));
        assertEquals("mark = 100 error", "10", UniAdmin.calcGrade(100));
        assertEquals("mark > 100 error", "", UniAdmin.calcGrade(105));
        
    }
    
}