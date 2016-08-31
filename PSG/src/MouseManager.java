

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MouseManager implements MouseListener, MouseMotionListener{
	public static int x;
	public static int y;
	
	public static boolean clicked;
	
	
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		x=e.getX();
		y=e.getY();
	}

	public void mouseClicked(MouseEvent e) {

		if(Player.ammo>0 && !Player.dead){
			if(e.getX()>Map.player.pos.xpos+Map.player.width/2){
			Vector2F location= new Vector2F(e.getX()-Bullet.width,e.getY()-Bullet.height/2);
			BulletManager.bulletlist.add(new Bullet(Map.player.pos.xpos+Map.player.width,Map.player.pos.ypos+Map.player.height/2-Bullet.height/2,true,location));
			}
			else{
			Vector2F location= new Vector2F(e.getX(),e.getY()-Bullet.height/2);
			BulletManager.bulletlist.add(new Bullet(Map.player.pos.xpos-Bullet.width,Map.player.pos.ypos+Map.player.height/2-Bullet.height/2,false,location));
			}
			Player.ammo--;
		}
		
	
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
