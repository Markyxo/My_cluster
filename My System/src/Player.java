import java.util.LinkedList;

public class Player extends MySys.Koords{
	public String nickName = "";
	public String passW = "";
	public static int playersCount = 500;
	public static Player[] allPlayers = new Player[playersCount];
	//public static int myNumber;
	//public static LinkedList<Player> playerList = new LinkedList<>();
	
	
	public static void initPlayers() {
		for (int i=0;i<playersCount;i++) {
			allPlayers[i] = new Player(null, null);
		}
	}
	
	Player (String nick, String passWorld){
		this.x = 0;
		this.y = 0;
		this.nickName = nick;
		this.passW = passWorld;//XD
	}
	
	
	
	//public MySys.Koords koords = new MySys.Koords();

}
