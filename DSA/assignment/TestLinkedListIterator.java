import java.util.Iterator;

public class TestLinkedListIterator {

    public static void main(String[] args) {

	//VARIABLE DECLERATION:
	DSALinkedList<String> theList;
	Object value;
	String stringValue;
	int iNumPassed = 0;
	int iNumTest = 0;

	System.out.println("\n\nTESTING DSALINKEDLIST ITERATOR:\n");
	System.out.println("=====================================\n");

	//LINKEDLIST DECLERATION (no need to check functionality):
	theList = new DSALinkedList<String>();

	theList.insertLast("abc");
	theList.insertLast("def");
	theList.insertLast("ghi");
	theList.insertLast("jkl");

	// TEST 1: ITERATOR INITIALIZATION + HASNEXT() METHOD
	System.out.println("TEST 1: ITERATOR INITIALIZATION + HASNEXT() METHOD\n");
	
	Iterator<String> iterator = theList.iterator();

	try {

	    iNumTest++;

	    if (!(iterator.hasNext())) {
		throw new IllegalArgumentException("FAILED");
	    }

	    iNumPassed++;
	    System.out.println("PASSED\n");

	} catch (Exception e) {
	    System.out.println("FAILED\n");
	}

	System.out.println("=====================================\n");

	//TEST 2: NEXT() METHOD
	System.out.println("TEST 2: NEXT() METHOD:\n");

	iNumTest++;
	value = iterator.next();
	stringValue = (String) value;
	if (!(stringValue.equals("abc"))) {
	    System.out.println("FAILED");
	} else {

	    iNumPassed++;
	    System.out.println("PASSED\n");

	}

	System.out.println("=====================================\n");

	//TOTAL SCORE:
	System.out.println("PASSED: " + iNumPassed + " / " + iNumTest + "\n");

    }

}
