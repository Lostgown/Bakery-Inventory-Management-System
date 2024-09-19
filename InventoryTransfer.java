import java.util.Date;
public class InventoryTransfer extends StockMovement{
	
	private String transferID;
	private String transferFrom;
	private String transferTo;

    public InventoryTransfer() {
    }
    
    public InventoryTransfer(Date date, ItemDetails itemDetails, String transferID, String transferFrom, String transferTo) {
    	super(date,itemDetails);
    	this.transferID = transferID;
    	this.transferFrom = transferFrom;
    	this.transferTo = transferTo;
    }
    
    //get & set
    public void setTransferID(String transferID){
    	this.transferID = transferID;
    }
    
    public String getTransferID(){
    	return this.transferID;
    }
    
    //-----------------------------------------
    
    public void setTransferFrom(String transferFrom){
    	this.transferFrom = transferFrom;
    }
    
    public String getTransferFrom(){
    	return this.transferFrom;
    }
    
    //-----------------------------------------
    
    public void setTransferTo (String transferTo){
    	this.transferTo = transferTo;
    }
    
    public String getTransferTo(){
    	return this.transferTo;
    }
    
    //--End get & set--------------------------
    
    public void executeTransfer(){
    	System.out.println("Transfer ID: " + transferID);
    	System.out.println("Transfer from: " + transferFrom + " to " + transferTo);
    	stockOut();
    }
    
}