package diary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DiaryApp {

	private static BufferedReader reader1;
	private static BufferedReader reader2;
	private static BufferedReader reader;
	private static ArrayList<Entry> diaryEntries = new ArrayList<Entry>();
	private static ArrayList<String> diaries = new ArrayList<String>();
	private static String line1;
	private static String line2;
	private static Scanner in;
	private static Scanner con;
	private static int selection;
	private static Calendar cal = new GregorianCalendar();
	private static File file2 = new File("diaries.txt");

	public static void main(String[] args) {
		Boolean flag_running = true;
		in = new Scanner(System.in);

		doHeader();
		readDiary();

		while (flag_running) {
			doMenu();
			if (in.hasNextInt()) {
				selection = in.nextInt();

				switch (selection) {
				case 1:
					createEntry();
					break;
				case 0:
					newFile(diaryEntries);
					flag_running = false;
					System.out.println("Bye user!");
					break;
				default:
					flag_running = false;
					System.out.println();
					System.err.println("Invalid selection");
					break;
				}
			} else {
				flag_running = false;
				System.out.println();
				System.err.println("This program accepts only integers.");
			}
		}
	}

	public static void doHeader() {
		System.out.println("*********************");
		System.out.println("* Diary Application *");
		System.out.println("*********************");
	}

	public static void doMenu() {
		System.out.println();
		System.out
				.println("Please make your selection form the below options:\n");
		System.out.println("[1]: Write a new note.");
		System.out.println("[0]: Save and exit.\n");
		System.out.println("Type your selection:");

	}

	public static void getInput() {
		in = new Scanner(System.in);
		selection = in.nextInt();

	}

	public static void readDiary() {
		if (file2.length() != 0 && file2.exists()) {
			try {
				reader1 = new BufferedReader(new FileReader("diaries.txt"));
				while ((line1 = reader1.readLine()) != null) {
					diaries.add(line1);
				}
				reader1.close();

				for (int i = 0; i < diaries.size(); i++) {
					System.out.println();
					System.out.println("Date: " + diaries.get(i) + "\n");
					reader2 = new BufferedReader(new FileReader(diaries.get(i)
							+ ".txt"));
					while ((line2 = reader2.readLine()) != null) {
						System.out.println(line2);
					}
					reader2.close();
				}
			} catch (IOException e) {
			}
		} else {
			System.out.println("No previous Calendars!");
		}
	}

	public static void createEntry() {

		SimpleDateFormat d_f = new SimpleDateFormat("HH:mm:ss");
		String title;
		String time;
		String content;
		Entry e;

		time = d_f.format(cal.getTime());
		con = new Scanner(System.in);
		System.out.println("Please type the Title of your entry:");
		title = con.nextLine();
		System.out.println("Please type the content of your entry:");
		content = con.nextLine();
		e = new Entry(title, time, content);
		System.out.println(e.toString());
		diaryEntries.add(e);

	}

	public static void newFile(ArrayList<Entry> l) {
		SimpleDateFormat d_f = new SimpleDateFormat("yyyy-MM-dd");
		String date = d_f.format(cal.getTime());
		File file1 = new File(date + ".txt");
		try {
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(file1,
					true));
			for (int i = 0; i < l.size(); i++) {
				writer1.append("** " + l.get(i).toString() + "\n");
			}
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(file2,
					true));
			reader = new BufferedReader(new FileReader("diaries.txt"));
			if (file2.length() != 0) {
				if (reader.readLine().equals(date)) {

				} else {
					writer2.append(date + "\n");
				}
			} else {
				writer2.append(date + "\n");
			}
			writer1.close();
			writer2.close();

		} catch (IOException e) {
		}
	}

}
