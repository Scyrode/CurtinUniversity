import java.util.*;
import java.io.*;

public class TestHarnessDSAHeap {

    public static void main(String[] args) {

	DSAHeapEntry[] list = new DSAHeapEntry[8000];

	String line = "";
	String tokens[];

	int numTests = 0;
	int testsPassed = 0;
	int i;

	System.out.println("\nTESTING DSAHeap:");


	System.out.println("###################################\n");
	System.out.println("TEST 1: ADD/REMOVE METHODS(adding text: \"Rose Parks\"):\n");
	numTests++;

	DSAHeap heap = new DSAHeap(8000);

	heap.add(2, "Rose Parks");
	String test1 = (String) heap.remove();

	System.out.println("REMOVED TEXT: " + test1 + "\n");

	if (test1.equals("Rose Parks")) {
	    testsPassed++;
	    System.out.println("\tTEST 1: PASSED\n");
	} else {
	    System.out.println("\tTEST 1: FAILED\n");
	}


	System.out.println("###################################\n");
	System.out.println("TEST 2: USING HEAP SORT:\n");
	numTests++;

	try {

	    FileReader in = new FileReader("RandomNames7000.csv");
	    BufferedReader reader = new BufferedReader(in);

	    i = 0;

	    while ((line = reader.readLine()) != null) {

		tokens = line.split(",");
		list[i] = new DSAHeapEntry(Integer.parseInt(tokens[0]), tokens[1]);

		i++;

	    }

	    heap.heapSort(list);

	    String rootValue = (String) heap.remove();

	    // System.out.println("first entry:" + rootValue);

	    if (rootValue.equals("Margie Winchester")) {

		testsPassed++;
		System.out.println("\tTEST 2: PASSED\n");

	    } else {
		System.out.println("\tTEST 2: FAILED\n");
	    }

	    in.close();

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("\tTEST 2: FAILED\n");
	}


	System.out.println("###################################\n");
	System.out.println("TESTS PASSED: " + testsPassed + " / " + numTests + "\n");

    }

}
