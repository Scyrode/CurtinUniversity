import java.util.*;

public class DSALinkedList<E> implements Iterable<E> {

    private DSAListNode<E> head;
    private DSAListNode<E> tail;

    //DEFAULT CONSTRUCTOR:
    public DSALinkedList() {

	head = null;
	tail = null;

    }

    //MUTATORS:
    public void insertFirst(E newValue) {

	DSAListNode<E> newNd = new DSAListNode<E>(newValue);
	if (isEmpty()) {
	    head = newNd;
	    tail = newNd;
	} else {
	    newNd.setNext(head);
	    head = newNd;
	}

    }

    public void insertLast(E newValue) {
	
	DSAListNode<E> newNd = new DSAListNode<E>(newValue);
	if (isEmpty()) {
	    head = newNd;
	    tail = newNd;
	} else {
	    tail.setNext(newNd);
	    newNd.setPrev(tail);
	    tail = newNd;
	}

    }

    public E removeFirst() {

	E nodeValue;

	if (isEmpty()) {
	    throw new IllegalArgumentException("LinkedList is empty");
	} else if (head.getNext() == null) {
	    
	    nodeValue = head.getValue();
	    head = head.getNext();
	    tail = tail.getNext();
	    
	} else {
	    
	    nodeValue = head.getValue();
	    head = head.getNext();
	    head.setPrev(null);

	}

	return nodeValue;

    }

    public E removeLast() {

	E nodeValue;

	if (isEmpty()) {
	    throw new IllegalArgumentException("LinkedList is empty");
	} else if (head.getNext() == null) {

	    nodeValue = head.getValue();
	    head = null;
	    tail = null;

	} else {

	    nodeValue = tail.getValue();
	    tail = tail.getPrev();
	    tail.setNext(null);

	}

	return nodeValue;

    }


    //ACCESSORS:
    public boolean isEmpty() {

	boolean empty = false;
	if (head == null) {
	    empty = true;
	}

	return empty;

    }

    public E peekFirst() {

	E nodeValue;

	if (isEmpty()) {
	    throw new IllegalArgumentException("LinkedList is empty");
	} else {
	    nodeValue = head.getValue();
	}

	return nodeValue;

    }

    public E peekLast() {

	E nodeValue;

	if (isEmpty()) {
	    throw new IllegalArgumentException("LinkedList is empty");
	} else {
	    nodeValue = tail.getValue();
	}

	return nodeValue;

    }

    //PRIVATE DSALISTNODE:
    private class DSAListNode<E> {

	private E value;
	private DSAListNode<E> next;
	private DSAListNode<E> prev;

	//ALTERNATE CONSTRUCTOR:
	public DSAListNode(E inValue) {
	    
	    if (inValue != null) {
		value = inValue;
	    } else {
		throw new IllegalArgumentException ("invalid imported value");
	    }

	    next = null;
	    prev = null;
	    
	}

	//ACCESSORS:
	public E getValue() {
	    return value;
	}

	public DSAListNode<E> getNext() {
	    return next;
	}

	public DSAListNode<E> getPrev() {
	    return prev;
	}

	//MUTATORS:
	

	public void setNext(DSAListNode<E> newNext) {
	    next = newNext;
	}

	public void setPrev(DSAListNode<E> newPrev) {
	    prev = newPrev;
	}

	/* UNUSED MUTATOR:
	public void setValue(E inValue) {
	    if (inValue == null) {
		throw new IllegalArgumentException("invalid imported value");
	    } else {
		value = inValue;
	    }
	} */

    }

    public Iterator<E> iterator() {
	return new DSALinkedListIterator<E>(this);
    }

    private class DSALinkedListIterator<E> implements Iterator<E> {
	
	private DSALinkedList<E>.DSAListNode<E> iterNext;

	public DSALinkedListIterator(DSALinkedList<E> theList) {
	    iterNext = theList.head;
	}

	//iterator interface implementation:
	public boolean hasNext() { return (iterNext != null); }
	
	public E next() {
	    
	    E value;

	    if (iterNext == null) {
		value = null;
	    } else {
		value = iterNext.getValue();
		iterNext = iterNext.getNext();
	    }

	    return value;

	}

	public void remove() { throw new UnsupportedOperationException("Not supported"); }

    }

}
