package miun.player.controller;

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
     * @return the USERNAME
     */
    public String getUSERNAME() {
            return this.USERNAME;
    }

    
    /**
     * Sets user credentials in SERVICE 
     * @return true if credentials were accepted successfully
     */
    private boolean setUserCredentials() {
        try {
            Controller.SERVICE.setUserCredentials(USERNAME, PASSWORD);

        } catch (AuthenticationException e) {
            System.out.println( "ERROR: Login - " + e.getMessage() );
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
