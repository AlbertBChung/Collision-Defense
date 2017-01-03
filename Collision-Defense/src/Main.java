 

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

public class Main {

	
	public static SpriteSheet blocks = new SpriteSheet();
	
	//public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	//public static int width = gd.getDisplayMode().getWidth();
	//public static int height = gd.getDisplayMode().getHeight();
	
	public static int width = 1700;
	public static int height = 640;
	
	public static MouseManager mouse = new MouseManager();
	public static int endScore;
	public static String name;
	public static boolean printScore;
	GameLoop gl;
	public static boolean stop;
	
	public static void main(String[] args)
	{

		name = JOptionPane.showInputDialog("Mouse: Aim/Shoot\nW: up\nA: left\nS: down\nD: right\nSpace: dash\n\nEnter your name to start.");
		if(name==null){
			name="Anonymous";
		}
		
		Screen frame = new Screen("Protect Game", width, height);
		frame.setFullscreen(1);
		frame.addKeyListener(new Player());
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		frame.add(new GameLoop(width, height));
		frame.setVisible(true);
	}
}
