package download;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Download implements Runnable {
	String playListTitle;
	String cmd;
	String path;
	
	
	
	
	public Download(String playListTitle,String cmd) {
		// TODO Auto-generated constructor stub
		this.cmd = cmd;
		this.playListTitle = playListTitle;
		this.path = "c:\\videos\\" + playListTitle;
		
	}
	public void createDirectory(){
		


File theDirVideos = new File(path + "\\videos");
File thePictures = new File(path + "\\pictures");

// if the directory does not exist, create it
if (!theDirVideos.exists()) {
    System.out.println("creating directory: " + playListTitle);
    boolean result = false;

    try{
        theDirVideos.mkdirs();
        thePictures.mkdirs();
        result = true;
    } 
    catch(SecurityException se){
        //handle it
    }        
    if(result) {    
        System.out.println("DIR created");  
    }
}


		
		
	}
	public void downloadVideo(){
		
		createDirectory();
		
		playListTitle = '"'+playListTitle+'"';
		
		String command = "cd "+ playListTitle+ " && "+ cmd;
		System.out.println(command);
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c",command);
	        builder.redirectErrorStream(true);
	        Process p;
			try {
				p = builder.start();
				   BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			        String line;
			        while (true) {
			            line = r.readLine();
			            if (line == null) { break; }
			            System.out.println(line);
			        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		downloadVideo();
		
		
	}

}
