import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Ingredient> ingredients;
    
    public Inventory(){
        products = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

}
