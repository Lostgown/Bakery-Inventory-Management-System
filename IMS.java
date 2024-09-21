import java.util.Date;
import java.util.Scanner;

public class IMS {
    
    public static void clearScreen() {  
        //System.out.print("\033[H\033[2J");  
        //System.out.flush();
        for(int i = 0; i <50; i++){
            System.out.println(" ");
        }  
    }

    //method to refresh stock
    public static void updateStockLevels(Product[] products, StockMovement[] stockMovements) {
        for (int i = 0;  i < stockMovements.length; i++) {
            if (stockMovements[i] != null) {
                ItemDetails itemDetails = stockMovements[i].getItemDetails();
                Product product = itemDetails.getProduct();
                int orderQty = itemDetails.getOrderQty();
    
                if (stockMovements[i] instanceof PurchaseOrder) {
                    // Increase stock level if it's a purchase order
                    product.getStockLevel().increaseStock(orderQty);
                } else if (stockMovements[i] instanceof InventoryTransfer) {
                        // Decrease stock level if it's an inventory transfer to other branch
                    if((((InventoryTransfer) stockMovements[i]).getTransferFrom()).equals("Warehouse")){
                        product.getStockLevel().decreaseStock(orderQty);
                    }else{
                        //Increase stock level if not
                        product.getStockLevel().increaseStock(orderQty);
                    }
                }
            }
        }
    }

    //Stock Out Method
    public static void stockOut(int option, Product[] products, StockMovement[] stockMovements,double balance) {
        int productIndex = option - 1;  
        Product product = products[productIndex];  
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Stock Out for Product: " + product.getDescription());
        
        // Handle input for quantity
        System.out.print("Enter quantity to transfer out: ");
        while (!sc.hasNextInt()) {  // Check if the input is an integer
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();  // Consume invalid input
        }
        int quantity = sc.nextInt();
        
        
        sc.nextLine();  // Ensure the newline character is consumed
    
        // Check if there's enough stock to transfer out
        if (quantity <= product.getStockLevel().getStockQty()) {
            // Get the destination branch or location
            System.out.print("Enter the destination (Branch Name): ");
            String destination = sc.nextLine();  
    
            // Update the stock level
            product.getStockLevel().decreaseStock(quantity);
            
            // Create a new InventoryTransfer object
            ItemDetails itemDetails = new ItemDetails(product, quantity);
            InventoryTransfer stockOutMovement = new InventoryTransfer(new Date(), itemDetails, "T" + (stockMovements.length + 1), "Warehouse", destination);
            
            // Add the new stock movement to the stockMovements array
            for (int i = 0; i < stockMovements.length; i++) {
                if (stockMovements[i] == null) {  // Find the first empty slot
                    stockMovements[i] = stockOutMovement;
                    break;
                }
            }
            
            System.out.println("Stock Out successful! " + quantity + " units transferred to " + destination);
        } else {
            System.out.println("Insufficient stock! Available quantity: " + product.getStockLevel().getStockQty());
        }
    
        // Return to the Stock Out Menu
        System.out.print("Press any key to return to Stock Out Menu >>>>");
        sc.nextLine();
        stockOutMenu(products, stockMovements,balance);
    }

