package swap;

public class MainApp {

	public static void main(String[] args) {
		
		String food = "Bread";
		String metal = "Steel";
		String temp;
		
		System.out.println("The food variable: " + food );
		System.out.println("The metal variable: " + metal);
		System.out.println("Swap those variables!");
		
		temp = food;
		food = metal;
		metal = temp;
		
		System.out.println("The food variable: " + food );
		System.out.println("The metal variable: " + metal);
		System.out.println("Variables swaped!");

	}

}
