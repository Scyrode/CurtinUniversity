public class DSAStack {
    
    // CLASSFIELDS
    private Object[] stack;
    private int count;

    // CLASS CONSTANTS
    private final int DEFAULT_CAPACITY = 100;

    // DEFAULT CONSTRUCTOR
    public DSAStack() {

	stack = new Object[DEFAULT_CAPACITY];
	count = 0;

    }

    // ALTERNATE CONSTRUCTOR
    public DSAStack(int maxCapacity) {
	
	stack = new Object[maxCapacity];
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
	if (count == stack.length) {
	    isFull = true;
	} else {
	    isFull = false;
	}

	return isFull;

    }

    // MUTATORS
    public void push(Object value) {

	if (this.isFull()) {
	    throw new ArrayIndexOutOfBoundsException("Stack is already full");
	} else {
	    stack[count] = (Object) value;
	    count++;
	}

    }

    public Object pop() {

	Object topVal = (Object) top();
	count--;

	return topVal;

    }


    public Object top() {
	
	Object topVal;

	if (this.isEmpty()) {
	    throw new ArrayIndexOutOfBoundsException("Stack is empty");
	} else {
	    topVal = stack[count - 1];
	}

	return topVal;

    }

}

