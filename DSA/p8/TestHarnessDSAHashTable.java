import java.util.*;
import java.io.*;

public class TestHarnessDSAHashTable {

    public static void main(String[] args) {

	DSAHashTable<String> table = new DSAHashTable<String>(16);

	String line = "";
	String tokens[];

	int numTests = 0;
	int testsPassed = 0;
	int i;

	System.out.println("\nTESTING DSAHashTable:");


	System.out.println("###################################\n");
	System.out.println("TEST 1: CREATING THE HASHTABLE (TESTING METHODS: put, nextPrime, reSize AND hash):\n");
	numTests++;

	try {

	    FileReader in = new FileReader("RandomNames7000.csv");
	    BufferedReader reader = new BufferedReader(in);

	    i = 0;

	    while ((line = reader.readLine()) != null) {

		tokens = line.split(",");

		table.put(tokens[0], tokens[1]);

		i++;

	    }

	    System.out.println("HASHTABLE CREATED SUCCESSFULLY\n");
	    testsPassed++;
	    System.out.println("\tTEST 1: PASSED\n");

	    in.close();

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("\tTEST 1: FAILED\n");
	}

	System.out.println("SAVING CURRENT HASHTABLE TO FILE: testHashTable.csv");

	try {

	    FileWriter out = new FileWriter("testHashTable.csv");

	    out.write(table.toString());

	} catch (IOException e) {
	    e.printStackTrace();
	}


	System.out.println("###################################\n");
	System.out.println("TEST 2: TESTING METHOD: containsKey (TESTING USING KEY: 14824429)\n");
	numTests++;

	if (table.containsKey("14824429")) {
	    testsPassed++;
	    System.out.println("\tTEST 2: PASSED\n");
	} else {
	    System.out.println("\tTEST 2: FAILED\n");
	}


	System.out.println("###################################\n");
	System.out.println("TEST 3: TESTING METHOD:  (TESTING USING KEY: 14442184)\n");
	numTests++;

	table.remove("14442184");

	if (table.containsKey("14442184")) {
	    System.out.println("\tTEST 3: FAILED\n");
	} else {
	    testsPassed++;
	    System.out.println("\tTEST 3: PASSED\n");
	}



	System.out.println("###################################\n");
	System.out.println("TEST 4: TESTING METHOD: get (TESTING USING KEY: 14995816)\n");
	numTests++;

	String testValue = table.get("14995816");

	if (testValue.equals("Annabelle Hilson")) {
	    testsPassed++;
	    System.out.println("\tTEST 4: PASSED\n");
	} else {
	    System.out.println("\tTEST 4: FAILED\n");
	}

	System.out.println("###################################\n");
	System.out.println("TESTS PASSED: " + testsPassed + " / " + numTests + "\n");

    }

}
