import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


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
	
	public Bomb(Vector2F position, boolean placedRight) {
		pos=position;
		hit=false;
		destination=position;
		this.placedRight=placedRight;
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
					MonsterManager.eraseIndex.add(indexOfRecipient);
					Player.score+=2;
					for(int i =0; i<BombManager.bomblist.size();i++){
						if(this.equals(BombManager.bomblist.get(i))){
							BombManager.replaceBomb(i);	
						}
					}
				}
		}
		
		
		
		
		this.pos.xpos+=dx;
		this.pos.ypos+=dy;
		
		
		if(Math.abs(pos.xpos-destination.xpos)<3 && Math.abs(pos.ypos-destination.ypos)<3){
			dx=0;
			dy=0;
		}

		
	}
	public void render(Graphics2D g){
		g.fillRect((int)pos.xpos,(int)pos.ypos,width,height);
	}
	public  Vector2F getCenter(){
		if(placedRight)
			return new Vector2F(pos.xpos+width/2,pos.ypos+height/2);
		else
			return new Vector2F(pos.xpos+width/2,pos.ypos+height/2);
			
	}

}
