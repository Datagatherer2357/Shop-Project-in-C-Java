package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

import java.util.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.math.RoundingMode; // [0]
import java.text.DecimalFormat; // For pre-formatting decimal outputs

// [3,4,5]
public class Shop {

	// Prettify output:
	static String pretty = "-----------------------------------------------------\n";

	private double cash; // Same as cashfloat in shopInC
	// amount of money in the shop
	// Represent the product stock as an array list, as opposed to an array:
	// Array list type goes in <>, and we will call it "stock":
	private static ArrayList<ProductStock> stock; // had to make "static" to resolve error
	private int index; // New "index" added to keep track of quantity like shopInC

	private Object live;

	// Format decimal outputs:
	private static DecimalFormat df2 = new DecimalFormat("#.##"); // To get rid of excessive decimal prints

	
	// Constructor:
	// String to construct shop, take in filename that points to the CSV:
	// Read the whole file into a list: Option 5 JavaIO: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			// Read each line of the file into separate part of this list:
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			// Make a print out for CSV lines
//			System.out.println(lines.get(0)); // prints cash amount/gets first line
			cash = Double.parseDouble(lines.get(0)); //Covert string to double
			System.out.println("\n");
			System.out.println(pretty);
			System.out.println("\t--- SHOP ITINERARY & PRICES ---\n");
			System.out.println(pretty);
			System.out.println("The shop has €" + cash + " in the cashfloat\n");
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0); // remove index 0 in list as its treated differently
			for (String line : lines) { // each looped line will be in a variable called "line"
				String[] arr = line.split(","); // Split the line/keeps them apart! LEFT side is an array (arr)
				String name = arr[0]; // First element is product "name"
				double price = Double.parseDouble(arr[1]); // Second is "price" change/parse to double
				int quantity = Integer.parseInt(arr[2].trim()); // Third "quantity". "trim" removes whitespace
				Product p = new Product(name, price);// Create new product called p from above info (V similar to what we did in C)
				ProductStock s = new ProductStock(p, quantity);

//		System.out.println(p); // print product
				// System.out.println("\n"); // print space
				stock.add(s); // add method which keeps track of everything internally  "add" is a method belonging to "arraylist": SO here were just adding it on at the end

				// System.out.println("Shop stock is:\n");
				System.out.println(s); // print all stock details

			}

		}

		catch (IOException e) {

			// do something
			e.printStackTrace();
		}
	}

	public double getCash() {
		return cash;
	}

	// Need a an UPADTE cash method:
	public void setCash(double cash) {

		this.cash = cash;

	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	// Need an UPDATE stock method:
	public void setStock(ArrayList<ProductStock> stock) {

		this.stock = stock;

	}

	// Find a product in shop stock:
	public static String findProdInStock(String product) {
		// Loop over shop stock:
		for (ProductStock productStock : stock) {
			// If product in stock:
			if (productStock.getProduct().getName().equals(product)) {
				// Return product name::
				return productStock.getProduct().getName();
			}
		}
		// If not:

		return "NULL";

	}

	// Return shop details to string:
	@Override
	public String toString() {
		// return "Shop [cash=" + cash + ", stock=" + stock + "]";
		return String.format("");
		// return String.format("The shop has €%.2f in the cashfloat,\n%s", cash,
		// stock); // Omitted due unnecessary output
	}

	// Need an item finder function

	// Process cust order method: compare shoplist to product stock and process:
	public static void crossref(Shop shop, Customer newcust) {

		// Storage variables:
		double cost = 0.0;
		int quantity = 0;
		double total_cost = 0.0;
		double arrears = 0.0;

		ArrayList<ProductStock> custlist = newcust.getShoppingList(); // same as ShopInit
		ArrayList<ProductStock> stockprod = shop.getStock(); //

		String ret = "";
		for (ProductStock custprod : newcust.getShoppingList()) {
			// for (ProductStock stockprod: stock) {
			total_cost = total_cost + (custprod.getProduct().getPrice() * custprod.getQuantity());

		}
		// Need error handling for customers budget if doesn't have enough!!
		if (newcust.getBudget() < total_cost) {

			arrears = (total_cost - newcust.getBudget());

			System.out.println("You will be: €" + arrears + " in arrears");
		}

		// Check customer list products are stocked:
		// Test by putting bananas in CSV
		for (ProductStock custprod : newcust.getShoppingList()) {
			if (findProdInStock(custprod.getProduct().getName()).equals("NULL")) {

				// System.out.printf("OOPS! " +custprod.getProduct().getName()+ " is/are not in
				// stock, we will\nomit this from " + newcust.getName() + "'s order!\n");;

				return;

			}

		}

		// NOW!! check if we have each item on the order in stock

//		if(custprod.getProduct().getName().equals(stockprod)) {				
//			System.out.println(custprod.getProduct().getName()+ " "); // needs additional work

//		}

		// ERROR HANDLING - If stockprod less than custprod quantity:

//			if(stockprod.getQuantity() < custprod.getQuantity())	{

//				ProductStock stockprod; // product stock local variable solution
//				System.out.println("SORRY! We don't have that quantity of "	+ stockprod.getProduct().getName());

//			}

	}

	// else{

	// CALCULATE COST:
//				total_cost = stockprod.getProduct().getPrice() * custprod.getQuantity() + cost;
//				System.out.println("Cost: €%.2f" + total_cost);

	// UPDATE SHOP STOCK QUANTITY:
//				quantity = stockprod.getQuantity() - custprod.getQuantity();
//				System.out.println("Quantity update: " + quantity);

	// Diminish the shop stock by ordered amount
//				stockprod.setQuantity(quantity); // Re-set quantity TODO

	// Need a total customer bill with updated budget
	// Need update the shop cashfloat
	// Need to update stock quantities

	// ret = String.format("%s, %.2f, %d",stockprod, cost, quantity);
	// System.out.println(ret);

//	}}

	public static void main(String[] args) { // COULD MAKE SCANNER A VOID METHOD IN SHOP AND CALL IT IN MAIN AS
												// shop.scanner;

		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
		Customer newcust = new Customer("src/ShopVideoVersion/customer.csv");
//		System.out.println("\n"); // print space
//		System.out.println(shop); // print shop stock 	(dont need)

//		System.out.println("\n"); // print space
//		System.out.println(newcust); // print customer list

//		System.out.println("\n"); // print space

//		crossref(shop, newcust); // print crossref of stock and customer list

//		shop.processOrder(newcust); // processing new customer's order (processOrder is INSIDE the shop class)

//		shop.checkOut(newcust); // Display the customer's order going through checkout processor

		// Need to get name and quantity of customers items
		// find them in the shop stock
		// Ensure we can fulfill customers order

		// create the scanner to take in user input
		Scanner scan = new Scanner(System.in);

		// Switch/case equivalent in Java:
		// https://www.tutorialspoint.com/java/switch_statement_in_java.html:

		do {

			// SHOP MENU:
			System.out.println("\n\n\t--- WELCOME TO THE ONLINE SHOP! ---\n\n\n");
			int num, i;
//		
			System.out.println(pretty);
			System.out.println("--- MENU OPTIONS ---\n");
			System.out.println(pretty);
			System.out.println("\n");
			// OPTIONS:
			System.out.println("0. Load the shop\n"); // Load the shop
			System.out.println("1. Show shop stock\n"); // Show the shops stock, prices and quantities
			System.out.println("2. Load the customer\n"); // Load the customer
			System.out.println("3. Show the customer's order\n"); // Show the customer's order
			System.out.println("4. Customer checkout\n"); // Process the customers order & provide bill
			System.out.println("5. Customer LIVE mode\n"); // Enter into interactive LIVE mode
			System.out.println("6. Exit\n\n"); // Exit the shop

			System.out.println("Enter your choice :  ");

			int choice = scan.nextInt(); // To store the user's choice

			// Create a Scanner object to read input.
			// Scanner console = new Scanner(System.in);

			// choice = scan.next().charAt(0);

			// Determine which character the user entered.
			switch (choice) {
			case 0:
				// LOAD THE SHOP:
				System.out.println(pretty);
				System.out.println("\n");
				System.out.println("\tLoading the shop...\n");
				System.out.println("\n");
				System.out.println(pretty);
				// Shop shop = new Shop("src/ShopVideoVersion/stock.csv");

				break;

			case 1:

				// SHOW SHOP STOCK:
				System.out.println(pretty);
				System.out.println("\n");
				System.out.println("\tSHOP STOCK:\n");
				System.out.println("\n");
				System.out.println(pretty);
				System.out.println(shop = new Shop("src/ShopVideoVersion/stock.csv"));

				break;

			case 2:
				// LOAD THE CUSTOMER:
				System.out.println(pretty);
				System.out.println("\n");
				System.out.println("\tLoading the customer...\n");
				System.out.println("\n");
				System.out.println(pretty);
//				Customer newcust = new Customer("src/ShopVideoVersion/customer.csv"); // Calls customer loader

				break;

			case 3:
				// SHOW THE CUSTOMER'S ORDER:
				// System.out.println(newcust);
				// Below solved output issue:
				System.out.println(newcust = new Customer("src/ShopVideoVersion/customer.csv")); // Calls customer
				shop.processOrder(newcust); // processing new customer's order (processOrder is INSIDE the shop class)
											// // loader

				break;

			case 4:
				// CUSTOMER CHECKOUT:
				// crossref(shop, newcust); // print crossref of stock and customer list
				shop.checkOut(newcust); // Display the customer's order going through checkout processor
				System.out.println(newcust.getName() + " has left the shop, ");
				System.out.println("next customer please!\n");
				System.out.println(pretty);

				break;
			case 5:
				// LIVE MODE OPTION:
				System.out.println("\n");
				liveshop live = new liveshop();
				try {
					live.main(new String[0]); // Call MAIN method of liveshop class [12]
				} catch (Exception e) {

				}

				break;

			case 6: // EXIT OPTION:
				System.out.println("\n\tExiting the shop...Take care!\n\n");
				System.exit(0); // terminates the complete program execution

			default:
				System.out.println("Incorrect Input!");
			}
			;

// [6]
		} while (true); // [7] Resolved the menu returning to after choice issue!!
	}

	// New methods (Skype):

	// Price finder function:
	public double findPrice(String name) {
		for (ProductStock productStock : stock) { // Iterate over the stock
			Product p = productStock.getProduct();
			if (p.getName().equals(name)) {
				return p.getPrice();
			} // same if condition as strcmp in C
		}
		return 0.0; // If cannot find price

	}

	double total = 0.0; // Set the total bill cost variable
	// void doesn't return any info:
	// function for processing order:
	// PROCESS AND PRINT CUSTOMER INFO:
	int InStock = 0; // set the InStock variable

	public void processOrder(Customer c) {
		System.out.println(pretty);
		System.out.println("\t--- NEW CUSTOMER DETAILS: ---\n");
		System.out.println(pretty);

		System.out.println("The customer's name is " + c.getName() + ",\nHis/her budget is: €" + c.getBudget()); //
		System.out.println(pretty);

//		System.out.println("PRICE FINDER:"); // print space

		double total = 0.0; // Set the total bill cost variable
		for (ProductStock productStock : c.getShoppingList()) { // Original "stock" error amended to shopping list
			Product p = productStock.getProduct(); // Gets us the products
			int quantity = productStock.getQuantity();
			// Now we just need to find the PRICE, we'll do that in the function above this
			// one
			double price = findPrice(p.getName());

			System.out.println("Product's name is " + p.getName() + ",");
			System.out.println(p.getName() + "'s price is €" + price + ",");
			System.out.println(c.getName() + " wants to buy " + quantity + " of the product above,");
			// to save the customers product -set price to be stored in the product for the
			// future:
			p.setPrice(price);

			// TODO:
			// Below line is the cost calculator algorithm
			double cost = quantity * price;
			System.out.println("This will cost " + c.getName() + " €" + df2.format(cost) + ","); // Float formatted

			// System.out.println("The total cost is now: €" + cost);
			total += cost;// += Means increment variable on the left by value on the right

			System.out.println("The total cost to " + c.getName() + " is now	€" + df2.format(total));
			System.out.println("\n");
			// printf("%s's remaining budget is:\t%.2f\n",c.name, c.budget-total);

		}

		// ERROR HANDLING - If total price exceeds customer's budget:
		double arrears = c.getBudget() - total;

		// Update the cashfloat balance the amount taken in from order:
		setCash(getCash() + total);

		// Update customer's budget:
		c.setBudget(c.getBudget() - total);

		if (total > c.getBudget()) { // If the order cannot be satisfied/budget too low
			System.out.println("SORRY! " + c.getName() + "'s order total exceeds the\nspecified budget! " + c.getName()
					+ " will owe the shop:\n€" + arrears);
			return;

		}

	}

	// CUSTOMER CHECKOUT PROCESSOR:

	public void checkOut(Customer c) {
		// Create variable to store total order cost. Loop through order and calculate
		// total
		System.out.println(pretty);
		System.out.println("\t--- CUSTOMER CHECKOUT ---\n");
		System.out.println(pretty);
		System.out.println("\nComparing shopping list to current stock...\n");
		System.out.println("\n	Customer's shopping list:\n");

		// Iterate via for loop on customer's shopping list: [1]
		for (int i = 0; i < c.getShoppingList().size(); i++) {
			System.out.println(c.getShoppingList().get(i));
		}
		System.out.println(pretty);
		System.out.println("Now processing " + c.getName() + " 's order...");
		System.out.println("\n");

		// Check customer list with stock:
		for (ProductStock custprod : c.getShoppingList()) {
			int InStock = 0; // set the InStock variable
			for (ProductStock productStock : stock) {

				// Look for shopping list item in shop stock:
				// If shopping item is in shop then:
				if (custprod.getProduct().getName().equals(productStock.getProduct().getName())) {
					InStock = 1;
					System.out.println("We have " + productStock.getProduct().getName() + " in our current stock,");

					// Cross reference to see if order quantity can be taken from stock
//			int ordquant = custprod.getQuantity(); // Assign customer product quantities as integers
//			int shopquant = productStock.getQuantity(); // Assign shop stocked product quantities as integers
					// System.out.println(ordquant); // Test for ordquant print
//			if (ordquant<=shopquant){ // Compare both to see if the order can be satisfied
					System.out.println(
							"Adding " + productStock.getProduct().getName() + " to " + c.getName() + "'s basket...");
					// System.out.println("\n");
					// If order can be satisfied, take item from the stock:

					// total+=cashfloat;
					// shop.cashfloat = total+cashfloat;
					// check do we have enough in stock

					// ERROR HANDLING - If desired quantity not available: IVE SET BREAD TO HAVE LOW
					// QUANTITY TO TEST THIS
					if (custprod.getQuantity() > productStock.getQuantity()) {
						System.out
								.printf("SORRY! We don't have that quantity of " + productStock.getProduct().getName());
						System.out.println("\n");
						// continue;

					}

					// Diminish the shop stock by customer's order: //WORKING
					productStock.setQuantity(productStock.getQuantity() - custprod.getQuantity());

					// Output the shops new stock quantities: NEEDS FORMATTING?
					System.out.println("The shop now has " + productStock.getQuantity() + " of "
							+ productStock.getProduct().getName() + " in stock.\n");
					// ERROR HANDLING - Shopping list item is not is stock: PUT BANANAS IN TO TEST
					// IN JAMES'S ORDER
					// WORKS partially PRB in wrong place

				}
				if (findProdInStock(custprod.getProduct().getName()).equals("NULL")) { // If item in list is not found
																						// in stock
					// if(!custprod.getProduct().getName().equals(productStock.getProduct().getName()))
					// { // If NOT equal to i.e. not in stock
					System.out.println("OOPS! " + custprod.getProduct().getName()
							+ " is/are not in stock, we will\nomit this from " + c.getName() + "'s order!");
					break; // Output resolved

				}

//			else if (ordquant>shopquant){ 	//if the order cannot be satisfied
//				System.out.println("SORRY! We don't have that quantity of" + productStock.getProduct().getName());
				// return;
//						continue;
//					}

			}
		}

		// UPDATE SHOP STOCK AFTER PROCESSING:
		System.out.println(pretty);
		System.out.println("The shop's UPDATED stock now has:\n");

		// Iterate via for loop on updated shop stock: [1]
		for (int i = 0; i < stock.size(); i++) {
			System.out.println(stock.get(i));
		}
		System.out.println(pretty);

		// Output the shops new cashfloat balance:
		System.out.println("The cashfloat balance is: €" + cash);
		System.out.println(pretty);

		// Output the customers budget:
		System.out.println(c.getName() + "'s budget is now: €" + c.getBudget());
		System.out.println(pretty);

		return;
	}

}