    //Stock Out Menu
    public static void stockOutMenu(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("Stock Out");
        System.out.println("=========");
        System.out.printf(" %-10s %-20s %-10s%n", 
        "PRODUCT ID", "DESCRIPTION", "STOCK.QTY");
        System.out.println("----------------------------------------------------------");
        //show product stock
        for (int i = 0;  i < products.length; i++) {
            if (products[i] != null) {  // Check if the slot contains data
                System.out.print("(" + (i+1) + ")");
                System.out.printf("%-10s %-20s %-10d %n", 
                          products[i].getProductId(), 
                          products[i].getDescription(), 
                          products[i].getStockLevel().getStockQty());
            }
        }
        System.out.println("\n(0) Return");
        System.out.print("Select a product(1-5): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                stockOut(option,products,stockMovements,balance);
                break;
            case 0:
                clearScreen();
                updateStockMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                stockOutMenu(products,stockMovements,balance);
        }
    }
    //Purchase Order
    public static void generatePO(int option,Product[] products,StockMovement[] stockMovements,double balance){
        int productIndex = option - 1;  
        Product product = products[productIndex];
        Supplier supplier = product.getSupplier();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Stock In for Product: " + product.getDescription());
        
        // Handle input for quantity
        System.out.print("Enter quantity to stock in: ");
        while (!sc.hasNextInt()) {  // Check if the input is an integer
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();  // Consume invalid input
        }
        int quantity = sc.nextInt();
        
        
        sc.nextLine();

        if (quantity + product.getStockLevel().getStockQty() <= product.getStockLevel().getMaxStock()) {
            // Generate PO
            System.out.println("\t\t\t\tPURCHASE ORDER");
            System.out.println("=====================================================================");
            System.out.printf("%-15s %-25s %-15s %-15s %10s%n", 
            "PO Number", "Supplier", "Product", "Quantity","Carton Price");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.printf("%-15s %-25s %-15s %-15d %5.2f%n", 
                            "PO" + (stockMovements.length + 1), 
                            supplier.getCompany(), 
                            product.getDescription(), 
                            quantity,
                            product.getCartonPrice());
            System.out.printf("%70s %10.2f%n","Total Price",PurchaseOrder.calcTotalPrice(product.getCartonPrice(),quantity));
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Supplier Address: " + supplier.getAddress().toString());
            System.out.println("Contact: " + supplier.getPhone());
            System.out.println("====================================================================================");
            System.out.println("Current Account Balance: RM" + String.format("%.2f", balance));
            System.out.print("Are you sure to purchase(Press 1 to continue,press 0 to quit)>>>");
            while (!sc.hasNextInt()) {  // Check if the input is an integer
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();  // Consume invalid input
            }
            int confirm = sc.nextInt();
            sc.nextLine(); 

            if (confirm != 0) {
                if (PurchaseOrder.calcTotalPrice(product.getCartonPrice(),quantity)> balance) {
                    System.out.println("Not enough account balance to purchase stock!!!");
                    System.out.print("Press any key to return to Stock In Menu >>>>");
                    sc.nextLine();
                    stockInMenu(products, stockMovements,balance); 
                } else{
                    //Update balance
                    balance -= PurchaseOrder.calcTotalPrice(product.getCartonPrice(),quantity);
                    // Update the stock level
                    product.getStockLevel().increaseStock(quantity);
                    
                    // Create a new Purchase Order
                    ItemDetails itemDetails = new ItemDetails(product, quantity);
                    PurchaseOrder stockOutMovement = new PurchaseOrder(new Date(), itemDetails, "PO" + (stockMovements.length + 1), supplier);
                    
                    // Add the new stock movement to the stockMovements array
                    for (int i = 0; i < stockMovements.length; i++) {
                        if (stockMovements[i] == null) {  // Find the first empty slot
                            stockMovements[i] = stockOutMovement;
                            break;
                        }
                    }
                    
                    System.out.println("Purchase successful! " + quantity + " units Stock In from Supplier");
                    System.out.println("RM" + String.format("%.2f",(PurchaseOrder.calcTotalPrice(product.getCartonPrice(),quantity))) + " deducted");
                    System.out.println("New Account Balance :" + String.format("%.2f", balance));
                }
            }else {
                clearScreen();
                stockInMenu(products, stockMovements,balance);
            }

            
        } else {
            System.out.println("Stock exceed maximum stock quantity(Quantity after Stock In) : " + (product.getStockLevel().getStockQty() + quantity));
        }
    
        // Return to the Stock In Menu
        System.out.print("Press any key to return to Stock In Menu >>>>");
        sc.nextLine();
        stockInMenu(products, stockMovements,balance);
    }

