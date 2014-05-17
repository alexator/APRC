package gui_atm;

public class Account {

private int balance;
	

	public Account(int amount){
		
		balance = amount;
	}
	
	
	public int getBalance() {
		
		return balance;
	}
	
	
	public void deposit(int value) {
		balance = balance + value;
	}
	
	public void withdraw(int value){
		balance = balance - value;
	}
	
}
