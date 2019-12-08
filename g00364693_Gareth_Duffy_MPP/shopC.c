// Gareth Duffy g00364693 HDIP Data Analytics
// Shop assignment - Multi Paradigm Programming - Dr. Dominic Carr
// CSV shop in C & Live shop in C

// NECESSARY IMPORTS:
#include <stdio.h>  // Inbuilt C functions in stdio.h 
#include <string.h> // For string tokeniser
#include <stdlib.h> // For reading the CSVs
#include <ctype.h>  // Includes standard library functions to handle characters

// [0,1,2,3,4,5,6,7,8,9,10]

// Prettify output:
char *pretty = "-----------------------------------------------------\n";

// BEGIN MODELING THE ENTITIES: Shop, Customer, Products, Stock */
// Structs are data types that hold state but no functionality.

// Product entity:
struct Product{
	char* name; // Assign pointer to name variab
	double price;
	
};

// Stock entity:
// i.e. products available in the shop
// This below will provide a LINK between the stock and the quantity that we have of the stock
struct ProductStock{ 
	struct Product product; // The name
	int quantity;			// The amount of each stock we have
	
};

// Shop entity:
struct Shop{
	double cashfloat;				// Shop's float ("cash" in Dominics')
	struct ProductStock stock[20]; 	// ProductStock is how many of each product we have, i.e. and array, in this case a max of 20
	int index; 
	
};

// Customer entity:
struct Customer{
	char* name; // Pointer* allows memory expansion
	double budget;
	struct ProductStock shopingList[20]; //Customer shopping list i.e. an array
	int index; // Keeps track of the value
	
};

// END MODELING THE ENTITIES

/* Method to print out the product and customer information 
e.g. passing the struct into a method and using it etc*/

// Product info:
void printProduct(struct Product p) // Takes in a product as argument
{
	printf("Product's name is: %s, \n%s's price is: €%.2f,\n", p.name, p.name, p.price); // p was formerly "coke"
	return;
	
}	// Will print product name on one line and the price on the next line

// FUNCTION TO FIND PRODUCT PRICE:
double findProductPrice(struct Shop s, char *n){ // Takes two parameters, shop and entered item name
	
	// Loop that goes through the shop stock 
	for (int i = 0; i < s.index; i++)
	{
		struct Product product = s.stock[i].product;
		char *name = product.name;
		if (strcmp(name,n) == 0){ // strcmp compares strings and returns
			return product.price;
		}
			
	}
	return -2; // Returns -1 if we haven't found it
}

// PROCESS AND PRINT CUSTOMER INFO: 
void printCustomer(struct Customer c, struct Shop s)
{
	printf(pretty);
	printf("\t--- CUSTOMER ORDER ---\n");
	printf(pretty);
	printf(pretty,"\n");
	printf("The customer's name is: %s \nHis/her budget is: %.2f\n", c.name, c.budget); // c was formerly "Gareth": remember, c is customer's name
	printf(pretty,"\n");
	// for loop to loop through the desired customer products needed below
	double total =0;
	for(int i = 0; i < c.index; i++){ // [1],[2]
		for(int j = 0; j < s.index; j++){
		if(strcmp(c.shopingList[i].product.name,s.stock[j].product.name)==0){

        // Return the price of a product as customer CSV does not have the prices 
		printf("\n");
		double itemprice = findProductPrice(s, c.shopingList[i].product.name);
		
//	printf("\n");
// 	printProduct(c.shopingList[i].product); // Passing customers shopping list/Commented out as unnecessary now
	printf("Product's name is: %s, \n%s's price is: €%.2f,\n",c.shopingList[i].product.name, c.shopingList[i].product.name, itemprice); // Remember, it wont find bananas or anything thats not in the shop stock!
      
		
		printf("%s wants to buy %d of the product above\n", c.name, c.shopingList[i].quantity);
		// Above line is chaining of access for the order(how much they want, i.e. quantity)
		
		// Below line is the cost calculator algorithm 
		double cost = c.shopingList[i].quantity * itemprice;
		printf("This will cost %s: €%.2f\n", c.name, cost);
		
		printf("The total cost is now:\t\t%.2f\n\n", cost); 
		total+=cost; // += Means increment variable on the left by value on the right
	} }
      }
	printf("The total cost to %s is:\t%.2f\n",c.name, total); 
	printf("%s's remaining budget is:\t%.2f\n",c.name, c.budget-total); 
	
	// ERROR HANDLING - If total price exceeds customer's budget:
	if (total>c.budget){ 	// If the order cannot be satisfied
		printf("\n");
		printf("SORRY! %s's order total exceeds the\nspecified budget! %s will owe the shop:\n€%.2f\n", c.name, c.name, c.budget-total);
		return;	
	}
				
};

