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
    
   public void createOrder() {
        System.out.println("Order created with ID: " + purchaseID);
        System.out.println("Supplier: " + supplier.toString());
        System.out.println("Item Details: " + getItemDetails().toString());
    }
    
   public double calcPrice() {
        return getItemDetails().getProduct().getCartonPrice() * getItemDetails().getOrderQty();
    }
    
    public void payment(){
    	double price = calcPrice();
    	System.out.println("Total payment: " + price);
    }
    
}