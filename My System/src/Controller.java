import java.awt.Graphics;
import java.io.IOException;

public class Controller {

	public static MySys.Koords textKoords = new MySys.Koords();
	public static MySys.Koords SystemTextKoords = new MySys.Koords();
	
	public static boolean goOut = false;
	public static boolean serverCreated = true;
	
	public static String systemWord = "";
	public static String systemWordWas = "";
	
	public static String word = "";
	public static String dopWord = "";
	
	public static apart setServer = new apart();
	
	Controller() {
		textKoords.x = 5;
		textKoords.y = 205;
		
		SystemTextKoords.x = 165;
		SystemTextKoords.y = 75;
	}
	
	public static void AI(){
		switch (word) {
        case "bye":  {systemWordWas = systemWord; systemWord = "bye, bye!"; MySys.shutDown = true;}
                 break;

		case ("start server"):  {systemWordWas = systemWord; systemWord = "starting server..."; MySys.startServer = true;; 
		
			setSysText("Now i will wait for ");// + ServerCore.users + " connections");
		}
		
	    	break;
		}
	
		
	}
	
	public static void setSysText(String s) {
		systemWordWas = systemWord;
		systemWord = s;
	}
	
	public static class apart extends Thread{
		
		public void go(){
			start();
		}
		
		public void run() {
			try {
				ServerCore.StartServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void printText(Graphics g){
		g.setColor(Draw.RED);
		g.drawString(word, textKoords.x, textKoords.y);
		g.setColor(Draw.BLACK);
		
		g.drawString(systemWordWas, SystemTextKoords.x + 15, SystemTextKoords.y + 130);
		g.drawString(systemWord, SystemTextKoords.x + 15, SystemTextKoords.y + 140);
		
		if (MySys.startServer) {
			
			if (!serverCreated) {
				
				MySys.startServer = false;
				
				setServer.go();
				
			}
			serverCreated = false;
		}
		
		if (MySys.shutDown) {
			
			if (goOut) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				try {
					if (MySys.startServer) {
						ServerCore.server.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(1);
			}
			
			goOut = true;
		}
	}
	
}
