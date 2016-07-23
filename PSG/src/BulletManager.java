


import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class BulletManager {


	public static ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
	public static ArrayList<Integer> index = new ArrayList<Integer>();
	
	public BulletManager() {
		
	}
	
	
	public void init(){}
	public void tick (double deltaTime){
		//we cant access bullet while traversing bulletlist??
		//now we have  alot of lag.
		
		//if bullet collides with wall -> add
		//if bullet collides with mon -> add
		
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
