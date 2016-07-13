package src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import src.Block.BlockType;

public class Map {

	TileManager tiles = new TileManager();
	public static Player player = new Player();
	BulletManager bullets = new BulletManager();
	MonsterManager monsters = new MonsterManager();
	Random rng = new Random();
	Random rng_2= new Random();
	long time;
	int spawnCounter;
	public Map() {

	}
	public void init(){
		player. init();
		bullets.init();

		
		
		 BufferedImage map = null;
		 try{
		 map = LoadImageFrom.loadImageFrom(Main.class, "map.png");
		 }catch(Exception e){}
	
		 for (int x = 0; x<43; x++){
			for (int y =0;y<24;y++){
				int col = map.getRGB(x,y);
				
				switch(col & 0xFFFFFF){
					case 0x808080:
					//if gray, makes a block 
					tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Stone_1));
					break;	
					case 0x404040:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Wall_1).isSolid(true));
						break;	
					case 0x000000:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Wall_2).isPassable(true).isSolid(true));
						break;	
					case 0xffffff:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Reload_1).isSolid(true).isReloadCenter(true));
						break; 
				}
			}
		 }
	
	}
	private void makeMonster(int size, float speed){
		boolean direction = rng.nextBoolean();
		int y =rng_2.nextInt(400)+100;
		if(direction){
			Monster mon = new Monster(10,y,speed,true,size);
			 MonsterManager.monsterlist.add(mon);
		}
		else{
			Monster mon = new Monster(1310,y,speed,false,size);
			 MonsterManager.monsterlist.add(mon);
		}
	}
	
	public void tick(double deltaTime){
		try{
		tiles.tick(deltaTime);
		player.tick(deltaTime);
		long timern = System.currentTimeMillis();
		if (timern-time>=5000){
			time = timern;
			makeMonster(50,0.04f);
			spawnCounter++;
			if(spawnCounter%4==0){
				makeMonster(100,0.02f);
			}
			
		}
		
		monsters.tick(deltaTime);
		
			while(BulletManager.index.size()>0){
				int removeAtIndex=BulletManager.index.get(0).intValue();
				BulletManager.bulletlist.remove(removeAtIndex);
				BulletManager.index.remove(0);
				for(int i =0;i<BulletManager.index.size();i++){
					if(BulletManager.index.get(i)>removeAtIndex){
						BulletManager.index.set(i, BulletManager.index.get(i)-1);
					}
				}
				
			}
			while(MonsterManager.eraseIndex.size()>0){
				int removeAtIndex=MonsterManager.eraseIndex.get(0).intValue();
				MonsterManager.monsterlist.remove(removeAtIndex);
				MonsterManager.eraseIndex.remove(0);

				// mon mon mon Mon
				for(int i =0;i<MonsterManager.eraseIndex.size();i++){
					if(MonsterManager.eraseIndex.get(i)>removeAtIndex){
						MonsterManager.eraseIndex.set(i, MonsterManager.eraseIndex.get(i)-1);
					}
					
				}
				
			}
		bullets.tick(deltaTime);
		
		
		
		
		
	}catch(Exception e){
			this.bullets=new BulletManager(); System.out.println("Error handled!");
			}
	}
	
	
	
	
	public void render(Graphics2D g){
		tiles.render(g);
		player.render(g);
		bullets.render(g);
		monsters.render(g);
		
	}

}
