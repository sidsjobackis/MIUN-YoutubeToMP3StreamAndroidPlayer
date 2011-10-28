package miun.player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gdata.data.youtube.PlaylistEntry;
import com.google.gdata.data.youtube.PlaylistFeed;
import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.util.ServiceException;

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
	 * Populates the playlist with songs 
	 */
	public void populate() {
			
		try {
			playListFeed = PlayerActivity.SERVICE.getFeed(new URL( getIdentifier() ), PlaylistFeed.class);
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
