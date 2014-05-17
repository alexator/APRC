package vending_jlist;

public class VendingItem {

	private String name;
	private int price;
	private int stock;
	private int stockinit;
	
	public VendingItem(String s, int p, int st) {
		
		name = s;
		price = p;
		stock = st;
		stockinit = st;
	}
	
	public String getItemName() {
		return name;
	}
	
	public int getItemPrice() {
		return price;
	}
	
	public boolean stockLeft() {
		
		if(stock >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void buyItem() {
		if(stockLeft()) {
			stock = stock - 1;
		}
	}
	
	public void resetStock() {
		stock = stockinit;
	}
	
	private String convertPounds(int amount) {
		String conv = String.format("%d.%d", amount / 100, amount % 100);
		return conv;
	}
	
	public String toString() {
		return name + " price: " + convertPounds(price) + " and stock (" + stock + ")";
	}
}