// CREATE PRINT METHOD FOR THE SHOP ITSELF:

void printShop(struct Shop s) // "s": What we have now called the Shop struct (Simply a variable name)
{	printf("\n");
	printf("\n");
	printf(pretty,"\n");
	printf("\t--- SHOP ITINERARY & PRICES ---\n");
	printf(pretty,"\n");
	printf("The shop has €%.2f in the cashfloat\n", s.cashfloat);
	printf(pretty,"\n");
	// Middle part of a for loop: Condition
	for (int i = 0; i < s.index; i++)
	{
		struct Product product = s.stock[i].product;
		printProduct(product);
		printf("The shop has %d of the above in stock\n", s.stock[i].quantity);
		printf(pretty,"\n");
		
	}
};

// ORGANISE CHARACTERS AFFECTING FORMAT: [4], [5]
 char * trimnewline(char *Text){
	// Trim \n character by search
	if (Text[strlen(Text)-1] == '\n'){
		// If found, switch to \o 
		Text[strlen(Text)-1] = '\0';} // 
	// Trim/remove white space/character
	while(isspace((char)*Text)){
		Text++;} //[3]
		// Return formatted:
		return Text;
}


// CREATE THE SHOP FUNCTION AND LOAD THE STOCK:
// Testing area for reading first line of CSV:

struct Shop createAndStockShop()
{ 	// struct that represents the shop:
	struct Shop shop = {0};
	// reading in from the file:
	// Takes 3 arguments
    FILE * fp; // File pointer
    char * line = NULL;
    size_t len = 0;
    ssize_t read;

    fp = fopen("stock.csv", "r"); // r means read w form write etc
    if (fp == NULL)
        exit(EXIT_FAILURE);

    while ((read = getline(&line, &len, fp)) != -1) { // Necessary every time you read a line: CSV data goes in here 
        // printf("Retrieved line of length %zu:\n", read);
        // printf("%s IS A LINE", line);
	if (strstr(line,"cashfloat") != NULL){
        char *n = strtok(line, ",");
        char *c = strtok(NULL, ",");
        double cashfloat = atof(c);
        shop.cashfloat = cashfloat; // Assign new cashfloat to shop struct
		
    }
	
	
	else {
		// String tokenisers at work:
		char *n = strtok(line, ","); // name variable (n)
		char *p = strtok(NULL, ","); // price variable (p) -- NULL doesn't take an input
		char *q = strtok(NULL, ","); // quantity variable (q)
		// Break it apart to extract the: name, price and quantity using 
		int quantity = atoi(q);
		double price = atof(p);
		char *name = malloc(sizeof(char) * 50); 
		strcpy(name, n); // copy from a source to a destination (from name to n)
		//Prepare product and stockItems structures:
		struct Product product = {name, price}; // Putting the above into a product struct and product stock struct
		struct ProductStock stockProd = {product, quantity};
		//shop.stock i.e. index[0] is updated, incremented and updated with value stockProd.      
		shop.stock[shop.index++] = stockProd;
		
		// printf("NAME of product %s PRICE %.2f QUANTITY %d\n", name, price, quantity);
		//printf("NAME OF PRODUCT %s\n", name);
    }

	}
 
	// printProduct(shop.stock[0].product);
	return shop;
	
};

char* attrs[0];
char *name;
char *n;

// CUSTOMER LOADER: [0],[10]
// customer experimentation: 	NEEDS AMENDMENT (RESOLVED)

struct Customer loadCustomerList(char *csvfile) // Takes in CSV file as "parameter" with pointer
{
	printf(pretty,"\n");
	printf("\t--- NEW CUSTOMER DETAILS: ---\n"); 
	printf(pretty,"\n");
	
