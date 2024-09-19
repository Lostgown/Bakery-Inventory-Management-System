import java.util.Date;

public class StockMovement {
	
	private Date date;
	private ItemDetails itemDetails;

    public StockMovement() {
    }
    
    public StockMovement(Date date, ItemDetails itemDetails) {
    	this.date = date;
    	this.itemDetails = itemDetails;
    }
    
    //get & set
    
    public void setDate(Date date){
    	this.date = date;
    }
    
    public Date getDate(){
    	return this.date;
    }
    
    //-----------------------------------------
    
    public void setItemDetails(ItemDetails itemDetails){
    	this.itemDetails = itemDetails;
    }
    
    public ItemDetails getItemDetails(){
    	return this.itemDetails;
    }
    
    //--End get & set--------------------------
    
    
    // Stock in----------------------------------------------
    
    public void stockIn(){
    	itemDetails.getProduct().getStockLevel().setStockQty(itemDetails.getProduct().getStockLevel().
    	getStockQty() + itemDetails.getOrderQty());
    }
    
    
    // Stock Out---------------------------------------------------------
    
    public void stockOut(){
    	if(itemDetails.getProduct().getStockLevel().getStockQty() >= itemDetails.getOrderQty()){
    		itemDetails.getProduct().getStockLevel().
    		setStockQty(itemDetails.getProduct().getStockLevel().getStockQty() - itemDetails.getOrderQty());
    		System.out.println(itemDetails.getOrderQty() + "stock out at " + date);
    	}
    	else{
    		System.out.println("Not enough stock!");
    	}
    }
    
}