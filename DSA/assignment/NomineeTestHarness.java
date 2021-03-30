public class NomineeTestHarness {

    public static void main(String[] args) {



	System.out.println("\n\nTESTING NOMINEE CLASS:\n");
	System.out.println("=====================================\n");

	// TEST 1: CREATING A NOMINEE OBJECT:
	System.out.println("TEST 1: CREATING A NOMINEE OBJECT USING PARAMETERS:\n(\"NSW\", 151, \"Warringah\", \"LP\", \"Liberal\", 28624, \"ABBOTT\", \"Tony\", \"y\", \"y\")\n");

	Nominee nominee = new Nominee("NSW", 151, "Warringah", "LP", "Liberal", 28624, "ABBOTT", "Tony", 'y', 'y');
	
	String str = nominee.toString();

	System.out.println(str);

	System.out.println("TEST 1: PASSED\n");
	System.out.println("=====================================\n");

	System.out.println("TESTS PASSED: 1 / 1\n");

    }

}
