import java.awt.Graphics;
import java.io.IOException;

public class Controller {

	public static ClientForMySys.Koords textKoords = new ClientForMySys.Koords();
	public static ClientForMySys.Koords SystemTextKoords = new ClientForMySys.Koords();
	
	public static boolean goOut = false;
	public static boolean serverCreated = true;
	
	public static boolean ClientIsStarted = false;
	
	public static String systemWord = "";
	public static String systemWordWas = "";
	
	public static String ourWord = "";
	
	public static String word = "";
	public static String dopWord = "";
	
	public static String YourWord = "";
	
	public static int stage = 0;
	public static int talk = 0;
	public static int getIP = 1;
	public static int getPort = 2;
	public static int getNickname = 3;
	public static int getPassword = 4;
	public static int chat = 5;

	public static boolean play = false;
	
	static apart setClient = new apart();
	
	public static boolean enterPressed = false;
	
	Controller() {
		textKoords.x = 240;
		textKoords.y = 375;
		
		SystemTextKoords.x = 340;
		SystemTextKoords.y = 175;
	}
	
	public static class apart extends Thread{
		
		public void go(){
			start();
		}
		
		public void run() {
			
			ClientCore.StartClient();
			
		}
		
	}
	
	public static void AI(){
		
		/*if (stage == nick) {
			switch (word) {
			default: {stage = talk;}
			}
		}*/
		
		if (stage == getPort && !ClientIsStarted) {
			stage = getNickname;
			ClientForMySys.port = Integer.parseInt(word);
			setSysText("connecting...... >-<");
			setClient.go();
			ClientIsStarted = true;
			//ClientForMySys.StartGame = true;
			
		}
	
		if (stage == getIP) {
			ClientForMySys.IP = word;
			stage = getPort;
			setSysText("now say me the port");
			
		}
		
		if (stage == talk) {
			
				switch (word) {
		        case "bye":  {systemWordWas = systemWord; systemWord = "bye, bye!"; ClientForMySys.shutDown = true;}
		                 break;
		                 
		        case "connect":  {setSysText("give me the IP :p"); stage = getIP;}
		        break;
		        
		        case "OK":  {Controller.enterPressed = true; ClientCore.userWord = Controller.word;}
		        break;
		        
		        default: {//Controller.sendMassage = 30;
		        
		        
		        
		        /*try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		        
		        //<-----------------------------
		        
		        //Controller.setSysText("SLEEEP!");
		        
		        //ourWord = word;
		   
				        
				        
				        //Controller.setSysText("!!!");
				        
		        
		        	}
		               
				}
			
		}
		
		if (stage == chat) {
			
			switch (word) {
	        case "bye":  {systemWordWas = systemWord; systemWord = "bye, bye!"; ClientForMySys.shutDown = true;}
	                 break;
			}
		}
		
		if (stage == getPort && ClientIsStarted) {
			stage = talk;
		}
		
	}
	
	public static void setSysText(String s) {
		systemWordWas = systemWord;
		systemWord = s;
	}
	
	public static void setYourMassage(String s) {
		YourWord = s;
		//systemWord = s;
	}
	
	public static void printText(Graphics g) {
		g.setColor(Draw.RED);
		g.drawString(word, textKoords.x + 6, textKoords.y + 8);
		g.setColor(Draw.BLACK);
		
		/*if (systemWord == null) {
			systemWord = "";
		}*/
		
		if (!systemWord.equals("")) {
			g.drawString(systemWordWas, SystemTextKoords.x + 15, SystemTextKoords.y + 130);
			g.drawString(systemWord, SystemTextKoords.x + 15, SystemTextKoords.y + 140);
		}
		
		g.drawString(YourWord, SystemTextKoords.x + 180, SystemTextKoords.y + 130);
		
		if (ClientForMySys.shutDown) {
			
			if (goOut) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				System.exit(1);
			}
			
			goOut = true;
		}
	}
	
}
