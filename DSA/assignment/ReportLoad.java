import java.util.*;
import java.io.*;

/***************************************************************************
 *  FILE: ReportLoad.java
 *  AUTHOR: Ahmad Allahham - 19170251
 *  UNIT: DSA - Assignment
 *  PURPOSE: this class contains the functions used to load data to    
 *	     Data Structures intialised in the Main Program (functions      
 *	     include: loadNominees and loadDistances)
 *  LAST MOD: 26/10/18
 ***************************************************************************/
public class ReportLoad {

    /***************************************************************************
     *  FUNCTION: loadNominees
     *  PARAMETERES: tree (BinarySearchTree<Nominee>)
     *  EXPORT: NONE
     *  PURPOSE: to load the nominee list into a
     *		 Binary Searach Tree
     *  LAST MOD: 20/10/18
     ***************************************************************************/
    public static void loadNominees(BinarySearchTree<Nominee> tree) throws IOException {

	String filename;
	Scanner sc = new Scanner(System.in);

	// ask the user for the name of the file to 
	// read the list of nominees from
	System.out.println("Input the name of the file you would like to read poll data from:\n");
	
	filename = sc.next();
	sc.nextLine();

	FileReader in = new FileReader(filename);

	System.out.print("\nUPLOADING FILE: ");

	// read the list, one line at a time ignoring 
	// the first two lines (title and column names)
	// then, create a temporary nominee and store
	// the contents of that line into it. Finally,
	// insert this nominee into the binary search
	// tree (using the CandidateID as the key)
	
	int c = 0;
	String delim = ",";
	String text = "";
	String[] tokens;
	Nominee nominee;

	for (int i = 0; i < 2; i++) {
	    
	    do {
		c = in.read();
	    } while (c != '\n');

	}

	while ((c = in.read()) != -1) {

	    text = "";

	    do {
		text += (char) c;
	    } while ((c = in.read()) != '\n');

	    tokens = text.split(delim);

	    // dealing with an odd case
	    // ("Shooters, Fishers and Farmers" as a party name)
	    if (tokens[4].equals("\"Shooters")) {

		tokens[4] = tokens[4] + "," + tokens[5];
		tokens[4] = tokens[4].replace("\"", "");
		tokens[4] = tokens[4].replace(",", "");
		nominee = new Nominee(tokens[0], Integer.valueOf(tokens[1]), tokens[2], tokens[3], tokens[4], Integer.valueOf(tokens[6]), tokens[7], tokens[8], tokens[9].charAt(0), tokens[10].charAt(0));

	    } else {
		nominee = new Nominee(tokens[0], Integer.valueOf(tokens[1]), tokens[2], tokens[3], tokens[4], Integer.valueOf(tokens[5]), tokens[6], tokens[7], tokens[8].charAt(0), tokens[9].charAt(0));
	    }

	    tree.insert(Integer.toString(nominee.getCandidateID()), nominee);

	}

	in.close();

	System.out.println("UPLOAD COMPLETE");
	System.out.println("\n-------------------------------------\n");

    }

    /***************************************************************************
     *  FUNCTION: loadVotes
     *  PARAMETERES: linkedList (String)
     *  EXPORT: NONE
     *  PURPOSE: to load the party votes from a list of files
     *  LAST MOD: 20/10/18
     ***************************************************************************/
    public static void loadVotes(DSALinkedList<String> list) throws IOException {

	Scanner sc = new Scanner(System.in);
	String filename;
	String text;
	int filesLoaded = 0;
	char choice;
	int c;

	// loop until the user decides not 
	// to load any more files
	do {

	    // output the number of files read so far
	    System.out.println("You have " + filesLoaded + " Vote Statistics files loaded.");
	    System.out.println("Would you like to load more files? (Y/N)\n");
	    choice = sc.next().charAt(0);

	    if (Character.toLowerCase(choice) == 'y') {

		// ask the user for the name of the 
		// file they would like to read from
		System.out.println("\nEnter the name of the file you would like to read:\n");

		filename = sc.next();
		sc.nextLine();

		try {

		    FileReader in = new FileReader(filename);
		    System.out.print("\nUPLOADING FILE: ");
		    
		    // skip over the first two lines
		    for (int i = 0; i < 2; i++) {
		
			do {
			    c = in.read();
			} while (c != '\n');

		    }

		    // raed the file one line at a time and store
		    // the lines read into the imported
		    // linked List
		    while ((c = in.read()) != -1) {

			text = "";

			do {
			    text += (char) c;
			} while ((c = in.read()) != '\n');

			list.insertLast(text);

		    }
    
		    // cloes the file
		    in.close();

		    System.out.println("UPLOAD COMPLETE");
		    System.out.println("\n-------------------------------------\n");

		    // increment the number of files read
		    filesLoaded++;

		} catch (FileNotFoundException e) {
		    System.out.println("\nError: File not Found\n");
		}

	    } else if (Character.toLowerCase(choice) == 'n') {
		System.out.println("\n-------------------------------------\n");
	    }

	} while (Character.toLowerCase(choice) != 'n');

    }

