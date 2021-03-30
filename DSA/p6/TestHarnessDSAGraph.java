import java.util.*;
import java.io.*;

public class TestHarnessDSAGraph {

    public static void main(String[] args) {

	int numTests = 0;
	int testsPassed = 0;
	Object Null = null;

	System.out.println("\nTESTING DSAGraph:");


	System.out.println("###################################\n");
	System.out.println("TEST 1: CONSTRUCTING FROM FILE:\n");
	numTests++;

	DSAGraph graph = new DSAGraph("prac6_1.al", false);
	testsPassed++;

	System.out.println("TEST 1: PASSED");


	System.out.println("###################################\n");
	System.out.println("TEST 2: DISPLAY LIST:\n");
	numTests++;

	graph.displayList();

	System.out.println("\nTEST 2: PASSED");
	testsPassed++;


	System.out.println("###################################\n");
	System.out.println("TEST 3: DISPLAY MATRIX:\n");
	numTests++;

	graph.displayMatrix();

	System.out.println("\nTEST 3: PASSED");
	testsPassed++;

	System.out.println("###################################\n");
	System.out.println("TESTS PASSED: " + testsPassed + " / " + numTests + "\n");
	
    }

}
