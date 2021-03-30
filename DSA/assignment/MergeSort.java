import java.util.*;

public class MergeSort {

    public static void mergeSort(Nominee[] array, int choice) {
	int startIndex = 0;
	int endIndex = array.length;
	int midIndex = endIndex / 2;

	Nominee[] right;

	if ((endIndex % 2) == 1) {
	    right = new Nominee[midIndex + 1];
	} else {
	    right = new Nominee[midIndex];
	}

	Nominee[] left = new Nominee[midIndex];

	for (int i = startIndex; i < midIndex; i++) {
	    left[i] = array[i];
	}

	for (int i = midIndex; i < endIndex; i++) {
	    right[i - midIndex] = array[i];
	}

	for (int i = 0; i < endIndex; i++) {
	    array[i] = null;
	}

	mergeSortRec(left, choice);
	mergeSortRec(right, choice);

	merge(array, left, right, choice);

	mergeSortRec(array, choice);
	
    }

    private static void mergeSortRec(Nominee[] array, int choice) {

	if (array.length >= 2) {

	    Nominee[] left = new Nominee[array.length / 2];
	    Nominee[] right = new Nominee[array.length - array.length / 2];

	    for (int i = 0; i < left.length; i++) {
		left[i] = array[i];
	    }

	    for (int i = 0; i < right.length; i++) {
		right[i] = array[i + array.length / 2];
	    }

	    mergeSortRec(left, choice);
	    mergeSortRec(right, choice);
	    merge(array, left, right, choice);

	}

    }

    private static void merge(Nominee[] array, Nominee[] left, Nominee[] right, int choice) {

	// choice = 1: SURNAME
	// choice = 2: DIVISION
	// choice = 3: PARTY
	// choice = 4: STATE

	int a = 0;
	int b = 0;

	if (choice == 4) {

	    for (int i = 0; i < array.length; i++) {

		if (b >= right.length || (a < left.length && left[a].getStateAb().compareToIgnoreCase(right[b].getStateAb()) < 0)) {
		    array[i] = left[a];
		    a++;
		} else {
		    array[i] = right[b];
		    b++;
		}

	    }


	} else if (choice == 3) {

	    for (int i = 0; i < array.length; i++) {

		if (b >= right.length || (a < left.length && left[a].getPartyNm().compareToIgnoreCase(right[b].getPartyNm()) < 0)) {
		    array[i] = left[a];
		    a++;
		} else {
		    array[i] = right[b];
		    b++;
		}

	    }

	} else if (choice == 2) {

	    for (int i = 0; i < array.length; i++) {

		if (b >= right.length || (a < left.length && left[a].getDivisionNm().compareToIgnoreCase(right[b].getDivisionNm()) < 0)) {
		    array[i] = left[a];
		    a++;
		} else {
		    array[i] = right[b];
		    b++;
		}

	    }

	} else if (choice == 1) {

	    for (int i = 0; i < array.length; i++) {

		if (b >= right.length || (a < left.length && left[a].getSurname().compareToIgnoreCase(right[b].getSurname()) < 0)) {
		    array[i] = left[a];
		    a++;
		} else {
		    array[i] = right[b];
		    b++;
		}

	    }

	}

    }

}
