package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

public class ProductStock {
	private Product product;
	private int quantity; // int is a primitive

	// Add a constructor using the fields:
	public ProductStock(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	// Add getters:
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	// Need an UPDATE quantity method:
	public void setQuantity(int quantity) {

		this.quantity = quantity;

	}
	// Add a toString method: primitives i.e. quantity, dont have toStringmethods
	@Override
	public String toString() {
		return "ProductStock [product=" + product + ", quantity=" + quantity + "]";
	}

}
