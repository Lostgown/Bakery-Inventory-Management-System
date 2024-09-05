public class Ingredient extends Item {
    
    public Ingredient(String id, String name, int quantity){
        super(id, name, quantity);
    }

    public void updateStock(int quantity){
        setQuantity(getQuantity() + quantity);
    }

    @Override
    public String getDetails(){
        return super.getDetails() + " (Ingredient)";
    }
}
