package src;

import java.awt.Graphics2D;

public class PSGLevelLoader extends PSGGameState{
	
	Map map;
	
	public PSGLevelLoader(PSGGameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
	 map = new Map();
	 map.init();
	}

	@Override
	public void tick(double deltaTime) {
		map.tick(deltaTime);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawString("Hello world!", 200, 200);
		map.render(g);
	}
	

}
