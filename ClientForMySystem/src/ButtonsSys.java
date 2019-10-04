import java.awt.Image;

public class ButtonsSys {

	public static button setText = new button();
	
	public static class button{
		int x;
		int y;
		Image img;
		Image imgON;
		int width;
		int height;
		boolean mouseIn;
	}
	
	public static void initButtons() {
		setText.x = 0;
		setText.y = 100;
		setText.width = 100;
		setText.height = 50;
	}
	
}
