package src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player implements KeyListener {

	Vector2F pos;
	private int width = 32;
	private int height= 32;
	private static boolean up,down,left,right;
	private float speed = 1F;
	private float fixDt = 1f/60F;
	public static boolean isJumping,isFalling;
	public static int counter = 0;
	private int animationState = 0 ;
	private ArrayList<BufferedImage> listUp;
	Animator ani_up;
	private ArrayList<BufferedImage> listDown;
	Animator ani_down;
	private ArrayList<BufferedImage> listLeft;
	Animator ani_left;
	private ArrayList<BufferedImage> listRight;
	Animator ani_right;
	
	
	public Player() {
		pos  = new Vector2F(Main.width/2 - width/2, Main.height/2-height/2);
	}

	public void init() {
		listUp = new ArrayList<BufferedImage>();
		listDown = new ArrayList<BufferedImage>();
		listRight = new ArrayList<BufferedImage>();
		listLeft = new ArrayList<BufferedImage>();
		
		listUp.add(Assets.player.getTile(0, 0, 16, 16));
		listUp.add(Assets.player.getTile(16, 0, 16, 16));
		listDown.add(Assets.player.getTile(0, 0, 16, 16));
		listDown.add(Assets.player.getTile(16, 0, 16, 16));
		listRight.add(Assets.player.getTile(0, 0, 16, 16));
		listRight.add(Assets.player.getTile(16, 0, 16, 16));
		listLeft.add(Assets.player.getTile(0, 0, 16, 16));
		listLeft.add(Assets.player.getTile(16, 0, 16, 16));
		
		
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
		
	}

	//if(isJumping){
	//	
	//	pos.ypos-=moveAmount;
	//	counter++;
	//	if(counter==5000){counter=0;isJumping=false;isFalling=true;}
	//}
//	
//	if(isFalling){
//		pos.ypos+=moveAmount; counter++;
//	if (counter == 4990){counter=0;isFalling=false;}
	//}
	public void tick(double deltaTime) {
		
		float moveAmount = (float)(speed*fixDt);
		
		
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
				
			
		}
		if(left){
			if(!Check.CollisionPlayerBlock(
				      
					  new Point((int) (pos.xpos + GameLoop.map.xpos-moveAmount) ,
					     (int) (pos.ypos + GameLoop.map.ypos +height)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos -moveAmount) , 
					            (int) (pos.ypos + GameLoop.map.ypos))  ))
			{
			pos.xpos-=moveAmount;
			animationState = 3;
			}
		}
		if(right){
			if(!Check.CollisionPlayerBlock(
				      
					  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) ,
					     (int) (pos.ypos + GameLoop.map.ypos)),
					          
					  new Point((int) (pos.xpos + GameLoop.map.xpos+width +moveAmount) , 
					            (int) (pos.ypos + GameLoop.map.ypos+height))  ))
			{
			pos.xpos+=moveAmount;
			animationState = 2;
			}
		}
		if (!up && !down && !left && !right){
			animationState = 4;
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
