package src;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Main {

	
	public static SpriteSheet blocks = new SpriteSheet();
	
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	
	
	
	
	public static void main(String[] args)
	{
		
		
		Screen frame = new Screen("Protect Game", width, height);
		frame.setFullscreen(1);
		frame.addKeyListener(new Player());
		frame.add(new GameLoop(width, height));
		frame.setVisible(true);
	}
}
