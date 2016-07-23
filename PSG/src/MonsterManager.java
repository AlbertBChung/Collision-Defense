

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class MonsterManager {

		
		public static ArrayList<Monster> monsterlist = new ArrayList<Monster>();
		public static ArrayList<Integer> eraseIndex = new ArrayList<Integer>();
		
		public MonsterManager() { 
			
		}
		
		public void init(){
			
		}
		public void tick (double deltaTime){
		
			
			for(int i =0;i<monsterlist.size();i++){
				
				monsterlist.get(i).tick(deltaTime);
			}
			
		
			
			
		}
		public static void knockback(Point p1, Point p2, int i){
			//sets the perpendicular knockback info
			monsterlist.get(i).hit=true;
			monsterlist.get(i).justKB=true;
			
			double xContact = p1.getX()-Bullet.speed;
			double yContact = ((p1.getY()+p2.getY())/2);
			Vector2F center = monsterlist.get(i).getCenter();
			double distance = Math.sqrt(Math.pow(center.xpos-xContact,2)+Math.pow(center.ypos-yContact,2));
			double xdestination = (center.xpos -xContact)*(monsterlist.get(i).knockbackDistance/distance);
			double ydestination = (center.ypos -yContact)*(monsterlist.get(i).knockbackDistance/distance);
			while(true)
			{
				if(monsterlist.get(i).pos.xpos+xdestination<55){xdestination+=1;}
				else if(monsterlist.get(i).pos.xpos+xdestination>1315-monsterlist.get(i).width){xdestination-=1;}
				else if(monsterlist.get(i).pos.ypos+ydestination<55){ydestination+=1;}
				else if(monsterlist.get(i).pos.ypos+ydestination>580-monsterlist.get(i).height){ydestination-=1;}
				else{break;}
				
			
			}
			
			monsterlist.get(i).tempDestination=new Vector2F((float)(monsterlist.get(i).getX()+xdestination),(float)(monsterlist.get(i).getY()+ydestination));
			

		}
		public void render(Graphics2D g){
			
			for(int i =0;i<monsterlist.size();i++){
				
				monsterlist.get(i).render(g);
				
			}
			//35,30 1335,30
			//30,600 
		}
	}

	


