package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

public class Product {
	String name;
	double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	// Price setter to change the price:
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

}
