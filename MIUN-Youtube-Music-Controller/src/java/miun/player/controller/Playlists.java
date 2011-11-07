
package miun.player.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.data.youtube.PlaylistLinkFeed;
import com.google.gdata.util.ServiceException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Playlists {
	
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private PlaylistLinkFeed feed = null;

    
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
     * Return the platlists as a JSON object
     * @return GSON/JSON object containing the playlists
     */
    public String getPlaylistAsJSONObject() {
        Gson gson = new Gson();
        
        ArrayList<String> playlistNames = new ArrayList<String>();
        
        //Store all playlistnames in a arraylist
        for(Playlist playlist : playlists) {
            playlistNames.add( playlist.getTitle() );
        }
        
        //Create JSON object
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        
        return gson.toJson(playlistNames, listType);
    }

    
    /**
     * Return playlist with given index
     * @param index of given playlist
     * @return Playlist instance of the requested playlist
     */
    public Playlist getPlaylist( int index ) {
        return playlists.get( index );
    }

    
    /**
     * Populates the list with all the current users playlists 
     */
    public void populate() {

        String feedUrl = "http://gdata.youtube.com/feeds/api/users/" + Controller.USER.getUSERNAME() + "/playlists";

        try {
                feed = Controller.SERVICE.getFeed(new URL(feedUrl), PlaylistLinkFeed.class);
        } catch (MalformedURLException e) {
                // TODO Auto-generated catch block+
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (ServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        for(PlaylistLinkEntry entry : feed.getEntries()) {
                playlists.add( new Playlist( entry ) );
        }
    }
}