    public static void loadDistances(DSALinkedList<String> distanceList, DSALinkedList<String> marginList, int operation) throws IOException {
	
	Scanner sc = new Scanner(System.in);
	BufferedReader reader = null;
	Iterator<String> iterator = marginList.iterator();

	String tokens[];
	String filename;
	String text;
	String timeString[];
	String currentDivision;

	int hours, minutes;
	int totalMinutes;
	int c;

	// if chosen operation is = 0, populate the distanceList from the airport file
	// else, populate the distanceList from the electoral distances file
	if (operation == 0) {

	    // ask the user for the name of the 
	    // file they would like to read from
	    System.out.println("Enter the name of the airportDistances file:\n");

	    filename = sc.next();
	    sc.nextLine();

	    try {

		FileReader in = new FileReader(filename);
		reader = new BufferedReader(in);

		System.out.print("\nUPLOADING FILE: ");
		
		// skip over the first line
		reader.readLine();
		
		// raed the file one line at a time and store
		// the divisions as well as the time into the 
		// imported linked List
		while ((text = reader.readLine()) != null) {

		    tokens = text.split(",");

		    // calculate the total time in minutes 
		    // from the read time string
		    timeString = tokens[9].split(":");
		    hours = Integer.valueOf(timeString[0]);
		    minutes = Integer.valueOf(timeString[1]);

		    totalMinutes = hours * 60 + minutes;

		    if (!tokens[10].equalsIgnoreCase("car")) {
			distanceList.insertLast(tokens[1] + "," + tokens[5] + "," + Integer.toString(totalMinutes));
		    }

		}

		// cloes the file
		in.close();

		System.out.println("UPLOAD COMPLETE");
		System.out.println("\n-------------------------------------\n");

	    } catch (FileNotFoundException e) {
		System.out.println("\nError: File not Found\n");
	    }

	} else {
	    
	    // ask the user for the name of the 
	    // file they would like to read from
	    System.out.println("Enter the name of the Electoral Distances file:\n");

	    filename = sc.next();
	    sc.nextLine();

	    System.out.print("\nUPLOADING FILE: ");

	    while (iterator.hasNext()) {

		currentDivision = iterator.next();

		try {

		    FileReader in = new FileReader(filename);
		    reader = new BufferedReader(in);

		    // skip over the first line
		    reader.readLine();

		    // raed the file one line at a time and store
		    // the lines read into the imported
		    // linked List
		    while ((text = reader.readLine()) != null) {

			tokens = text.split(",");

			totalMinutes = Integer.parseInt(tokens[9]) / 60;

			if (currentDivision.equalsIgnoreCase(tokens[1])) {
			    distanceList.insertLast(tokens[1] + "," + tokens[5] + "," + Integer.toString(totalMinutes));
			} 

		    }

		    // cloes the file
		    in.close();

		} catch (FileNotFoundException e) {
		    System.out.println("\nError: File not Found\n");
		}

	    }

	    System.out.println("UPLOAD COMPLETE");
	    System.out.println("\n-------------------------------------\n");

	}

    }

}
