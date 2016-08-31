import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Fire {
	Vector2F pos = new Vector2F();
	public static int size=100;
	private ArrayList<BufferedImage> ani_list;
	Animator ani;
	boolean repeated;
	
	public Fire(float xpos, float ypos) {
		pos.xpos=xpos;
		pos.ypos=ypos;
		ani_list = new ArrayList<BufferedImage>();
		ani_list.add(Assets.fire_1);
		ani_list.add(Assets.fire_1);
		ani_list.add(Assets.fire_2);
		ani_list.add(Assets.fire_3);
		ani = new Animator(ani_list);
		ani.setSpeed(500);
		ani.play();
	}
	
	public void tick(double deltaTime){
		if(this.ani.currentFrame==3){repeated=true;}
		if(this.ani.currentFrame==0 && repeated){
			for(int i =0;i<FireManager.firelist.size();i++){
				if(this.equals(FireManager.firelist.get(i))){
					FireManager.eraseIndex.add(i);
				}
			}
		}
	}
	public void render(Graphics2D g){
		g.drawImage(ani.sprite,(int)pos.xpos, (int)pos.ypos, size,size,null);
		ani.update(System.currentTimeMillis());
	}
	
	
	

}
