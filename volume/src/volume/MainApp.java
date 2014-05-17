package volume;

import java.util.Scanner;

public class MainApp {

	static int length;
	static int height;
	static int width;

	static int volume;
	static int perimeter;
	static int surface;
	static boolean flag_prob;

	static Scanner input;

	public static void main(String[] args) {

		flag_prob = false;

		input = new Scanner(System.in);
		System.out
				.println("This program calculates the volume, the perimeter and the surface area of a Box.");
		System.out
				.println("Please give the length, the height and the width of the box in cm. ");

		System.out.println("Length:");
		if (input.hasNextInt()) {
			length = input.nextInt();
			System.out.println("Height:");
			if (input.hasNextInt()) {
				height = input.nextInt();
				System.out.println("Width:");
				if (input.hasNextInt()) {
					width = input.nextInt();
				} else {
					System.err.println("Your input was invalid.");
					flag_prob = true;
				}
			} else {
				System.err.println("Your input was invalid.");
				flag_prob = true;
			}
		} else {
			System.err.println("Your input was invalid.");
			flag_prob = true;
		}

		if (!flag_prob) {
			volume = length * height * width;
			surface = (2 * length * height) + (2 * length * width)
					+ (2 * width * height);
			perimeter = (4 * length) + (4 * width) + (4 * height);

			System.out.format("%n%s = %d", "Volume", volume);
			System.out.format("%n%s = %d", "Surface Area", surface);
			System.out.format("%n%s = %d", "Perimeter", perimeter);
		} else {
			System.err.println("Problem with your inputs");
		}

	}

}
