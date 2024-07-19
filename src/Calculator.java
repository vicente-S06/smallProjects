import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int repeat = 1;

		while (repeat == 1) {

			System.out.println("============================");
			System.out.println("Enter First Number: ");
			double numA = scan.nextDouble();
			System.out.println("Enter Second Number: ");
			double numB = scan.nextDouble();
			System.out.println("Enter Operation (+,-,*,/): ");
			char operation = scan.next().charAt(0);

			switch (operation) {

				case '+':
					System.out.println("\n" + numA + " + " + numB + " = " + (numA + numB));
					break;

				case '-':
					System.out.println("\n" + numA + " - " + numB + " = " + (numA - numB));
					break;

				case '*':
					System.out.println("\n" + numA + " * " + numB + " = " + (numA * numB));
					break;

				case '/':
					System.out.println("\n" + numA + " / " + numB + " = " + (numA / numB));
					break;
				default:
					System.out.println("A valid operation was not entered.");
					break;

			}
			System.out.println("============================");
			System.out.println("Start Another Operation?\n\tyes = 1\n\tno  = 0");
			repeat = scan.nextInt();
		}

	}

}
