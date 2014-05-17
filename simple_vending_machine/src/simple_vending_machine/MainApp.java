package simple_vending_machine;

import java.util.Scanner;

public class MainApp {

	static Scanner input;
	static int option;
	static boolean flag_running;
	final static int CHOCOLATE_VALUE = 57;
	final static int CHOCOLATEB_VALUE = 55;
	final static int COLA_VALUE = 71;
	final static int CRISPS_VALUE = 31;
	final static int MONEY = 100;
	final static int ZERO = 0;
	static int pounds;
	static int fifties;
	static int twenties;
	static int tens;
	static int fives;
	static int twos;
	static int ones;

	public static void main(String[] args) {

		flag_running = true;

		System.out.println("Hello Customer, this is a Vending Machine");
		System.out.println();

		input = new Scanner(System.in);

		menu();
		if (input.hasNextInt()) {
			option = input.nextInt();
			switch (option) {
			case 1:
				getLowestCoins(CHOCOLATE_VALUE);
				break;
			case 2:
				getLowestCoins(CHOCOLATEB_VALUE);
				break;
			case 3:
				getLowestCoins(COLA_VALUE);
				break;
			case 4:
				getLowestCoins(CRISPS_VALUE);
				break;
			case 0:
				getLowestCoins(ZERO);
				System.out.println("Bye user!");
				break;
			default:
				getLowestCoins(MONEY);
				System.out.println();
				System.err.println("No returns for invalid options.");
				break;
			}
		} else {
			getLowestCoins(MONEY);
			System.out.println();
			System.err.println("No returns for invalid options.");
		}
	}

	public static void menu() {
		System.out.println("Please type the number of your option to proceed");
		System.out.println();
		System.out.println("Options:");
		System.out.println();
		System.out.println("[1]: Chocolate 0.57p");
		System.out.println("[2]: Chocolate 0.55p");
		System.out.println("[3]: Cola 0.71p");
		System.out.println("[4]: Crisps 0.34p");
		System.out.println("[0]: Exit");
		System.out.println();
		System.out.println("Your selection:");
	}

	public static void getLowestCoins(int value) {

		int val;
		int chan;
		val = value;
		chan = MONEY - val;
		
		System.out.println();
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
	}

}
