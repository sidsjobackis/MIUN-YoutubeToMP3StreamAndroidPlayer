package miun.player;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gdata.client.*;
import com.google.gdata.client.youtube.*;
import com.google.gdata.data.*;
import com.google.gdata.data.geo.impl.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.media.mediarss.*;
import com.google.gdata.data.youtube.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class PlayerActivity extends Activity {
	
	private final String YOUTUBE_DEVELOPER_KEY = "AI39si6Y1C-zeZEIS-3sgm33QI8B6gChkA8dNrso3Ju0wpl5b1VVU5Nyl9Qfy2efWYKTApS5Ts0uCRD6D72RtMRdCw325OP7uQ";
	private final String APPLICATION_NAME = "MIUN-YOUTUBE-STREAMER";
	public static YouTubeService SERVICE;
	public static User USER = new User();
	private final Playlists userPlaylists = new Playlists();
	
	private EditText usernameTextbox;
	private EditText passwordTextbox;
	private Button loginButton;
		
	private Toast loginErrorToast = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                        
        //Connect to Youtube developer interface
        SERVICE = new YouTubeService(APPLICATION_NAME, YOUTUBE_DEVELOPER_KEY);

        this.usernameTextbox = (EditText)this.findViewById(R.id.tb_username);
        this.passwordTextbox = (EditText)this.findViewById(R.id.tb_password);
        this.loginButton = (Button)this.findViewById(R.id.bn_login);
        
        /**
         * If login button is clicked run the login function
         */
        this.loginButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login();
			}
		});
    }
    
 
    
    /**
     * Logs in to Youtube with the given user credentials  
     */
    private void login() {
    	//If (user credentials is correct) cont', else return errormessage
    	if( USER.setUsernameAndPassword( this.usernameTextbox.getText().toString(), this.passwordTextbox.getText().toString() ) ) {
    		userPlaylists.populate();
    	} else {
    		this.passwordTextbox.clearComposingText();
    		loginErrorToast().show();
    	}
    }
    
    /**
     * Login error toast factory
     * @return
     */
    private Toast loginErrorToast() {
    	if( this.loginErrorToast == null ) {
    		CharSequence loginErrorToastMsg = "Wrong username and/or password";
    		int loginErrorToastDuration = Toast.LENGTH_SHORT;
    		this.loginErrorToast = Toast.makeText(getApplicationContext(), loginErrorToastMsg, loginErrorToastDuration);
    		return this.loginErrorToast;
    	}
    	
    	return this.loginErrorToast;
    }
    
   
  
}