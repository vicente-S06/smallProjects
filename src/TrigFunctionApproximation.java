import java.util.Scanner;

public class TrigFunctionApproximation {
	final static double pi = Math.PI;
	static Scanner scan = new Scanner(System.in);

	public static double sine(double rad) {
		double value = 0;
		boolean isNegative = false;
		if (rad < 0) {
			rad *= -1;
			isNegative = true;
		}

		rad %= 2 * pi;
		if (0.00001 >= Math.abs(pi - rad))
			return 0;

		if (rad > pi) {
			rad -= pi;
			rad *= -1;
		}

		for (int i = 1; i <= 7; i++) {
			value += Math.pow(-1, i - 1) * (Math.pow(rad, 2 * i - 1) / factorial(2 * i - 1));
		}
		if (isNegative)
			value *= -1;

		return value;
	}

	public static double cosine(double rad) {
		double value = 0;
		if (rad < 0)
			rad *= -1;

		rad %= 2 * pi;
		if (0.00001 >= Math.abs(pi / 2 - rad))
			return 0;
		if (rad > pi) {
			rad -= 2 * pi;
		}

		for (int i = 0; i <= 6; i++) {
			value += Math.pow(-1, i) * (Math.pow(rad, 2 * i) / factorial(2 * i));
		}
		return value;
	}

	public static double tangent(double rad) {
		try {
			return (sine(rad) / cosine(rad));
		} catch (Exception e) {
			System.out.println("Value for tan(" + rad + ") is undefined");
			return 0;
		}
	}

	public static double cotangent(double rad) {
		try {
			return (cosine(rad) / sine(rad));
		} catch (Exception e) {
			System.out.println("Value for cot(" + rad + ") is undefined");
			return 0;
		}
	}

	public static double secant(double rad) {
		try {
			return (1 / cosine(rad));
		} catch (Exception e) {
			System.out.println("Value for sec(" + rad + ") is undefined");
			return 0;
		}
	}

	public static double cosecant(double rad) {
		try {
			return (1 / sine(rad));
		} catch (Exception e) {
			System.out.println("Value for csc(" + rad + ") is undefined");
			return 0;
		}
	}

	public static int factorial(int n) {
		int val = 1;
		while (n > 1) {
			val *= n;
			n--;
		}
		return val;
	}

	/*
	 * Method: canDoubleCast()
	 * ===========================================================================
	 * Precondition: N/A
	 * ===========================================================================
	 * Postcondition: Returns true if string can be parsed turned into a double.
	 * Otherwise, returns false.
	 */
	public static boolean canDoubleCast(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Method: evaluateInput()
	 * ===========================================================================
	 * Precondition: Parameter is a string in standard radians format. -----------
	 * Ex. -pi/3, 4pi/2, 6pi
	 * ===========================================================================
	 * Postcondition: Returns value of angle as a double.
	 */
	public static double evaluateInput(String str) {
		double angle = 1;
		boolean isNegative = false;

		if (str.charAt(0) == '-') {
			isNegative = true;
			str = str.substring(1);
		}

		int piLoc = str.indexOf("pi");
		if (piLoc > 0) {
			angle = Double.parseDouble(str.substring(0, piLoc)) * pi;
		} else {
			angle = pi;
		}
		str = str.substring(piLoc + 2);

		if (str.length() > 0 && str.charAt(0) == '/') {
			angle /= Double.parseDouble(str.substring(1));
		}

		if (isNegative)
			angle *= -1;

		return angle;
	}

	/*
	 * Method: inputAngle()
	 * ===========================================================================
	 * Precondition: input either a parse-able double OR in standard radians format.
	 * Ex. 42.156 Ex. -pi/3, 4pi/2, 6pi
	 * ===========================================================================
	 * Postcondition: Returns the value of the angle as a double.
	 */
	public static double inputAngle() {
		double angle = 0;
		String temp = scan.next();
		temp.replaceAll(" ", "");
		if (canDoubleCast(temp)) {
			angle = Double.parseDouble(temp);
		} else {
			angle = evaluateInput(temp);
		}

		return angle;
	}

	/*
	 * Method: printOutput()
	 * ===========================================================================
	 * Precondition: 1 <= trigOperator <= 6
	 * ===========================================================================
	 * Postcondition: Prints the value of the corresponding trigonometric function
	 * using the passed angle.
	 */
	public static void printOutput(int trigOperator, double angle) {
		switch (trigOperator) {
		case 1:
			System.out.printf("The value of sin(%f) is %.4f\n", angle, sine(angle));
			break;
		case 2:
			System.out.printf("The value of cos(%f) is %.4f\n", angle, cosine(angle));
			break;
		case 3:
			System.out.printf("The value of tan(%f) is %.4f\n", angle, tangent(angle));
			break;
		case 4:
			System.out.printf("The value of csc(%f) is %.4f\n", angle, cosecant(angle));
			break;
		case 5:
			System.out.printf("The value of sec(%f) is %.4f\n", angle, secant(angle));
			break;
		case 6:
			System.out.printf("The value of cot(%f) is %.4f\n", angle, cotangent(angle));
			break;
		}
	}

	public static void main(String[] args) {
		char repeat = 'n';
		int trigOperator = -1;
		double angle = 0;
		do {
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			// Which Trigonometry Function to Calculate
			while (trigOperator < 1 || trigOperator > 6) {
				System.out.println("Calculate Which Trig Approximation");
				System.out.printf("%11s\t%9s\t%12s\n", "Sine[1]", "Cosine[2]", "Tangent[3]");
				System.out.printf("%11s\t%9s\t%12s\n", "Cosecant[4]", "Secant[5]", "Cotangent[6]");
				trigOperator = scan.nextInt();
			}

			// Angle Input
			System.out.println("Enter angle in radians: ");
			angle = inputAngle();

			// Printing
			printOutput(trigOperator, angle);

			// Reset
			trigOperator = -1;
			System.out.println("Calculate Again? (Y/N)");
			repeat = scan.next().toLowerCase().charAt(0);
		} while (repeat == 'y');
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		scan.close();
	}

}
