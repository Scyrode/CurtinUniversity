public class EquationSolverTestHarness {
    
    public static void main(String[] args) {

	Double answer;
	String equation = "5 + 3 - 4 * ( 2 * 3 + 3 ) - 4 / ( 2 + 4 )";

	answer = EquationSolver.solve(equation);

	System.out.println(Double.valueOf(answer));

    }

}
