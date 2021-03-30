public class DSAHashTable<E> {

    private DSAHashEntry<E>[] table;
    private int count;

    // DEFAULT CONSTRUCTOR
    public DSAHashTable(int tableSize) {

	int actualSize = nextPrime(tableSize);
	table = new DSAHashEntry[actualSize];

	for (int i = 0; i < actualSize; i++) {
	    table[i] = new DSAHashEntry<E>();
	}

    }

    public E get(String inKey) {

	E retValue;
	int hashIdx = find(inKey);

	if (table[hashIdx].state == 0 || table[hashIdx].state == -1) {
	    throw new IllegalArgumentException("Key not in hashTable");
	} else {
	    retValue = table[hashIdx].value;
	}

	return retValue;

    }

    public void put(String inKey, E inValue) {

	DSAHashEntry<E> newEntry = new DSAHashEntry<E>(inKey, inValue);
	put(newEntry);

    }

    private void put(DSAHashEntry<E> newEntry) {

	int hashIdx = find(newEntry.key);
	double loadFactor;

	if (table[hashIdx].state == 1) {} 
	else {

	    table[hashIdx] = newEntry;
	    count++;

	    loadFactor = (double) count / table.length;

	    // using an upper threshold of 0.75 (good value)
	    if (loadFactor > 0.75) {

		int currentPrime = table.length;
		int newPrime = currentPrime * 2;
		newPrime = nextPrime(newPrime);

		reSize(newPrime);

	    }

	}

    }

    public E remove(String inKey) {

	int hashIdx = find(inKey);
	E retValue;

	if (table[hashIdx].state == 1) {
	    retValue = table[hashIdx].value;
	    table[hashIdx].state = -1;
	    count--;
	} else {
	    throw new IllegalArgumentException("Key not in HashTable");
	}

	return retValue;

    }

    public boolean containsKey(String inKey) {

	boolean isFound = false;
	int hashIdx = find(inKey);

	if (table[hashIdx].state == 1 && table[hashIdx].key.equals(inKey)) {
	    isFound = true;
	}

	return isFound;

    }

    private int hash(String inKey) {

	int hashIdx = 0;

	for (int i = 0; i < inKey.length(); i++) {
	    hashIdx = (33 * hashIdx) + inKey.charAt(i);
	}

	return hashIdx % table.length;

    }

    private int find(String inKey) {

	int hashIdx = hash(inKey);
	int origIdx = hashIdx;
	boolean found = false;
	boolean giveup = false;

	while (!found && !giveup) {
	    
	    if (table[hashIdx].state == 0) {
		giveup = true;
	    } else if (table[hashIdx].key.equals(inKey)) {
		found = true;
	    } else {
		hashIdx = (hashIdx + 1) % table.length;
		if (hashIdx == origIdx) {
		    giveup = true;
		}
	    }

	}

	// MODIFICATION: To create the find method, I removed 
	// the exception thrown if the key is not found

	return hashIdx;

    }

    private void reSize(int size) {

	DSAHashEntry<E>[] newArray = new DSAHashEntry[size];
	DSAHashEntry<E>[] tempArray = new DSAHashEntry[table.length];
	String currentKey;
	E currentValue;

	for (int i = 0; i < size; i++) {
	    newArray[i] = new DSAHashEntry<E>();
	}

	for (int i = 0; i < table.length; i++) {
	    tempArray[i] = table[i];
	}

	table = newArray;

	for (int i = 0; i < tempArray.length; i++) {

	    if (tempArray[i].state == 1) {

		currentKey = tempArray[i].key;
		currentValue = tempArray[i].value;
		put(currentKey, currentValue);

	    }

	}

    }

    private int nextPrime(int startVal) {

	int primeVal;
	int rootVal;
	int i;

	boolean isPrime = false;

	if (startVal % 2 == 0) {
	    primeVal = startVal - 1;
	} else {
	    primeVal = startVal;
	}

	do {

	    primeVal += 2;
	    i = 3;
	    isPrime = true;
	    rootVal = (int) Math.sqrt(primeVal);

	    do {

		if (primeVal % i == 0) {
		    isPrime = false;
		} else {
		    i += 2;
		}

	    } while (i <= rootVal && isPrime);

	} while (!isPrime);

	return primeVal;

    }

    public String toString() {

	String str = "";

	for (int i = 0; i < table.length; i++) {

	    if (table[i] != null && table[i].state != -1 && table[i].state != 0) {
		str += table[i].key + "," + table[i].value.toString() + "\n";
	    }

	}

	return str;

    }
    
    // PRIVATE CLASS: DSAHashEntry
    private class DSAHashEntry<E> {

	// CLASS FIELDS
	public String key;
	public E value;
	public int state;

	// DEFAULT CONSTRUCTOR
	public DSAHashEntry() {

	    key = "";
	    value = null;
	    state = 0;

	}

	// ALTERNATE CONSTRUCTOR
	public DSAHashEntry(String inKey, E inValue) {

	    key = inKey;
	    value = inValue;
	    state = 1;

	}

    }

}
