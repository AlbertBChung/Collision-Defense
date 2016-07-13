package src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class MonsterManager {

		
		public static ArrayList<Monster> monsterlist = new ArrayList<Monster>();
		public static ArrayList<Integer> eraseIndex = new ArrayList<Integer>();
		
		public MonsterManager() { 
			
		}
		
		public void init(){
			
		}
		public void tick (double deltaTime){
		
			
			for(int i =0;i<monsterlist.size();i++){
				
				monsterlist.get(i).tick(deltaTime);
			}
			
		
			
			
		}
		public void render(Graphics2D g){
			
			for(int i =0;i<monsterlist.size();i++){
				
				monsterlist.get(i).render(g);
				
			}
		}
	}

	


