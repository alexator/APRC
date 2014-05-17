/**
 * @author Alex Georgantas
 * 
 * This program calculates the area of a circle using a radius value that a user provides.
 */

package area;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainApp {

	static Scanner input;	//Variable the holds the user's inputs
	static int radius;
	static double area;
	static double circumf;

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("This program calculates the area and the circumference of a circle with a given radius.");
		System.out.println("Please give the radius of the circle in cm..");
		System.out.println("Radius:");

		input = new Scanner(System.in);	//Creates an new Scanner instance that reads system's input

		/* Check if the input is an integer */
		if (input.hasNextInt()) {

			radius = input.nextInt();	

			area = Math.PI * Math.pow(radius, 2);	//Calculation of circle's area
			circumf = 2 * Math.PI * radius;			//Calculation of circle's circumference

			DecimalFormat format = new DecimalFormat("#.##");	//Creates a rule in order the result to have two decimal digits
			
			/*//Apply's the rule and prints the result */
			System.out.println("The radius of the circle is " + format.format(radius) + " cm.");
			System.out.println("The area of the circle is " + format.format(area) + " square cm.");
			System.out.println("The circumference of the circle is " + format.format(circumf) + " cm.");
		} else {
			System.err.println("Problem with your input!");	//Error message in console if the input is not an integer
		}
	}
}
