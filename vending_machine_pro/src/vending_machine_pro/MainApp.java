package vending_machine_pro;

import java.util.Scanner;

public class MainApp {

	static Scanner input;
	static int option;
	static boolean flag_running;
	final static int CHOCOLATE_VALUE = 57;
	final static int CHOCOLATEB_VALUE = 55;
	final static int COLA_VALUE = 71;
	final static int CRISPS_VALUE = 34;
	final static int MONEY = 500;
	final static int ZERO = 0;
	static int two_pounds;
	static int total;
	static int pounds;
	static int fifties;
	static int twenties;
	static int tens;
	static int fives;
	static int twos;
	static int ones;
	static int choc_count;
	static int cola_count;
	static int crisp_count;
	static String item;

	public static void main(String[] args) {

		choc_count = 0;
		cola_count = 0;
		crisp_count = 0;
		flag_running = true;
		total = 0;

		System.out.println("Hello Customer, this is a Vending Machine");
		System.out.println();

		input = new Scanner(System.in);

		while (flag_running) {
			menu();
			if (input.hasNextInt()) {
				option = input.nextInt();
				switch (option) {
				case 1:
					buy("Chocolate", CHOCOLATE_VALUE);
					break;
				case 2:
					buy("Cola", COLA_VALUE);
					break;
				case 3:
					buy("Crisps", CRISPS_VALUE);
					break;
				case 4:
					dispReceipt(choc_count, cola_count, crisp_count);
					flag_running = false;
					break;
				case 0:
					flag_running = false;
					getLowestCoins(ZERO);
					System.out.println("Bye user!");
					break;
				default:
					flag_running = false;
					getLowestCoins(MONEY);
					System.out.println();
					System.err.println("No returns for invalid options.");
					break;
				}
			} else {
				flag_running = false;
				getLowestCoins(MONEY);
				System.out.println();
				System.err.println("No returns for invalid options.");
			}
		}
	}

	public static void menu() {
		System.out.println("Please type the number of your option to proceed");
		System.out.println();
		System.out.println("Options:");
		System.out.println();
		System.out.println("[1]: Chocolate 0.57p");
		System.out.println("[2]: Cola 0.71p");
		System.out.println("[3]: Crisps 0.34p");
		System.out.println("[4]: Complete Sale.");
		System.out.println("[0]: Exit");
		System.out.println();
		System.out.println("Your selection:");
	}

	public static void buy(String item, int val1) {

		if ((MONEY - total - val1) >= 0) {
			virtualReceipt(val1, option);
			System.out.println();
			System.out.println("You added the item: " + item + " which costs "
					+ (double) val1 / 100 + " pounds and now you have left "
					+ (double) (MONEY - total) / 100 + " pounds");
			System.out.println();
		} else {
			System.out.println();
			System.err.println("Not enough money to add this item");
			System.out.println();
		}
	}

	public static void virtualReceipt(int val2, int op) {

		total = total + val2;

		switch (op) {
		case 1:
			choc_count = choc_count + 1;
			break;
		case 2:
			cola_count = cola_count + 1;
			break;
		case 3:
			crisp_count = crisp_count + 1;
			break;
		}
	}

	public static void dispReceipt(int c, int co, int cr) {

		System.out.println();
		System.out.println("***   Reciept   ***");
		System.out.println("===================");
		System.out.println();
		System.out.println("You took:");
		if (c > 0) {
			System.out.println(c + ": Chocolate");
		}
		if (co > 0) {
			System.out.println(co + ": Cola");
		}
		if (cr > 0) {
			System.out.println(cr + ": Crisps");
		}
		System.out.println();
		System.out.println("Your total was " + (double) total / 100
				+ " pounds and your change are:");
		getLowestCoins(total);
	}

	public static void getLowestCoins(int value) {

		int chan;
		chan = MONEY - value;

		System.out.println();

		two_pounds = chan / 200;
		if (two_pounds  > 0) {
			chan = chan % 200;
			System.out.format("%-4s%3s%2d", "2P", ":", two_pounds);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "2P", ":", " 0");
			System.out.println();
		}

		pounds = chan / 100;
		if (pounds > 0) {
			chan = chan % 100;
			System.out.format("%-4s%3s%2d", "1P", ":", pounds);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "1P", ":", " 0");
			System.out.println();
		}

		fifties = chan / 50;
		if (fifties > 0) {
			chan = chan % 50;
			System.out.format("%-4s%3s%2d", "50p", ":", fifties);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "50p", ":", " 0");
			System.out.println();
		}

		twenties = chan / 20;
		if (twenties > 0) {
			chan = chan % 20;
			System.out.format("%-4s%3s%2d", "20p", ":", twenties);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "20p", ":", " 0");
			System.out.println();
		}

		tens = chan / 10;
		if (tens > 0) {
			chan = chan % 10;
			System.out.format("%-4s%3s%2d", "10p", ":", tens);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "10p", ":", " 0");
			System.out.println();
		}

		fives = chan / 5;
		if (fives > 0) {
			chan = chan % 5;
			System.out.format("%-4s%3s%2d", "5p", ":", fives);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "5p", ":", " 0");
			System.out.println();
		}

		twos = chan / 2;
		if (twos > 0) {
			chan = chan % 2;
			System.out.format("%-4s%3s%2d", "2p", ":", twos);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "2p", ":", " 0");
			System.out.println();
		}

		ones = chan / 1;
		if (ones > 0) {
			chan = chan % 1;
			System.out.format("%-4s%3s%2d", "1p", ":", ones);
			System.out.println();
		} else {
			System.out.format("%-4s%3s%s", "1p", ":", " 0");
			System.out.println();
		}

		System.out.println();
		System.out.println("=========================");
		System.out.println("*     TOTAL:  " + (double) (MONEY - total) / 100
				+ "      *");
		System.out.println("=========================");
	}

}
