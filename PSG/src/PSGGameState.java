package src;

import java.awt.Graphics2D;

public abstract class PSGGameState {

	
	
	public PSGGameStateManager gsm;
	
	public PSGGameState(PSGGameStateManager gsm) {
		this.gsm = gsm;
	}

	public abstract void init();
	public abstract void tick(double deltaTime);
	public abstract void render(Graphics2D g);
}
