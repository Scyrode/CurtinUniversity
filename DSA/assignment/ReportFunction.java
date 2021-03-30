import java.util.*;
import java.text.*;
import java.io.*;

/***************************************************************************
 *  FILE: ReportFunction.java
 *  AUTHOR: Ahmad Allahham - 19170251
 *  UNIT: DSA - Assignment
 *  PURPOSE: this class stores the main functions called by the 
 *	     Main Menu in the Main Program (functions include: listNominees,
 *	     nomineeSearch, listByMargin and itineraryByMargin)
 *  LAST MOD: 26/10/18
 ***************************************************************************/
public class ReportFunction {

    /***************************************************************************
     *  FUNCTION: listNominees
     *  PARAMETERES: tree (BinarySearchTree<Nominee>)
     *  EXPORT: NONE
     *  PURPOSE: lists the nominees according to the
     *		 filters and order chosen by the user
     *  LAST MOD: 28/10/18
     ***************************************************************************/
    public static void listNominees(BinarySearchTree<Nominee> tree) {

	// create an array to store all the nominees and preform the sorting
	Nominee[] array = new Nominee[2000];
	DSAQueue<Nominee> queue = new DSAQueue<Nominee>();
	Scanner sc = new Scanner(System.in);
	FileWriter out = null;

	String tempState = "";
	String tempParty = "";
	String tempDivision = "";
	String filename = "";
	String saveFileChoice = "";
	String str = "";

	boolean filterState = false;
	boolean filterParty = false;
	boolean filterDivision = false;

	boolean orderSurname = false;
	boolean orderState = false;
	boolean orderParty = false;
	boolean orderDivision = false;

	int filtersPresent = 0;
	int ordersPresent = 0;
	int i = 0;
	int arrayLength = 0;

	// export the queue in ascending order
	// using the CandidateID (inOrderTraversal)
	tree.inOrderTrav(queue);
    
	// check for ordering by surname
	System.out.println("\nOrder by Surname? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    orderSurname = true;
	    ordersPresent++;
	}

	str = "";

	// check for ordering by state  
	System.out.println("\nOrder by State? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    orderState = true;
	    ordersPresent++;
	}

	str = "";

	// check for ordering by party
	System.out.println("\nOrder by Party? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    orderParty = true;
	    ordersPresent++;
	}

	str = "";

	// check for ordering by division
	System.out.println("\nOrder by division? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    orderDivision = true;
	    ordersPresent++;
	}

	str = "";
    
	// check for filtering by state
	System.out.println("\nFilter by State? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    filterState = true;
	    System.out.println("\nEnter the STATE ABBREVIATION you would like to filter by:\n");
	    tempState = sc.next();
	    sc.nextLine();
	    filtersPresent++;
	}

	str = "";

	// check for filtering by party
	System.out.println("\nFilter by Party? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    filterParty = true;
	    System.out.println("\nEnter the PARTY ABBREVIATION you would like to filter by:\n");
	    tempParty = sc.next();
	    sc.nextLine();
	    filtersPresent++;
	}

	str = "";

	// check for filtering by division
	System.out.println("\nFilter by Division? (Y/N)\n");
	str = sc.next();
	sc.nextLine();

	if (str.equalsIgnoreCase("Y")) {
	    filterDivision = true;
	    System.out.println("\nEnter the DIVISION NAME you would like to filter by:\n");
	    tempDivision = sc.next();
	    sc.nextLine();
	    filtersPresent++;
	}

	str = "";

	try {

	    // ask if user wants to save report then initiate FileWriter if yes
	    System.out.println("\nWould you like to save this report? (Y/N)\n");
	    saveFileChoice = sc.next();
	    sc.nextLine();
 
	    if (saveFileChoice.equalsIgnoreCase("y")) {

		System.out.println("\nWould you like to save this report to the default folder: (nominee_list.csv)? (Y/N)\n");
		str = sc.next();
		sc.nextLine();

		if (str.equalsIgnoreCase("y")) {
		    filename = "nominee_list.csv";
		} else if (str.equalsIgnoreCase("n")) {

		    System.out.println("\nPlease enter the name of the file to write to:\n");
		    filename = sc.next();
		    sc.nextLine();

		}

		out = new FileWriter(filename);
		str = "";

	    }

	    // ONLY empty the entries from the queue into the array that match the filters chosen by
	    // the user. If the entry from the queue does not match the chosen filters, discard it
	    while (!(queue.isEmpty())) {

		array[i] = queue.dequeue();

		if (filtersPresent == 3) {

		    if (tempState.equalsIgnoreCase(array[i].getStateAb())) {
			if (tempParty.equalsIgnoreCase(array[i].getPartyAb())) {
			    if (tempDivision.equalsIgnoreCase(array[i].getDivisionNm())) {
				i++;
			    }
			}
		    }

		} else if (filtersPresent == 2) {

		    if (filterState && filterParty) {
			if (tempState.equalsIgnoreCase(array[i].getStateAb())) {
			    if (tempParty.equalsIgnoreCase(array[i].getPartyAb())) {
				i++;
			    }
			}
		    }

		    if (filterState && filterDivision) {
			if (tempState.equalsIgnoreCase(array[i].getStateAb())) {
			    if (tempDivision.equalsIgnoreCase(array[i].getDivisionNm())) {
				i++;
			    }
			}
		    }

		    if (filterParty && filterDivision) {
			if (tempParty.equalsIgnoreCase(array[i].getPartyAb())) {
			    if (tempDivision.equalsIgnoreCase(array[i].getDivisionNm())) {
				i++;
			    }
			}
		    }

		} else if (filtersPresent == 1) {

		    if (filterState) {

			if (tempState.equalsIgnoreCase(array[i].getStateAb())) {
			    i++;
			}

		    } else if (filterParty) {

			if (tempParty.equalsIgnoreCase(array[i].getPartyAb())) {
			    i++;
			}

		    } else if (filterDivision) {

			if (tempDivision.equalsIgnoreCase(array[i].getDivisionNm())) {
			    i++;
			}

		    }

		} else if (filtersPresent == 0) {
		    i++;
		}

	    }
	    // FILTERING DONE
	    
	    // recreate an array with the CORRECT array length (the number of entries in the
	    // filtered array)
	    Nominee[] filteredArray = new Nominee[i];

	    for (int ii = 0; ii < i; ii++) {
		filteredArray[ii] = array[ii];
	    }

	    // merge sort is used as it is a STABLE sort;
	    // necessary for multilayer sorting
	    switch (ordersPresent) {

		case 0:
		    break;
		case 1:

		    if (orderSurname) {
			MergeSort.mergeSort(filteredArray, 1);
		    } else if (orderDivision) {
			MergeSort.mergeSort(filteredArray, 2);
		    } else if (orderParty) {
			MergeSort.mergeSort(filteredArray, 3);
		    } else if (orderState) {
			MergeSort.mergeSort(filteredArray, 4);
		    }

		    break;
		case 2:

		    if (orderSurname) {
			MergeSort.mergeSort(filteredArray, 1);
			if (orderDivision) {
			    MergeSort.mergeSort(filteredArray, 2);
			} else if (orderParty) {
			    MergeSort.mergeSort(filteredArray, 3);
			} else if (orderState) {
			    MergeSort.mergeSort(filteredArray, 4);
			}
		    } else if (orderDivision) {
			MergeSort.mergeSort(filteredArray, 2);
			if (orderParty) {
			    MergeSort.mergeSort(filteredArray, 3);
			} else if (orderState) {
			    MergeSort.mergeSort(filteredArray, 4);
			}
		    } else {
			MergeSort.mergeSort(filteredArray, 3);
			MergeSort.mergeSort(filteredArray, 4);
		    }

		    break;
		case 3:

		    if (orderSurname) {
			MergeSort.mergeSort(filteredArray, 1);
			if (orderDivision) {
			    MergeSort.mergeSort(filteredArray, 2);
			    if (orderParty) {
				MergeSort.mergeSort(filteredArray, 3);
			    } else {
				MergeSort.mergeSort(filteredArray, 4);
			    }
			} else {
			    MergeSort.mergeSort(filteredArray, 3);
			    MergeSort.mergeSort(filteredArray, 4);
			}
		    } else {
			MergeSort.mergeSort(filteredArray, 2);
			MergeSort.mergeSort(filteredArray, 3);
			MergeSort.mergeSort(filteredArray, 4);
		    }

		    break;
		case 4:

		    MergeSort.mergeSort(filteredArray, 1);
		    MergeSort.mergeSort(filteredArray, 2);
		    MergeSort.mergeSort(filteredArray, 3);
		    MergeSort.mergeSort(filteredArray, 4);

		    break;
		default:
		    System.out.println("INCORRECT ALGORITHIM");

	    }
	    
	    //the arrayLength is equal to i (i is incremented everytime an entry was added into the
	    //array)
	    arrayLength = i;
	    i = 0;

	    // print the ordered report
	    System.out.println("\n-------------------------------------\n");
	    System.out.println("StateAb, DivisionID, DivisionNm, PartyAb, PartyNm, CandidateID, Surname, GivenNm, Elected, HistoricElected\n");

	    if (saveFileChoice.equalsIgnoreCase("y")) {
		str = "StateAb, DivisionID, DivisionNm, PartyAb, PartyNm, CandidateID, Surname, GivenNm, Elected, HistoricElected";
		out.write(str);
		out.write(System.lineSeparator());
	    }

	    while (i < arrayLength) {

		str = filteredArray[i].toString();
		System.out.println(str);

		if (saveFileChoice.equalsIgnoreCase("y")) {
		    out.write(str);
		    out.write(System.lineSeparator());
		}

		i++;

	    }

	    if (out != null) {
		out.close();
	    }

	} catch (InputMismatchException e) {
	    System.out.println("\nInvalid Input (must input (true/false) for ordering and filtering)");
	} catch (IOException e) {
	    System.out.println("Error while writing to file");
	}

    }

