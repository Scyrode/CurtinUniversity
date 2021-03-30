public class EquationSolver {
    
    public static double solve(String equation) {
	
	double answer;
	DSAQueue queue = new DSAQueue();

	queue = parseInfixToPostfix(equation);

	answer = evaluatePostfix(queue);

	return answer;

    }

    private static DSAQueue parseInfixToPostfix(String equation) {
	    
	DSAStack opStack = new DSAStack();
	DSAQueue postfix = new DSAQueue();
	
	String delims = " ";
	String[] tokens = equation.split(delims);
	int tokenCount = tokens.length;
	int i = 0;

	String term;

	while (i < tokenCount) {
	    
	    term = tokens[i];

	    if (term.charAt(0) == '(') {

		opStack.push('(');

	    } else if (term.charAt(0) == ')') {

		while (opStack.top().toString().charAt(0) != '(') {
		    postfix.enqueue(opStack.pop());
		}
		opStack.pop();
		
	    } else if ((term.charAt(0) == '+') || (term.charAt(0) == '-') || (term.charAt(0) == '*') || (term.charAt(0) == '/')) {
		
		while ((!opStack.isEmpty()) && (opStack.top().toString().charAt(0) != '(') && (precedenceOf(opStack.top().toString().charAt(0)) >= precedenceOf(term.charAt(0)))) {
		    
		    postfix.enqueue(opStack.pop());

		}
		opStack.push(term);

	    } else {
		Double operand = Double.valueOf(term);
		postfix.enqueue(operand);
	    }
	    i++;

	}

	while (!opStack.isEmpty()) {
	    postfix.enqueue(opStack.pop());
	}

	/*TEST: print content of queue
	String test;
	int count = postfix.getCount();
	for (int ii = 0; ii < count; ii++) {

	    test = postfix.dequeue().toString();
	    System.out.println(test);

	} */

	return postfix;

    }

    private static double evaluatePostfix(DSAQueue postfixQueue) {

	DSAStack opStack = new DSAStack();
	int i = 0;
	char  op;
	Double result = 0.0;
	int queueCount = postfixQueue.getCount();

	while (i < queueCount) {
		
	    Object item = postfixQueue.dequeue();

	    if (item instanceof Double) {
		opStack.push((Double) item);
	    } else {
		op = item.toString().charAt(0);

		Double op2 = (Double) opStack.pop();
		Double op1 = (Double) opStack.pop();

		result = executeOperation(op, op1, op2);
		    
		opStack.push(result);
	    }

	    i++;
	}

	return (double) opStack.pop();

    }

    private static int precedenceOf(char theOperator) {

	int output;

	if ((theOperator == '+') || (theOperator == '-')) {
	    output = 1;
	} else {
	    output = 2;
	}

	return output;

    }

    private static double executeOperation(char op, double op1, double op2) {

	Double result = 0.0;

	if (op == '+') {
	    result = (Double) op1 + op2;
	} else if (op == '-') {
	    result = (Double) op1 - op2;
	} else if (op == '*') {
	    result = (Double) op1 * op2;
	} else if (op == '/') {
	    result = (Double) op1 / op2;
	}

	return result;

    }

}
