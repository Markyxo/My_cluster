import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MySys extends JFrame{
    
	public static boolean MainMenu = true;
	public static boolean StartGame = false;
	public static boolean gameSetuped = false;
	public static boolean shutDown = false;
	public static boolean startServer = false;
	
	public static int playersCount = 0;
	
	public static int width = 495;
	public static int height = 236;
	
	 
	
	public static int dist(int x1, int y1, int x2, int y2) {
		int d;
		
		d = (int) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		
		return d;
	}
	
    //private JLabel label;
	
    public MySys() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //super("No Name Empire");
        //createGUI();
    }
 
   
    
    public static void createGUI(Draw imgPanel) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
        
       
 
        
        //
        /*
        label = new JLabel();
        label.setFont(new Font("Calibri", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        */
        
        
        
        imgPanel.addKeyListener(new KeyAdapter() {
        	
        	public void keyPressed(KeyEvent e) {
        		//System.out.println(e.getKeyCode());
        		
        		if (MySys.MainMenu) {
        			
        			KeySys.printText(e);
        		}
        		
        		if (e.getKeyCode() == 13) {
            		//Map.map[MouseSys.focusWas.x][MouseSys.focusWas.y].isFocused = false;
            		//MouseSys.playerInHand = false;
            	}
        		
        		if (e.getKeyCode() == 49) {
            		//Draw.cameraSet1 = true;
            		
            	}
        		//MoveCamera
        		if (e.getKeyCode() == 87) {
            		//Camera.moveU = true;
            	}
        		if (e.getKeyCode() == 83) {
            		//Camera.moveD = true;
            	}
        		if (e.getKeyCode() == 65) {
            		//Camera.moveL = true;
            	}
        		if (e.getKeyCode() == 68) {
            		//Camera.moveR = true;
            	}
        	}
        	
            public void keyReleased(KeyEvent e) {
            	
            	//MoveCamera
        		if (e.getKeyCode() == 87) {
            		//Camera.moveU = false;
            	}
        		if (e.getKeyCode() == 83) {
            		//Camera.moveD = false;
            	}
        		if (e.getKeyCode() == 65) {
            		//Camera.moveL = false;
            	}
        		if (e.getKeyCode() == 68) {
            		//Camera.moveR = false;
            	}
                //label.setText(e.getKeyText(e.getKeyCode()));
            	
            	/*
            	//TRANSLATE CAMERA
            	
            	
            	if (e.getKeyCode() == 50) {
            		Player.allPlayers[1].setCamera = true;
            	}
            	
            	if (e.getKeyCode() == 51) {
            		Player.allPlayers[2].setCamera = true;
            	}
            	
            	if (e.getKeyCode() == 52) {
            		Player.allPlayers[3].setCamera = true;
            	}
            	
            	if (e.getKeyCode() == 53) {
            		Player.allPlayers[4].setCamera = true;
            	}*/
            }
             
        });
        
        imgPanel.addMouseListener(new MouseAdapter() {
        	
        	public void mousePressed(MouseEvent e) {
      	  	  MouseSys.mousePressed(e);
        		//MouseSys.mouse.x = e.getX();
        		//MouseSys.mouse.y = e.getY();
        		
        		//MouseSys.Action();
      	  
      	  }
        });
        
        imgPanel.addMouseMotionListener(new MouseAdapter() {
        	
        	public void mouseMoved(MouseEvent e) {
      	  	  
        		MouseSys.mouse.x = e.getX();
        		MouseSys.mouse.y = e.getY();
        		
        		//MouseSys.MouseIn();
      	  
      	  }
        });
         
        //panel.add(label, BorderLayout.CENTER);
                 
        //setPreferredSize(new Dimension(200, 200));
        //this.add(imagePanel);
        //this.add(panel);
        
    }
     
    public static class Koords {
		int x;
		int y;
	}
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //public void run() {
                //JFrame.setDefaultLookAndFeelDecorated(true);
    			Draw imagePanel = new Draw();
    			createGUI(imagePanel);
                JFrame frame = new JFrame();
                //frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(imagePanel);
                frame.setBounds(0, 0, width, height);
        		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        		//frame.setUndecorated(true);
                //frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            //}
        //});
    }

}

