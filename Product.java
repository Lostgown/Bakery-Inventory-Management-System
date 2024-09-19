public class Product {
	
	private String productID;
	private String description;
	private double cartonPrice;
    private int unitPerCarton;
	private String category;
	private Supplier supplier;
	private StockLevel stockLevel;

    public Product() {
    }
    
    public Product(String productID, String description, double cartonPrice,int unitPerCarton, String category, Supplier supplier, StockLevel stockLevel) {
    	this.productID = productID;
    	this.description = description;
    	this.cartonPrice = cartonPrice;
        this.unitPerCarton = unitPerCarton;
    	this.category = category;
    	this.supplier = supplier;
    	this.stockLevel = stockLevel;
    }
    
    //get & set
    public void setProductId(String productID){
    	this.productID = productID;
    }
    
    public String getProductId(){
    	return this.productID;
    }
    
    //-----------------------------------------
    
    public void setDescription (String description){
    	this.description = description;
    }
    
    public String getDescription(){
    	return this.description;
    }
    
    //-----------------------------------------
    
    public void setCartonPrice (double cartonPrice){
    	this.cartonPrice = cartonPrice;
    }
    
    public double getCartonPrice(){
    	return this.cartonPrice;
    }
    
    //-----------------------------------------

    public void setUnitPerCarton (int unitPerCarton){
    	this.unitPerCarton = unitPerCarton;
    }
    
    public double getUnitPerCarton(){
    	return this.unitPerCarton;
    }
    
    //-----------------------------------------
    
    public void setCategory (String category){
    	this.category = category;
    }
    
    public String getCategory(){
    	return this.category;
    }
    
    //-----------------------------------------
    
    public void setSupplier (Supplier supplier){
    	this.supplier = supplier;
    }
    
    public Supplier getSupplier(){
    	return this.supplier;
    }
    
    //-----------------------------------------
    
    public void setStockLevel (StockLevel stockLevel){
    	this.stockLevel = stockLevel;
    }
    
    public StockLevel getStockLevel(){
    	return this.stockLevel;
    }
    
    //--End get & set--------------------------
    
    public String toString(){
    	return productID + ", " + description + ", " + category + ", " + cartonPrice + ", Supplier: " + supplier.toString();
    }





}