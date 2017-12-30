package accounts;

/**
 * Class contains user information
 * 
 * @author Alexey Kopylov
 *
 */
public class UserProfile {
	private String name;
	private String password;
	private String email;
	
	public UserProfile(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
}
