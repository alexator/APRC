package oo_atm;

import java.util.Scanner;

public class AtmSystem {

	private static int balInPen = 100 * 100;
	private static Account user1 = new Account(balInPen);

	final static int MAX_DEP = 500 * 100;
	final static int MIN_DEP = 100 * 100;
	final static int MAX_WITH = 250 * 100;
	final static int MIN_WITH = 10 * 100;
	static int dep;
	static int wit;

	private static boolean flag_running;
	private static Scanner input;
	private static int option;

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
				.println(" ++++++++++++++++    A.T.M    +++++++++++++++++++  ");
		System.out
				.println("===================================================");
		System.out.println();
	}

	private static void doMenu() {

		System.out.println("Please type the number of your option to proceed");
		System.out.println();
		System.out.println("Options:");
		System.out.println();
		System.out.println("[1]: Deposit");
		System.out.println("[2]: Withdrawal");
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
				doDeposit(user1);
				break;
			case 2:
				doWithdraw(user1);
				break;
			case 0:
				flag_running = false;
				break;
			default:
				System.err.println("Invalid Option");
			}
		} else {
			flag_running = false;
			System.out.println();
		}
	}

	private static void doDeposit(Account u) {
		getDeposit();
		if (checkRangeDep(dep)) {
			u.deposit(dep);
			dispDep(u);
		} else {
			System.err
					.println("Your amount must be between 100 and 500 Pounds");
		}
	}

	private static void getDeposit() {
		System.out.println();
		System.out
				.println("Please type the amount of money that you want to depot in pence format:");
		dep = input.nextInt();
		dep = dep * 100;
	}

	private static void dispDep(Account u) {

		System.out.println("Your deposit was: " + convertPounds(dep));
		System.out.println("Your new Balance is: "
				+ convertPounds(u.getBalance()));
		System.out.println();

	}

	private static boolean checkRangeDep(int d) {
		if ((d >= MIN_DEP) && (d <= MAX_DEP)) {
			return true;
		} else {
			return false;
		}
	}

	private static void doWithdraw(Account u) {
		getWithdraw();
		if (checkRangeWit(wit)) {
			if (checkMultWit(wit)) {
				if (noZeroAcc(u)) {
					u.withdraw(wit);
					dispWit(u);
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

	private static void getWithdraw() {
		System.out.println();
		System.out
				.println("Please type the amount of money that you want to withdrawal:");
		wit = input.nextInt();
		wit = wit * 100;
	}

	private static boolean checkRangeWit(int w) {
		if ((w >= MIN_WITH) && (w <= MAX_WITH)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkMultWit(int w) {
		if ((w / 100) % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean noZeroAcc(Account u) {
		if ((u.getBalance() - wit) > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static void dispWit(Account u) {

		System.out.println("Your withdrawal was: " + convertPounds(wit));
		System.out.println("Your new Balance is: "
				+ convertPounds(u.getBalance()));
		System.out.println();

	}

	private static String convertPounds(int amount) {

		String conv = String.format("%d.%d", amount / 100, amount % 100);
		return conv;
	}
}
