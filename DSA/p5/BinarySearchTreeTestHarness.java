import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class BinarySearchTreeTestHarness {

    public static void main(String[] args) throws IOException {

	int numTests = 0;
	int testsPassed = 0;

	System.out.println("\nBINARY SEARCH TREE TEST:");
	System.out.println("\n*********************************\n");

	FileReader in = new FileReader("RandomNames7000.csv");
	System.out.print("UPLOADING FILE: ");
	String delims = ",";
	String text = "";
	int c;

	while ((c = in.read()) != -1) {
	    text += (char) c;
	}

	text = text.replace("\n", ",");

	in.close();

	String[] tokens = text.split(delims);

	System.out.println("UPLOAD SUCCESSFUL");

	numTests++;

	BinarySearchTree<String> tree = new BinarySearchTree<String>();

	// ERROR: CRASHES IF THE NUMBER OF NODES CREATED IS GREATER THAN 2400 (UNSOLVED)
	for (int i = 0; i < 2400; i++) {
	    tree.insert(tokens[i*2], tokens[(i*2)+1]);
	}



	System.out.println("\n*********************************\n");
	System.out.println("TEST 1: SEARCH METHOD, SEARCHING FOR 14222210\n");

	if ((tree.find("14222210")) != null) {
	    testsPassed++;
	    System.out.println("\tTEST 1: PASSED\n");
	} else {
	    System.out.println("\tTEST 1: FAILED\n");
	}



	System.out.println("*********************************\n");
	System.out.println("TEST 2: DELETE METHOD, DELETING VALUE 14033977\n");

	numTests++;

	try {

	    tree.delete("14033977");

	    try {

		tree.find("14033977");
		System.out.println("\tTEST 2: FAILED\n");

	    } catch (Exception e) {
		System.out.println("\tTEST 2: PASSED\n");
		testsPassed++;
	    }

	} catch (Exception e) {
	    System.out.println("\tTEST 2: FAILED\n");
	}




	System.out.println("*********************************\n");
	System.out.println("TEST 3: HEIGHT METHOD\n");

	numTests++;

	int height = 0;
	height = tree.height();

	if (height != 0) {
	    System.out.println("HEIGHT: " + height + "\n");
	    System.out.println("\tTEST 3: PASSED\n");
	    testsPassed++;
	} else {
	    System.out.println("\tTEST 3: FAILED\n");
	}



	System.out.println("*********************************\n");
	System.out.println("TEST 4: INORDER TRAVERSAL, TESTING FOR SMALLEST KEY: 14000704\n");

	numTests++;

	DSAQueue<String> queue1 = new DSAQueue<String>();

	tree.inOrderTrav(queue1);

	String test = queue1.dequeue();

	if (test.equals("14000704")) {
	    System.out.println("\tTEST 4: PASSED\n");
	    testsPassed++;
	} else {
	    System.out.println("\tTEST 4: FAILED\n");
	}



	System.out.println("*********************************\n");
	System.out.println("TEST 5: PREORDER TRAVERSAL, TESTING FOR FIRST KEY: 14495655\n");

	numTests++;

	DSAQueue<String> queue2 = new DSAQueue<String>();

	tree.preOrderTrav(queue2);

	test = queue2.dequeue();

	if (test.equals("14495655")) {
	    System.out.println("\tTEST 5: PASSED\n");
	    testsPassed++;
	} else {
	    System.out.println("\tTEST 5: FAILED\n");
	}



	System.out.println("*********************************\n");
	System.out.println("TEST 6: POSTORDER TRAVERSAL, TESTING FOR SECOND SMALLEST KEY 14001445:\t(SINCE SMALLEST NODE HAS A RIGHT CHILD)\n");

	numTests++;

	DSAQueue<String> queue3 = new DSAQueue<String>();

	tree.postOrderTrav(queue3);

	test = queue3.dequeue();
	// System.out.println(test); (TEST)

	if (test.equals("14001445")) {
	    System.out.println("\tTEST 5: PASSED\n");
	    testsPassed++;
	} else {
	    System.out.println("\tTEST 5: FAILED\n");
	}



	System.out.println("*********************************\n");
	System.out.println("TESTS PASSED: " + testsPassed + " / " + numTests + "\n");

    }

}
