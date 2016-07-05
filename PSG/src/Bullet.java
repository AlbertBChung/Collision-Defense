package src;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Bullet {
	public static ArrayList<Bullet> fire = new ArrayList<Bullet>();
	Vector2F pos = new Vector2F();
	public Bullet(float xpos, float ypos) {
		pos.xpos = xpos;
		pos.ypos = ypos;
		
	}
	public void init(){}
	public void tick(double deltaTime){
		pos.xpos++;
	}
	public void render(Graphics2D g){
		g.drawRect((int)pos.xpos, (int)pos.ypos, 10, 10);
}
}
