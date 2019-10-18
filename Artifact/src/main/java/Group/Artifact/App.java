package Group.Artifact;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Program outups all prime numbers, smaller than x.");
		System.out.println("Enter x:");
		int n = input.nextInt();
		int max = squareRoot.floorSqrt(n);
		
		boolean[] primeNumbers = new boolean[n];
		for(int i=0;i<n;i++) {
			primeNumbers[i] = true;
		}
		
		for(int i=2;i<max;i++) {
			if (primeNumbers[i]) {
				for(int j=2*i;j<n;j=j+i)
					primeNumbers[j] = false;
			}
		}
		
		for(int i=2;i<n;i++)
			if (primeNumbers[i])
				System.out.println(i);

	}
}
