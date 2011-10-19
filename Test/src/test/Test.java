/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jonas
 */
public class Test {
    
    private String currentVideo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test a = new Test();
        
        a.tes();
    }
    
    public Test() {
        
    }
      
    public void tes() {
        if( fetchVideo("http://www.youtube.com/watch?v=o6tsmb2A1H4") ) {
           System.out.println( getM3UStream() );
       }
    }    
    
    private String urlToName(String url) {
        return url.substring(31);
    }
    
    public boolean fetchVideo(String url) {
        
        currentVideo = urlToName(url);
        String cmdString = "cmd /c perl YoutubeStream-To-FLV-Converter.pl " + url; //"perl YoutubeStream-To-FLV-Converter.pl " + url
        Process p;

        String s = null;
        
        try
        {
            p = Runtime.getRuntime().exec( cmdString );
          
            //Handling the streams so that dead lock situation never occurs.  
            ProcessHandler inputStream = new ProcessHandler(p.getInputStream(),"INPUT") {};
            ProcessHandler errorStream = new ProcessHandler(p.getErrorStream(),"ERROR");
            
            //Start the stream threads 
            inputStream.start();
            errorStream.start();

            p.waitFor();
            if (p.exitValue() == 0) {
                System.out.println("Command Successful");
                return true;
            } else {
                System.out.println("Command Failure");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            return false;
        }
    }
    
    public String getM3UStream() {
        convertToMp3();
        createM3uFile();
        return "";
    }
    
    /**
     * Converts the *.flv to *.mp3 using the externa ffmpeg tool
     * @return filepath to the *.m3u (e.g. http://example.com/example.m3u)
     */
    public String convertToMp3() {
  
        String cmdString = "cmd.exe /c ffmpeg.exe -i " + currentVideo + ".flv " + currentVideo + ".mp3";
        System.out.println(cmdString);
        Process p;
        
        //Call the Pearl-script and start download
//        try {
//            p = Runtime.getRuntime().exec(cmdString);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            return convertToMp3();
//        }
//        
//        //Wait for the download to complete
//        try {
//            p.waitFor();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            return convertToMp3();
//        }
        
        String s = null;
        
        try
        {
            p = Runtime.getRuntime().exec( cmdString );
            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            p.waitFor();
            if (p.exitValue() == 0) {
                System.out.println("Command Successful");
            } else {
                System.out.println("Command Failure");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
        
        
        return currentVideo + ".mp3";
    }

    /**
     * Creates an *m3u file with the new *.mp3 file
     */
    private void createM3uFile() {
        
        String[] output = { "#EXTM3U\n", "#EXTINF:123,Sample Artist - Sample title\n", "C:\\Dropbox\\Skolarbete\\Datateknik (AV) - Service Oriented Architecture SOA (7.5hp)\\Project\\MIUN-Youtube-Music-Converter\\" + currentVideo + ".mp3" };
        
        try {
            FileWriter fstream = new FileWriter( currentVideo + ".m3u");
            BufferedWriter out = new BufferedWriter(fstream);
           
            for(String line : output) {
                out.write(line);
            }
            
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "Error: " + e.getMessage(), e);
        }
    }

}
