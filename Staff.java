public class Staff {
    private String staffId;
    private String name;
    private String password;

    public Staff(String staffId,String name,String password){
        this.staffId = staffId;
        this.name = name;
        this.password = password;
    }

    public String getStaffId(){
        return staffId;
    }

    public void setStaffId(String staffId){
        this.staffId = staffId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return staffId;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
