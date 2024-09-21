import java.util.Date;
public class PurchaseOrder extends StockMovement{
	
	private String purchaseID;
	private Supplier supplier;
	
	public PurchaseOrder() {
    }

    public PurchaseOrder(Date date, ItemDetails itemDetails, String purchaseID, Supplier supplier) {
    	super(date,itemDetails);
    	this.purchaseID = purchaseID;
    	this.supplier = supplier;
    }
    
    //get & set
    
    public void setPurchaseID(String purchaseID){
    	this.purchaseID = purchaseID;
    }
    
    public String getPurchaseID(){
    	return this.purchaseID;
    }
    
    //-----------------------------------------
    
    public void setSupplier(Supplier supplier){
    	this.supplier = supplier;
    }
    
    public Supplier getSupplier(){
    	return this.supplier;
    }
    
    //--End get & set--------------------------
    
   public static Double calcTotalPrice(double price,int quantity) {
        return price * quantity;
    }

    public String toString(){
            return String.format("%s %10s %s",        
            super.toString(),
            purchaseID,
            supplier.toString()
        );
    }
    
}