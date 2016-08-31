

import java.awt.Point;
import java.time.chrono.Era;

public class Check {


	
public static boolean CollisionPlayerBlock(Point p1, Point p2){
		
		for(Block block : TileManager.blocks){
			if(block.isSolid()){
				if(block.contains(p1) || block.contains(p2)){
					return true;
				}
			}
			
		}
		
		return false;
	}

	public static boolean CollisionBulletBlock(Point p1, Point p2){
		for(Block block : TileManager.blocks){
			if(block.isSolid() && !block.isPassable()){
				if(block.contains(p1) || block.contains(p2)){
					return true;
				}
			}
			
		}
		
		return false;
	}
	public static boolean CollisionBombBlock(Point p1, Point p2){
		for(Block block : TileManager.blocks){
			if(block.isSolid() && !block.isPassable()){
				if(block.contains(p1) || block.contains(p2)){
					return true;
				}
			}
			
		}
		
		return false;
	}

	
	public static boolean CollisionMonster(Point p1, Point p2) {
		
		for (int i =0; i<MonsterManager.monsterlist.size();i++)
		{
				if(MonsterManager.monsterlist.get(i).contains(p1) || MonsterManager.monsterlist.get(i).contains(p2)){
					
					//MonsterManager.knockback(p1, p2, i);
					return true;
				}
			
			
		}
		
		return false;
	}
	
public static boolean CollisionBomb(Point p1, Point p2) {
		
		for (int i =0; i<BombManager.bomblist.size();i++)
		{
				if(BombManager.bomblist.get(i).contains(p1) || BombManager.bomblist.get(i).contains(p2)){
					BombManager.knockback(p1, p2, i);
					return true;
				}
		}
		
		return false;
	}


	public static boolean CollisionReload(Point p1, Point p2){
		for(Block block : TileManager.blocks){
			if(block.isReloadCenter){
				if(block.contains(p1) || block.contains(p2)){
					return true;
				}
			}
			
		}
		
		return false;
	}
	public static int indexBombWithMon (Point p1, Point p2, Point p3, Point p4){
		for(int i =0;i < MonsterManager.monsterlist.size();i++){
				if((MonsterManager.monsterlist.get(i).contains(p1) || MonsterManager.monsterlist.get(i).contains(p2) || MonsterManager.monsterlist.get(i).contains(p3) || MonsterManager.monsterlist.get(i).contains(p4))){
					return i;
				}
			}
		
			
		
		
		return -1;
	}
	
	
	
	
	
	}

	


	