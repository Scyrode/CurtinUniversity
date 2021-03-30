public class DSAQueue {
    
    // CLASSFIELDS
    private Object[] queue;
    private int count;

    // CLASS CONSTANTS
    private final int DEFAULT_CAPACITY = 100;

    // DEFAULT CONSTRUCTOR
    public DSAQueue() {

	queue = new Object[DEFAULT_CAPACITY];
	count = 0;

    }

    // ALTERNATE CONSTRUCTOR
    public DSAQueue(int maxCapacity) {
	
	queue = new Object[maxCapacity];
	count = 0;

    }

    // ACCESSORS
    public int getCount() {

	return count;

    }

    public boolean isEmpty() {

	boolean isEmpty;
	if (count == 0) {
	    isEmpty = true;
	} else {
	    isEmpty = false;
	}

	return isEmpty;

    }

    public boolean isFull() {
	
	boolean isFull;
	if (count == queue.length) {
	    isFull = true;
	} else {
	    isFull = false;
	}

	return isFull;

    }

    // MUTATORS
    public void enqueue(Object value) {

	if (this.isFull()) {
	    throw new ArrayIndexOutOfBoundsException("Stack is already full");
	} else {
	    queue[count] = (Object) value;
	    count++;
	}

    }

    public Object dequeue() {

	Object topVal = (Object) peek();

	for (int i = 0; i < this.getCount() -1; i++) {
	    queue[i] = queue[i + 1];
	}

	count--;

	return topVal;

    }


    public Object peek() {
	
	Object topVal;

	if (this.isEmpty()) {
	    throw new ArrayIndexOutOfBoundsException("Stack is empty");
	} else {

	    topVal = queue[0];
	    return topVal;

	}

    }

}

