package miun.player.controller;

import com.google.gdata.data.youtube.PlaylistEntry;

public class Song {

    PlaylistEntry playlistEntry = null;
    String m3uLink;

    
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

    
    /**
     * Return the link to the m3u file
     * @return String containing link to the m3u file
     */
    public String getM3uLink() {
        return this.m3uLink;
    }
    
    
    /**
     * Sets the m3u link
     * @param m3uLink - string containing the m3u link
     */
    public void setM3uLink( String m3uLink ) {
        this.m3uLink = m3uLink;
    }
            
}
