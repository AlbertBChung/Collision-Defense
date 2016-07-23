
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
	
public class Monster extends Rectangle{

	public Vector2F pos = new Vector2F();
	private float speed;
	private boolean directionIsRight;
	public  int width=50;
	public  int height=50;
	private ArrayList<BufferedImage> anilist;
	Animator ani;
	//for spawn
	private Random rand = new Random();
	private Random rand_2 = new Random();
	Vector2F tempDestination;
	double dx;
	double dy;
	long time;
	long timern;
	long timeDPS;
	long timernDPS;
	boolean hit = false;
	boolean justKB;
	int knockbackDistance;
	int knockbackSpeed=3;
	
	public Monster(float xpos, float ypos, float speed, boolean directionIsRight, int size){
		pos.xpos=xpos;
		pos.ypos=ypos;
		this.speed=speed;
		this.directionIsRight=directionIsRight;
		this.width=size;
		this.height=size;
		if (size == 100) {knockbackDistance = 100;}
		else if (size == 50){knockbackDistance = 200;}		

		anilist = new ArrayList<BufferedImage>();
		anilist.add(Assets.player.getTile(0, 16, 16, 16));
		anilist.add(Assets.player.getTile(16, 16, 16, 16));
		anilist.add(Assets.player.getTile(32, 16, 16, 16));
		anilist.add(Assets.player.getTile(48, 16, 16, 16));
		anilist.add(Assets.player.getTile(64, 16, 16, 16));
		anilist.add(Assets.player.getTile(80, 16, 16, 16));
		
		ani = new Animator(anilist);
		ani .setSpeed(180);
		ani.play();
		
	}
	
public void init(){
		
	}


private void move(boolean directionIsRight){
	
	
	timern=System.currentTimeMillis();
	
	 if (timern-time>=2000){
		do{
			if(directionIsRight){
				tempDestination = new Vector2F(573-width,rand.nextFloat()*400+100);
			}
			else
			{
			tempDestination = new Vector2F(800,rand.nextFloat()*400+100);}
		}while(Math.abs(tempDestination.ypos-pos.ypos)>=50);
		
		double distance = Math.sqrt(Math.pow(Math.abs(pos.xpos-tempDestination.xpos),2)+Math.pow(Math.abs(pos.ypos-tempDestination.ypos),2));
		time=timern;
		dx=(tempDestination.xpos-pos.xpos)/(distance/speed);
		dy=(tempDestination.ypos-pos.ypos)/(distance/speed);
		
	}
	
	
	pos.xpos+=dx;
	pos.ypos+=dy;
	
	
	
}



public void tick(double deltaTime){

		
	
		setBounds((int)pos.xpos, (int)pos.ypos, width, height);
		
		
		if (directionIsRight&& pos.xpos<=573-width || !directionIsRight && pos.xpos>=800)
				{move(directionIsRight);}
			
		else{	
			timernDPS=System.currentTimeMillis();
			if (timernDPS-timeDPS>=1000){
				timeDPS=timernDPS;
				if(!Player.dead){
					if(height==100)
						Player.health-=2;
					else
						Player.health--;
					
					if(Player.health<=0){
						Player.dead=true;
					}
				}
				
			}
		}
		
	}
	public void render(Graphics2D g){
		g.drawImage(ani.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
		ani.update(System.currentTimeMillis());
		
	
	}
	
	
	public int getxpos() {
		return (int)pos.xpos;
	}
	public int getypos() {
		return (int)pos.ypos;
	}
	
	
	
}
