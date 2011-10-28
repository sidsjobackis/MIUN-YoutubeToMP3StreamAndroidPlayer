/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miun.player.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import miun.player.converter.Converter_Service;


@WebService(serviceName = "Controller")
public class Controller {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/MIUN-Youtube-Music-Converter/Converter.wsdl")
    private Converter_Service service;
    private String userName = "";
    private String userAuthenticationToken = "";

    /**
     * Sets users name and authenticationToken
     * @param userName
     * @param userAuthenticationToken 
     */
    public String setAuthenticationKey(String name, String authenticationToken) {
        this.userName = name;
        this.userAuthenticationToken = authenticationToken;
        
        return loginAndFetchPlaylists();
    }
    
    /**
     * Login and fetch user playlists
     * @return 
     */
    public String loginAndFetchPlaylists() {
        //TODO Test youtube login
        return "";
    }    
    
    /**
     * 
     * @return 
     */
    public String fetchUserPlaylist() {
        //TODO fetch user playlist and return the playlist
        return "";
    }
    
    /**
     * Connects to webservice "Converter" and run function getM3UStream( "Youtube URL" ), 
     * which will download, convert and repackage the clip to a mp3 file wrapped in a *.m3u file.
     * @param url - Youtube url
     * @return url to *.m3u file
     */
    public String fetchVideo(String youtubeUrl) {
        String m3uURL = "";

        //WebService call
        miun.player.converter.Converter port = service.getConverterPort();
        m3uURL = port.getM3UStream( youtubeUrl );
        
        try {
            if( m3uURL.length() == 0 ) throw new Exception( "returned no URL" );
        } catch (Exception e) {
            System.out.println( "CONTROLLER: Convertion failed, error: " + e.toString() );
            Logger.getLogger( Controller.class.getName() ).log( Level.SEVERE, "Error: " + e.getMessage(), e );
        }
                
        return m3uURL;
    }

}

    