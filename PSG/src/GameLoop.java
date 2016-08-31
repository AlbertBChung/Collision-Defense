	

import java.awt.Component;

public class GameLoop extends IDGameLoop {

	PSGGameStateManager gsm;
	public static Assets assets = new Assets();
	public static Vector2F map = new Vector2F();
	
	public GameLoop(int fwidth, int fheight){
		super(fwidth, fheight);
	}	
	
	@Override
	public void init() {
		assets.init();
		Vector2F.setWorldVariable(map.xpos, map.ypos );
		gsm = new PSGGameStateManager();
		gsm.init();
		super.init();
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}
	@Override
	public void tick(double deltaTime) {

		Vector2F.setWorldVariable(map.xpos, map.ypos);
		gsm.tick(deltaTime);
	}
	
	@Override
	public void render() {
		super.render();
		gsm.render(graphics2D);
		clear();
	}
	
	
}