    /***************************************************************************
     *  FUNCTION: nomineeSearch
     *  PARAMETERES: tree (BinarySearchTree<Nominee>)
     *  EXPORT: NONE
     *  PURPOSE: lists all the nominees that match
     *		 the search done by the user
     *		 (including any filters)
     *  LAST MOD: 28/10/18
     ***************************************************************************/
    public static void nomineeSearch(BinarySearchTree<Nominee> tree) {

	// create a queue used to export the nominees
	// from the binary search tree
	DSAQueue<Nominee> queue = new DSAQueue<Nominee>();
	Scanner sc = new Scanner(System.in);
	FileWriter out = null;

	Nominee tempNominee;
	String tempSurname = "";
	String tempState = "";
	String tempParty = "";
	String saveFileChoice = "";
	String str = "";
	String filename = "";
	boolean filterState = false;
	boolean filterParty = false;
	int testsRequired = 0;

	// fill the queue with the nominees 
	// in the same order they were read
	// (using pre-order traversal)
	tree.preOrderTrav(queue);

	// obtain the substring to search
	// by from the user
	System.out.println("\nPlease enter the SURNAME SUBSTRING you would like to search by:\n");
	tempSurname = sc.next();
	sc.nextLine();

	// check for filtering by state
	System.out.println("\nFilter by State? (true/false)\n");
	filterState = sc.nextBoolean();
	sc.nextLine();

	if (filterState) {

	    System.out.println("\nEnter the STATE ABBREVIATION you would like to filter by:\n");
	    tempState = sc.next();
	    sc.nextLine();
	    testsRequired++;

	}

	// check for filtering by party
	System.out.println("\nFilter by Party? (true/false)\n");
	filterParty = sc.nextBoolean();
	sc.nextLine();

	if (filterParty) {

	    System.out.println("\nEnter the PARTY ABBREVIATION you would like to filter by:\n");
	    tempParty = sc.next();
	    sc.nextLine();
	    testsRequired++;

	}

	try {
	    
	    // ask if user wants to save report then initiate FileWriter if yes
	    System.out.println("\nWould you like to save this report? (Y/N)\n");
	    saveFileChoice = sc.next();
	    sc.nextLine();
 
	    if (saveFileChoice.equalsIgnoreCase("y")) {

		System.out.println("\nWould you like to save this report to the default folder: (" + tempSurname + "_search.csv)? (Y/N)\n");
		str = sc.next();
		sc.nextLine();

		if (str.equalsIgnoreCase("y")) {
		    filename = tempSurname + "_search.csv";
		} else if (str.equalsIgnoreCase("n")) {

		    System.out.println("\nPlease enter the name of the file to write to:\n");
		    filename = sc.next();
		    sc.nextLine();

		}

		out = new FileWriter(filename);

		out.write("StateAb, DivisionID, DivisionNm, PartyAb, PartyNm, CandidateID, Surname, GivenNm, Elected, HistoricElected");
		out.write(System.lineSeparator());

		str = "";

	    }

	    // print the list of nominees while complying with
	    // the filters chosen by the user
	    System.out.println("\n-------------------------------------\n");
	    System.out.println("StateAb, DivisionID, DivisionNm, PartyAb, PartyNm, CandidateID, Surname, GivenNm, Elected, HistoricElected\n");

	    while (!(queue.isEmpty())) {

		tempNominee = queue.dequeue();

		if (tempNominee.getSurname().indexOf(tempSurname.toUpperCase()) != -1) {

		    if (testsRequired == 2) {

			if (tempState.equalsIgnoreCase(tempNominee.getStateAb())) {

			    if (tempParty.equalsIgnoreCase(tempNominee.getPartyAb())) {

				str = tempNominee.toString();
				System.out.println(str);

				if (saveFileChoice.equalsIgnoreCase("y")) {
				    out.write(str);
				    out.write(System.lineSeparator());
				}

			    }

			}

		    }

		    if (testsRequired == 1) {

			if (filterState) {

			    if (tempState.equalsIgnoreCase(tempNominee.getStateAb())) {

				str = tempNominee.toString();
				System.out.println(str);

				if (saveFileChoice.equalsIgnoreCase("y")) {
				    out.write(str);
				    out.write(System.lineSeparator());
				}

			    }

			}

			if (filterParty) {
			    
			    if (tempParty.equalsIgnoreCase(tempNominee.getPartyAb())) {

				str = tempNominee.toString();
				System.out.println(str);

				if (saveFileChoice.equalsIgnoreCase("y")) {
				    out.write(str);
				    out.write(System.lineSeparator());
				}

			    }

			}

		    }

		    if (testsRequired == 0) {

			str = tempNominee.toString();
			System.out.println(str);

			if (saveFileChoice.equalsIgnoreCase("y")) {
			    out.write(str);
			    out.write(System.lineSeparator());
			}

		    }

		}

	    }

	    if (out != null) {
		out.close();
	    }

	} catch (InputMismatchException e) {
		System.out.println("\nInvalid Input (must input (true/false) for filtering)");
	} catch (IOException e) {
		System.out.println("Error while writing to file");
	}

    }