    //Stock In Inventory Transfer Method
    public static void stockInInventory(int option,Product[] products,StockMovement[] stockMovements,double balance){
        int productIndex = option - 1;  
        Product product = products[productIndex];  
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Stock In for Product: " + product.getDescription());
        
        // Handle input for quantity
        System.out.print("Enter quantity to stock in: ");
        while (!sc.hasNextInt()) {  // Check if the input is an integer
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();  // Consume invalid input
        }
        int quantity = sc.nextInt();
        
        sc.nextLine();  // Ensure the newline character is consumed
    
        // Check if stock exceed max quantity
        if (quantity + product.getStockLevel().getStockQty() <= product.getStockLevel().getMaxStock()) {
            // Get the destination branch or location
            System.out.print("Enter the source (Branch Name): ");
            String source = sc.nextLine();  
    
            // Update the stock level
            product.getStockLevel().increaseStock(quantity);
            
            // Create a new InventoryTransfer object
            ItemDetails itemDetails = new ItemDetails(product, quantity);
            InventoryTransfer stockOutMovement = new InventoryTransfer(new Date(), itemDetails, "T" + (stockMovements.length + 1), source,"Warehouse");
            
            // Add the new stock movement to the stockMovements array
            for (int i = 0; i < stockMovements.length; i++) {
                if (stockMovements[i] == null) {  // Find the first empty slot
                    stockMovements[i] = stockOutMovement;
                    break;
                }
            }
            
            System.out.println("Stock In successful! " + quantity + " units stock In from " + source);
        } else {
            System.out.println("Stock exceed maximum stock quantity(Quantity after Stock In) : " + (product.getStockLevel().getStockQty() + quantity));
        }
    
        // Return to the Stock Out Menu
        System.out.print("Press any key to return to Stock In Menu >>>>");
        sc.nextLine();
        stockInMenu(products, stockMovements,balance);
        
    }

    //Stock In Menu
    public static void stockInMenu(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("Stock In");
        System.out.println("=========");
        System.out.printf(" %-10s %-20s %-10s %10s %n", 
        "PRODUCT ID", "DESCRIPTION", "STOCK.QTY", "SUPPLIER");
        System.out.println("------------------------------------------------------------------------------------");
        //show product stock
        for (int i = 0;  i < products.length; i++) {
            if (products[i] != null) {  // Check if the slot contains data
                System.out.print("(" + (i+1) + ")");
                System.out.printf(" %-10s %-20s %-10d %-20s %n", 
                          products[i].getProductId(), 
                          products[i].getDescription(), 
                          products[i].getStockLevel().getStockQty(),
                          products[i].getSupplier().getCompany());
            }
        }
        System.out.println("\n(0) Return");
        System.out.print("Select a product(1-5): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        
        switch (option) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("\n(1) Inventory Transfer");
                System.out.println("(2) Purchase");
                System.out.println("(0) Return");
                System.out.print("Select a method(1-2): ");
                int method = sc.nextInt();
                switch (method){
                            case 1:
                                stockInInventory(option,products,stockMovements,balance);
                                break;
                            case 2:
                                generatePO(option, products, stockMovements,balance);
                                break;
                            case 0:
                                stockInMenu(products,stockMovements,balance);
                                break;
                            default:
                                System.out.println("!!!Invalid Input!!!");
                                stockInMenu(products,stockMovements,balance);
                        }
            case 0:
                clearScreen();
                updateStockMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                stockInMenu(products,stockMovements,balance);
        }
        
                
    }

    //Update StockMenu
    public static void updateStockMenu(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("Update Stock Menu");
        System.out.println("=================");
        System.out.println("(1) Stock In");
        System.out.println("(2) Stock Out");
        System.out.println("(0) Return");
        System.out.print("Select an option(1-2): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                clearScreen();
                stockInMenu(products,stockMovements,balance);
                break;
            case 2:
                clearScreen();
                stockOutMenu(products,stockMovements,balance);
                break;
            case 0:
                clearScreen();
                mainMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                updateStockMenu(products,stockMovements,balance);
        }
    }

