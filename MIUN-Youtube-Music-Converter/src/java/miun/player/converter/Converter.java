/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miun.player.converter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 * Converts youtube link to m3u/mp3 file/stream
 * @author Jonas
 */
@WebService(serviceName = "Converter")
public class Converter {
    

    private String currentVideo;
   
    public Converter() {
        
    }
    
    private String urlToName(String url) {
        return url.substring(31);
    }
    
    private String fetchVideo(String url) {
        
        currentVideo = urlToName(url);
        String cmdString = "cmd /c perl YoutubeStream-To-FLV-Converter.pl " + url;
        Process p;
        
        //Call the Pearl-script and start download
        try {
            p = Runtime.getRuntime().exec( cmdString );
            
            //Handling the streams so that dead lock situation never occurs.  
//            ProcessHandler inputStream = new ProcessHandler(p.getInputStream(),"INPUT");
//            ProcessHandler errorStream = new ProcessHandler(p.getErrorStream(),"ERROR");
            
            //Start the stream threads 
//            inputStream.start();
//            errorStream.start();

            //Wait for the download to complete
            p.waitFor();
            
            if (p.exitValue() == 0) {
                System.out.println("CONVERTER: Command Successful");
                return "CONVERTER: Command Successful"; //true;
            } else {
                System.out.println("CONVERTER: Command Failure");
                return "CONVERTER: Command Failure"; //false;
            }
            
        } catch (Exception e) {
            System.out.println("CONVERTER: perl-script fetch failed, error: " + e.toString());
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, e);
            return "CONVERTER: perl-script fetch failed, error: " + e.toString();//false;
        }
    }
    
    public String getM3UStream( String url ) {
        //url = "http://www.youtube.com/watch?v=TWfph3iNC-k"; //DEBUG
//        String errorMsg = "fetchVideo";
//        if ( fetchVideo( url ) ) {
//            errorMsg ="convertToMp3";
//            if( convertToMp3() ) {
//                errorMsg ="createM3uFile";
//                if( createM3uFile() ) {
//                    return this.currentVideo + ".m3u";
//                }
//            }
//        }
        String errorMsg = "";
        errorMsg = fetchVideo( url );
        convertToMp3();
        createM3uFile();
        return "ERROR: (" + url + ")" + errorMsg;
    }
    
    /**
     * Converts the *.mp4 to *.mp3 using the externa ffmpeg tool
     * @return filepath to the *.m3u (e.g. http://example.com/example.m3u)
     */
    private boolean convertToMp3() {
  
        String cmdString = "cmd.exe /c ffmpeg.exe -i " + currentVideo + ".mp4 " + currentVideo + ".mp3";
        Process p;
        
        try {
            p = Runtime.getRuntime().exec( cmdString );
            
            //Handling the streams so that dead lock situation never occurs.  
            ProcessHandler inputStream = new ProcessHandler(p.getInputStream(),"INPUT");
            ProcessHandler errorStream = new ProcessHandler(p.getErrorStream(),"ERROR");
            
            //Start the stream threads 
            inputStream.start();
            errorStream.start();
            

            //Wait for convertion to complete
            p.waitFor();
            
            if (p.exitValue() == 0) {
                System.out.println("CONVERTER: ffmpeg - Command Successful");
                return true;
            } else {
                System.out.println("CONVERTER: ffmpeg - Command Failure");
                return false;
            }
        } catch (Exception e) {
            System.out.println( "CONVERTER: ffmpeg conversion failed, error: " + e.toString() );
            return false;
        }
    }

    /**
     * Creates an *m3u file with the new *.mp3 file
     */
    private boolean createM3uFile() {
        
        String[] output = { "#EXTM3U\n", "#EXTINF:123,Sample Artist - Sample title\n", "C:\\Dropbox\\Skolarbete\\Datateknik (AV) - Service Oriented Architecture SOA (7.5hp)\\Project\\MIUN-Youtube-Music-Converter\\" + currentVideo + ".mp3" };
        
        try {
            FileWriter fstream = new FileWriter( currentVideo + ".m3u");
            BufferedWriter out = new BufferedWriter(fstream);
           
            for(String line : output) {
                out.write(line);
            }
            
            //Close the output stream
            out.close();
        } catch (Exception e){
            System.out.println("CONVERTER: m3u creation failed, error: " + e.toString() );
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, "Error: " + e.getMessage(), e);
            return false;
        }
        return true;
    }
}
