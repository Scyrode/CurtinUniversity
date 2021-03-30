public class DSAStack<E> {
    
    // CLASSFIELDS
    private DSALinkedList<E> stack;

    // DEFAULT CONSTRUCTOR
    public DSAStack() {

	stack = new DSALinkedList<E>();

    }

    public boolean isEmpty() {
	return stack.isEmpty();
    }

    // MUTATORS
    public void push(E value) {
	stack.insertFirst(value);
    }

    public E pop() {

	E topVal = stack.removeFirst();
	return topVal;

    }


    public E top() {
	
	E topVal;

	topVal = stack.peekLast();

	return topVal;

    }

}

