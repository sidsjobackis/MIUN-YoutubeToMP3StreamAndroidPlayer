package miun.player;

import miun.player.RestClient.RequestMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PlaylistsActivity extends Activity {

	private ListView lv_playlists = null;
	private Intent playlistIntent = new Intent(this, PlaylistActivity.class);
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists);
        
        
        lv_playlists = (ListView)this.findViewById(R.id.lv_playlists);
        
        for( Playlist pList : PlayerActivity.USERPLAYLISTS.getPlaylists() ) {
        	lv_playlists.addView( pList.getSongAdapter().getView(lv_playlists.getCount(), null, null) );
        }
             
        
        lv_playlists.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                try {
                    RestClient clientLogin = new RestClient(PlayerActivity.LOGIN_BASE_URL); //fetchUserPlaylist
                    int itemClickedIndex = lv_playlists.getSelectedItemPosition();
                    
                	clientLogin.AddParam( "index", String.valueOf(itemClickedIndex) );
                	
                	//Call Webservice fetchUserPlaylist(int index)
            		try {
            			clientLogin.Execute(RequestMethod.GET);
                	} catch (Exception e) {
                	    e.printStackTrace();
                	}
                	    	    	   
                	//Take returned JSON/GSON object and populate the playlist
                	PlayerActivity.USERPLAYLISTS.getPlaylist(itemClickedIndex).populate(clientLogin.getResponse());
                	                	
                	playlistIntent.putExtra("playlistIndex", itemClickedIndex);
                	startActivity(playlistIntent);
                	
                }
                catch(Exception e) {
                    System.out.println("Nay, cannot get the selected index");
                }
            }
        });
		
	}
	
	
	/**
	 * Override finish method
	 */
	@Override
	public void finish() {
		Intent exit = new Intent();
		setResult(RESULT_OK, exit);
		super.finish();
	}
}
