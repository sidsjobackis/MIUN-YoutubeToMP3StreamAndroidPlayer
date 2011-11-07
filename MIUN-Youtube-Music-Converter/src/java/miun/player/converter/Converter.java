/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miun.player.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        
    public String getM3UStream( String url ) {
        //url = "http://www.youtube.com/watch?v=TWfph3iNC-k"; //DEBUG 
        String errorMsg = "fetchVideo";
        if( fetchVideo( url ) ) {
            errorMsg ="convertToMp3";
            if( convertToMp3() ) {
                errorMsg ="createM3uFile";
                if( createM3uFile() ) {
                    return copyFiles();//this.currentVideo + ".m3u"; //Could add context location e.g. "C:\\Users\\Jonas\\.netbeans\\7.0\\config\\GF3\\domain1\\"
                }
            }
        }
//        String errorMsg = "";
//        errorMsg = fetchVideo( url );
//        errorMsg = (String)convertToMp3();
//        errorMsg = (String)createM3uFile();
        return "ERROR on (" + url + ") @" + errorMsg;
    }
    
    private String urlToName(String url) {
        return url.substring(31);
    }
    
    private boolean fetchVideo(String url) {
        
        currentVideo = urlToName(url);
        String cmdString = "cmd /c perl D:\\Programmering\\Git\\MIUN-YoutubeToMP3StreamAndroidPlayer\\MIUN-Youtube-Music-Converter\\YoutubeStream-To-FLV-Converter.pl " + url; 
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
                return true; //"CONVERTER: Command Successful";
            } else {
                System.out.println("CONVERTER: Command Failure");
                return false; //"CONVERTER: Command Failure"; 
            }
            
        } catch (Exception e) {
            System.out.println("CONVERTER: perl-script fetch failed, error: " + e.toString());
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, e);
            return false; //"CONVERTER: perl-script fetch failed, error: " + e.toString();//false;
        }
    }
        
    /**
     * Converts the *.mp4 to *.mp3 using the externa ffmpeg tool
     * @return filepath to the *.m3u (e.g. http://example.com/example.m3u)
     */
    private boolean convertToMp3() {
  
        String cmdString = "cmd.exe /c D:\\Programmering\\Git\\MIUN-YoutubeToMP3StreamAndroidPlayer\\MIUN-Youtube-Music-Converter\\ffmpeg.exe -i " + currentVideo + ".flv " + currentVideo + ".mp3";
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
       
           
        
        try {
            FileWriter fstream = new FileWriter( currentVideo + ".m3u" );
            BufferedWriter out = new BufferedWriter(fstream);
        
//          FacesContext context = FacesContext.getCurrentInstance(); 
//          ServletContext sc = (ServletContext) context.getExternalContext().getContext(); 
            String path = "http://10.0.2.2/"; //getDocRoot();//sc.getRealPath(currentVideo + ".m3u");

            String[] output = { "#EXTM3U\n", "#EXTINF:123,Sample Artist - Sample title\n", path + currentVideo + ".mp3" };
            
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

    
    /** 
     * Get path of the docroot, regardless of where project is deployed 
     * @return current context path 
     */ 
    private static String getDocRoot() { 
        File currentContextFile = new File(""); 
        
        try { 
            currentContextFile = currentContextFile.getCanonicalFile(); 
        } catch (IOException e) { 
            currentContextFile = currentContextFile.getAbsoluteFile(); 
        } 
        
        return currentContextFile.getPath() + "\\"; 
    }

    
    /**
     * Copy files to XAMPP htdocs directory
     * @return 
     */
    private String copyFiles() {
        
        //FIRST_COPY: Copy m3u file
                       
        String srcDir = "C:\\Users\\Jonas\\.netbeans\\7.0\\config\\GF3\\domain1\\" + currentVideo + ".m3u";
        String dest = "C:\\Users\\Jonas\\xampp\\htdocs\\";
        String[] command = { "cmd.exe", "/c", "copy", srcDir, dest };
            Process p1;
                
        try {
            //Execute command
             p1 = Runtime.getRuntime().exec( command );
        
            try {
                //Wait for process to finish
                p1.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }

            //Check if successful
            if (p1.exitValue() == 0) {
                System.out.println("FIRST_COPY: Command Successful");
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, "FIRST_COPY: Command Successful");
            } else {
                System.out.println("FIRST_COPY: Command Failure");
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, "FIRST_COPY: Command Failure");
                return ""; //"CONVERTER: Command Failure"; 
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
        
        
        //SECOND_COPY: Copy mp3
        
        srcDir = "C:\\Users\\Jonas\\.netbeans\\7.0\\config\\GF3\\domain1\\" + currentVideo + ".mp3";
        dest = "C:\\Users\\Jonas\\xampp\\htdocs\\";
        String command2[] = { "cmd.exe", "/c", "copy", srcDir, dest };
        Process p2;
                
        try {
            //Execute command
            p2 = Runtime.getRuntime().exec( command2 );
            
            try {
                //Wait for process to finish
                p2.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }

            //Check if successful
            if (p2.exitValue() == 0) {
                System.out.println("SECOND_COPY: Command Successful");
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, "SECOND_COPY: Command Successful");
                return "http://10.0.2.2/" + currentVideo + ".mp3"; //TODO Change to m3u if player can handle it
            } else {
                System.out.println("SECOND_COPY: Command Failure");
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, "SECOND_COPY: Command Failure");
                return ""; //"CONVERTER: Command Failure"; 
            }  

            //return "http://10.0.2.2/" + currentVideo + ".mp3"; //TODO Change to m3u if player can handle it
            
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
    }

}



