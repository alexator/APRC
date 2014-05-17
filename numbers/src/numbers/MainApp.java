package numbers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainApp {

	static ArrayList<Integer> num1 = new ArrayList<Integer>();
	static ArrayList<Integer> num2 = new ArrayList<Integer>();
	static ArrayList<Integer> numsort = new ArrayList<Integer>();
	static Scanner lineScanner;
	static BufferedReader reader1;
	static BufferedReader reader2;
	static int sum;
	static double aver;

	public static void main(String[] args) {

		try {
			reader1 = new BufferedReader(new FileReader(
					"numbers1.txt"));

			String line1;
			while ((line1 = reader1.readLine()) != null) {
				num1.add(Integer.parseInt(line1));
			}

			reader2 = new BufferedReader(new FileReader(
					"numbers2.txt"));

			lineScanner = new Scanner(reader2).useDelimiter(" ");
			while ((lineScanner.hasNext())) {
				num2.add(Integer.parseInt(lineScanner.next()));
			}
			
			lineScanner.close();

			reader1.close();
			reader2.close();

		} catch (IOException e) {
		}

		printList(num1);
		printList(num2);
		sumList(num1);
		averList(num1);
		copyList(num1);
		printList(numsort);
		maxElem(numsort);
		minElem(numsort);
		newFile(num1,num2);
		checkArrayList(num1, num2);

	}

	public static void printList(ArrayList<Integer> l) {

		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));

		}
	}

	public static void sumList(ArrayList<Integer> l) {
		sum = 0;
		for (int i = 0; i < l.size(); i++) {
			sum = sum + l.get(i);

		}
		System.out.println("Summary is: " + sum);
	}

	public static void averList(ArrayList<Integer> l) {

		aver = (double) sum / l.size();
		System.out.println("Average is : " + aver);
	}
	
	public static void copyList(ArrayList<Integer> l) {
		for (int i = 0; i < l.size(); i++) {
			numsort.add(l.get(i));
		}
		sortList(numsort);
	}
	
	public static void sortList(ArrayList<Integer> l) {
		Collections.sort(l);
	}
	
	public static void maxElem(ArrayList<Integer> l) {
		int max;
		max = l.get((l.size() - 1));
		System.out.println("The max is: " + max);
	}
	
	public static void minElem(ArrayList<Integer> l) {
		int min;
		min = l.get(0);
		System.out.println("The min is: " + min);
	}
	
	public static void newFile(ArrayList<Integer> l1, ArrayList<Integer> l2 ) {
		
		try {
			BufferedWriter writer1 = new BufferedWriter(new FileWriter("newNumbers1.txt")); 
			for (int i = 0; i < l1.size() - 1; i += 2) {
				writer1.write(l1.get(i) + "," + l1.get(i+1) + "\n"); 
			}
			writer1.close(); 
		} catch (IOException e) {
		}
		try {
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("newNumbers2.txt")); 
			for (int i = 0; i < l2.size() - 1; i += 2) {
				writer2.write(l2.get(i) + "," + l2.get(i+1) + "\n"); 
			}
			writer2.close(); 
		} catch (IOException e) {
		}
	}
	
	public static void checkArrayList(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		
		boolean flag_equals = false;
		
		for (int i = 0; i < l1.size(); i++) {
			if (l1.get(i).equals(l2.get(i))) {
				flag_equals = true;
			} else {
				flag_equals = false;
			}
		}
		if (flag_equals) {
			System.out.println("The two ArrayLists num1 and num2 are equal!");
		} else {
			System.err.println("The two ArrayLists are not equal!");
		}
	}
}