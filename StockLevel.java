public class StockLevel {
	
	private int stockQty;
	private int min;
	private int max;

    public StockLevel() {
    }
    
    public StockLevel(int stockQty, int min, int max) {
    	this.stockQty = stockQty;
    	this.min = min;
    	this.max = max;
    }
    
    //get & set
    
    public void setStockQty(int stockQty){
    	this.stockQty = stockQty;
    }
    
    public int getStockQty(){
    	return this.stockQty;
    }
    
    //-----------------------------------------
    
    public void setMinStock(int min){
    	this.min = min;
    }
    
    public int getMinStock(){
    	return this.min;
    }
    
    //-----------------------------------------
    
    public void setMaxStock(int max){
    	this.max = max;
    }
    
    public int getMaxStock(){
    	return this.max;
    }
    
    //--End get & set--------------------------
    
    public String checkStockLevel(int stockQty,int min){
    	if (stockQty < min){
    		return "Insufficient";
    	}
    	else {
            return "Sufficient";
        }
    	
    }

    public void increaseStock(int qty) {
        stockQty += qty;
        if (stockQty > max) {
            stockQty = max;  // Prevent stock from exceeding maxStock
        }
    }

    public void decreaseStock(int qty) {
        stockQty -= qty;
        if (stockQty < 0) {
            stockQty = 0;  // Prevent stock from going negative
        }
    }
    
    
}