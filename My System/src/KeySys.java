import java.awt.event.KeyEvent;

public class KeySys {

	public static void printText(KeyEvent e) {
		
		if ((e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() >= 64 && e.getKeyCode() <= 73)) {
			Controller.word = Controller.word + e.getKeyChar();
		}
		
		if (e.getKeyCode() == 32) {
			Controller.word = Controller.word + " ";
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
			
			Controller.AI();
			
			Controller.word = "";
		}
	}
	
}
