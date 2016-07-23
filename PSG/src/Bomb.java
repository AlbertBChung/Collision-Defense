import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public class Bomb extends Rectangle {
	public  boolean hit;
	public  boolean justKB;
	public static double knockbackDistance=50;
	public  Vector2F destination;
	Vector2F pos = new Vector2F();
	public int width=20;
	public int height=20;
	private double knockbackSpeed=5;
	double dx;
	double dy;
	
	public Bomb(Vector2F position) {
		pos=position;
		destination=position;
	}
	public void tick(double deltaTime){
		setBounds((int)pos.xpos, (int)pos.ypos, width, height);
		
		if (hit){
			if(justKB)
			{
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
							BombManager.eraseIndex.add(i);break;
						}
					}
				}
		}
		
		
		
		
		this.pos.xpos+=dx;
		this.pos.ypos+=dy;
		
	}
	public void render(Graphics2D g){
		g.fillRect((int)pos.xpos,(int)pos.ypos,width,height);
	}
	public  Vector2F getCenter(){
		//at direct center. may need change to edge midpoint
			return new Vector2F(pos.xpos+width/2,pos.ypos+height/2);
	}

}
