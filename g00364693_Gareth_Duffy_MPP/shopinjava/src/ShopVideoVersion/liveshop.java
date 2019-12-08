package ShopVideoVersion;

//Gareth Duffy g00364693 HDIP Data Analytics
//Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
//CSV shop in Java & Live shop in Java

import java.util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class liveshop { // [8,9,10,11]

	// Prettify output:
	static String pretty = "-----------------------------------------------------\n";

	// Format decimal outputs:
	private static DecimalFormat df2 = new DecimalFormat("#.##"); // To get rid of excessive decimal prints
	
	// Constructor from superclass:
	public liveshop() {
		super(); // Shop
		// TODO Auto-generated constructor stub
	}


	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		// 2BF = To be fixed
		// DECLARE LIVE MODE VARIABLES:

		double totalCost = 0.0;
		int i; // Iterator
		int j; // Iterator
		int choice;
		int c = 1; // Option pointer
		int[] a = null; // "a" represents product (array)

		// allocating memory for 9 integers:
		a = new int[9]; // SOLVED array allocation issue 1
		// double cost;
		double[] cost = null;
		cost = new double[9]; // SOLVED array allocation issue 1
		for (i = 0; i < a.length; i++) // ++ arithmetic operator to increment value by 1
			a[i] = 0; // a is the ID variable of the product
		// int yourbudget = 0;
		// String str; // = malloc(sizeof(char) * 90); // name memory allocator & char
		// for customers name | data type that holds one character //2BF

		// Product item list:
		ArrayList<String> items = new ArrayList<String>();
		items.add("Bread");
		items.add("Spaghetti");
		items.add("Tomato sauce");
		items.add("Coke can");
		items.add("Coffee");
		items.add("Green tea");
		items.add("Bin bags");
		items.add("Bleach");
		items.add("Fairy liquid");
		// Traversing list through for-each loop
		// for(String obj:items)
		//    System.out.println(obj); 

		// array declaration:
		// declares an Array of integers.
		// int[] arr;
//	public double totalCost;
//	private int c;
//	private int[] cost; 

		{

			// allocating memory for 5 integers.
//    arr = new int[5]; 

			// initialize the first elements of the array
			// arr[0] = 10;

			// initialize the second elements of the array
			// arr[1] = 20;

			// so on...
			// arr[2] = 30;
			// arr[3] = 40;
			// arr[4] = 50;

			// accessing the elements of the specified array
			// for (int i = 0; i < arr.length; i++)
			// System.out.println("Element at index " + i +
			// " : "+ arr[i]);

			// public liveshop() {
			// TODO Auto-generated constructor stub

			System.out.println(pretty);
			System.out.println("--- LIVE SHOP ---\n");
			System.out.println(pretty);
			System.out.println(pretty);

			// 1:
			System.out.println("Welcome to the LIVE shop!\n\nPlease tell us your name...\t");
			System.out.println("\n");
			System.out.println(pretty);

			// 2:
			// https://stackoverflow.com/questions/16981232/is-there-an-equivalent-method-to-cs-scanf-in-java
			String str; // To take name input /assign

			// 3:
			str = scan.nextLine();

			// scan.close(); // OMITTED/problematic
			// System.out.println(str); // 
			System.out.println("\n");

			// 1:
			System.out.println("Hello " + str + " what is your budget?\t\n");
			// 2:
			// int yourbudget;
				// 3:

			int yourbudget = scan.nextInt();

			// String yourbudget = null;
			System.out.println("Hello " + str + "...Enjoy spending your €" + yourbudget + "!\n\n");
			// Equivalent of system("pause") in C:
			try {
				java.util.concurrent.TimeUnit.SECONDS.sleep(2); // sleeps for n seconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // https://stackoverflow.com/questions/43507587/how-to-pause-my-java-program-for-2-seconds

			do {

				System.out.println(pretty); // For testing do

				c = 1;
//					int[] a = null;
//					int i;
//					Object[] items;
				if (c == 1) {
					System.out.println(pretty);
					System.out.println(
							"Please take the time to explore our products in stock:\n\n1:- FOOD ITEMS\n2:- DRINKS\n3:- CLEANING ITEMS\nPress any other number to go to exit the shop\n");
		
					// parameter is an int, second is users choice. //2BF
					choice = scan.nextInt(); // Store the user's choice

					switch (choice) {
					case 1: // FOOD PRODUCTS:
					{

						System.out.println(pretty);
						System.out.println(
								"Choose a product:\n\n1:- Bread - €0.70\n2:- Spaghetti - €1.20\n3:- Tomato Sauce - €0.80\nPress any other number to go to the checkout\n");
						int foodChoice = scan.nextInt();
//						double[] cost = null; //https://www.geeksforgeeks.org/arrays-in-java/

						// Hard code product prices:
						cost[0] = 0.70;
						cost[1] = 1.20;
						cost[2] = 0.80;
						switch (foodChoice) {
						case 1: // Choice 1
						{
							// int num;

							System.out.println(	"You chose Bread with €0.70. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
					
							int num = scan.nextInt();
							if (num == 1) {
								a[0]++;
								totalCost += 0.70;// += means incrementing the variable on the left by value on the
													// right.
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break; // Tested cost function and working but loop error persists(RESOLVED)
						}
						case 2: // Choice 2
						{

							System.out.println("You chose Spaghetti with €1.20. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
						
							int num = scan.nextInt();
							if (num == 1) {
								a[1]++;
								totalCost += 1.20;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						case 3: // Choice 3
						{

							System.out.println(
									"You chose Tomato sauce with €0.80. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
					
							int num = scan.nextInt();
							if (num == 1) {
								a[2]++;
								totalCost += 0.80;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						default: {
							System.out.println("Exit from FOOD PRODUCTS\n"); // change to exit from food items
							break;
						}
						}
						break;
					}
					case 2: // DRINK PRODUCTS:
					{

						System.out.println(pretty);
						System.out.println(
								"Choose a product:\n\n1:- Coke cans - €1.10\n2:- Coffee - €2.80\n3:- Green tea - €3.10\n Press any other number to go to the checkout\n");
						// 2BF
						// int drinkChoice = 0;
						int drinkChoice = scan.nextInt(); // change to drinkChoice
//						    double[] cost = null;
						cost[3] = 1.10;
						cost[4] = 2.80;
						cost[5] = 3.10;
						switch (drinkChoice) {
						case 1: {

							System.out.println(
									"You chose Coke cans for €1.10. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
				
							int num = scan.nextInt();
							if (num == 1) {
								a[3]++;
								totalCost += 1.10;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						case 2: {

							System.out.println(
									"You chose Coffee for €2.80. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
						
							int num = scan.nextInt();
							if (num == 1) {
								a[4]++;
								totalCost += 2.80;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						case 3: {

							System.out.println(
									"You chose Green tea for €3.10. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
						
							int num = scan.nextInt();
							if (num == 1) {
								a[5]++;
								totalCost += 3.10;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						default: { // default argument is an argument to a function that a programmer is not
									// required to specify.
							System.out.println("Exit from DRINKS Category\n");
							break;
						}
						}
						break;
					}
					case 3: // CLEANING PRODUCTS:
					{
						// int cleanChoice = 0; //
						System.out.println(pretty);
						System.out.println(
								"Choose a product:\n\n1:- Bin Bags - €2.50\n2:- Bleach - €1.75\n3:- Fairy liquid - €0.95\nPress any other number to go to the checkout\n");
				
						int cleanChoice = scan.nextInt(); // change to cleanChoice
						// double[] cost = null;
						cost[6] = 2.50;
						cost[7] = 1.75;
						cost[8] = 0.95;
						switch (cleanChoice) {
						case 1: {
							// int num = 0;
							System.out.println(
									"You chose to buy Bin bags for €2.50. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
					
							int num = scan.nextInt();
							if (num == 1) {
								a[6]++;
								totalCost += 2.50;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						case 2: {
							// int num = 0;
							System.out.println(
									"You chose to buy Bleach for €1.75. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
					
							int num = scan.nextInt();
							if (num == 1) {
								a[7]++;
								totalCost += 1.75;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n");
							break;
						}
						case 3: {
							// int num = 0;
							System.out.println(
									"You chose to buy Fairy liquid for €0.95. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
					
							int num = scan.nextInt();
							if (num == 1) {
								a[8]++;
								totalCost += 0.95;
							}
							System.out.println("Your cost in cart is:€" + totalCost + "\n"); // 2bf
							break;
						}
						default: {
							System.out.println("Exit from CLEANING PRODUCTS\n"); // change
							break;
						}
						}
						break;
					}

					default: {// ERROR HANDLING:
						System.out.println("OOPS! Please enter a valid category choice\n");
						break;
					}
					}

					// FROM HERE (MISSING) "CART" SECTION:
					System.out.println("\n");
					System.out.println(str + "'s cart:\n");
					System.out.println("\n");
					System.out.println("ID\tItems\t\tQuantity\tCost\n");
					for (i = 0; i < 9; i++) {
						if (a[i] != 0) {
							// FORMAT CART VARIABLE OUTPUTS: Almost exactly right

							// VARIABLES:
							// i = ID
							// items.get(i) = Item name
							// a[i] = quantity
							// (cost[i] * a[i]) = cost

							System.out.println(+i + "\t" + items.get(i) + "\t\t" + a[i] + "\t\t\t€" + (cost[i] * a[i]) + "\n");
							
							//
						}
					}

					System.out.println("Total Cost\t\t\t\t\t€" + totalCost + "\n");
					System.out.println("\n");
					System.out.println(
							"If you wish to buy anything more please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");

					c = scan.nextInt();
				}

				// END OF CART SECTION

				// PRODUCT IDs:
				if (c == 2) {

					// int id;
					System.out.println("Please enter the product ID to delete item\n");
					int id = scan.nextInt(); // To store the user's choice

			
					if (id < 9 && id > 0) {
						totalCost = totalCost - (cost[id] * a[id]);
						a[id] = 0;
					}
					// ERROR HANDLING:
					else {
						System.out.println("OOPS! Enter valid ID\n");
					}
					System.out.println("Revised items:\n");
					System.out.println("ID\tItems\t\tQuantity\tCost\n");
					for (i = 0; i < 9; i++) {
						if (a[i] != 0) {
							System.out.println(+i + "\t" + items.get(i) + "\t" + a[i] + "\t\t€" + (cost[i] * a[i]) + "\n"); // MUST
																																															// ISSUE

						}
					}
					System.out.println("Total Cost\t\t\t\t\t€" + totalCost + "\n");
					System.out.println(
							"If you wish to buy anything more, please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");
					System.out.println(c); // In C: ("%d",&c);
				}
				if (c == 3) // If choice is 3 then:
				{
					int id, quantity;
					System.out.println("Please enter the product ID to change the quantity of an item\n");
				
					id = scan.nextInt();
					System.out.println("Enter quantity to be changed of an item\n");
				
					quantity = scan.nextInt();
					if (id < 9 && id > 0) {
						if (quantity > 0 && a[id] > 0) {
							if (quantity < a[id]) {
								int dec = a[id] - quantity; // decrease
								a[id] = quantity;
								totalCost = totalCost - (cost[id] * dec);
							}
							if (quantity > a[id]) {
								int inc = quantity - a[id];// increase
								a[id] = quantity;
								totalCost = totalCost + (cost[id] * inc);
							}
							if (quantity == a[id]) {
								a[id] = quantity;
								totalCost = totalCost + 0;
							}

						} else { // Error handling:
							System.out.println("OOPS! This item has no quantity. Please try again\n");
						}
					} else {
						System.out.println("Please enter a valid product ID\n");
					}
					System.out.println("Revised Items \n");
					System.out.println("ID\tItems\t\tQuantity\tCost\n");
					for (i = 0; i < 9; i++) {

						// PREV below omitted if loop and string menu texts:
						if (a[i] != 0) {
							System.out.println(+i + "\t" + items.get(i) + "\t" + a[i] + "\t\t€" + (cost[i] * a[i]) + "\n");
						
						}
					}
					System.out.println("Total Cost\t\t\t\t€" + totalCost + "\n");
					System.out.println(
							"If you wish to buy anything more please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");
//					 
					c = scan.nextInt(); 
				}
			} while (c == 1 || c == 2 || c == 3) // while choice 1 or 2 or 3
			;
			System.out.println("\n");
			System.out.println(pretty);
			System.out.println("-------------------CUSTOMER BILL---------------------\n");
			System.out.println(str + " your final bill comes to: €" + totalCost); // problem with printing the
																					// final total cost (SOLVED)
			double change = yourbudget - totalCost; // declare customer change variable
			System.out.println(str + " your leftover budget is: €" + df2.format(change)); // Output the customer's leftover budget and format decimals
			System.out.println("\n");			
			System.out.println("\n");  
			System.out.println("Thank you for choosing us " + str + ", please visit us again!\n");
			System.out.println(pretty);
			System.exit(0); // Exit program

			// break; // LIVE MODE ENDS
			// do ends below /next bracket:
		}

		while (true); // Outside "do"

//	System.out.println(pretty);
	}
}


// REFS:
// [8]. https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
// [9]. https://stackoverflow.com/questions/15857846/how-to-return-to-main-menu-in-switch-case-after-executing-a-method
// [10]. https://stackoverflow.com/questions/39228386/user-input-if-statements-in-java-not-working
// [11]. https://stackoverflow.com/questions/24130399/whiletrue-loop-throws-unreachable-code-when-isnt-in-a-void
