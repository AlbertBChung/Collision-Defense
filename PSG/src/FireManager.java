import java.awt.Graphics2D;
import java.util.ArrayList;


public class FireManager {
	public static ArrayList<Fire> firelist = new ArrayList<Fire>();
	public static ArrayList<Integer> eraseIndex = new ArrayList<Integer>();
	
	public FireManager() {
	}
	
	public void tick(double deltaTime){
	for(int i =0;i<firelist.size();i++){	
		firelist.get(i).tick(deltaTime);
	}
	}
	
	public void render(Graphics2D g){
		for(int i =0;i<firelist.size();i++){
			firelist.get(i).render(g);
			
		}
	}
}
