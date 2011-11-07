package miun.player;

import miun.player.RestClient.RequestMethod;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerActivity extends Activity {
	
	public static Playlists USERPLAYLISTS = new Playlists();
	public static String LOGIN_BASE_URL = "http://10.0.2.2/MIUN-Youtube-Music-Controller/resources/controller/"; //WEBSERVICE URL 

	
	private EditText usernameTextbox;
	private EditText passwordTextbox;
	private Button loginButton;
	private Intent playlistsIntent = null;
	private Button openLinkButton;
	
	private Toast loginErrorToast = null;
	//private Intent audioPlayerIntent = new Intent(this, AudioPlayer.class);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        this.usernameTextbox = (EditText)this.findViewById(R.id.tb_username);
        this.passwordTextbox = (EditText)this.findViewById(R.id.tb_password);
        this.loginButton = (Button)this.findViewById(R.id.bn_login);
        this.openLinkButton = (Button)this.findViewById(R.id.bn_openlink); 
        
        /**
         * If login button is clicked run the login function
         */
        this.loginButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login();
			}
		});
        
        /**
         * If open link button is clicked run the ask for url function
         */
        this.openLinkButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				askForUrl();		
			}
		});
    }
    
 
    
    /**
     * Logs in to Youtube with the given user credentials  
     */
    private void login() {
    	//If (user credentials is correct) cont', else return errormessage
    
    	RestClient clientLogin = new RestClient(LOGIN_BASE_URL + this.usernameTextbox.getText().toString() + "/" + this.passwordTextbox.getText().toString());
    	//clientLogin.AddParam( "name", this.usernameTextbox.getText().toString() );
    	//clientLogin.AddParam( "password", this.passwordTextbox.getText().toString() );
    	
    			
    	//Call Webservice Login(username, password)
		try {
			clientLogin.Execute(RequestMethod.GET);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	    	    	   
    	//Check if user credentials is correct
    	if( Boolean.parseBoolean( clientLogin.getResponse() ) ) {
    		RestClient clientFetchUserPlaylists = new RestClient(LOGIN_BASE_URL);
    		
    		//Call Webservice FetchUserPlaylists
    		try {
    			clientFetchUserPlaylists.Execute(RequestMethod.GET);
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        	
    		USERPLAYLISTS.populate( clientFetchUserPlaylists.getResponse() ); 
    		
    		playlistsIntent = new Intent(this, PlayerActivity.class);
    		startActivity(playlistsIntent);
    		
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
    
    
    /**
     * Ask for youtube URL and play the given song
     */
    public void askForUrl() {
//    	final EditText ed = new EditText(this);
//		ed.setText("http://www.youtube.com/watch?v=TWfph3iNC-k"); //default debug url
//		ed.setSelection(ed.getText().length());
//		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setView(ed)
//				.setTitle("Open URL")
//				.setPositiveButton("Open",
//						new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialogInt, int whichButton) {
//								openLink( ed.getText().toString() );
//							}
//						}).show();
    }
    
    
    /**
     * Open specified url
     * @param youtubeLink
     */
    public void openLink( String youtubeLink ) {
    	
//    	try {
//			URL siteURL = new URL(LOGIN_BASE_URL + youtubeLink);
//			Log.i("URL", siteURL.getPath());
//			SAXParserFactory factory = SAXParserFactory.newInstance();
//			SAXParser parser = factory.newSAXParser();
//			XMLReader reader = parser.getXMLReader();
//			reader.parse(new InputSource(siteURL.openStream()));
//			Log.i("Input stream", "SUCCESS");
//			//Put the m3u link as intent extras
//			audioPlayerIntent.putExtra("m3uLink",
//					"http://www.youtube.com/watch?v=TWfph3iNC-k");
//			audioPlayerIntent.putExtra("songTitle", "Requested URL");
//			startActivity(audioPlayerIntent);
//		} catch (Exception e) {
//			Log.i("Input stream", "ERROR!");
//		}
    }
    
 
  
}