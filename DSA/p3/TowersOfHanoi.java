import java.util.*;

public class TowersOfHanoi {
    
    public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter the number of disks:");
	int n = sc.nextInt();

	System.out.println("Enter the source location (between 1-3):");
	int src = sc.nextInt();

	System.out.println("Enter the destination (between 1-3)");
	int dest = sc.nextInt();

	towers(n, src, dest);

    }

    public static void towers(int n, int src, int dest) {
	
	if (n == 1) {
	    moveDisk(src, dest);
	} else {
	    
	    int temp = 6 - src - dest;
	    towers(n-1, src, temp);

	    moveDisk(src, dest);
	    towers(n-1, temp, dest);

	}

    }

    public static void moveDisk(int src, int dest) {
	
	System.out.println("Moving top disk from peg " + src + " to peg " + dest);

    }

}
