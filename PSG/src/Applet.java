

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JApplet;



public class Applet extends JApplet{

	public static SpriteSheet blocks = new SpriteSheet();
	
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	public static MouseManager mouse = new MouseManager();
	
	
	public void init(){
		//Screen frame = new Screen("Protect Game", width, height);
		//frame.setFullscreen(1);
		this.addKeyListener(new Player());
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.add(new GameLoop(width, height));
		this.setSize(new Dimension(width, height));
		this.setFocusable(true);
		requestFocusInWindow();
		
		this.setVisible(true);
	}
	
	
	
	
}
	

