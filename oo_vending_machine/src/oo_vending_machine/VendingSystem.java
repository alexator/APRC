package oo_vending_machine;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingSystem {

	private static VendingItem chocolate = new VendingItem("Chocolate", 57, 5);
	private static VendingItem cola = new VendingItem("Cola", 71, 2);
	private static VendingItem crisps = new VendingItem("Crisps", 34, 4);
	private static VendingItem curryPot = new VendingItem("Curry Pot", 149, 2);
	final private static int MONEY = 500;
	private static int change = MONEY;
	private static int cost = 0;
	private static ArrayList<String> receipt = new ArrayList<String>();
	static int two_pounds;
	static int pounds;
	static int fifties;
	static int twenties;
	static int tens;
	static int fives;
	static int twos;
	static int ones;
	static Scanner input;
	static int option;
	static boolean flag_running;

	public static void main(String[] args) {

		flag_running = true;

		doRun();
	}

	private static void doRun() {

		doHeader();
		while (flag_running) {
			doMenu();
			getInput();
		}
		System.out.println();
		System.out
				.println("=================     Bye User     ================");
	}

	private static void doHeader() {

		System.out
				.println("===================================================");
		System.out
				.println("*~~~~~~~~~~~~~~~~ Vending Machine ~~~~~~~~~~~~~~~~*");
		System.out
				.println("===================================================");
		System.out.println();
	}

	private static void doMenu() {

		System.out.println("Please type the number of your option to proceed");
		System.out.println();
		System.out.println("Options:");
		System.out.println();
		System.out.println("[1]: " + chocolate.toString());
		System.out.println("[2]: " + cola.toString());
		System.out.println("[3]: " + crisps.toString());
		System.out.println("[4]: " + curryPot.toString());
		System.out.println("[5]: Add an extra pound");
		System.out.println("[6]: Complete Sale.");
		System.out.println("[0]: Exit");
		System.out.println();
		System.out.println("Your selection:");
	}

	private static void getInput() {

		input = new Scanner(System.in);

		if (input.hasNextInt()) {
			option = input.nextInt();

			switch (option) {

			case 1:
				buyItem(chocolate);
				break;
			case 2:
				buyItem(cola);
				break;
			case 3:
				buyItem(crisps);
				break;
			case 4:
				buyItem(curryPot);
				break;
			case 5:
				insertPound();
				break;
			case 6:
				completeSale();
				flag_running = false;
				break;
			case 0:
				noSale();
				flag_running = false;
				break;
			default:
				System.err.println("Invalid Option");
			}
		} else {
			flag_running = false;
			System.out.println();
			System.err.println("No refund for invalid options.");
			getLowestCoins(0);
		}
	}

	private static void addToReceipt(String n, int va) {

		receipt.add(String.format("%-12s%-10s%-10s", n, convertPounds(va),
				convertPounds(cost)));

		System.out.println("Your selection was: " + n + " ,which costs "
				+ convertPounds(va) + " pounds and your change are "
				+ convertPounds(change) + " pounds.");
		System.out.println();
	}

	private static String convertPounds(int amount) {

		String conv = String.format("%d.%d", amount / 100, amount % 100);
		return conv;
	}

	private static void displayReceipt() {

		System.out.println();
		System.out.format("%-12s%-10s%-10s", "ITEM", "COST", "Total");
		System.out.println();
		System.out.println("=============================");
		for (int i = 0; i < receipt.size(); i++) {
			System.out.println(receipt.get(i));
		}
		System.out.println();
		System.out.println("Your total was " + convertPounds(cost)
				+ " pounds and your change are " + convertPounds(change)
				+ " pounds.");
		System.out.println();
	}

	private static void getLowestCoins(int value) {

		System.out.println();

		two_pounds = value / 200;
		if (two_pounds > 0) {
			value = value % 200;
			System.out.format("%-4s%3s%2d", "2P", ":", two_pounds);
			System.out.println();
		} else {
			two_pounds = 0;
			System.out.format("%-4s%3s%s", "2P", ":", " 0");
			System.out.println();
		}

		pounds = value / 100;
		if (pounds > 0) {
			value = value % 100;
			System.out.format("%-4s%3s%2d", "1P", ":", pounds);
			System.out.println();
		} else {
			pounds = 0;
			System.out.format("%-4s%3s%s", "1P", ":", " 0");
			System.out.println();
		}

		fifties = value / 50;
		if (fifties > 0) {
			value = value % 50;
			System.out.format("%-4s%3s%2d", "50p", ":", fifties);
			System.out.println();
		} else {
			fifties = 0;
			System.out.format("%-4s%3s%s", "50p", ":", " 0");
			System.out.println();
		}

		twenties = value / 20;
		if (twenties > 0) {
			value = value % 20;
			System.out.format("%-4s%3s%2d", "20p", ":", twenties);
			System.out.println();
		} else {
			twenties = 0;
			System.out.format("%-4s%3s%s", "20p", ":", " 0");
			System.out.println();
		}

		tens = value / 10;
		if (tens > 0) {
			value = value % 10;
			System.out.format("%-4s%3s%2d", "10p", ":", tens);
			System.out.println();
		} else {
			tens = 0;
			System.out.format("%-4s%3s%s", "10p", ":", " 0");
			System.out.println();
		}

		fives = value / 5;
		if (fives > 0) {
			value = value % 5;
			System.out.format("%-4s%3s%2d", "5p", ":", fives);
			System.out.println();
		} else {
			fives = 0;
			System.out.format("%-4s%3s%s", "5p", ":", " 0");
			System.out.println();
		}

		twos = value / 2;
		if (twos > 0) {
			value = value % 2;
			System.out.format("%-4s%3s%2d", "2p", ":", twos);
			System.out.println();
		} else {
			twos = 0;
			System.out.format("%-4s%3s%s", "2p", ":", " 0");
			System.out.println();
		}

		ones = value / 1;
		if (ones > 0) {
			value = value % 1;
			System.out.format("%-4s%3s%2d", "1p", ":", ones);
			System.out.println();
		} else {
			ones = 0;
			System.out.format("%-4s%3s%s", "1p", ":", " 0");
			System.out.println();
		}
	}

	private static void doSale(int va) {

		cost = cost + va;
		change = change - va;
	}

	private static void completeSale() {

		displayReceipt();
		getLowestCoins(change);
	}

	private static void noSale() {

		System.out.println("Your money was refund");
		getLowestCoins(MONEY);

	}

	private static void buyItem(VendingItem i) {

		if (i.stockLeft()) {
			if (change >= i.getItemPrice()) {
				i.buyItem();
				doSale(i.getItemPrice());
				addToReceipt(i.getItemName(), i.getItemPrice());
			} else {
				System.err.println("Not enough money!");
			}
		} else {
			System.err.println("Out of stock");
		}
	}

	private static void insertPound() {
		change = change + 100;
		System.out.println("Your new balance is " +convertPounds(change) + "pounds.");
	}
}
