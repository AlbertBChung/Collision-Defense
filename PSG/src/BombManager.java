import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class BombManager {
	
	public static ArrayList<Bomb> bomblist = new ArrayList<Bomb>();
	public static ArrayList<Integer> eraseIndex = new ArrayList<Integer>();
	
	public BombManager() {
			
		
	}
	
	public static void knockback(Point p1, Point p2, int i){
		//sets the perpendicular knockback info
		bomblist.get(i).hit=true;
		bomblist.get(i).justKB=true;
		
		double xContact = p1.getX()-Bullet.speed;
		double yContact = ((p1.getY()+p2.getY())/2);
		Vector2F center = bomblist.get(i).getCenter();
		double distance = Math.sqrt(Math.pow(center.xpos-xContact,2)+Math.pow(center.ypos-yContact,2));
		double xdestination = (center.xpos -xContact)*(bomblist.get(i).knockbackDistance/distance);
		double ydestination = (center.ypos -yContact)*(bomblist.get(i).knockbackDistance/distance);
		while(true)
		{
			if(bomblist.get(i).pos.xpos+xdestination<55){xdestination+=1;}
			else if(bomblist.get(i).pos.xpos+xdestination>1315-bomblist.get(i).width){xdestination-=1;}
			else if(bomblist.get(i).pos.ypos+ydestination<55){ydestination+=1;}
			else if(bomblist.get(i).pos.ypos+ydestination>580-bomblist.get(i).height){ydestination-=1;}
			else{break;}
		
		}
		bomblist.get(i).destination=new Vector2F((float)(bomblist.get(i).getX()+xdestination),(float)(bomblist.get(i).getY()+ydestination));
	}
	
	
	
	public Bomb makeBomb(boolean right){
		Random rand = new Random();
		if (right){
			float xright = rand.nextFloat()*100+780;
			float yright = rand.nextFloat()*500+100;
			return new Bomb(new Vector2F(xright,yright));
		}
		else{
			float xleft = rand.nextFloat()*100+500;
			float yleft = rand.nextFloat()*500+100;
			return new Bomb(new Vector2F(xleft,yleft));
		}
		
	}
	public void tick (double deltaTime){
		
		
		
		for(int i =0;i<bomblist.size();i++){
			
			bomblist.get(i).tick(deltaTime);
		}
	
		
	}
	public void render(Graphics2D g){
		
		for(int i =0;i<bomblist.size();i++){
			
			bomblist.get(i).render(g);
			
		}
	}

}
