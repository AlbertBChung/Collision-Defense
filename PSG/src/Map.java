
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Map {

	TileManager tiles = new TileManager();
	public static Player player = new Player();
	BulletManager bullets = new BulletManager();
	MonsterManager monsters = new MonsterManager();
	BombManager bombs = new BombManager();
	Random rng = new Random();
	Random rng_2= new Random();
	long time;
	int spawnCounter;
	
	public Map() {

	}
	public void init(){
		player. init();
		bullets.init();
		bombs.init();
		
		
		 BufferedImage map = null;
		 try{
		 map = LoadImageFrom.loadImageFrom(Applet.class, "map.png");
		 }catch(Exception e){}
	
		 for (int x = 0; x<43; x++){
			for (int y =0;y<24;y++){
				int col = map.getRGB(x,y);
				
				switch(col & 0xFFFFFF){
					case 0x808080:
					//if gray, makes a block 
					tiles.blocks.add(new Block (new Vector2F(x*32, y*32),"Stone_1"));
					break;	
					case 0x404040:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),"Wall_1").isSolid(true));
						break;	
					case 0x000000:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),"Wall_2").isPassable(true).isSolid(true));
						break;	
					case 0xffffff:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),"Reload_1").isSolid(true).isReloadCenter(true));
						break; 
				}
			}
		 }
	
	}
	private void makeMonster(int size, float speed,boolean direction_right){
		int y =rng_2.nextInt(400)+100;
		if(direction_right){
			Monster mon = new Monster(40,y,speed,true,size);
			 MonsterManager.monsterlist.add(mon);
		}
		else{
			Monster mon = new Monster(1290,y,speed,false,size);
			 MonsterManager.monsterlist.add(mon);
		}
	}
	
	public void tick(double deltaTime){
		
		tiles.tick(deltaTime);
		player.tick(deltaTime);
		long timern = System.currentTimeMillis();
		if (timern-time>=5000){
			time = timern;
			boolean direction_right = rng.nextBoolean();
			makeMonster(50,.5f,direction_right);
			
			spawnCounter++;
			if(spawnCounter%4==0){
				makeMonster(100,.5f, direction_right);
			}
			
		}
		
		
		
		
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
			
		
		

		monsters.tick(deltaTime);
		bullets.tick(deltaTime);
		bombs.tick(deltaTime);
	}
	
	
	
	
	public void render(Graphics2D g){
		tiles.render(g);
		player.render(g);
		bullets.render(g);
		monsters.render(g);
		bombs.render(g);
		
		g.setFont(new Font("Comic Sans MS	",Font.PLAIN,20));
		g.drawString("Ammo #: "+String.valueOf(Player.ammo)+"     Health: "+String.valueOf(Player.health)+"       " +
				"Score: "+String.valueOf(Player.score), 20, 20);
		g.drawRect(450, 2, 100, 25);
		long timern=System.currentTimeMillis();
		double fraction;
		if(timern-Player.dashCooldownTime>=1000){fraction=1;}
		else{fraction = (double)(timern-Player.dashCooldownTime)/1000.0;}
		g.fillRect(450,2,(int)(100*fraction),25);
		
	}

}