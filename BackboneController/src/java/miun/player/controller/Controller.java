/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miun.player.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import com.google.gdata.client.*;
import com.google.gdata.client.youtube.*;
import com.google.gdata.data.*;
import com.google.gdata.data.geo.impl.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.media.mediarss.*;
import com.google.gdata.data.youtube.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.io.File;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;


/**
 *
 * @author Jonas Bäckström
 */
@Path("/controller")
@Produces("application/xml")
public class Controller {
   private miun.player.controller.Converter_Service service = new miun.player.controller.Converter_Service();
    
    private final String YOUTUBE_DEVELOPER_KEY = "AI39si6Y1C-zeZEIS-3sgm33QI8B6gChkA8dNrso3Ju0wpl5b1VVU5Nyl9Qfy2efWYKTApS5Ts0uCRD6D72RtMRdCw325OP7uQ";
    private final String APPLICATION_NAME = "MIUN-YOUTUBE-STREAMER";
    public static YouTubeService SERVICE = null;
    public static User USER = new User();
    private final Playlists userPlaylists = new Playlists();
    private boolean initilazed = false;
        
    /**
     * Initilize the service if not already done.
     */
    private void initilizeService() {
        if( !initilazed ) {
            SERVICE = new YouTubeService(APPLICATION_NAME, YOUTUBE_DEVELOPER_KEY);
            initilazed = true;
        }
    }
    
    
    /**
     * Webservice called to set user credentials
     * @param userName
     * @param userAuthenticationToken 
     */
//    @GET
//    @Path("{name}/{password}")
    public boolean login(@PathParam("name") String name, @PathParam("password") String password) {
        initilizeService();
        return setAuthenticationKey(name, password);
    }
    
    
    /**
     * Logs in to Youtube with the given user credentials  
     */
    private boolean setAuthenticationKey(String name, String password) {
    	//If (user credentials is correct) populate and return true
    	if( USER.setUsernameAndPassword( name, password ) ) {
            return true;
    	} 
        return false;
    }
    
    
    /** 
     * Fetch users playlists and return to android client
     * @return JSON object containing user playlist information
     */
//    @GET
    public String fetchUserPlaylists() {
        userPlaylists.populate();
        return userPlaylists.getPlaylistAsJSONObject();
    }
        
    
    /**
     * Return user playlist content as JSON object
     * @return JSON object containg songs
     */
//    @GET
//    @Path("{index}")
    public String fetchUserPlaylist( @PathParam("index") int index ) {
        userPlaylists.getPlaylist( index ).populate();
        return userPlaylists.getPlaylist(index).getListOfSongsAsJSONObject();
    }
    
    
    /**
     * Connects to webservice "Converter" and run function getM3UStream( "Youtube URL" ), 
     * which will download, convert and repackage the clip to a mp3 file wrapped in a *.m3u file.
     * @param url - Youtube url
     * @return url to *.m3u file
     */
//    @GET
//    @Path("{playListIndex}/{songIndex}")
    public String fetchVideo( @PathParam("playListIndex") int playlistIndex, @PathParam("songIndex") int songIndex ) {
        String m3uURL = "";
                

        //WebService call
        miun.player.controller.Converter port = service.getConverterPort();
        m3uURL = port.getM3UStream( 
                                    userPlaylists
                                        .getPlaylist(playlistIndex)
                                        .getSong(songIndex)
                                        .getHtmlLink() );
        
        try {
            if( m3uURL.length() == 0 ) throw new Exception( "returned no URL" );
        } catch (Exception e) {
            System.out.println( "CONTROLLER: Convertion failed, error: " + e.toString() );
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, "Error: " + e.getMessage(), e );
        }
                
        return m3uURL;
    }
    
    
    /**
     * DEPRECATED VERSION - Connects to webservice "Converter" and run function getM3UStream( "Youtube URL" ), 
     * which will download, convert and repackage the clip to a mp3 file wrapped in a *.m3u file.
     * @param url - Youtube url
     * @return url to *.m3u file
     */
    @GET
    @Path("{youtubeurl}")
    public String fetchVideo_DEPRECATED( @PathParam("youtubeurl") String youtubeUrl ) {
        String m3uURL = "";
                
        //WebService call t
        miun.player.controller.Converter port = service.getConverterPort();
        m3uURL = port.getM3UStream( "http://www.youtube.com/watch?v=" + youtubeUrl );
        
        try {
            if( m3uURL.length() == 0 ) throw new Exception( "returned no URL" );
        } catch (Exception e) {
            System.out.println( "CONTROLLER: Convertion failed, error: " + e.toString() );
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, "Error: " + e.getMessage(), e );
        }
                
        return m3uURL; //"C:\\Users\\Jonas\\.netbeans\\7.0\\config\\GF3\\domain1\\S9BrzOcRsjQ.m3u";
    }

}
