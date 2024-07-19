import java.util.ArrayList;
import java.util.Scanner;

//Vicente Santos-Linares
public class AdvancedCalculator {

	/*
	 * Post-condition: Checks if a string is an operation
	 */
	public static boolean hasOperation(String str, int index) {
		return str.charAt(index) == '+' || str.charAt(index) == '-' || str.charAt(index) == '*'
				|| str.charAt(index) == '/';

	}

	public static boolean hasMultiOrDiv(ArrayList<Character> symbols) {
		for (Character c : symbols) {
			if (c == '*') {
				return true;
			} else if (c == '/') {
				return true;
			}
		}

		return false;
	}

	public static boolean hasAddOrSub(ArrayList<Character> symbols) {
		for (Character c : symbols) {
			if (c == '+') {
				return true;
			} else if (c == '-') {
				return true;
			}
		}

		return false;
	}

	public static void performMultiplication(ArrayList<Double> numbers, int index) {
		numbers.set(index, numbers.get(index) * numbers.remove(index + 1));
	}

	public static void performDivision(ArrayList<Double> numbers, int index) {
		numbers.set(index, numbers.get(index) / numbers.remove(index + 1));
	}

	public static void performAddition(ArrayList<Double> numbers, int index) {
		numbers.set(index, numbers.get(index) + numbers.remove(index + 1));
	}

	public static void performSubtraction(ArrayList<Double> numbers, int index) {
		numbers.set(index, numbers.get(index) - numbers.remove(index + 1));
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String operation;
		char repeatChar = 'y';
		while (repeatChar == 'y') {
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("\tEnter Entire Operation:");
			System.out.println("\t(Must Contain Decimal)");
			System.out.print("\t ");

			do {
				operation = scan.nextLine();
			} while (operation.length() == 0);
			String noSpacesOperation = "";

			// Removes Spaces from Operation
			for (int x = 0; x < operation.length(); x++) {
				if (!operation.substring(x, x + 1).equals(" "))
					noSpacesOperation = noSpacesOperation.concat(operation.substring(x, x + 1));
			}

			// Adds numbers in operation to an ArrayList
			ArrayList<Double> operationNumbers = new ArrayList<Double>();
			String storedPart = "";
			for (int x = 0; x < noSpacesOperation.length(); x++) {

				if (x > 1 && hasOperation(noSpacesOperation, x - 1)
						&& noSpacesOperation.substring(x, x + 1).equals("-")) {
					storedPart = storedPart.concat(noSpacesOperation.substring(x, x + 1));
				} else if (hasOperation(noSpacesOperation, x) && x != 0) {
					operationNumbers.add(Double.parseDouble(storedPart));
					storedPart = "";
				} else {
					storedPart = storedPart.concat(noSpacesOperation.substring(x, x + 1));
					if (x == noSpacesOperation.length() - 1) {
						operationNumbers.add(Double.parseDouble(storedPart));
					}
				}
			}

			// Test Cases
			// 3.25 * -92.52/3.5+22.0
			// 3.25*-92.52/3.5+22.0-32.5

			// Extracts Operation Symbols from String
			ArrayList<Character> operationSymbols = new ArrayList<Character>();
			int storedIndex = 0;
			storedPart = "";
			for (int x = 0; x < operationNumbers.size() - 1; x++) {
				storedPart = "" + operationNumbers.get(x);
				storedIndex = noSpacesOperation.indexOf(storedPart, storedIndex) + storedPart.length();
				operationSymbols.add(noSpacesOperation.substring(storedIndex, storedIndex + 1).charAt(0));
			}

			while (hasMultiOrDiv(operationSymbols)) {
				for (int i = 0; i < operationSymbols.size(); i++) {
					if (operationSymbols.get(i) == '*') {
						performMultiplication(operationNumbers, i);
						operationSymbols.remove(i);

						i += operationSymbols.size();

					} else if (operationSymbols.get(i) == '/') {
						performDivision(operationNumbers, i);
						operationSymbols.remove(i);

						i += operationSymbols.size();

					}
				}
			}

			while (hasAddOrSub(operationSymbols)) {
				for (int i = 0; i < operationSymbols.size(); i++) {
					if (operationSymbols.get(i) == '+') {
						performAddition(operationNumbers, i);
						operationSymbols.remove(i);
						i += operationSymbols.size();
					} else if (operationSymbols.get(i) == '-') {
						performSubtraction(operationNumbers, i);
						operationSymbols.remove(i);
						i += operationSymbols.size();
					}
				}
			}
			// Test Cases:
			// 3.25*-92.52/3.5+22.0-32.5
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("\t    The Answer is: \n\t    " + operationNumbers.get(0));
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("\tStart Another Operation?");
			System.out.println("\t\t(y/n)");
			System.out.print("\t\t  ");
			operation = "";
			repeatChar = 'n';
			repeatChar = scan.next().charAt(0);
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println("\t\tGoodbye!");
		System.exit(0);
	}
}
