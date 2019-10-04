import java.awt.event.KeyEvent;

public class KeySys {

	public static boolean go = false;
	
	public static void printText(KeyEvent e) {
		
		if (!Controller.play && ((e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() >= 48 && e.getKeyCode() <= 57))) {
			Controller.word = Controller.word + e.getKeyChar();
		}
		
		if (e.getKeyCode() == 32) {
			Controller.word = Controller.word + " ";
		}
		
		if (e.getKeyCode() == 0) {
			Controller.word = Controller.word + ".";
		}
		
		if (e.getKeyCode() == 8) {
			 char[] a = Controller.word.toCharArray();
			 for(int i=0;i<a.length-1;i++) {
				 Controller.dopWord = Controller.dopWord + a[i];
			 }
			 
			 Controller.word =  Controller.dopWord;
			 Controller.dopWord = "";
		}
		
		if (e.getKeyCode() == 10) {
			ClientCore.userWord = Controller.word;

			if (Controller.stage == Controller.chat) {
				Controller.enterPressed = true;
				ClientCore.userWord = Controller.word;
			} else
			if (Controller.stage == Controller.getPassword) {
				ClientCore.password = Controller.word;
				Controller.stage = Controller.chat;
			} else
			if (Controller.stage == Controller.getNickname) {
				ClientCore.nickname = Controller.word;
				Controller.stage = Controller.getPassword;
			}
			
			Controller.AI();
			
			Controller.word = "";
			//e.getKeyCode() = null;
		}
	}
	
	public static void move(KeyEvent e) {
		if (go) {
			//ÓÏÐÀÂËÅÍÈÅ ÄÂÈÆÅÍÈÅÌ
			if (e.getKeyCode() == 87) {
	    		Player.moveU = true;
	    	}
			if (e.getKeyCode() == 83) {
	    		Player.moveD = true;
	    	}
			if (e.getKeyCode() == 65) {
	    		Player.moveL = true;
	    	}
			if (e.getKeyCode() == 68) {
	    		Player.moveR = true;
	    	}
		}
	}
	
	public static void moveStop(KeyEvent e) {
		if (go) {
			//ÓÏÐÀÂËÅÍÈÅ ÎÑÒÀÍÎÂÊÎÉ ÄÂÈÆÅÍÈß
			if (e.getKeyCode() == 87) {
	    		Player.moveU = false;
	    	}
			if (e.getKeyCode() == 83) {
	    		Player.moveD = false;
	    	}
			if (e.getKeyCode() == 65) {
	    		Player.moveL = false;
	    	}
			if (e.getKeyCode() == 68) {
	    		Player.moveR = false;
	    	}
		}
	}
	
}
