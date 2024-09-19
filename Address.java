public class Address {
	
	private String street;
	private String city;
	private String state;
	private String postal;
	private String country;

    public Address() {
    }
    
    public Address(String street, String city, String state, String postal, String country) {
    	this.street = street;
    	this.city = city;
    	this.state = state;
    	this.postal = postal;
    	this.country = country;
    }
    
    //get & set
    
    public void setStreet(String street){
    	this.street = street;
    }
    
    public String getStreet(){
    	return this.street;
    }
    
    //-----------------------------------------
    
    public void setCity(String city){
    	this.city = city;
    }
    
    public String getCity(){
    	return this.city;
    }
    
    //-----------------------------------------
    
    public void setState(String state){
    	this.state = state;
    }
    
    public String getState(){
    	return this.state;
    }
    
    //-----------------------------------------
    
    public void setPostal(String postal){
    	this.postal = postal;
    }
    
    public String getPostal(){
    	return this.postal;
    }
    
    //-----------------------------------------
    
    public void setCountry(String country){
    	this.country = country;
    }
    
    public String getCountry(){
    	return this.country;
    }
    
    //--End get & set--------------------------
    
    public String toString(){
    	return street + ", " + city + ", " + state + ", " +postal + ", " + country;
    }
    
}