    /***************************************************************************
     *  FUNCTION: listByMargin
     *  PARAMETERES: tree (BinarySearchTree<Nominee>)
     *  EXPORT: marginList (DSALinkedList<String>)
     *  PURPOSE: lists all the nominees that match
     *		 the search done by the user
     *		 (including any filters)
     *  LAST MOD: 28/10/18
     ***************************************************************************/
    public static DSALinkedList<String> listByMargin(DSALinkedList<String> list, DSALinkedList<String> marginList) {

	Scanner sc = new Scanner(System.in);

	FileWriter out = null;
	Iterator<String> iterator = list.iterator();

	String[] tokens;
	String divisionNm = "";
	String currentDiv = "";
	String stateAb = "";
	String partyAb = "";
	String chosenParty = "";
	String saveFileChoice = "";
	String filename = "";
	String str = "";

	int votesFor = 0;
	int votesAgainst = 0;

	double threshold = 6.0;
	double currentMargin = 0.0;

	boolean isFirstTime;

	char choice;

	// prompt the user for the party abbreviation
	System.out.println("Enter the PARTY ABBREVIATION for the list by margin:\n");
	chosenParty = sc.next();
	sc.nextLine();

	// prompt the usre for changing the threshold (default value of 6%)
	System.out.println("\nDefault threshold is 6%. Would you like to change it? (Y/N)\n");
	choice = sc.next().charAt(0);
	sc.nextLine();

	if (Character.toLowerCase(choice) == 'y') {

	    System.out.println("\nEnter the threshold: (as a percentage without the '%')\n");
	    threshold = sc.nextDouble();
	    sc.nextLine();

	}

	try {
	
	    // ask if user wants to save report then initiate FileWriter if yes
	    System.out.println("\nWould you like to save this report? (Y/N)\n");
	    saveFileChoice = sc.next();
	    sc.nextLine();
 
	    if (saveFileChoice.equalsIgnoreCase("y")) {

		System.out.println("\nWould you like to save this report to the default folder: (" + chosenParty.toUpperCase() + "_list_By_Margin.csv)? (Y/N)\n");
		str = sc.next();
		sc.nextLine();

		if (str.equalsIgnoreCase("y")) {
		    filename = chosenParty.toUpperCase() + "_list_By_Margin.csv";
		} else if (str.equalsIgnoreCase("n")) {

		    System.out.println("\nPlease enter the name of the file to write to:\n");
		    filename = sc.next();
		    sc.nextLine();

		}

		out = new FileWriter(filename);

		out.write("State,Division,Votes For,Votes Against");
		out.write(System.lineSeparator());

		str = "";

	    }

	    // for every new division name, calculate the votes
	    // for and against, then add to the margin list 
	    // the division name, state Abreviation and margin
	    //  *ONLY if the margin is within the chosen threshold
	    //  **Default threshold of 6%
	    if (iterator.hasNext()) {
		tokens = iterator.next().split(",");
	    } else {
		tokens = null;
	    }

	    while (iterator.hasNext()) {

		// for every new division, set isFirstTime
		// to true (first entry for this division)
		// and reset votes for and against
		isFirstTime = true;
		votesFor = 0;
		votesAgainst = 0;

		do {

		    // if it is the first time, don't 
		    // extract the next entry from the list
		    // and update the divisionNm and currentDiv.
		    // Else extract the next entry from the list
		    if (isFirstTime) {

			divisionNm = tokens[2];
			currentDiv = divisionNm;
			isFirstTime = false;

		    } else {

			tokens = iterator.next().split(",");
			currentDiv = tokens[2];

		    }

		    // this should only run when an entry with
		    // a new division is extracted from the list.
		    // If this happens, calculate the margin and
		    // if it is less than the thershold, create
		    // an entry in to be exported linked list
		    // (marginList) and output that seat to the
		    // user
		    if (!(currentDiv.equals(divisionNm)) || !(iterator.hasNext())) {

			currentMargin =  ( (double) votesFor / (double) (votesFor + votesAgainst)) * 100 - 50.0;

			if (((currentMargin > (-1 * threshold)) && (currentMargin < threshold))) {

			    System.out.println("\nState: " + stateAb + ", Divsion: " + divisionNm + ", Party: " + chosenParty.toUpperCase() + "\nvotes for: " + votesFor + ", votes against: " + votesAgainst + "\nMargin: " + Double.toString(currentMargin) + ", within the threshold of +/- " + Double.toString(threshold) + "%");

			    str = stateAb + "," + divisionNm + "," + votesFor + "," + votesAgainst;
			    marginList.insertLast(divisionNm);

			    if (saveFileChoice.equalsIgnoreCase("y")) {
				out.write(str);
				out.write(System.lineSeparator());
			    }


			}

		    }

		    partyAb = tokens[11];
		    stateAb = tokens[0];

		    // increment the votes for if the entry is
		    // for the party chosen, else increment
		    // the votes against
		    if (currentDiv.equals(divisionNm)) {

			if (partyAb.equalsIgnoreCase(chosenParty)) {

			    if (tokens[12].equals("\"Shooters")) {
				votesFor += Integer.parseInt(tokens[14]);
			    } else {
				votesFor += Integer.parseInt(tokens[13]);
			    }

			} else {

			    if (tokens[12].equals("\"Shooters")) {
				votesAgainst += Integer.parseInt(tokens[14]);
			    } else {
				votesAgainst += Integer.parseInt(tokens[13]);
			    }

			}

		    }

		} while (currentDiv.equals(divisionNm) && iterator.hasNext());

	    }

	    if (out != null) {
		out.close();
	    }


	} catch (IOException e) {
	    System.out.println("Error while writing to file");
	}

	return marginList;

    }

