package src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class BulletManager {


	public static ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
	public static ArrayList<Integer> index = new ArrayList<Integer>();
	
	public BulletManager() {
		
	}
	public static void knockback(Point p1, Point p2, Monster mon){
		//sets the perpendicular knockback info
		
		mon.hit=true;
		mon.justKB=true;
		
		double xContact = p1.getX()-Bullet.speed;
		double yContact = ((p1.getY()+p2.getY())/2);
		Vector2F center = mon.getCenter();
		double distance = Math.sqrt(Math.pow(center.xpos-xContact,2)+Math.pow(center.ypos-yContact,2));
		double xdestination = (center.xpos -xContact)*(mon.knockbackDistance/distance);
		double ydestination = (center.ypos -yContact)*(mon.knockbackDistance/distance);
		mon.tempDestination=new Vector2F((float)(mon.getX()+xdestination),(float)(mon.getY()+ydestination));
		
		
	}
	
	public void init(){}
	public void tick (double deltaTime){
		//we cant access bullet while traversing bulletlist??
		//now we have  alot of lag.
		
		ArrayList<Bullet> newbulletlist = new ArrayList<Bullet>();
		for(int i =0;i<bulletlist.size();i++){
			newbulletlist.add(bulletlist.get(i));
		}
		for(int i =0;i<newbulletlist.size();i++){
		
			
			if(newbulletlist.get(i).directionIsRight){
				if(Check.CollisionBulletBlock(
						  new Point((int) (newbulletlist.get(i).getxpos() + Bullet.width +Bullet.speed) ,
						     (int) (newbulletlist.get(i).getypos())),
						          
						  new Point((int) (newbulletlist.get(i).getxpos() +Bullet.width +Bullet.speed) , 
						            (int) (newbulletlist.get(i).getypos() +Bullet.height))  ))
						{
						index.add(i);
						}
					else if(Check.CollisionMonster(
						  new Point((int) (newbulletlist.get(i).getxpos() + Bullet.width +Bullet.speed) ,
						     (int) (newbulletlist.get(i).getypos())),
						          
						  new Point((int) (newbulletlist.get(i).getxpos() +Bullet.width +Bullet.speed) , 
						            (int) (newbulletlist.get(i).getypos() +Bullet.height))  ))
						{
							index.add(i);
						}
			}
			else{
				if(Check.CollisionBulletBlock(
						  new Point((int) (newbulletlist.get(i).getxpos()-Bullet.speed) ,
						     (int) (newbulletlist.get(i).getypos())),
						          
						  new Point((int) (newbulletlist.get(i).getxpos()-Bullet.speed) , 
						            (int) (newbulletlist.get(i).getypos() +Bullet.height))  ))
						{
						index.add(i);
						}
					else if(Check.CollisionMonster(
						  new Point((int) (newbulletlist.get(i).getxpos()-Bullet.speed) ,
						     (int) (newbulletlist.get(i).getypos())),
						          
						  new Point((int) (newbulletlist.get(i).getxpos()-Bullet.speed) , 
						            (int) (newbulletlist.get(i).getypos() +Bullet.height))  ))
						{
							index.add(i);
						}
				
				
			}
					
		}
		
		
		
		for(int i =0;i<bulletlist.size();i++){
			
			bulletlist.get(i).tick(deltaTime);
		}
		
	
		
		
	}
	public void render(Graphics2D g){
		
		for(int i =0;i<bulletlist.size();i++){
			
			bulletlist.get(i).render(g);
			
		}
	}
}
