package menu;

abstract public class Sandwich {
	String ingredient;
	int amount;
	
	public abstract void prepareIngredient();
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void printInfo() {
		System.out.println("ingredient: " + ingredient + ", amount: " + amount);
	}
}
