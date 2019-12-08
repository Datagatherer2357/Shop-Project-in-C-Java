package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

	private String name; // String class for name keeps things simpler, A string is just (internally) an array of characters, 
						// but the string class lets us work with it at a higher level
	private double budget;
	private ArrayList<ProductStock> shoppingList;
	private int index; // New "index" added to keep track of quantity like shopInC
	// Construct customer:
	public Customer(String fileName) {
		shoppingList = new ArrayList<>(); // Instansiate the shopping list
		// Read the whole file into a list: Jhttps://www.geeksforgeeks.org/different-ways-reading-text-file-java/
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			// get(0) is the FIRST thing in the array:
			String[] firstLine = lines.get(0).split(","); // Persons name & budget
			name = firstLine[0];
			budget = Double.parseDouble(firstLine[1]);
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				int quantity = Integer.parseInt(arr[1].trim());
				Product p = new Product(name, 0);
				ProductStock s = new ProductStock(p, quantity);
				shoppingList.add(s);

			}

		}

		catch (IOException e) {

			// do something
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public double getBudget() {
		return budget;
	}

	// Add customer's set budget:
	public void setBudget(double budget) {

		this.budget = budget;

	}

	public ArrayList<ProductStock> getShoppingList() {
		return shoppingList;
	}

	@Override // Using toString method from other
	public String toString() {
		// Prettify output:
		String pretty = "-----------------------------------------------------\n";
		System.out.println(pretty);
		System.out.println("\t--- CUSTOMER ORDER ---\n");
		System.out.println(pretty);
		String ret = "Customer [name=" + name + ", budget=" + budget + ", shoppingList=\n"; // ret just means return
																							// value
		for (ProductStock productStock : shoppingList) {
			ret += productStock.getProduct().getName() + " Quantity: " + productStock.getQuantity() + "\n";
		}
		return ret + "]";
	}

	public static void main(String[] args) {
		Customer newcust = new Customer("src/ShopVideoVersion/customer.csv");
		System.out.println(newcust);
		System.out.println("\n");

		// Customer's list and implement price finder:
		ArrayList<ProductStock> custlist = newcust.getShoppingList();
		// Feed in customer shopping list:
		for (ProductStock prodStock : custlist) {
			// Check stock and assign as custquant:
			int custquant = prodStock.getQuantity();
			// Check product name and assign as custprod:
			String custprod = prodStock.getProduct().getName();
			System.out.println(
					"Product's name is: " + custprod + "Product's price is: " + prodStock.getProduct().getPrice());
			System.out.println("\n");
			System.out.println(custprod);
			System.out.println(custquant);

		}

	}
}
