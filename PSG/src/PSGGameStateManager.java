package src;

import java.awt.Graphics2D;
import java.util.Stack;

public class PSGGameStateManager {
	public static Stack<PSGGameState> states;
	
	public PSGGameStateManager() {
		states= new Stack<PSGGameState>();
		states.push(new PSGLevelLoader(this));
	}
	public void tick(double deltaTime){
		states.peek().tick(deltaTime);
	}
	public void render (Graphics2D g){
		states.peek().render(g);
		
	}
	public void init() {
		states.peek().init();
		
	}
	
}
