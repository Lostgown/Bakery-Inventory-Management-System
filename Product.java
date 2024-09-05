public class Product extends Item {
    private double price;

    public Product(String id, String name, int quantity,double price){
        super(id, name, quantity);
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void updateStock(int quantity){
        setQuantity(getQuantity() + quantity);
    }

    @Override
    public String getDetails(){
        return super.getDetails() + " | Price: RM" + price;
    }

}