	printf("The customer's name, budget and order is:\n");
	printf("\n");
	struct Customer customer = {0}; // So we can return customer struct
	struct Shop shop = {0};
	struct Product product = {0};
	double *itemprice;
	FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
	char *	attrs[2]; // CSV attribute (tokens) stored in pointer (Array index) [9]
	
    fp = fopen(csvfile, "r"); // r means read w form write etc
    if (fp == NULL) // If nothing read then exit
		
        exit(EXIT_FAILURE);
	//double itemprice = findProductPrice(shop, "Coffee"); // second parameter takes the product name i.e  "Bread"
	//printf("%.2f", itemprice);	
	
	// Read customer csv line by line
	while ((read = getline(&line, &len, fp)) != -1) {
		int i=0; // Reset the attr array index
		
		printf(line);
		
		char *gd = strtok(line, ","); // Read first value until a comma is encountered
		
		// Read until a null character is encountered
		while (gd != NULL ){ // [9]
			char *attr = malloc(sizeof(char) * 50); // Create local (attr) string variable
			strcpy(attr,trimnewline(gd)); // Copy the value gd to attr after trim/format
			attrs[i++]=attr; // Append attrs to the attr array  
			gd = strtok(NULL, ","); // Read the next attr until null encountered
		}
		
		// If name read in:
		if (strstr(attrs[0],"name") != NULL)
		{
			// Add customer name to the customer structure variable name
			customer.name = attrs[1];
		}
		// Else-if budget is returned:
		else if (strstr(attrs[0],"budget") != NULL)
		{
			// Convert budget value to float
			double budget = atof(attrs[1]);
			// Assign budget to customer struct: 
			customer.budget = budget;
		}
		
		// EXPERIMENTS ON PRICE OUTPUT:		
//		else if (strstr(attrs[0],"Coffee") != NULL)
//		{
//			c.shopingList[i].product.price; = itemprice;
//		}
		
		
		// Else: Add the shopping list quantity and cost to customer and increment the index
		else
		{
			// Product name:
			char* attrs[0]; // assigning mem exp
//			printf(attrs[0]); // prints name of product!! omitted due to print errors
//	char *n = attrs[0]; // add customer product to the customer struct
//			customer.shopingList[i].product.name = attrs[0];

			// Product quantity:
			int q = atoi(attrs[1]); // Convert number of items to integer value
	//		double cost = atof(attrs[1]);// convert the cost to a float (cost variable needs name change!!!)
			  
 
			// SHOULD SOLVE IT, VERY CLOSE
			int i;
			for(int i = 0; i < customer.index; i++){
				for(int j = 0; j < shop.index; j++){

			if(strcmp(customer.shopingList[i].product.name,shop.stock[j].product.name)==0){

         	printf("\n");
		//	double itemprice = findProductPrice(shop, customer.shopingList[i].product.name);		
			printf("%s's price is:%.2f\n", customer.shopingList[i].product.name, itemprice); // Remember, it wont find bananas or anything thats not in the shop stock!!!	
//	 goto end; // goto FAILS because C does not allow it: https://stackoverflow.com/questions/42447494/how-can-i-use-goto-function-across-different-functions-for-outside-main

       }
      }

   }
			
	//	end:	
			// product price/cost:
			double itemprice = findProductPrice(shop,customer.shopingList[i].product.name); 
	//		customer.shopingList[i].product.price = itemprice;
//			printf("%.2f", itemprice); // omitted due to print erros
			
			// Create a product struct with a name and cost/itemprice
			struct Product product = {attrs[0],	 itemprice};
						
			// Add product struct and item quantity to the shoping list:
			struct ProductStock shopingList = {product, q}; 
						
			// Update shopping list with content/increment index: 
			customer.shopingList[customer.index++] = shopingList; 
		}
		
		
		//struct Customer customer = {cuname,budget};
		//printf("CUSTOMER NAME: %s",cuname);
		//printf("CUSTOMER: NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
		//printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", cuname, budget);
		}
	return customer;
	
};




// CUSTOMER CHECKOUT PROCESSOR: 

