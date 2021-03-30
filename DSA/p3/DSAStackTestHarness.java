// DSAStack.java Test Harness

public class DSAStackTestHarness {
    
    public static void main(String[] args)
    {
	
	DSAStack stack1 = new DSAStack();
	DSAStack stack2 = new DSAStack(100);

	if (stack1.isEmpty()) {
	    System.out.println("stack1 is empty");

	}

	if (stack2.isEmpty()) {
	     System.out.println("stack2 is empty");

	}

	Integer number = 0;

	stack1.push(number);

	number = 1;

	stack1.push(number);

	number = 2;

	stack2.push(number);

	number = 3;

	stack2.push(number);

	if (stack1.isEmpty()) {
	    System.out.println("stack1 is empty");

	}

	if (stack2.isEmpty()) {
	     System.out.println("stack2 is empty");

	}


	int count = stack1.getCount();

	System.out.println(count);
	
	count = stack2.getCount();

	System.out.println(count);



	int result = (int) stack1.pop();

	System.out.println(result);

	result = (int) stack2.pop();

	System.out.println(result);

	result = (int) stack1.pop();

	System.out.println(result);

	result = (int) stack2.pop();

	System.out.println(result);


	if (!(stack1.isFull())) {
	    System.out.println("stack1 is not full");

	}

	if (stack2.isEmpty()) {
	    System.out.println("stack2 is empty");

	}

	count = stack1.getCount();

	System.out.println(count);
	
	count = stack2.getCount();

	System.out.println(count);
    }

}
