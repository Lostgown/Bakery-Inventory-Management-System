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
    		return "\u001B[31mInsufficient\u001B[0m";
    	}
    	else {
            return "\u001B[32mSufficient\u001B[0m";
        }
    	
    }
    
    
}