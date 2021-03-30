import java.util.*;
import java.io.*;

/***************************************************************************
 *  FILE: MainProgram.java
 *  AUTHOR: Ahmad Allahham - 19170251
 *  UNIT: DSA - Assignment
 *  PURPOSE: This program allows the user to display and export 
 *	     nominee reports that have been filtered and/or ordered
 *	     according to the users needs
 *  LAST MOD: 20/10/18
 ***************************************************************************/
public class MainProgram {

    public static void main(String[] args) {

	// create the tree that stores the nominees
	BinarySearchTree<Nominee> tree = new BinarySearchTree<Nominee>();
	DSALinkedList<String> list = new DSALinkedList<String>();
	DSALinkedList<String> marginList = new DSALinkedList<String>();

	System.out.println("\nWelcome to The Poll Data Analyser!!");

	try {
	    
	    // display the main menu
	    mainMenu(tree, list, marginList);

	} catch (IOException e) {
	    e.printStackTrace();
	}


    }

    /***************************************************************************
     *  FUNCTION: mainMenu
     *  PARAMETERES: tree (BinarySearchTree<Nominee>)
     *  EXPORT: NONE
     *  PURPOSE: display the a menu from which the user can choose
     *		 how to construct and export a nominee report
     *  LAST MOD: 20/10/18
     ***************************************************************************/
    private static void mainMenu(BinarySearchTree<Nominee> tree, DSALinkedList<String> list, DSALinkedList<String> marginList) throws IOException {

	Scanner sc = new Scanner(System.in);

	int choice;

	// loop the menu until the user decides to quit
	do {

	    System.out.println("\n-------------------------------------\n");
	    System.out.println("Please choose one of the following options:");
	    System.out.println("(1)\t-List Nominees");
	    System.out.println("(2)\t-Nominee Search");
	    System.out.println("(3)\t-List by Margin");
	    System.out.println("(4)\t-Itinerary by Margin");
	    System.out.println("(0)\t-Exit");
	    System.out.print("\nchoice:> ");

	    choice = sc.nextInt();
	    sc.nextLine();

	    switch (choice) {
		case 0:
		    break;
		case 1:

		    System.out.println("\n-------------------------------------\n");
		    
		    // store the nominees in the tree (if not already stored)
		    if (tree.height() == -1) {
			ReportLoad.loadNominees(tree);
		    }

		    // call the listNominees function and pass it the tree
		    ReportFunction.listNominees(tree);
		    break;

		case 2:

		    System.out.println("\n-------------------------------------\n");

		    // store the nominees in the tree (if not already stored)
		    if (tree.height() == -1) {
			ReportLoad.loadNominees(tree);
		    }

		    // call the nomineeSearch function and pass it the tree
		    ReportFunction.nomineeSearch(tree);
		    break;

		case 3:

		    System.out.println("\n-------------------------------------\n");

		    // store the votes in a linkedList (if not already stored)
		    if (list.isEmpty()) {
			ReportLoad.loadVotes(list);
		    }

		    ReportFunction.listByMargin(list, marginList);
		    break;

		case 4:

		    System.out.println("\n-------------------------------------\n");
		    ReportFunction.itineraryByMargin(marginList);
		    break;

		default:
		    System.out.println("\nMust choose a number between 0 and 4 (inclusive)");
	    }

	} while (choice != 0);

	sc.close();

    }

}