void checkOut(struct Shop s, struct Customer c){
	// Create variable to store total order cost. Loop through order and calculate total
	printf(pretty);
	printf("\t--- CUSTOMER CHECKOUT ---\n");
	printf(pretty);
	printf("\nComparing shopping list to current stock...\n",c.name, pretty);
	printf("\n%s	Customer's shopping list:\n%s",pretty);
	
	// Check customer list with stock:
	for (int i=0;i<c.index;i++){
		printf("%i. %s: %i %.0\n",i,c.shopingList[i].product.name, c.shopingList[i].quantity); // Format quantity output to resolve (%s:%i %.0)
	}
	printf("\n%s	The shop's CURRENT stock has:\n",pretty);
	printf(pretty);
	for (int i=0;i<s.index;i++){
		printf("%i. %s: %i %.0\n",i,s.stock[i].product.name, s.stock[i].quantity); // With index
		
	} 
	printf(pretty);
	printf("Now processing %s's order...\n", c.name);
	printf("\n");
	
	// Shop stock list compared to customer shopping list:	
	// [7], [8]
	for (int i=0;i<c.index;i++){ // For customer list
		int InStock = 0; // set the InStock variable
		
		for (int j = 0; j < s.index; j++){ // For stock list
			// Memory allocation:
			char *shop = malloc(sizeof(char) * 30);
			strcpy(shop,  s.stock[j].product.name);
			// Look for shopping list item in shop stock:
			// If shopping item is in shop then: 
			if (strstr(c.shopingList[i].product.name, shop) != NULL){
				InStock=1;
				printf("We have [%s] in our current stock,\n", shop);
				
				// Cross reference to see if order quantity can be taken from stock
				int ordquant = c.shopingList[i].quantity; // Assign customer product quantities as integers
				int shopquant = s.stock[j].quantity; // Assign shop stocked product quantities as integers
				if (ordquant<shopquant){ // Compare both to see if the order can be satisfied
					printf("Adding %s to %s's basket...",shop, c.name);
					printf("\n");
					// If order can be satisfied, take item from the stock:
					
					// total+=cashfloat;	
					// shop.cashfloat = total+cashfloat; 
				
				}
				//ERROR HANDLING - If desired quantity not available:
				else if (ordquant>shopquant){ 	//if the order cannot be satisfied
					printf("SORRY! We don't have that quantity of %s\n",shop);
				//	return;
					continue;
				}
				
		// UPDATE STOCK:
		
		//	for(int i = 0; i < c.index; i++){ // [1],[2]
		// for(int j = 0; j < s.index; j++){                (LOOPS NOT NECESSARY ANYMORE)

		// PRICE FIND METHOD INCORPORATED AGAIN FOR PROCESSING:
        if(strcmp(c.shopingList[i].product.name,s.stock[j].product.name)==0){

			printf("\n");
			double itemprice = findProductPrice(s, c.shopingList[i].product.name);
			//printf("\n");
			
				// Diminish the shop stock by customer's order: //WORKING
				s.stock[j].quantity -= c.shopingList[i].quantity;
				
				// Output the shops new stock quantities:
				printf("The shop now has %.0d of %s in stock.\n", s.stock[j].quantity, s.stock[j].product.name);
								
				// Update the cashfloat balance the amount taken in from order
				s.cashfloat += itemprice * c.shopingList[i].quantity;
				
				// Output the shops new cashfloat balance: 
				printf(pretty);
				printf("The cashfloat balance is now: €%.2f\n", s.cashfloat);
				printf(pretty);
            	
			}}		
			
			//ERROR HANDLING - Shopping list item is not is stock:
			while (j == s.index-1 & !InStock) { // If shop does not have what the customer requested
				printf(pretty);
				printf("OOPS! %s is/are not in stock, we will\nomit this from %s's order!", c.shopingList[i].product.name,c.name);
			//	printf("\n");
			
			//UPDATE SHOP STOCK AFTER PROCESSING:
				printf("\n%s	The shop's UPDATED stock now has:\n",pretty);
				printf(pretty);
				for (int i=0;i<s.index;i++){
					printf("%i. %s: %i %.0\n",i,s.stock[i].product.name, s.stock[i].quantity); // Format quantity output (%s:%i %.0)		
				} 
				printf("\n");
				printf("The cashfloat balance is: €%.2f\n", s.cashfloat);
				printf(pretty);
				
				break;
				
			//		return; // Resolved double printing issue
			}
			//	else {};
			//	continue;
			
		}
		
	}

return;
}

double *itemprice;  // declaring and initializing global variables

