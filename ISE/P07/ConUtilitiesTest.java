import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ConUtilitiesTest
{
	
	@Before
	public void setup()
	{
		ConsoleRedirect.captureOutput();
	}

	@Test
	public void printRealTest()
	{

		/* two test cases:
		 * decimal places <= 0
		 * decimal okaces > 0 */

		// test case 1: decimal places <= 0
		ConUtilities.printReal(3.123, -5);
		double result = ConsoleRedirect.getCapturedOutput();
		assertEquals("Decimal places <= 0", 3.0, result);

		// test case 2: decimal places > 0
		ConUtilities.printReal(3.123, 2);
		result = ConsoleRedirect.getCapturedOutput();
		asserEquals("Decimal places > 0", 3.12, result);

	}

	@Test
	public void readValidIntTest()
	{
		/* four test cases:
		 * input is valid
		 * input is real value
		 * input is alphabet
		 * input is empty */

		// test case 1: input is valid
		ConsoleRedirect.fakeInput(5);
		int resultExport = ConUtilities.readValidInt();
		String resultOutput = ConsoleRedirect.getCapturedOutput();
		assertEquals("input is valid", 5, resultExport);
		assertEquals("input is valid", "Enter a valid Integer", resultExport);

		// test case 2: input is real
		ConsoleRedirect.fakeInput(3.4);
		int resultExport = ConUtilities.readValidInt();
		String resultOutput = ConsoleRedirect.getCapturedOutput();
		assertEquals("input is real value", -1, resultExport);
		assertEquals("input is valid", "Enter a valid Integer", resultExport);

		// test case 3: input is from the alphabet
		ConsoleRedirect.fakeInput(123a);
		int resultExport = ConUtilities.readValidInt();
		String resultOutput = ConsoleRedirect.getCapturedOutput();
		assertEquals("input is real value", -1, resultExport);
		assertEquals("input is valid", "Enter a valid Integer", resultExport);

		// test case 4: input is empty
		ConsoleRedirect.fakeInput();
		int resultExport = ConUtilities.readValidInt();
		String resultOutput = ConsoleRedirect.getCapturedOutput();
		assertEquals("input is real value", -1, resultExport);
		assertEquals("input is valid", "Enter a valid Integer", resultExport);

	}

	@Test
	public void consoleMaxTest()
	{

		/* n = "Enter first integer"
		 * s = "Enter second integer"
		 * t = "The maximum is "
		 *
		 * five test cases:
		 * value1 > value2 : input = "10/n20" : output = n+s+t+20
		 * value1 < value2
		 * value1 = value2
		 * value1 invalid
		 * value2 invalid */
	}

	@Test
	public void containsCharTest()
	{

		/* two test cases:
		 * contains inChar : inChar = 'e' input = "Hello" : found = true output = "Enter Text: " + "Contains " + inChar
		 * does not contain inChar : inChar = 'w' input = "Breakfast" : found = false output = "Enter text: " + "does not contain " + inChar */

	}

	@After
	public void tearDown()
	{
		ConsoleRedirect.restoreOutput();
		ConsoleRedirect.restoreInput();
	}


}
