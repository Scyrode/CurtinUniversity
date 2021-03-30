// DSAStack.java Test Harness

public class DSAStackTestHarness {
    
    public static void main(String[] args)
    {
	
	DSAStack<Integer> stack1 = new DSAStack<Integer>();
	DSAStack<Integer> stack2 = new DSAStack<Integer>();

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



	Integer result = stack1.pop();

	System.out.println(result);

	result = stack2.pop();

	System.out.println(result);

	result = stack1.pop();

	System.out.println(result);

	result = stack2.pop();

	System.out.println(result);

	if (stack2.isEmpty()) {
	    System.out.println("stack2 is empty");

	}

    }

}
