public class DSAQueue<E> {
    
    // CLASSFIELDS
    private DSALinkedList<E> queue;

    // DEFAULT CONSTRUCTOR
    public DSAQueue() {
	queue = new DSALinkedList<E>();
    }

    public boolean isEmpty() {
	return queue.isEmpty();
    }

    // MUTATORS
    public void enqueue(E value) {
	queue.insertLast(value);
    }

    public E dequeue() {
	return queue.removeFirst();
    }


    public E peek() {
	
	E topVal;

	if (this.isEmpty()) {
	    throw new ArrayIndexOutOfBoundsException("Stack is empty");
	} else {

	    topVal = queue.peekFirst();
	    return topVal;

	}

    }

}