    /***************************************************************************
     *  FUNCTION: itineraryByMargin
     *  PARAMETERES: marginListn (DSALinkedList<String>)
     *  EXPORT: NONE
     *  PURPOSE: To produce an itinerary for the party chosen in the list
     *		 by margin, which is optimised to visit all the places listed
     *		 in the list by margin, visiting all the connected divisions
     *		 within a state first, then visiting the divisions not connected
     *		 directly before going to other states
     *  LAST MOD: 28/10/18
     ***************************************************************************/
    public static void itineraryByMargin(DSALinkedList<String> marginList) {

	Scanner sc = new Scanner(System.in);

	Iterator<String> marginListIterator = marginList.iterator();

	String dateString = "";
	String currentDiv = "";
	String futureDiv = "";
	String currentDistance = "";
	String tokens[];

	long nineAmMilli = 32400000;
	long meetAndGreetTime = 10800000;
	long initialTime;

	int day, month, year;
	int currentMinutes;
	int futureMinutes = 0;

	boolean isFirstIteration = true;
	boolean noMore = false;

	if (marginList.isEmpty()) {
	    System.out.println("Must produce list by margin first. \nExecute option 3 before executing option 4");
	} else {

	    DSALinkedList<String> distanceList = new DSALinkedList<String>();

	    try {
		ReportLoad.loadDistances(distanceList, marginList, 0);
		ReportLoad.loadDistances(distanceList, marginList, 1);
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    System.out.println("Enter the date for the start of the itinerary: (YY/MM/DD)\n");
	    dateString = sc.next();
	    sc.nextLine();

	    SimpleDateFormat ft = new SimpleDateFormat ("yy/MM/dd");

	    Iterator<String> distanceListIterator;

	    try {

		Date date = ft.parse(dateString);

		initialTime = date.getTime();

		date.setTime(initialTime + nineAmMilli);

		// test
		System.out.println(date.toString());

		System.out.println("\nDepart from, Departure Date, Destination, Arrival Date\n");

		while (marginListIterator.hasNext()) { 

		    currentDiv = marginListIterator.next();
		    distanceListIterator = distanceList.iterator();

		    do {

			tokens = distanceListIterator.next().split(",");

		    } while (!tokens[0].equalsIgnoreCase(currentDiv));

		    while (tokens[0].equalsIgnoreCase(currentDiv) && !noMore) {

			futureDiv = tokens[1];
			futureMinutes = Integer.parseInt(tokens[2]);
			if (distanceListIterator.hasNext()) {
			    tokens = distanceListIterator.next().split(",");
			} else {
			    noMore = true;
			}

		    }

		    Date futureDate = (Date) date.clone();

		    initialTime = futureDate.getTime();
		    futureDate.setTime(initialTime + futureMinutes * 60 *  60);

		    System.out.println(futureDiv + ", " + date.toString() + ", " + currentDiv + ", " + futureDate.toString());

		    date = (Date) futureDate.clone();

		    initialTime = futureDate.getTime();

		    date.setTime(initialTime + meetAndGreetTime);
		    futureDate.setTime(initialTime + meetAndGreetTime + futureMinutes * 60 * 60);

		    System.out.println(currentDiv + ", " + date.toString() + ", " + futureDiv + ", " + futureDate.toString());

		}

		// TO BE CONTINUED: 
		//  - WRITE TO FILE
		//  - TRAVEL BETWEEN AIRPORTS
		//  - REPORT
		//  - COMMENTS
		//  - REMOVE UNNECESSARY CLASSES
		//  - UML

	    } catch (ParseException e) {
		System.out.println("\nError in date format. must input: YY/MM/DD\n");
	    }

	}

    }

}
