package miun.player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.UiModeManager;

import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.data.youtube.PlaylistLinkFeed;
import com.google.gdata.util.ServiceException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Playlists extends ListActivity {
	
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
	private ProgressDialog playlist_ProgressDialog = null; 
    private PlaylistAdapter playlistAdapter;
    //private Runnable viewPlaylists;
	
	/**
	 * Default constructor
	 */
	public Playlists() {
		
	}

	
	/**
	 * Return arraylist containing all playlists
	 * @return the playlists
	 */
	public ArrayList<Playlist> getPlaylists() {
		return this.playlists;
	}
	

	/**
	 * Return playlist on given index
	 * @param index of playlist
	 * @return playlist object
	 */
	public Playlist getPlaylist( int index ) {
		return this.playlists.get(index);
	}

	
	/**
	 * Populates the list with all the current users playlists 
	 */
	public void populate( String JSONString ) {
		//Parse JSON and populate arraylist with new Playlist objects
		Gson gson = new Gson();
			
		Type listType = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> playlistNames = gson.fromJson(JSONString, listType);
		
		for(String playlistName : playlistNames) {
			playlists.add( new Playlist(playlistName) );
		}
		
		runOnUiThread(returnRes);
	}
	
	
	private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if( playlists != null && playlists.size() > 0 ) {
            	playlistAdapter.notifyDataSetChanged();
                for( Playlist playlist : playlists ) {
                	playlistAdapter.add( playlist );
                }
            }
            playlist_ProgressDialog.dismiss();
            playlistAdapter.notifyDataSetChanged();
        }
    };
	
}
