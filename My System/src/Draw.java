
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Draw extends JPanel implements ActionListener{

	public static MySys.Koords smeshenie = new MySys.Koords();
	
	public static Color RED = new Color(255,0,0);
	public static Color BLACK = new Color(0,0,0);
	public static Color WHITE = new Color(255,255,255);
	
	public static Controller controller = new Controller();
	
	public static boolean buttonsControl = false;
	public static boolean cameraSet1 = false;
	
	int y;
	
	
	//
	
	//��������� �������
	 Toolkit toolKit = Toolkit.getDefaultToolkit();
	 Cursor myCursor = toolKit.getDefaultToolkit().createCustomCursor(toolKit.getDefaultToolkit().getImage("res/NOPE.png"), new Point(0,0), "myCursor");
	//��������� �����
	 final Clip clip2 = AudioSystem.getClip();
	 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Server/first.wav").getAbsoluteFile());
	 
	Timer mainTimer = new Timer(20, this);
	
	
	 
	
	public Draw() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.setLayout(null);
		
		mainTimer.start();
		setFocusable(true);
		this.setCursor(myCursor);
		
		//ButtonsSys.initButtons();
		//Map.MapSetup();
		Player.initPlayers();
		
		
		
		
		clip2.open(audioInputStream);	
		clip2.start();     
	}
	
	public void paint(Graphics g) {
		//Graphics f = g;
		
		
		
		if (MySys.MainMenu) {
			g.drawImage(ImageSys.MySysImage, 0, 0, null);
			drawServerMenu(g);
			//for (int i = 0;i<Player.playerList.size();i++ ) {
				//g.fillRect(Player.AAA.x, Player.AAA.y, 50, 50);
			//}
			
			controller.printText(g);
			
		}
		
		if (MySys.StartGame) {
			/*
			Camera.Cam.moveCamera();
			
			g.translate(Camera.Cam.x, Camera.Cam.y);//Player.Player1.x - Player.Player1.x*Map.CellSize - Map.indentation, Player.Player1.y - Player.Player1.y*Map.CellSize - Map.indentation/4);
			
			
			if (!buttonsControl) {
				for (int i = 0;i<ButtonsSys.MainMenubutonsCount;i++) {
					this.remove(ButtonsSys.MainMenuButtons[i].button);
				}
				buttonsControl = true;
			}
			Map.drawMap(g);
			*/
			clip2.stop();	
		}
		
		g.drawImage(ImageSys.mouseI, MouseSys.mouse.x, MouseSys.mouse.y, null);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		//this.add(ButtonsSys.ExitButton);
	}
	
	private static void drawServerMenu(Graphics g) {
		g.drawImage(ImageSys.textField, Controller.textKoords.x - 3, Controller.textKoords.y - 12, null);
		g.drawImage(ImageSys.SystemTextField, Controller.SystemTextKoords.x - 3, Controller.SystemTextKoords.y - 12, null);
	}
	
}

