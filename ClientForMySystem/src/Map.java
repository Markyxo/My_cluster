import java.awt.Graphics;

public class Map {
	public static int mapHeight = 100;
	public static int mapWidth = 100;
	public static int map[][] = new int[mapWidth][mapHeight];
	public static int Void = 0;
	public static int CellSize = 50;
	
	
	public static void initMap() {
		for(int i=0;i<mapWidth;i++) {
			for(int j=0;j<mapHeight;j++) {
				map[i][j] = Void;
			}
		}
	}
	public static void drawMap(Graphics g) {
		for(int i=0;i<mapWidth;i++) {
			for(int j=0;j<mapHeight;j++) {
				if (map[i][j] == Void) {
					g.drawRect(CellSize*i, CellSize*j, CellSize, CellSize);
				}
			}
		}
	}
}	

