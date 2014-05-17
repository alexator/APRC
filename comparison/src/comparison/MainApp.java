package comparison;

import java.util.Scanner;

public class MainApp {

	static int num1;
	static int num2;
	static int num3;
	static int temp1;
	static int temp2;
	static int max;
	static int min;
	static boolean flag_prob;

	static Scanner input;

	public static void main(String[] args) {

		flag_prob = false;

		System.out
				.println("Hello user! This program compares three numbers and give you as a result the largest and the smallest!");
		System.out.println("Please give three integer numbers.");

		input = new Scanner(System.in);

		System.out.println("Number one:");
		if (input.hasNextInt()) {
			num1 = input.nextInt();
			System.out.println("Number two:");
			if (input.hasNextInt()) {
				num2 = input.nextInt();
				System.out.println("Number three:");
				if (input.hasNextInt()) {
					num3 = input.nextInt();
				} else {
					System.err.println("Your input was invalid");
					flag_prob = true;
				}
			} else {
				System.err.println("Your input was invalid");
				flag_prob = true;
			}
		} else {
			System.err.println("Your input was invalid");
			flag_prob = true;
		}

		if (!flag_prob) {

			if (num1 > num2) {
				temp1 = num1;
				temp2 = num2;
			} else {
				temp1 = num2;
				temp2 = num1;
			}
			if (temp1 > num3) {
				max = temp1;
			} else {
				max = num3;
			}
			if (temp2 > num3) {
				min = num3;
			} else {
				min = temp2;
			}

			System.out.println("The largest number is " + max
					+ " and the smallest is " + min);

		} else {
			System.err.println("Your inputs was invalid, try again");
		}

	}

}
