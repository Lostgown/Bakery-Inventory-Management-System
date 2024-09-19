import java.util.Scanner;

public class IMS {
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    //Update Stock
    //Stock In

    //Stock Out

    //method to check stock
    public static void checkProductStock(Product[] products){
        System.out.println("PRODUCT STOCK");
        System.out.println("=============");
        System.out.printf("%-15s %-30s %-25s %-10s%n", "PRODUCT ID", "DESCRIPTION", "REMAINING STOCK (CARTON)", "STATUS");
        System.out.println("---------------------------------------------------------------------------------------------");
        //Print out all product stockhere ***
        for (int i = 0;  i < products.length; i++) {
            System.out.printf("%-15s %-30s %-25d %-10s %n", 
                          products[i].getProductId(), 
                          products[i].getDescription(), 
                          products[i].getStockLevel().getStockQty(),products[i].getStockLevel().checkStockLevel(products[i].getStockLevel().getStockQty(), products[i].getStockLevel().getMinStock()));
        }
        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                mainMenu(products);
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                viewProductDetails(products);
        }
    }

    //method to view product
    public static void viewProductDetails(Product[] products){
        System.out.println("PRODUCT DETAILS");
        System.out.println("===============");
        System.out.printf("%-10s %-20s %-15s %-10s | %-30s %-15s %-30s%n", 
        "PRODUCT ID", "DESCRIPTION", "CATEGORY", "Price(Carton)", "SUPPLIER", "PHONE NO", "ADDRESS");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        //Print out all product here ***
        for (int i = 0;  i < products.length; i++) {
            System.out.println(products[i]);
        }
        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                reportMenu(products);
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                viewProductDetails(products);
        }
    }

    public static void reportMenu(Product[] products){
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
                viewProductDetails(products);
                break;
            case 2:
                clearScreen();
                //
                break;
            case 0:
                clearScreen();
                mainMenu(products);
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                reportMenu(products);
        }
    }
    
    public static void mainMenu(Product[] products){
        System.out.println("Warehouse Inventory Management System");
        System.out.println("=====================================");
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
                checkProductStock(products);
                break;
            case 2:
                clearScreen();
                //
                break;
            case 3:
                clearScreen();
                reportMenu(products);
                break;
            case 0:
                System.exit(-1);
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                mainMenu(products);
                
        }
    }

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

        mainMenu(products);
        
    }
}

