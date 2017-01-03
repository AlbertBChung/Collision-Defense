import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class BombManager {
	
	public static ArrayList<Bomb> bomblist = new ArrayList<Bomb>();
	
	public BombManager() {
			
		
	}
	public void init(){

		bomblist.add(new Bomb(new Vector2F(570-Bomb.width/2,100+100*1),false));
		bomblist.add(new Bomb(new Vector2F(570-Bomb.width/2,100+100*2),false));
		bomblist.add(new Bomb(new Vector2F(570-Bomb.width/2,100+100*3),false));
		bomblist.add(new Bomb(new Vector2F(570-Bomb.width/2,100+100*4),false));
		
		bomblist.add(new Bomb(new Vector2F(810-Bomb.width/2,100+100*1),true));
		bomblist.add(new Bomb(new Vector2F(810-Bomb.width/2,100+100*2),true));
		bomblist.add(new Bomb(new Vector2F(810-Bomb.width/2,100+100*3),true));
		bomblist.add(new Bomb(new Vector2F(810-Bomb.width/2,100+100*4),true));

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
	/*
		while(true)
		{
			if(bomblist.get(i).pos.xpos+xdestination<55){xdestination+=1;}
			else if(bomblist.get(i).pos.xpos+xdestination>1315-bomblist.get(i).width){xdestination-=1;}
			else if(bomblist.get(i).pos.ypos+ydestination<55){ydestination+=1;}
			else if(bomblist.get(i).pos.ypos+ydestination>580-bomblist.get(i).height){ydestination-=1;}
			else{break;}
		
		}
*/
		bomblist.get(i).destination=new Vector2F((float)(bomblist.get(i).getX()+xdestination),(float)(bomblist.get(i).getY()+ydestination));
		
	}
	
	
	

	public void tick (double deltaTime){
	
	
		
		
		for(int i =0;i<bomblist.size();i++){
			
			bomblist.get(i).tick(deltaTime);
		}
		for(int i =0;i<bomblist.size();i++){
			if(bomblist.get(i).hit){
				long timern=System.currentTimeMillis();
				if(timern-bomblist.get(i).timeHit>30000){
					Fire fire = new Fire(bomblist.get(i).pos.xpos-Fire.size/2,bomblist.get(i).pos.ypos-Fire.size/2);
					FireManager.firelist.add(fire);
					bomblist.get(i).timeHit=timern;
					replaceBomb(i);
				}
				
			}
		}
		
	}
	public static void replaceBomb(int i) {
		switch (i){
		case 0:
			bomblist.set(0,new Bomb(new Vector2F(570-Bomb.width/2,100+100*1),false));
			break;
		case 1:
			bomblist.set(1,new Bomb(new Vector2F(570-Bomb.width/2,100+100*2),false));
			break;
		case 2:
			bomblist.set(2,new Bomb(new Vector2F(570-Bomb.width/2,100+100*3),false));
			break;
		case 3:
			bomblist.set(3,new Bomb(new Vector2F(570-Bomb.width/2,100+100*4),false));
			break;
		case 4:
			bomblist.set(4,new Bomb(new Vector2F(810-Bomb.width/2,100+100*1),true));
			break;
		case 5:
			bomblist.set(5,new Bomb(new Vector2F(810-Bomb.width/2,100+100*2),true));
			break;
		case 6:
			bomblist.set(6,new Bomb(new Vector2F(810-Bomb.width/2,100+100*3),true));
			break;
		case 7:
			bomblist.set(7,new Bomb(new Vector2F(810-Bomb.width/2,100+100*4),true));
			break;
		
		}
		
	}
	public void render(Graphics2D g){
		
		for(int i =0;i<bomblist.size();i++){
			
			bomblist.get(i).render(g);
			
		}
	}

}
