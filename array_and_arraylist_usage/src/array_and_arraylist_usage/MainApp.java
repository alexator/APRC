package array_and_arraylist_usage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainApp {

	static int[] numbers = { 1, 7, 3, 9, 5, 0, 8, 2, 6, 4 };
	static int sum;
	static double aver;
	static int[] num_asc;
	static ArrayList<Integer> list;
	static Scanner in = new Scanner(System.in);
	static int num;
	static int high;
	static int low;
	static int mid;

	public static void main(String[] args) {

		sum();
		average();
		asc_sort();
		dsc_sort();
		arrLst();
		list_sort();
		binInit();

		System.out
				.println("Please type the number that you want to search for:");
		if (in.hasNextInt()) {
			num = in.nextInt();
			binSrch(num, high, mid, low);
		} else {
			System.err.println("Try to use an integer...");
		}

	}

	public static void sum() {

		sum = 0;

		for (int i = 0; i < numbers.length; i++) {
			sum = sum + numbers[i];

		}

		System.out.println("The sum of the array is: " + sum);
	}

	public static void average() {

		aver = (double) sum / numbers.length;
		System.out.println("The average of the array is: " + aver);
	}

	public static void asc_sort() {

		Arrays.sort(numbers);

		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");

		}
	}

	public static void dsc_sort() {

		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < (numbers.length - 1); j++) {
				if (numbers[j] < numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}

		System.out.println();

		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");

		}
		System.out.println();
	}

	public static void arrLst() {
		list = new ArrayList<Integer>();

		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		System.out.println(list);
	}

	public static void list_sort() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < (list.size() - 1); j++) {
				if (list.get(j) > list.get(j + 1)) {
					int temp2 = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp2);
				}
			}
		}
		System.out.println(list);
	}

	public static void binInit() {
		high = list.size() - 1;
		low = 0;
		mid = (high + low) / 2;
	}

	public static void binSrch(int n, int h, int m, int l) {

		int high;
		int low;
		int mid;

		if (n > list.get(h) || n < list.get(l)) {
			System.err.println("Not in list!");
		} else {
			if (n == list.get(m)) {
				System.out.println("Found it in list at position: " + m);
			} else if (n >= list.get(m)) {
				high = h;
				low = m + 1;
				mid = (high + low) / 2;
				binSrch(n, high, mid, low);
			} else if (n <= list.get(m)) {
				high = m - 1;
				low = 0;
				mid = (high + low) / 2;
				binSrch(n, high, mid, low);
			}
		}
	}

}