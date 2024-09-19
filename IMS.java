import java.util.Scanner;

public class IMS {
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void checkProductStock(){
        System.out.println("Product Stock");
        System.out.println("=============");
        //Print out all product stock here ***

        System.out.print("Press 0 to return: ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                clearScreen();
                mainMenu();
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                checkProductStock();
        }
    }

    public static void reportMenu(){
        System.out.println("Report Menu");
        System.out.println("===========");
        System.out.println("(1) View All Product Details");
        System.out.println("(2) View Stock Movement Report");
        System.out.println("(0) Return");
        System.out.println("Select an option(1-2): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                clearScreen();
                //
                break;
            case 2:
                clearScreen();
                //
                break;
            case 0:
                clearScreen();
                mainMenu();
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                reportMenu();
        }
    }
    
    public static void mainMenu(){
        System.out.println("Warehouse Inventory Management System");
        System.out.println("=====================================");
        System.out.println("(1) Check Product Stock Level");
        System.out.println("(2) Update Stock");
        System.out.println("(3) View Report");
        System.out.println("(0) Exit");
        System.out.print("Select an option(1-4): ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                clearScreen();
                checkProductStock();
                break;
            case 2:
                clearScreen();
                //
                break;
            case 3:
                clearScreen();
                reportMenu();
                break;
            case 0:
                System.exit(-1);
                break;
            default:
                clearScreen();
                System.out.println("\u001B[31mInvalid Input\u001B[0m");
                mainMenu();
                
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
        Supplier supplier2 = new Supplier("Coke Sdn. Bhd.","0129998888",address2);
        Supplier supplier3 = new Supplier("Coke Sdn. Bhd.","0129998888",address3);
        Supplier supplier4 = new Supplier("Coke Sdn. Bhd.","0129998888",address4);
        Supplier supplier5 = new Supplier("Coke Sdn. Bhd.","0129998888",address5);
        //Product
        mainMenu();
        
    }
}

