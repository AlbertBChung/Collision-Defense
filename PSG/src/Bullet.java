

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Bullet {
	
	Vector2F pos = new Vector2F();
	public boolean directionIsRight;
	public static int width=50;
	public static int height=20;
	Vector2F destination = new Vector2F();
	public static double speed = 7;
	double distance; 
	double dx,dy;
	
	public Bullet(float xpos , float ypos, boolean directionIsRight, Vector2F dest) {
	pos.xpos = xpos;
	pos.ypos = ypos;
	this.directionIsRight=directionIsRight;
	destination = dest;
	distance = Math.sqrt(Math.pow(Math.abs(xpos-dest.xpos),2)+Math.pow(Math.abs(ypos-dest.ypos),2));
	dx=(destination.xpos-pos.xpos)/(distance/speed);
	dy=(destination.ypos-pos.ypos)/(distance/speed);
		
	}
	public float getxpos(){
		return pos.xpos;
	}
	public float getypos(){
		return pos.ypos;
	}
	public void init(){
	}
	
	public void tick(double deltaTime){
				
	    pos.xpos+=dx;
	    pos.ypos+=dy;
		
		
	}
	public void render(Graphics2D g){
	
		if(this.directionIsRight)
			g.drawImage(Assets.bulletRight,(int)(getxpos()+Bullet.width),(int)getypos()+height/2,Bullet.width,Bullet.height,null);
		else
			g.drawImage(Assets.bulletLeft,(int)(getxpos()+Bullet.width),(int)getypos()+height/2,Bullet.width,Bullet.height,null);
		
			
	}
}
