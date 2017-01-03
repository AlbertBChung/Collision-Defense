import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Bomb extends Rectangle {
	public  boolean hit;
	public  boolean justKB;
	public  double knockbackDistance=5000;
	public  Vector2F destination;
	public Vector2F pos = new Vector2F();
	public static int width=50;
	public static int height=50;
	private double knockbackSpeed=5;
	double dx;
	double dy;
	public boolean placedRight=false;
	public long timeHit;
	private ArrayList<BufferedImage> anilist;
	Animator ani;
	
	public Bomb(Vector2F position, boolean placedRight) {
		pos=position;
		hit=false;
		destination=position;
		this.placedRight=placedRight;
		anilist=new ArrayList<BufferedImage>();
		anilist.add(Assets.bomb_1);
		anilist.add(Assets.bomb_2);
		ani = new Animator(anilist);
		ani.setSpeed(90);
		ani.play();
	}
	public void tick(double deltaTime){
		
		setBounds((int)pos.xpos, (int)pos.ypos, width, height);
		
		if (hit){
			if(justKB)
			{
				timeHit=System.currentTimeMillis();
			//sets new dy,dx of bomb movement
			double distance = Math.sqrt(Math.pow(Math.abs(pos.xpos-destination.xpos),2)+Math.pow(Math.abs(pos.ypos-destination.ypos),2));
			dx=(destination.xpos-pos.xpos)/(distance/knockbackSpeed);
			dy=(destination.ypos-pos.ypos)/(distance/knockbackSpeed);
			}
			justKB=false;
			
			int indexOfRecipient = Check.indexBombWithMon(new Point((int)pos.xpos,(int)pos.ypos),new Point((int)pos.xpos+this.width,(int)pos.ypos),new Point((int)pos.xpos,(int)pos.ypos+this.height), 
					new Point((int)pos.xpos+this.width,(int)pos.ypos+this.height));
				if(indexOfRecipient>=0)
				{	
					boolean exists=false;
					for(int i =0;i<MonsterManager.eraseIndex.size();i++){
						if(MonsterManager.eraseIndex.get(i)==indexOfRecipient){
							exists=true;		
						}
					}
					if(!exists){
					MonsterManager.eraseIndex.add(indexOfRecipient);
					Player.score++;
					for(int i =0; i<BombManager.bomblist.size();i++){
						if(this.equals(BombManager.bomblist.get(i))){
							Fire fire = new Fire(BombManager.bomblist.get(i).pos.xpos-Fire.size/2,BombManager.bomblist.get(i).pos.ypos-Fire.size/2);
							FireManager.firelist.add(fire);
							BombManager.replaceBomb(i);	
						}
					}
					}
					
				}
		}
		
		
		
		
		this.pos.xpos+=dx;
		this.pos.ypos+=dy;
		
		
		if(Check.CollisionBombBlock(
				  new Point((int) (pos.xpos + this.width +dx) ,
						     (int) (pos.ypos+dy)),
						          
				  new Point((int) (pos.xpos + this.width +dx) , 
				            (int) (pos.ypos+dy+this.height))) && placedRight){
			dx=0;
			dy=0;
		}
		else if(Check.CollisionBombBlock(
				  new Point((int) (pos.xpos  +dx) ,
						     (int) (pos.ypos+dy)),
						          
				  new Point((int) (pos.xpos  +dx) , 
				            (int) (pos.ypos+dy+this.height))) && !placedRight){
			dx=0;
			dy=0;
		}

		
	}
	public void render(Graphics2D g){
		g.drawImage(ani.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
		ani.update(System.currentTimeMillis());}
	
	public  Vector2F getCenter(){
		if(placedRight)
			return new Vector2F(pos.xpos+width,pos.ypos+height/2);
		else
			return new Vector2F(pos.xpos,pos.ypos+height/2);
			
	}

}
