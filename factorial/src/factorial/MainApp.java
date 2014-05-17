package factorial;

import java.util.Scanner;

public class MainApp {

	static Scanner input;
	static int number;
	static int fact;

	public static void main(String[] args) {
		System.out
				.println("Hello user! This program calculates the fatorial of a given number!");
		System.out.println("Please give the integer number that you want.");
		System.out.println("Number:");

		input = new Scanner(System.in);

		if (input.hasNextInt()) {
			number = input.nextInt();
			factorial(number);
			System.out.println("The factorial of " + number + " is " + fact);
		} else {
			System.err.println("Your input was invalid, please try again!");
		}

	}

	public static int factorial(int n) {

		if (n == 0) {
			fact = 1;
		} else {
			fact = n * factorial(n - 1);
		}

		return fact;
	}
}