    //method to check stock
    public static void checkProductStock(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("PRODUCT STOCK");
        System.out.println("=============");
        System.out.printf("%-15s %-30s %-25s %-10s%n", "PRODUCT ID", "DESCRIPTION", "REMAINING STOCK (CARTON)", "STATUS");
        System.out.println("---------------------------------------------------------------------------------------------");
        //Print out all product stock here ***
        for (int i = 0;  i < products.length; i++) {
            if (products[i] != null) {  // Check if the slot contains data
                System.out.printf("%-15s %-30s %-25d %-10s %n", 
                          products[i].getProductId(), 
                          products[i].getDescription(), 
                          products[i].getStockLevel().getStockQty(),products[i].getStockLevel().checkStockLevel(products[i].getStockLevel().getStockQty(), products[i].getStockLevel().getMinStock()));
            }
        }
        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                mainMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                viewProductDetails(products,stockMovements,balance);
        }
    }

    //method to view product
    public static void viewProductDetails(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("PRODUCT DETAILS");
        System.out.println("===============");
        System.out.printf("%-10s %-20s %-15s %-10s | %-30s %-15s %-30s%n", 
        "PRODUCT ID", "DESCRIPTION", "CATEGORY", "Price(Carton)", "SUPPLIER", "PHONE NO", "ADDRESS");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        //Print out all product here ***
        for (int i = 0;  i < products.length; i++) {
            if (products[i] != null) {  // Check if the slot contains data
                System.out.println(products[i]);
            };
        }
        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                reportMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                viewProductDetails(products,stockMovements,balance);
        }
    }
    //Method to check Stock Movement
    public static void viewStockMovement(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("STOCK MOVEMENT");
        System.out.println("==============");
        System.out.printf("%-13s| %-10s %-30s %-10s %-20s %-15s %-15s %-15s%n", 
        "MOVEMENT TYPE", "MOVEMENT ID","DATETIME" ,"PRODUCT ID", "DESCRIPTION", "MOVEMENT QTY", "T.FROM", "T.TO");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0; i<stockMovements.length; i++){
            if (stockMovements[i] != null) {
                if(stockMovements[i] instanceof InventoryTransfer){
                    System.out.printf("%14s %-10s ","Transfer|",((InventoryTransfer) stockMovements[i]).getTransferID());
                } else if (stockMovements[i] instanceof PurchaseOrder){
                    System.out.printf("%14s %-10s ","Purchase|",((PurchaseOrder) stockMovements[i]).getPurchaseID());
                }
                System.out.printf("%-30s %-10s %-20s %12d ",
                    stockMovements[i].getDate(),
                    stockMovements[i].getItemDetails().getProduct().getProductId(),
                    stockMovements[i].getItemDetails().getProduct().getDescription(),
                    stockMovements[i].getItemDetails().getOrderQty()                    
                );
                //print transfer from to
                if(stockMovements[i] instanceof InventoryTransfer){
                    System.out.printf(" %-12s %-12s %n",((InventoryTransfer) stockMovements[i]).getTransferFrom(),((InventoryTransfer) stockMovements[i]).getTransferTo());
                } else if (stockMovements[i] instanceof PurchaseOrder){
                    System.out.printf(" %-12s %-12s %n","Supplier","Warehouse");
                }
            }
        }
        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                reportMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                viewProductDetails(products,stockMovements,balance);
        }
    }

    //method for reportMenu
    public static void reportMenu(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("Report Menu");
        System.out.println("===========");
        System.out.println("(1) View All Product Details");
        System.out.println("(2) View Stock Movement Report");
        System.out.println("(0) Return");
        System.out.print("Select an option(1-2): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                clearScreen();
                viewProductDetails(products,stockMovements,balance);
                break;
            case 2:
                clearScreen();
                viewStockMovement(products,stockMovements,balance);
                break;
            case 0:
                clearScreen();
                mainMenu(products,stockMovements,balance);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                reportMenu(products,stockMovements,balance);
        }
    }
    
    public static void mainMenu(Product[] products,StockMovement[] stockMovements,double balance){
        System.out.println("Warehouse Inventory Management System");
        System.out.println("=====================================");
        System.out.println("Current Account Balance: RM" + String.format("%.2f", balance));
        System.out.println("(1) Check Product Stock Level");
        System.out.println("(2) Update Stock");
        System.out.println("(3) View Report");
        System.out.println("(0) Exit");
        System.out.print("Select an option(1-3): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                clearScreen();
                checkProductStock(products,stockMovements,balance);
                break;
            case 2:
                clearScreen();
                updateStockMenu(products,stockMovements,balance);
                break;
            case 3:
                clearScreen();
                reportMenu(products,stockMovements,balance);
                break;
            case 0:
                System.exit(-1);
                break;
            default:
                clearScreen();
                System.out.println("!!!Invalid Input!!!");
                mainMenu(products,stockMovements,balance);
                
        }
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        //DATASET
        //address
        Address address1 = new Address("34,Jln Kanan","Kiri","Bawah","33700","Delhi");
        Address address2 = new Address("12,Jln Kiri","Kanan","Atas","77300","Mumbai");
        Address address3 = new Address("56,Jln Japan","Atas","Tengah","88810","Taiwan");
        Address address4 = new Address("43,Jln India","Bawah","Kiri","77788","Bangladesh");
        Address address5 = new Address("21,Jln Port","Tengah","Kanan","11300","Roma");
        //Supplier
        Supplier supplier1 = new Supplier("Coke Sdn. Bhd.","0129998888",address1);
        Supplier supplier2 = new Supplier("Sprite Sdn. Bhd.","0129876543",address2);
        Supplier supplier3 = new Supplier("100 Sdn. Bhd.","0134567890",address3);
        Supplier supplier4 = new Supplier("Milo Sdn. Bhd.","0145678901",address4);
        Supplier supplier5 = new Supplier("Hip Sang Sdn. Bhd.","0156789012",address5);
        //StockLvl
        StockLevel stockLevel1 = new StockLevel(2140,200,10000);
        StockLevel stockLevel2 = new StockLevel(3450,200,10000);
        StockLevel stockLevel3 = new StockLevel(1420,200,10000);
        StockLevel stockLevel4 = new StockLevel(845,200,10000);
        StockLevel stockLevel5 = new StockLevel(380,400,10000);
        //Product
        Product[] products = new Product[5];
        products[0] = new Product("P001","Coke",49.99,24,"Beverage",supplier1,stockLevel1);
        products[1] = new Product("P002","Sprite",46.89,24,"Beverage",supplier2,stockLevel2);
        products[2] = new Product("P003","100 Plus",44.79,24,"Beverage",supplier3,stockLevel3);
        products[3] = new Product("P004","Milo(Packet)",259.20,24,"Food",supplier4,stockLevel4);
        products[4] = new Product("P005","Hip Sang",69.90,12,"Food",supplier5,stockLevel5);
        //ItemDetails
        ItemDetails[] itemDetails = new ItemDetails[1000];
        itemDetails[0] = new ItemDetails(products[0], 140);
        itemDetails[1] = new ItemDetails(products[1], 450);
        itemDetails[2] = new ItemDetails(products[2], 20);
        itemDetails[3] = new ItemDetails(products[3], 45);
        itemDetails[4] = new ItemDetails(products[4], 4220);
        //InventoryTransfer
        StockMovement[] stockMovements = new StockMovement[1000];
        stockMovements[0] = new InventoryTransfer(new Date(124, 8, 8,17,34),itemDetails[0],"T0001","Warehouse","BranchA");
        stockMovements[1] = new InventoryTransfer(new Date(124, 8, 10,14,56),itemDetails[1],"T0002","BranchB","Warehouse");
        stockMovements[2] = new InventoryTransfer(new Date(124, 8, 11,13,50),itemDetails[2],"T0003","BranchA","Warehouse");
        stockMovements[3] = new InventoryTransfer(new Date(124, 8, 11,16,30),itemDetails[3],"T0003","Warehouse","BranchB");
        //PurchaseOrder
        stockMovements[4] = new PurchaseOrder(new Date(124, 8, 8,11,30),itemDetails[4],"PO0001",supplier5);
        //Virtual Account Money Balance
        double balance = 1399958.30;

        updateStockLevels(products, stockMovements);
        mainMenu(products,stockMovements,balance);
        
        
    }
}

