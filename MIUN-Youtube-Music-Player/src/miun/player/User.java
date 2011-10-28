package miun.player;

import com.google.gdata.util.AuthenticationException;

public class User {

	private String USERNAME;
	private String PASSWORD;
	
	/**
	 * Default constructor
	 */
	public User() {

	}
	
	/**
    * Sets user credentials
    */
    public boolean setUsernameAndPassword(String username, String password) {
    	this.USERNAME = username;
		this.PASSWORD = password;
		return setUserCredentials();
    }
    
        
    /**
     * Get username
	 * @return the uSERNAME
	 */
	public String getUSERNAME() {
		return this.USERNAME;
	}

	/**
	 * Sets user credentials in SERVICE 
	 */
	private boolean setUserCredentials() {
    	try {
			PlayerActivity.SERVICE.setUserCredentials(USERNAME, PASSWORD);
			
		} catch (AuthenticationException e) {
			System.out.println( "ERROR: Login - " + e.getMessage() );
			e.printStackTrace();
			return false;
		}
		return true;
    }
}
