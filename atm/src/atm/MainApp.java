package atm;

import java.util.Scanner;

public class MainApp {

	final static int MAX_DEP = 500 * 100;
	final static int MIN_DEP = 100 * 100;
	final static int MAX_WITH = 250 * 100;
	final static int MIN_WITH = 10 * 100;
	static Scanner input;
	static boolean flag_running;
	static int option;
	static int balance;
	static int dep;
	static int wit;
	static int wittemp;

	public static void main(String[] args) {

		balance = 100 * 100;
		flag_running = true;

		System.out.println("Hello and Welcome to your Bank!");
		System.out.println();

		input = new Scanner(System.in);

		do {
			menu();
			if (input.hasNextInt()) {
				option = input.nextInt();
				switch (option) {
				case 1:
					deposit();
					break;
				case 2:
					withdrawal();
					break;
				case 3:
					balance();
					break;
				case 0:
					flag_running = false;
					System.out.println("Bye user!");
					break;
				default:
					System.out.println();
					System.err
							.println("Please make a selection form the above options.");
					System.out.println();
					break;
				}
			} else {
				System.err.println("Invalid command, please try again");
				flag_running = false;
			}
		} while (flag_running);

	}

	public static void menu() {
		System.out.println("Please type the number of your option to proceed");
		System.out.println();
		System.out.println("Options:");
		System.out.println();
		System.out.println("[1]: Deposit");
		System.out.println("[2]: Withdrawal");
		System.out.println("[3]: Balance");
		System.out.println("[0]: Exit");
		System.out.println();
		System.out.println("Your selection:");
	}

	public static void deposit() {
		System.out.println();
		System.out
				.println("Please type the amount of money that you want to depot in pence format:");
		dep = input.nextInt();
		if ((dep >= MIN_DEP) && (dep <= MAX_DEP)) {
			balance = balance + dep;
			System.out.println("Your deposit was: " + dep / 100 + "," + dep
					% 100);
			System.out.println("Your new Balance is: " + balance / 100 + ","
					+ balance % 100);
			System.out.println();
		} else {
			System.err
					.println("Your amount must be between 100 and 500 Pounds");
		}
	}

	public static void withdrawal() {
		System.out.println();
		System.out
				.println("Please type the amount of money that you want to withdrawal:");
		wittemp = input.nextInt();
		wit = wittemp * 100;
		if ((wit >= MIN_WITH) && (wit <= MAX_WITH)) {
			if (wittemp % 10 == 0) {
				if ((balance - wit) > 0) {
					balance = balance - wit;
					System.out.println("Your withdrawal was: " + wit / 100
							+ "," + wit % 100);
					System.out.println("Your new Balance is: " + balance / 100
							+ "," + balance % 100);
					System.out.println();
				} else {
					System.err.println("You can't empty your account");
				}
			} else {
				System.err.println("Your withdrawal must be a multiple of 10");
			}
		} else {
			System.err.println("Your amount must be between 10 and 250 Pounds");
		}
	}

	public static void balance() {
		System.out.println("Your Balance is: " + balance / 100 + "," + balance
				% 100);
	}

}
