public class ItemDetails {
	
	private Product product;
	private int orderQty;

    public ItemDetails() {
    }
    
    public ItemDetails(Product product, int orderQty) {
    	this.product = product;
    	this.orderQty = orderQty;
    }
    
  
    //get & set
    
    public void setProduct(Product product){
    	this.product = product;
    }
    
    public Product getProduct(){
    	return this.product;
    }
    
    //-----------------------------------------
    
    public void setOrderQty(int orderQty){
    	this.orderQty = orderQty;
    }
    
    public int getOrderQty(){
    	return this.orderQty;
    }
    
    //--End get & set--------------------------
    
    public String toString(){
    	return String.format("%-10s %-15s %7d",
        product.getProductId(),                
        product.getDescription(),                  
        orderQty
    );
    }
    
}