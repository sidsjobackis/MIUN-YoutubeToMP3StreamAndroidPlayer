package miun.player;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import miun.player.RestClient.RequestMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PlaylistActivity extends Activity {

	private ListView lv_playlist = null;
	private int playlistIndex;
	private Intent audioPlayerIntent = new Intent(this, AudioPlayerActivity.class);

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);
        
        lv_playlist = (ListView)this.findViewById(R.id.lv_playlist);
        
        Bundle extras = getIntent().getExtras();
        playlistIndex = extras.getInt("playlistIndex");
        
        for( Playlist pList : PlayerActivity.USERPLAYLISTS.getPlaylists() ) {
        	lv_playlist.addView( pList.getSongAdapter().getView(lv_playlist.getCount(), null, null) );
        }
             
        lv_playlist.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                try {
                    int itemClickedIndex = lv_playlist.getSelectedItemPosition();
                    RestClient clientFetchSong = new RestClient( 
                    											PlayerActivity.BASE_URL 
                    											+ String.valueOf(itemClickedIndex) 
                    											+ "/" + String.valueOf(playlistIndex) ); //fetchVideo
//                    clientFetchSong.AddParam( "playListIndex", String.valueOf(itemClickedIndex) );
//                    clientFetchSong.AddParam( "songIndex", String.valueOf(playlistIndex) );
                	
                	//Call Webservice fetchVideo(int playlistIndex, int songIndex)
            		try {
            			clientFetchSong.Execute(RequestMethod.GET);
                	} catch (Exception e) {
                	    e.printStackTrace();
                	}
                	    	    	   
                	//Put the m3u link as intent extras
                	audioPlayerIntent.putExtra( "m3uLink", parseJSONToString( clientFetchSong.getResponse() ) );
                	audioPlayerIntent.putExtra( 
                				"songTitle", 
                				PlayerActivity
                					.USERPLAYLISTS
                						.getPlaylist(playlistIndex)
                							.getSongTitle(itemClickedIndex) );
                	startActivity(audioPlayerIntent);
                }
                catch(Exception e) {
                    Log.v(getString(R.string.app_name), e.getMessage());
                }
            }
        });
		
	}
	
	
	/**
	 * Parse JSON string and return song m3u 
	 * @param jsonString
	 * @return String containing the songs m3u path
	 */
	private String parseJSONToString(String JSONString) {
		
		Gson gson = new Gson();
		String m3uLink = gson.fromJson(JSONString, String.class);
		
		return m3uLink;
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
