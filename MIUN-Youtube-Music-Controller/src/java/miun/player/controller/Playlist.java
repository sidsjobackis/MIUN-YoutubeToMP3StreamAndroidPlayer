package miun.player.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gdata.data.youtube.PlaylistEntry;
import com.google.gdata.data.youtube.PlaylistFeed;
import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.util.ServiceException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Playlist {

    PlaylistLinkEntry playlistInformation;
    private PlaylistFeed playListFeed = null;
    private ArrayList<Song> listOfSongs = new ArrayList<Song>(); 


    /**
     * Default constructor
     * @param title
     */
    public Playlist(PlaylistLinkEntry playlistInformation) {
        this.playlistInformation = playlistInformation;
    }



    /**
     * Return playlist title
     * @return the title
     */
    public String getTitle() {
        return this.playlistInformation.getTitle().getPlainText();
    }



    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return this.playlistInformation.getFeedUrl();
    }



    /**
     * Return Arraylist containing all songs in playlist
     * @return the listOfSongs
     */
    public ArrayList<Song> getListOfSongs() {
        return this.listOfSongs;
    }

    /**
     * Return GSON/JSON object containing all songs in playlist
     * @return GSON/JSON object containing all songs in playlist
     */
    public String getListOfSongsAsJSONObject() {
        Gson gson = new Gson();
        ArrayList<String> listOfSongsNames = new ArrayList<String>();
        
        //Store all song names in a arraylist
        for(Song song : listOfSongs) {
            listOfSongsNames.add( song.getTitle() );
        }
        
        //Create JSON object
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
                
        return gson.toJson(listOfSongsNames, listType);
    }
    
    
    /**
     * Return song of given index
     * @param index of requested song
     * @return Song object of requested index
     */
    public Song getSong( int index ) {
        return this.listOfSongs.get(index);
    }


    /**
     * Populates the playlist with songs 
     */
    public void populate() {

        try {
                playListFeed = Controller.SERVICE.getFeed(new URL( getIdentifier() ), PlaylistFeed.class);
        } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (ServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

        for(PlaylistEntry playlistEntry : playListFeed.getEntries()) {
                listOfSongs.add( new Song(playlistEntry) );
        }
    }
}
