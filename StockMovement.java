import java.util.Date;

public class StockMovement implements Comparable{
	
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

    //compare
    public int compareTo(Object o){
    	StockMovement otherMovement = (StockMovement) o;
    	return date.compareTo(otherMovement.date);
    }

    public String toString(){
        return String.format("%-10s %s",
        date,        
        itemDetails.toString()
    );
    }
    
}