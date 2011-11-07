package miun.player;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.R.integer;
import android.app.ListActivity;
import android.app.ProgressDialog;

import com.google.gdata.data.youtube.PlaylistEntry;
import com.google.gdata.data.youtube.PlaylistFeed;
import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.util.ServiceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Playlist extends ListActivity {
	
	String title; 
	private ArrayList<String> listOfSongs = null; //List of song names
	
	private ProgressDialog playlist_ProgressDialog = null; 
    private SongAdapter songAdapter;
    
    
    /**
	 * @return the songAdapter
	 */
	public SongAdapter getSongAdapter() {
		return this.songAdapter;
	}


	//private Runnable viewPlaylist;

	/**
	 * Type constructor
	 * @param title
	 */
	public Playlist(String title) {
		this.title = title;
		this.listOfSongs = new ArrayList<String>();
	}
	
	
	/**
	 * @return the title
	 */
	public String getListTitle() {
		return this.title;
	}


	/**
	 * @return the listOfSongs
	 */
	public ArrayList<String> getListOfSongs() {
		return this.listOfSongs;
	}
	
	
	/**
	 * Return title of requested song
	 * @param index of song requested
	 * @return Title
	 */
	public String getSongTitle( int index ) {
		return this.listOfSongs.get(index);
	}

	
	/**
	 * Populates the playlist with songs 
	 */
	public void populate( String JSONString ) {
		//Parse JSON string and populate playlist with title and list of songs
		
		Gson gson = new Gson();
		
		Type listType = new TypeToken<ArrayList<String>>(){}.getType();
		listOfSongs = gson.fromJson(JSONString, listType);
		
		runOnUiThread(returnRes);
	}


	private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if( listOfSongs != null && listOfSongs.size() > 0 ) {
            	songAdapter.notifyDataSetChanged();
                for( String song : listOfSongs ) {
                	songAdapter.add( song );
                }
            }
            playlist_ProgressDialog.dismiss();
            songAdapter.notifyDataSetChanged();
        }
    };
}
