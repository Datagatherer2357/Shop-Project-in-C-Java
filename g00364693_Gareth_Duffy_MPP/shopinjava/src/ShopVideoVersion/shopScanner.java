package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

// IGNORE THIS FILE AS UNNECESSARY FOR PROGRAM

import java.util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// Live mode:
public class shopScanner{

	// Prettify output:
	static String pretty = "-----------------------------------------------------\n";

	public static void main(String args[]) {
		// create the scanner to take in user input
		Scanner scan = new Scanner(System.in);

		// ask the user for what they want to buy and save as string
//		System.out.println("What product do you want to buy?");
//		String productName = scan.nextLine();
		// !!Use the productName to find its price in the shop
		// OR, check if its in stock

		// Ask how many they want and save as a integer
//		System.out.println("How many " + productName + " do you want?");
//		int amount = scan.nextInt();

		// find out how much money they have to pay you with
//		System.out.println("How much money do you have?");
//		double cash = scan.nextDouble();
		// Build on top of this to see if the customer can afford this

		// Print the information
//		System.out.println("You want to buy " + amount + " " + productName + " and you have " + cash + ".");
		// scan.close();

		// Switch/case equivalent in Java:
		// https://www.tutorialspoint.com/java/switch_statement_in_java.html:

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
//		Scanner scan = new Scanner(System.in);

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

				break;

			default:
				System.out.println("Incorrect Input!");
			}
			;

// [6]
		} while (true); // [7] Resolved the menu returning to after choice issue!!
	}}


//	private static void main() {
// TODO Auto-generated method stub

//	}}


