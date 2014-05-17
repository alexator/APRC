package math_operations;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainApp {

	static float result;
	static float num1;
	static float num2;
	static Scanner input;
	static boolean flag_prob = false;

	public static void dispRes(String operator) {

		DecimalFormat format = new DecimalFormat();

		switch (operator) {

		case "+":
			System.out.println(format.format(num1) + " + "
					+ format.format(num2) + " = " + format.format(result));
			result = 0;
			break;
		case "-":
			System.out.println(format.format(num1) + " - "
					+ format.format(num2) + " = " + format.format(result));
			result = 0;
			break;
		case "*":
			System.out.println(format.format(num1) + " * "
					+ format.format(num2) + " = " + format.format(result));
			result = 0;
			break;
		case "/":
			System.out.println(format.format(num1) + " / "
					+ format.format(num2) + " = " + format.format(result));
			result = 0;
			break;
		case "%":
			System.out.println(format.format(num1) + " % "
					+ format.format(num2) + " = " + format.format(result));
			result = 0;
			break;
		default:
			System.err.println("Problem with the method.");
		}

	}

	public static void main(String[] args) {

		System.out.println("Hello user! Please give two numbers!");
		System.out.println("Number one:");
		input = new Scanner(System.in);

		if (input.hasNextFloat()) {
			num1 = input.nextFloat();
			System.out.println("Number two:");
			if (input.hasNextFloat()) {
				num2 = input.nextFloat();
			} else {
				System.err.println("Your second input was invalid!");
				flag_prob = true;
			}
		} else {
			System.err.println("Your first input was invalid!");
			flag_prob = true;
		}

		if (flag_prob == false) {
			System.out.println();
			System.out.println("Your results are:");
			System.out.println();

			result = num1 + num2;
			dispRes("+");
			result = num1 - num2;
			dispRes("-");
			result = num1 * num2;
			dispRes("*");
			result = num1 / num2;
			dispRes("/");
			result = num1 % num2;
			dispRes("%");
		} else {
			System.err.println("Sorry your input was invalid try again!");
		}
	}
}
