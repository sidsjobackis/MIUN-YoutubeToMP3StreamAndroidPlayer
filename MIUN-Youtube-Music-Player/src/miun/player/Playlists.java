package miun.player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;

import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.data.youtube.PlaylistLinkFeed;
import com.google.gdata.util.ServiceException;

public class Playlists extends ListActivity {
	
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	private	PlaylistLinkFeed feed = null;

	private ProgressDialog m_ProgressDialog = null; 
    private PlaylistAdapter playlistAdapter;
    private Runnable viewPlaylists;
	
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
	 * Populates the list with all the current users playlists 
	 */
	public void populate() {
		
		String feedUrl = "http://gdata.youtube.com/feeds/api/users/" + PlayerActivity.USER.getUSERNAME() + "/playlists";
				
		try {
			feed = PlayerActivity.SERVICE.getFeed(new URL(feedUrl), PlaylistLinkFeed.class);
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

		for(PlaylistLinkEntry entry : feed.getEntries()) {
			playlists.add( new Playlist( entry ) );
		}
		runOnUiThread(returnRes);
	}
	
	
	private Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if( playlists != null && playlists.size() > 0 ) {
            	playlistAdapter.notifyDataSetChanged();
                for( int i = 0; i < playlists.size(); i++ )
                	playlistAdapter.add( playlists.get(i) );
            }
            m_ProgressDialog.dismiss();
            playlistAdapter.notifyDataSetChanged();
        }
    };
	
}
