


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player implements KeyListener {

	Vector2F pos;
	public static int width =70;
	public static int height= 70;
	public static boolean up,down,left,right;
	private static boolean dash;
	private static float speed = 100F;
	private float fixDt = 1f/60F;
	public static boolean isJumping,isFalling;
	public static int counter = 0;
	public boolean directionIsRight;
	public static int ammo=1000;
	public static int health=5;
	public static boolean dead;
	public static int score;
	private int animationState = 0 ;
	private static long dashtime;
	public static long dashCooldownTime;
	private ArrayList<BufferedImage> listUp;
	Animator ani_up;
	private ArrayList<BufferedImage> listDown;
	Animator ani_down;
	private ArrayList<BufferedImage> listLeft;
	Animator ani_left;
	private ArrayList<BufferedImage> listRight;
	Animator ani_right;
	private ArrayList<BufferedImage> listIdle;
	Animator ani_idle;
	private ArrayList<BufferedImage> listDeath;
	Animator ani_death;
	
	
	
	public Player() {
		pos  = new Vector2F(Applet.width/2 - width/2, Applet.height/2-height/2);
	}

	public void init() {
		listUp = new ArrayList<BufferedImage>();
		listDown = new ArrayList<BufferedImage>();
		listRight = new ArrayList<BufferedImage>();
		listLeft = new ArrayList<BufferedImage>();
		listIdle = new ArrayList<BufferedImage>();
		listDeath = new ArrayList<BufferedImage>();
		
		listUp.add(Assets.player.getTile(0, 90, 18, 29));
		listUp.add(Assets.player.getTile(18, 90, 18, 29));
		listUp.add(Assets.player.getTile(36, 90, 18, 29));
		listUp.add(Assets.player.getTile(54, 90, 18, 29));
		listUp.add(Assets.player.getTile(72, 90, 18, 29));
		listUp.add(Assets.player.getTile(90, 90,18, 29));
		listUp.add(Assets.player.getTile(108, 90,18, 29));
		listUp.add(Assets.player.getTile(126, 90, 18, 29));
		
		listDown.add(Assets.player.getTile(0, 61, 18, 29));
		listDown.add(Assets.player.getTile(18, 61, 18, 29));
		listDown.add(Assets.player.getTile(36, 61, 18, 29));
		listDown.add(Assets.player.getTile(54, 61, 18, 29));
		listDown.add(Assets.player.getTile(72, 61, 18, 29));
		listDown.add(Assets.player.getTile(90, 61,18, 29));
		listDown.add(Assets.player.getTile(108,61,18, 29));
		listDown.add(Assets.player.getTile(126, 61, 18, 29));
		
		
		listRight.add(Assets.player.getTile(0, 119, 18, 31));
		listRight.add(Assets.player.getTile(18, 119, 18, 31));
		
		listLeft.add(Assets.player.getTile(0, 150, 18, 31));
		listLeft.add(Assets.player.getTile(18, 150, 18, 31));
		
		listIdle.add(Assets.player.getTile(0, 32, 18, 29));
		listIdle.add(Assets.player.getTile(18, 32, 18, 29));
		listIdle.add(Assets.player.getTile(36, 32, 18, 29));
		listIdle.add(Assets.player.getTile(54, 32, 18, 29));
		
		listDeath.add(Assets.player.getTile(0, 16, 16, 16));
		listDeath.add(Assets.player.getTile(16,16, 16, 16));
		
		ani_up = new Animator(listUp);
		ani_up .setSpeed(180);
		ani_up.play();
		ani_down = new Animator(listDown);
		ani_down .setSpeed(180);
		ani_down.play();
		ani_right = new Animator(listRight);
		ani_right .setSpeed(180);
		ani_right.play();
		ani_left = new Animator(listLeft);
		ani_left .setSpeed(180);
		ani_left.play();
		ani_idle = new Animator(listIdle);
		ani_idle .setSpeed(180);
		ani_idle.play();
		ani_death = new Animator(listDeath);
		ani_death .setSpeed(180);
		ani_death.play();
		
	}


	//if(isJumping){
	//	
	//	pos.ypos-=moveAmount;
	//	counter++;
	//	if(counter==5000){counter=0;isJumping=false;isFalling=true;}
	//}
//	
//	if(isFalling){
//		pos.ypos+=moveAmount; counter++;pp
//	if (counter == 4990){counter=0;isFalling=false;}
	//}
	public void tick(double deltaTime) {
		
		float moveAmount = (float)(speed*fixDt);
		if(Player.dash){
			
			if(System.currentTimeMillis()-Player.dashtime>=75){ Player.speed/=10; Player.dash=false;}
		}
		
		
		if(up){
			if(!Check.CollisionPlayerBlock(
				      
					  new Point((int) (pos.xpos + GameLoop.map.xpos) ,
					     (int) (pos.ypos + GameLoop.map.ypos - moveAmount)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos + width) , 
					            (int) (pos.ypos + GameLoop.map.ypos - moveAmount))  ))
			{

				pos.ypos-=moveAmount;
				animationState = 0;
			}
			else{
				if(Check.CollisionReload(

						  new Point((int) (pos.xpos + GameLoop.map.xpos) ,
						     (int) (pos.ypos + GameLoop.map.ypos - moveAmount)),
						          
						  new Point((int) (pos.xpos + GameLoop.map.xpos + width) , 
						            (int) (pos.ypos + GameLoop.map.ypos - moveAmount))  ))
				{
				Player.ammo=10;
				}
			}
			
		
			
		
		}
		if(down){
			
			if(!Check.CollisionPlayerBlock(
				      
					  new Point((int) (pos.xpos + GameLoop.map.xpos) ,
					     (int) (pos.ypos + GameLoop.map.ypos +height+ moveAmount)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos + width) , 
					            (int) (pos.ypos + GameLoop.map.ypos +height+ moveAmount))  ))
			{
				pos.ypos+=moveAmount;
				animationState = 1;
			}
			else{
				if(Check.CollisionReload(

						  new Point((int) (pos.xpos + GameLoop.map.xpos) ,
						     (int) (pos.ypos + GameLoop.map.ypos +height+ moveAmount)),
						          
						  new Point((int) (pos.xpos + GameLoop.map.xpos + width) , 
						            (int) (pos.ypos + GameLoop.map.ypos +height+ moveAmount))  ))
				{
				Player.ammo=10;
				}
			}
				
			
		}
		if(left ){
			directionIsRight =false;
			if(!Check.CollisionPlayerBlock(
				      
					  new Point((int) (pos.xpos + GameLoop.map.xpos-moveAmount) ,
					     (int) (pos.ypos + GameLoop.map.ypos +height)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos -moveAmount) , 
					            (int) (pos.ypos + GameLoop.map.ypos))  ))
			{
			pos.xpos-=moveAmount;
			animationState = 3;
			}
			else{
				if(Check.CollisionReload(

						  new Point((int) (pos.xpos + GameLoop.map.xpos-moveAmount) ,
						     (int) (pos.ypos + GameLoop.map.ypos +height)),
						          
						  new Point((int) (pos.xpos + GameLoop.map.xpos -moveAmount) , 
						            (int) (pos.ypos + GameLoop.map.ypos))  ))
				{
				Player.ammo=10;
				}
			}
		}
		if(right){
			directionIsRight = true;
			if(!Check.CollisionPlayerBlock(
			 
					  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) ,
					     (int) (pos.ypos + GameLoop.map.ypos)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) , 
					            (int) (pos.ypos + GameLoop.map.ypos+height))  ))
			{
			pos.xpos+=moveAmount;
			animationState = 2;
			}
			else{
				if(Check.CollisionReload(
						 
						  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) ,
						     (int) (pos.ypos + GameLoop.map.ypos)),
						          
						  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) , 
						            (int) (pos.ypos + GameLoop.map.ypos+height))  ))
				{
				Player.ammo=100;
				}
			}
		}
		if (!up && !down && !left && !right && !dead){
			animationState = 4;
		}
		if(dead){
			animationState = 5;
		}
		
		
	}

	public void render(Graphics2D g) {
		if(animationState == 0 ){
			g.drawImage(ani_up.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (up){ani_up.update(System.currentTimeMillis());}
		}
		if(animationState == 1 ){
			g.drawImage(ani_down.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (down){ani_down.update(System.currentTimeMillis());}
		}
		if(animationState == 2 ){
			g.drawImage(ani_right.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (right){ani_right.update(System.currentTimeMillis());}
		}
		if(animationState == 3 ){
			g.drawImage(ani_left.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (left){ani_left.update(System.currentTimeMillis());}
		}
		if(animationState == 4){
			g.drawImage(ani_idle.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (!up && !down && !left && !right){ani_idle.update(System.currentTimeMillis());}
		}
		if(animationState == 5){
			g.drawImage(ani_death.sprite,(int)pos.xpos, (int)pos.ypos, width,height,null);
			if (dead){ani_death.update(System.currentTimeMillis());}
		}
		
		}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W){
			up = true;
			isJumping = true;
		}
		if(key == KeyEvent.VK_S){
			down = true;
		}
		if(key == KeyEvent.VK_A){
			left = true;
		}
		if(key == KeyEvent.VK_D){
			right = true;
		}
		if(key == KeyEvent.VK_SPACE){
			long timern=System.currentTimeMillis();
			if(timern-dashCooldownTime>1000){
			dashCooldownTime=timern;
			Player.dash=true;
			Player.dashtime = System.currentTimeMillis();
			Player.speed*=10;
			}
		if(key == KeyEvent.VK_Q){
			
		}
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W){
			up = false;
		}
		if(key == KeyEvent.VK_S){
			down = false;
		}
		if(key == KeyEvent.VK_A){
			left = false;
		}
		if(key == KeyEvent.VK_D){
			right = false;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
