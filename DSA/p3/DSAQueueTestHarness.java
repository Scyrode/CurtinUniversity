// DSAQueue.java Test Harness

public class DSAQueueTestHarness {
    
    public static void main(String[] args)
    {
	
	DSAQueue queue1 = new DSAQueue();
	DSAQueue queue2 = new DSAQueue(100);

	if (queue1.isEmpty()) {
	    System.out.println("queue1 is empty");

	}

	if (queue2.isEmpty()) {
	     System.out.println("queue2 is empty");

	}

	Integer number = 0;

	queue1.enqueue(number);

	number = 1;

	queue1.enqueue(number);

	number = 2;

	queue2.enqueue(number);

	number = 3;

	queue2.enqueue(number);

	if (queue1.isEmpty()) {
	    System.out.println("queue1 is empty");

	}

	if (queue2.isEmpty()) {
	     System.out.println("queue2 is empty");

	}


	int count = queue1.getCount();

	System.out.println(count);
	
	count = queue2.getCount();

	System.out.println(count);



	int result = (int) queue1.dequeue();

	System.out.println(result);

	result = (int) queue2.dequeue();

	System.out.println(result);

	result = (int) queue1.dequeue();

	System.out.println(result);

	result = (int) queue2.dequeue();

	System.out.println(result);


	if (!(queue1.isFull())) {
	    System.out.println("queue1 is not full");

	}

	if (queue2.isEmpty()) {
	    System.out.println("queue2 is empty");

	}

	count = queue1.getCount();

	System.out.println(count);
	
	count = queue2.getCount();

	System.out.println(count);
    }

}
