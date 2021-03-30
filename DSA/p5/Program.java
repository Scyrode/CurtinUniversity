import java.util.*;
import java.io.*;

public class Program {

    public static void main(String[] args) {

	int answer;
	String filename;
	Scanner sc = new Scanner(System.in);
	BinarySearchTree<String> tree = new BinarySearchTree<String>();

	do {

	    printMenu(0);
	    answer = sc.nextInt();
	    sc.nextLine();
	    String text = "";

	    switch (answer) {

		case 1:

		    tree = new BinarySearchTree<String>();
		    printMenu(2);
		    filename = sc.nextLine();

		    try {
			readCSV(filename, tree);
		    } catch (IOException e) {
			e.printStackTrace();
		    }

		    break;

		case 2:
		    tree = readSerialized("serializedTree.ser");
		    break;
		case 3:

		    printMenu(1);
		    answer = sc.nextInt();
		    sc.nextLine();
		    displayTree(answer, tree);
		    break;

		case 4:

		    printMenu(1);
		    answer = sc.nextInt();
		    sc.nextLine();
		    try {
			writeCSV(answer, tree);
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		    break;

		case 5:
		    writeSerialized(tree);
		    break;
		case 6:
		    break;
		default:
		    System.out.println("input must be between 1 and 6 inclusive");

	    }

	} while (answer != 6);

	sc.close();

    }

    private static void printMenu(int menuNum) {

	if (menuNum == 0) {
	    System.out.println("CHOOSE ONE OF THE FOLLOWING:");
	    System.out.println("1) read a CSV file\n2) read a serialized file\n3) display tree\n4) write a CSV file\n5) write a serialized file\n6) exit");
	} else if (menuNum == 1) {
	    System.out.println("\nCHOOSE ONE OF THE FOLLOWING:");
	    System.out.println("1) in-order traversal\n2) pre-order traversal\n3) post-order traversal");
	} else if (menuNum == 2) {
	    System.out.print("\nEnter the name of the file you would like to read: ");
	}

    }

    private static void readCSV(String filename, BinarySearchTree<String> tree) throws IOException {

	FileReader in = new FileReader(filename);
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

	// ERROR: CRASHES IF THE NUMBER OF NODES CREATED IS GREATER THAN 2400 (UNSOLVED)
	for (int i = 0; i < 2400; i++) {
	    tree.insert(tokens[i*2], tokens[(i*2)+1]);
	}

	System.out.println("UPLOAD COMPLETE\n");

    }

    private static void writeCSV(int choice, BinarySearchTree<String> tree) throws IOException {

	DSAQueue<String> queue = new DSAQueue<String>();
	FileWriter out = new FileWriter("tree.csv");

	switch (choice) {

	    case 1:
		tree.inOrderTrav(queue);
		break;
	    case 2:
		tree.preOrderTrav(queue);
		break;
	    case 3:
		tree.postOrderTrav(queue);
		break;
	    default:
		System.out.println("input must be between 1 and 3 inclusive.");

	}

	if ((choice > 0) && (choice < 4)) {

	    while (!(queue.isEmpty())) {
		out.write(queue.dequeue());
		out.write(",");
		out.write(queue.dequeue());
		out.write("\n");
	    }

	    System.out.println("\nWRITE SUCCESSFUL\n");

	} else {
	    System.out.println("\nWRITE UNSUCCESSFUL\n");
	}

	out.close();

    }

    private static void writeSerialized(BinarySearchTree<String> tree) {

	FileOutputStream fileStrm;
	ObjectOutputStream objStrm;

	try {

	    fileStrm = new FileOutputStream("serializedTree.ser");
	    objStrm = new ObjectOutputStream(fileStrm);
	    objStrm.writeObject(tree);
	    objStrm.close();
	    System.out.println("\nWRITE SUCCESSFUL\n");
		        
	} catch (Exception e) {
	    throw new IllegalArgumentException("Unable to save object to file");
	}

    }

    private static BinarySearchTree<String> readSerialized(String filename) throws IllegalArgumentException {

	FileInputStream fileStrm;
	ObjectInputStream objStrm;
	BinarySearchTree<String> inObj = null;

	try {

	    fileStrm = new FileInputStream(filename);
	    objStrm = new ObjectInputStream(fileStrm);
	    inObj = (BinarySearchTree<String>) objStrm.readObject();
	    objStrm.close();
	    System.out.println("\nREAD SUCCESSFUL\n");
		       
	} catch (ClassNotFoundException e) {
	       System.out.println("Class BinarySearchTree<E> not found" + e.getMessage()); 
	} catch (Exception e) {
	    throw new IllegalArgumentException("Unable to load object from file");
	}

	return inObj;

    }
    private static void displayTree(int choice, BinarySearchTree<String> tree) {

	DSAQueue<String> queue = new DSAQueue<String>();

	switch (choice) {

	    case 1:
		tree.inOrderTrav(queue);
		break;
	    case 2:
		tree.preOrderTrav(queue);
		break;
	    case 3:
		tree.postOrderTrav(queue);
		break;
	    default:
		System.out.println("input must be between 1 and 3 inclusive.");

	}

	if ((choice > 0) && (choice < 4)) {

	    while (!(queue.isEmpty())) {
		System.out.print(queue.dequeue());
		System.out.print(",");
		System.out.print(queue.dequeue());
		System.out.println();
	    }

	    System.out.println("\nDISPLAY SUCCESSFUL\n");

	} else {
	    System.out.println("\nDISPLAY UNSUCCESSFUL\n");
	}

    }


}
