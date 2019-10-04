import java.util.LinkedList;

public class Player extends ClientForMySys.Koords{
	public static int v = 5;
	
	public static int playersCount = 500;
	public static Player[] allPlayers = new Player[playersCount];
	public static Player me = new Player("I");
	
	public static int myNumber;
	
	public static byte Hero = 0;
	
	public static boolean moveR = false;
	public static boolean moveL = false;
	public static boolean moveD = false;
	public static boolean moveU = false;
	
	public String nickName = "";
	
	//public ClientForMySys.Koords koords = new ClientForMySys.Koords();
	
	Player(String nick){
		nickName = nick;
		x = 900;
		y = 480;
	}
	
	public static void initPlayers() {
		for (int j = 0; j<playersCount;j++) {
			allPlayers[j] = new Player(null);
		}
	}
	
	public static void movePlayer() {
		if (moveR) {
			allPlayers[myNumber].x = allPlayers[myNumber].x + v;
			Draw.Camera.x = Draw.Camera.x - v;
			//MouseSys.mouse.x++;
		}
		if (moveL) {
			allPlayers[myNumber].x = allPlayers[myNumber].x - v;
			Draw.Camera.x = Draw.Camera.x + v;
			//MouseSys.mouse.x--;
		}
		if (moveU) {
			allPlayers[myNumber].y = allPlayers[myNumber].y - v;
			Draw.Camera.y = Draw.Camera.y + v;
			//MouseSys.mouse.y--;
		}
		if (moveD) {
			allPlayers[myNumber].y = allPlayers[myNumber].y + v;
			Draw.Camera.y = Draw.Camera.y - v;
			//MouseSys.mouse.y++;
		}
	}

}