int main(void)
{	double *itemprice; // For price finder solution

	//DISPLAY INTERACTIVE OPTIONS/MENU:
		
	// INITIAL CALL TO SHOP:
	struct Shop shop = createAndStockShop(); 
	printShop(shop); // Initial print of shop
	
	//CALL CUSTOMER:
    //printCustomer(customer);
	struct Customer customer = loadCustomerList("customer2.csv"); 
	//	loadCustomerList(); // call customer order

//------------------------------------------------------------------------------------------
// BELOW IMP: Fixed the price problem when integrated into printCustomer with nested loops
// PRICE PROBLEM: This method works but cannot pass it into printCustomer function
//for(int i = 0; i < customer.index; i++){
//     for(int j = 0; j < shop.index; j++){

//         if(strcmp(customer.shopingList[i].product.name,shop.stock[j].product.name)==0){

 //printf("\n");
//	double itemprice = findProductPrice(shop, customer.shopingList[i].product.name);
//	printf("\n");
//	printf("%s's price is:%.2f\n", customer.shopingList[i].product.name, itemprice); // Functional: Won't find or process anything not in the shop stock
//      }
//      }

//   }	
	
//	printCustomer(customer,shop); // Moved to call here instead of after load customer due to printing errors(missing products)
//-------------------------------------------------------------------------------------------
	
	// CALL CHECKOUT:
	// checkOut(shop,customer); 	
	
	
	// SHOP MENU:
	printf("\n\n\t--- WELCOME TO THE ONLINE SHOP! ---\n\n\n");
	int choice, num, i; // Declare option-based variables
//	struct Shop shop;
//	struct Customer customer;
	struct Product product;
	
    while(1)
    {	printf(pretty);
		printf("--- MENU OPTIONS ---\n");
		printf(pretty);
		printf("\n");
		// OPTIONS:
		printf("0. Load the shop\n"); // Load the shop
        printf("1. Show shop stock\n"); // Show the shops stock, prices and quantities
		printf("2. Load the customer\n"); // Load the customer
		printf("3. Show the customer's order\n"); // Show the customer's order
		printf("4. Customer checkout\n"); // Process the customers order & provide bill
        printf("5. Customer LIVE mode\n"); // Enter into interactive LIVE mode
        printf("6. Exit\n\n"); // Exit the shop
		
		
        printf("Enter your choice :  ");
        scanf("%d",&choice);
		
        // Switch/case conditions:
        switch(choice)
        {	case 0: // LOAD THE SHOP:
				printf(pretty);
				printf("\n");
				printf("\tLoading the shop...\n");
				printf("\n");
				printf(pretty);
				shop = createAndStockShop();
                break;
			
            case 1: // SHOW SHOP STOCK:
				printf(pretty);
				printf("\n");
                printf("\tSHOP STOCK:\n");
				printf("\n");
				printf(pretty);
				printShop(shop);
                break;
        
			case 2: // LOAD THE CUSTOMER:
				printf(pretty);
				printf("\n");
				printf("\tLoading the customer...\n");
				printf("\n");
				printf(pretty);
				customer; // Calls customer loader
                break;
				
			case 3: // SHOW THE CUSTOMER'S ORDER:
				printCustomer(customer,shop);
                break;
           
			case 4: // CUSTOMER CHECKOUT:	
				checkOut(shop,customer); 	
				printf("%s has left the shop, ",customer.name);
				printf("next customer please!\n");
				printf(pretty);
				break;
					
		case 5: // LIVE MODE OPTION: // [11,12,13,14,15,16,17]
			
printf("\n");
	

//	static int totalCost; // static v'S https://stackoverflow.com/questions/572547/what-does-static-mean-in-c

// DECLARE LIVE MODE VARIABLES:

double	totalCost = 0;
int i;
int j;
int choice;
int c=1; // Option pointer
int a[9]; // Represents	product ID 
double  cost[9];
for(i=0;i<9;i++) // ++ arithmetic operator to increment	value by 1
a[i]=0; 
int yourbudget;
char *str = malloc(sizeof(char) * 90);  // name memory allocator & char for customers name | data type that holds one character
char items[9][100]={ // 9 items in stock array, 100 characters each memory alloc

// Products:
"Bread",  
"Spaghetti", 
"Tomato sauce", 
"Coke can", 
"Coffee", 
"Green tea", 
"Bin`bags",
"Bleach",	
"Fairy liquid", 

 };
	printf(pretty);
	printf("--- LIVE SHOP ---\n");
	printf(pretty);
	printf(pretty);  	
	printf("Welcome to the LIVE shop!\n\nPlease tell us your name...\t");
	printf("\n");
	printf(pretty);
	scanf("%s",str); //i let you read the doc to avoid overflow :)
	printf("What is your budget %s?\t",str);
	scanf("%d",&yourbudget);
	printf("Hello %s!...Enjoy spending your €%d\n\n\n",str,yourbudget);
	system("pause");
 do{
  //C is 1 by default
  if(c==1){
  printf(pretty);
  printf("Please take the time to explore our products in stock:\n\n1:- FOOD ITEMS\n2:- DRINKS\n3:- CLEANING ITEMS\nPress any other number to go to exit the shop\n");
  scanf("%d",&choice); // https://computer.howstuffworks.com/c7.html first parameter is an int, second is users choice.
  switch(choice) // A switch statement allows a variable to be tested for equality against a list of values. Each value is called a case, and the variable being switched on is checked for each switch case. (https://www.tutorialspoint.com/cprogramming/switch_statement_in_c.html)
  {
	case 1: // FOOD PRODUCTS:
	{
		int foodChoice;
		printf(pretty);
		printf("Choose a product:\n\n1:- Bread - €0.70\n2:- Spaghetti - €1.20\n3:- Tomato Sauce - €0.80\nPress any other number to go to the checkout\n");
		scanf("%d",&foodChoice);
		cost[0]=0.70;
		cost[1]=1.20;
		cost[2]=0.80;
		switch(foodChoice)
    {
    case 1: // Choice 1 
    {
		int num;
		printf("You chose Bread with €0.70. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
		scanf("%d",&num);
		if(num==1)
		{
		a[0]++;
		totalCost+=0.70;//+= means incrementing the variable on the left by value on the right.
		}
		printf("Your cost in cart is:€%.2f\n",totalCost);
		break;
	}
 case 2: // Choice 2 
    {
		int num;
		printf("You chose Spaghetti with €1.20. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
		scanf("%d",&num); 
		if(num==1)
		{
		a[1]++;
		totalCost+=1.20;
		}
		printf("Your cost in cart is:€%.2f\n",totalCost);
		break;
    }
    case 3: // Choice 3
    {
		int num;
		printf("You chose Tomato sauce with €0.80. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
		scanf("%d",&num);
		if(num==1)
		{
		a[2]++;
		totalCost+=0.80;
		}
		printf("Your cost in cart is:€%.2f\n",totalCost);
		break;
    }
    default:{
    printf("Exit from FOOD PRODUCTS\n"); // change to exit from food items
    break;
    }
 }
 break;
   }
   case 2: // DRINK PRODUCTS:
   {
    int drinkChoice; // change to drinkChoice
	printf(pretty);
    printf("Choose a product:\n\n1:- Coke cans - €1.10\n2:- Coffee - €2.80\n3:- Green tea - €3.10\n Press any other number to go to the checkout\n");
    scanf("%d",&drinkChoice);
    cost[3]=1.10;
    cost[4]=2.80;
    cost[5]=3.10;
    switch(drinkChoice)
    {
     case 1:
     {
      int num;
      printf("You chose Coke cans for €1.10. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[3]++;
       totalCost+=1.10;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
     }
	 case 2:
     {
      int num;
      printf("You chose Coffee for €2.80. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[4]++;
       totalCost+=2.80;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
     }
	 case 3:
     {
      int num;
      printf("You chose Green tea for €3.10. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[5]++;
       totalCost+=3.10;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
     }
     default:{ // default argument is an argument to a function that a programmer is not required to specify.
      printf("Exit from DRINKS Category\n");
      break;
     }
	 }
    break;
   }
   case 3: // CLEANING PRODUCTS:
   {
    int cleanChoice; //
	printf(pretty);
    printf("Choose a product:\n\n1:- Bin Bags - €2.50\n2:- Bleach - €1.75\n3:- Fairy liquid - €0.95\nPress any other number to go to the checkout\n");
    scanf("%d",&cleanChoice);
    cost[6]=2.50;
    cost[7]=1.75;
    cost[8]=0.95;
    switch(cleanChoice)
    {
     case 1:
     {
      int num;
      printf("You chose to buy Bin bags for €2.50. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[6]++;
       totalCost+=2.50;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
 }
 case 2:
     {
      int num;
      printf("You chose to buy Bleach for €1.75. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[7]++;
       totalCost+=1.75;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
     }
	 case 3:
     {
      int num;
      printf("You chose to buy Fairy liquid for €0.95. Are you sure you want to buy this?\n\nIf 'Yes' please enter 1, or press any number to continue shopping\n");
      scanf("%d",&num);
      if(num==1)
      {
       a[8]++;
       totalCost+=0.95;
      }
      printf("Your cost in cart is:€%.2f\n",totalCost);
      break;
     }
     default:{
      printf("Exit from CLEANING PRODUCTS\n"); 
      break;
     }
    }
    break;
   }
   
 default:
   {// ERROR HANDLING:
    printf("OOPS! Please enter a valid category choice\n");
    break;
   }
  }
  printf("\n");
  printf("%s's cart:\n",str);
  printf("\n");
  printf("ID\tItems\t\tQuantity\tCost\n");
  
// VARIABLES:
// i = ID
// items.get(i) = Item name
// a[i] = quantity
// (cost[i] * a[i]) = cost
  
  for(i=0;i<9;i++)
  {
   if(a[i]!=0)
   {
    printf("%d\t%s\t\t%d\t\t\t€%.2f\n",i,items[i],a[i],(cost[i]*a[i]));
   }
  }
  
  printf("Total Cost\t\t\t\t\t€%.2f\n",totalCost);
  printf("\n");
  printf("If you wish to buy anything more please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");
  scanf("%d",&c);
 }
 
 // PRODUCT IDs: [11, 16]
  if(c==2)
  {
   int id;
   printf("Please enter the product ID to delete item\n");
   scanf("%d",&id);
   if(id<9&&id>0){
   totalCost=totalCost-(cost[id]*a[id]);
   a[id]=0; // ID ref set
   }
   // ERROR HANDLING:
   else{
    printf("OOPS! Enter valid ID\n");
   }
       printf("Revised items:\n");
       printf("ID\tItems\t\tQuantity\tCost\n");
            for(i=0;i<9;i++)
      {
     if(a[i]!=0)
      {
    printf("%d\t%s\t%d\t\t€%.2f\n",i,items[i],a[i],(cost[i]*a[i]));
      }
     }
        printf("Total Cost\t\t\t\t\t€%.2f\n",totalCost);
        printf("If you wish to buy anything more, please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");
     scanf("%d",&c);
  }
  if(c==3) // If choice is 3 then:
  {
   int id,quantity;
   printf("Please enter the product ID to change the quantity of an item\n");
   scanf("%d",&id);
   printf("Enter quantity to be changed of an item\n");
   scanf("%d",&quantity);
   if(id<9&&id>0){
    if(quantity>0 && a[id]>0){  
        if(quantity<a[id]) 
     {
      int dec=a[id]-quantity; // decrease
      a[id]=quantity;
       totalCost=totalCost-(cost[id]*dec); 
     }
     if(quantity>a[id]) 
     {
      int inc=quantity-a[id];// increase
      a[id]=quantity;
       totalCost=totalCost+(cost[id]*inc); 
     }
     if(quantity==a[id]) 
     {
      a[id]=quantity;
       totalCost=totalCost+0; 
     }   
           
    }
    else{ // Error handling:
       printf("OOPS! This item has no quantity. Please try again\n");
    }
     }
     else{
    printf("Please enter a valid product ID\n");
   }
       printf("Revised Items \n");
       printf("ID\tItems\t\tQuantity\tCost\n");
            for(i=0;i<9;i++)
      {
     if(a[i]!=0)
      {
    printf("%d\t%s\t%d\t\t€%.2f\n",i,items[i],a[i],(cost[i]*a[i]));
      }
     }
        printf("Total Cost\t\t\t\t\t€%.2f\n",totalCost);
        printf("If you wish to buy anything more please choose:\n\n\n1 to Add Item\n2 to Delete Items\n3 to Change Quantity \nPress any other number to go to the checkout\n");
     scanf("%d",&c);
}
}		while(c==1 || c==2 ||c==3);
			printf("\n");
			printf(pretty);
			printf("-------------------CUSTOMER BILL---------------------\n");
			printf("%s, your final bill comes to: €%.2f\n",str, totalCost); // problem with printing the final total cost(RESLOVED)
			printf("\n");
			printf("%s, your leftover budget is: €%.2f\n",str,yourbudget - totalCost); // Output the customer's leftover budget
			printf("\n");
			printf("Thank you for choosing us %s, please visit us again!\n",str);
			printf(pretty); 			
        break;  // LIVE MODE ENDS
        
            
			
			case 6: // EXIT OPTION:
                printf("\n\tExiting the shop...Take care!\n\n");
                exit(0);    // terminates the complete program execution
			}
			}
	
 // return 0; 

}

// MISCELLANEOUS ASSOCIATED STRUCTS:
	
	//struct Customer gareth = {"Gareth",100.0};	
		
	//struct Product coke = {"Can Coke", 1.10};
	//struct Product bread = {"Bread", 0.70};
	// printProduct(coke); // Calling the method (coke)
	
	//struct ProductStock cokeStock = {coke, 20};
	//struct ProductStock breadStock = {bread, 2};
	
	//gareth.shoppingList[gareth.index++]= cokeStock; // [0] in the array/retrieves the index value (This adds an item to the customers order)
	//gareth.shoppingList[gareth.index++]= breadStock;
	
	//printCustomer(gareth); // Calling the method
	// printf("The shop has %d of the product %s\n", cokeStock.quantity, cokeStock.product.name); /* Pulling data out of parent data */

//--------------------------------------------------------------------------------------------------------------//

// INDEX OF UNRESOLVED ISSUES:														STATUS OF ISSUE:

// Cannot facilitate shopping list prices unless by storing them in customer CSV: 	RESOLVED (Loops in printCustomer with price finder)
// Cannot fix double printing error that occurs in output of customer requests: 	RESLVED  ISSUE (whitespace in CSV)
// Cannot throw error if customer's budget is less than their shopping list total:  RESOLVED ISSUE (Created loop and amended to suit)
// Cannot have BOTH quantity lack and product not in stock errors at same time: 	RESOLVED ISSUE (Amended layout of loops)
// When two non-stocked items are unavailable, only one error is thrown:			RESOLVED ISSUE (Provided break in loop)
// Cannot print customers LIVE input 												RESOLVED ISSUE

//--------------------------------------------------------------------------------------------------------------//

// REFERENCES:

// [0]: https://www.geeksforgeeks.org/data-structures/
// [1]: https://rosettacode.org/wiki/Loops/Increment_loop_index_within_loop_body#C
// [2]: https://beginnersbook.com/2014/01/c-for-loop/
// [3]: https://stackoverflow.com/questions/122616/how-do-i-trim-leading-trailing-whitespace-in-a-standard-waySS
// [4]: https://stackoverflow.com/questions/29147785/how-to-compare-length-of-4-strings-according-to-strlen
// [5]: https://www.tutorialspoint.com/cprogramming/c_strings.htm
// [6]: https://www.programiz.com/c-programming/c-if-else-statement
// [7]: https://www.programiz.com/c-programming/c-for-loop
// [8]: https://www.geeksforgeeks.org/data-types-in-c
// [9]: https://stackoverflow.com/questions/23970329/how-to-store-tokensstrtok-in-a-pointer-on-an-array

// [10]: https://courses.cs.washington.edu/courses/cse351/16wi/sections/1/Cheatsheet-c.pdf
// [11]: http://www.cprograms4future.com/p/online-shopping.html
// [12]: https://www.geeksforgeeks.org/switch-statement-cc/
// [13]: https://www.tutorialspoint.com/cprogramming/switch_statement_in_c.html)
// [14]: https://www.tutorialspoint.com/cprogramming/c_do_while_loop.htm
// [15]: https://www.quora.com/What-is-the-importance-of-a-default-statement-in-C-programming
// [16]: https://www.tutorialspoint.com/cprogramming/nested_if_statements_in_c.htm
// [17]: http://www.cprograms4future.com/p/list-of-all-c-programs-in-my-blog.html

//--------------------------------------------------------------------------------------------------------------//



