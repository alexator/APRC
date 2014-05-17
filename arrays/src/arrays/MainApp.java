package arrays;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainApp {

	static int[] myarray = { 2, 5, 9, 2, -1, 2, 22, -2, 10 };
	static int option;
	static Scanner input;
	static boolean flag_running;
	static int c;
	static int sum;
	static double aver;
	static DecimalFormat format;

	public static void main(String[] args) {
		
		format = new DecimalFormat("#.###");
		flag_running = true;
		c = 0;
		sum = 0;
		aver = 0.0;

		System.out.println("This program performs some actions on an array!");
		System.out.println();
		input = new Scanner(System.in);

		while (flag_running) {
			menu();
			System.out.println();
			System.out.println("Please make your selection:");
			if (input.hasNextInt()) {
				option = input.nextInt();
				switch (option) {
				case 1:
					count();
					break;
				case 2:
					sum();
					break;
				case 3:
					average();
					break;
				case 4:
					content();
					break;
				case 0:
					flag_running = false;
					System.out.println("Bye user");
					break;
				default :
					System.err.println("Invalid selection, try again.");
					flag_running = false;
					break;
				}
			} else {
				System.err.println("Invalid input, try again.");
				flag_running = false;
			}
		}
	}

	public static void menu() {
		System.out.println("1: How many 2 there is in the array?");
		System.out.println("2: The sum of the array.");
		System.out.println("3: The average of the array.");
		System.out.println("4: The content of the array.");
		System.out.println("0: Exit program.");
	}

	public static void count() {
		for (int i = 0; i < myarray.length; i++) {
			if (myarray[i] == 2) {
				c = c + 1;
			}
		}
		System.out.println("The number 2 appears " + c + " times in the array");
		System.out.println();
	}

	public static void sum() {
		for (int i = 0; i < myarray.length; i++) {
			sum = sum + myarray[i];
		}
		System.out.println("The sum of the array is " + sum);
		System.out.println();
	}

	public static void average() {
		
		for (int i = 0; i < myarray.length; i++) {
			sum = sum + myarray[i];
		}
		
		aver = (double) sum / myarray.length;
		System.out.println("The average of the array is " + format.format(aver));
		System.out.println();
	}

	public static void content() {
		System.out.println("The content of the array is:");
		for (int i = 0; i < myarray.length; i++) {
			System.out.format("%3d", myarray[i]);
		}
		System.out.println();
		System.out.println();
	}
}