//UPDATE SHOP STOCK AFTER PROCESSING:

// REFERENCES: 

// [0]. https://www.mkyong.com/java/java-display-double-in-2-decimal-points/
// [1]. https://crunchify.com/how-to-iterate-through-java-list-4-way-to-iterate-through-loop/
// [2]. https://www.geeksforgeeks.org/arraylist-listiterator-method-in-java-with-examples/
// [3]. https://www.geeksforgeeks.org/classes-objects-java/
// [4]. https://codereview.stackexchange.com/questions/71527/shopping-list-application/71575
// [5]. https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
// [6]. https://stackoverflow.com/questions/39228386/user-input-if-statements-in-java-not-working
// [7]. https://stackoverflow.com/questions/15857846/how-to-return-to-main-menu-in-switch-case-after-executing-a-method

// [8]. https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
// [9]. https://stackoverflow.com/questions/15857846/how-to-return-to-main-menu-in-switch-case-after-executing-a-method
// [10]. https://stackoverflow.com/questions/39228386/user-input-if-statements-in-java-not-working
// [11]. https://stackoverflow.com/questions/24130399/whiletrue-loop-throws-unreachable-code-when-isnt-in-a-void
// [12]. https://stackoverflow.com/questions/15218892/running-a-java-program-from-another-java-program