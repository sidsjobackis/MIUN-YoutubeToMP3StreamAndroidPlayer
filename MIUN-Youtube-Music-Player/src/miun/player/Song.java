package miun.player;

import com.google.gdata.data.youtube.PlaylistEntry;

public class Song {
	
	PlaylistEntry playlistEntry = null;

	/**
	 * Default constructor
	 * @param title
	 */
	public Song(PlaylistEntry playlistEntry) {
		this.playlistEntry = playlistEntry;
	}

	/**
	 * Return the song title
	 * @return the title
	 */
	public String getTitle() {
		return this.playlistEntry.getTitle().getPlainText();
	}

	
	/**
	 * Return the songs HTML-link
	 * @return the m3uLink
	 */
	public String getHtmlLink() {
		return this.playlistEntry.getTitle().getPlainText();
	}

}
