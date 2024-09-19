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
    	return company + "|" + phone + "|" + address.toString();
    }
    
}