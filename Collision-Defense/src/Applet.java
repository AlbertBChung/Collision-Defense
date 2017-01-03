

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JOptionPane;



public class Applet extends JApplet{

	public static SpriteSheet blocks = new SpriteSheet();
	
	//public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	//public static int width = gd.getDisplayMode().getWidth();
	//public static int height = gd.getDisplayMode().getHeight();
	
	public static int width = 1700;
	public static int height = 640;
	public static boolean ran = false;
	public static MouseManager mouse = new MouseManager();
	public static int endScore;
	public static String name;
	public static boolean printScore;
	GameLoop gl;
	public static boolean stop;
	
	public void init(){
		
	}
	public void start(){
		ran = true;
		if(stop){
			Map.player=new Player();
			Monster.speedBigRef=.2F; Monster.speedBigRef=.1F;
			MonsterManager.spawnRate = 10000; MonsterManager.eraseIndex=new ArrayList<Integer>(); MonsterManager.monsterlist= new ArrayList<Monster>();
			
			BombManager.bomblist=new ArrayList<Bomb>();
			BulletManager.bulletlist=new ArrayList<Bullet>();BulletManager.index=new ArrayList<Integer>();
			FireManager.firelist=new ArrayList<Fire>();FireManager.eraseIndex=new ArrayList<Integer>();
			Player.dead=false; Player.health=5; Player.score=0;	Player.ammo=100000000;
			TileManager.blocks=new ArrayList<Block>();
			stop=false;
		}
		name = JOptionPane.showInputDialog("Mouse: Aim/Shoot\nW: up\nA: left\nS: down\nD: right\nSpace: dash\n\nEnter your name to start.");
		if(getName()==null){
			name="Anonymous";
		}
		gl=new GameLoop(width,height);
		this.add(gl);
		this.addKeyListener(new Player());
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.setSize(new Dimension(width, height));
		this.setFocusable(true);
		requestFocusInWindow();
		this.setVisible(true);
		
		
	}
	public void stop(){
		stop=true;
	}
	
	public int getScore(){
		endScore=Player.score;
		return endScore;
	}
	public String getName(){
		return name;
	}
	
	
	public boolean print(){
		return printScore;
	}
}
	

