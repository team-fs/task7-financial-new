package databeans;
//@PrimaryKey("username")
public class EmployeeBean {
	private String username;
	private String password;
	private String fName;
	private String lName;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFname() {
		return fName;
	}
	public String getLname() {
		return lName;
	}
	
	public void setUsername(String s) {
	    username = s;
	}
	public void setPassword(String s) {
    	password = s;	 
    }
	public void setFname(String s) {
    	fName = s;
    }
	public void setLname(String s) {
	   	lName = s;
    }
}
