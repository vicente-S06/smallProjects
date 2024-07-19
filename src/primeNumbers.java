import java.util.Scanner;

//Vicente Santos-Linares
public class primeNumbers {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many prime numbers to print?");
		int primesNeeded = scan.nextInt();
		int count = 0;
		int storedNum = 2;
		
		while(count < primesNeeded) {
			if(isPrime(storedNum)) {
				count++;
				System.out.print(storedNum + ", ");
			}
			storedNum++;
		}
		
	}
	
	public static boolean isPrime(int checkingNum) {
		
		for(int x = checkingNum - 1; x > 1; x--) {
			if(checkingNum % x == 0) {
				return false;
			}
		}
		
		return true;
	}

}
