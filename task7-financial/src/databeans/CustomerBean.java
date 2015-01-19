package databeans;
import org.genericdao.PrimaryKey;

@PrimaryKey("customerId")
public class CustomerBean {
	private int customerId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String addrL1;
	private String addrL2;
	private String city;
	private String state;
	private int zip;
	private long cash;
	
	public int getCustomerId() {
		return customerId;           
	}
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
	public String getAddrL1() {
		return addrL1;
	}
	public String getAddrL2() {
		return addrL2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getZip() {
		return zip;
	}
	public long getCash() {
		return cash;
	}

    public void setCustomerId(int i) { 
    	customerId = i;     
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
    public void setAddrL1(String s) {
    	addrL1 = s;
    }
    public void setAddrL2(String s) {
    	addrL2 = s;
    }
    public void setCity(String s) {
    	city = s;
    }
    public void setState(String s) {
    	state = s;
    }
    public void setZip(int i) {
    	zip = i;
    }
    public void setCash(long l) {
    	cash = l;
    }
}
