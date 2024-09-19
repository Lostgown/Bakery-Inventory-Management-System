public class Supplier {
	
	private String company;
	private String phone;
	private Address address;

    public Supplier() {
    }
    
    public Supplier(String company, String phone, Address address) {
    	this.company = company;
    	this.phone = phone;
    	this.address = address;
    }
    
    //get & set
    public void setCompany(String company){
    	this.company = company;
    }
    
    public String getCompany(){
    	return this.company;
    }
    
    //-----------------------------------------
    
    public void setPhone(String phone){
    	this.phone = phone;
    }
    
    public String getPhone(){
    	return this.phone;
    }
    
    //-----------------------------------------
    
    public void setAddress (Address address){
    	this.address = address;
    }
    
    public Address getAddress(){
    	return this.address;
    }
    
    //--End get & set--------------------------
    
    public String toString(){
    	return String.format("%-30s %-15s %s",
        company,                // %-15s ensures company names are aligned with 15-character width
        phone,                  // %-15s ensures phone numbers are aligned with 15-character width
        address.toString()      // Calls Address's toString() for the full address
    );
    }
    
}