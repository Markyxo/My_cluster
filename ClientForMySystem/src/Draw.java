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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Draw extends JPanel implements ActionListener{

	public static ClientForMySys.Koords smeshenie = new ClientForMySys.Koords();
	
	public static Color RED = new Color(255,0,0);
	public static Color BLACK = new Color(0,0,0);
	public static Color WHITE = new Color(255,255,255);
	
	public static Controller controller = new Controller();
	
	public static boolean buttonsControl = false;
	public static boolean cameraSet1 = false;
	public static boolean frameSetup = false;
	
	int y;
	
	public static class Camera{
		public static int x;
		public static int y;
	}
	
	//установка курсора
	 Toolkit toolKit = Toolkit.getDefaultToolkit();
	 Cursor myCursor = toolKit.getDefaultToolkit().createCustomCursor(toolKit.getDefaultToolkit().getImage("res/NOPE.png"), new Point(0,0), "myCursor");
	//установка звука
	 final Clip clip2 = AudioSystem.getClip();
	 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Client/first.wav").getAbsoluteFile());
	 
	Timer mainTimer = new Timer(20, this);
	
	
	 
	
	public Draw() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.setLayout(null);
		
		mainTimer.start();
		setFocusable(true);
		this.setCursor(myCursor);
		Player.initPlayers();
		Player.allPlayers[0] = new Player("ME");
		//ButtonsSys.initButtons();
		//Map.MapSetup();
		//Player.initPlayers();
		Map.initMap();
		
		
		clip2.open(audioInputStream);	
		clip2.start();     
	}
	
	public void paint(Graphics g) {
		//Graphics f = g;
		
		
		
		if (ClientForMySys.MainMenu) {
			
			
			g.drawImage(ImageSys.MySysImage, 0, 0, null);
			drawCLientMenu(g);
			
			controller.printText(g);
			
		}
		
		if (ClientForMySys.StartGame) {
			Map.drawMap(g);
			Player.movePlayer();
			g.translate(Camera.x, Camera.y);
			for (int i=0;i<Player.playersCount;i++) {
				if (Player.allPlayers[i].nickName != null) {
					g.fillRect(Player.allPlayers[i].x, Player.allPlayers[i].y, 50,50);
					g.setColor(RED);
					g.drawString(Player.allPlayers[i].nickName, Player.allPlayers[i].x, Player.allPlayers[i].y);
				}
			}
			//Camera.moveCamera();
			
			//g.translate(Camera.x, Camera.y);//Player.Player1.x - Player.Player1.x*Map.CellSize - Map.indentation, Player.Player1.y - Player.Player1.y*Map.CellSize - Map.indentation/4);
			
			
			clip2.stop();	
		}

		g.drawImage(ImageSys.mouseI, MouseSys.mouse.x - Camera.x, MouseSys.mouse.y - Camera.y, null);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		//this.add(ButtonsSys.ExitButton);
	}
	
	private static void drawCLientMenu(Graphics g) {
		g.drawImage(ImageSys.textField, Controller.textKoords.x - 3, Controller.textKoords.y - 12, null);
		g.drawImage(ImageSys.SystemTextField, Controller.SystemTextKoords.x - 3, Controller.SystemTextKoords.y - 12, null);
	}
	
}


