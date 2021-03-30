import sun.misc.Queue;

// DSAQueue.java Test Harness

public class DSAQueueTestHarness {
    
    public static void main(String[] args)
    {
	
	Queue<Integer> queue1 = new Queue<Integer>();
	Queue<Integer> queue2 = new Queue<Integer>();

	if (queue1.isEmpty()) {
	    System.out.println("queue1 is empty");

	}

	if (queue2.isEmpty()) {
	     System.out.println("queue2 is empty");

	}

	Integer number = 0;

	try {

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

	    Integer result = queue1.dequeue();

	    System.out.println(result);

	    result = queue2.dequeue();

	    System.out.println(result);

	    result = queue1.dequeue();

	    System.out.println(result);

	    result = queue2.dequeue();

	    System.out.println(result);

	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }

}
