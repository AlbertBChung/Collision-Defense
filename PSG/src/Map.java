package src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.Block.BlockType;

public class Map {

	TileManager tiles = new TileManager();
	Player player = new Player();
	
	public Map() {
		
	}
	public void init(){
		player. init();
		 BufferedImage map = null;
		 try{
		 map = LoadImageFrom.loadImageFrom(Main.class, "map.png");
		 }catch(Exception e){}
	
		 for (int x = 0; x<100; x++){
			for (int y =0;y<100;y++){
				int col = map.getRGB(x,y);
				
				switch(col & 0xFFFFFF){
					case 0x808080:
					//if gray, makes a block 
					tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Stone_1));
					break;	
					case 0x404040:
						tiles.blocks.add(new Block (new Vector2F(x*32, y*32),BlockType.Wall_1).isSolid(true));
						break;	
				}
			}
		 }
		 
	}
	public void tick(double deltaTime){
		tiles.tick(deltaTime);
		player.tick(deltaTime);
		
	}
	public void render(Graphics2D g){
		tiles.render(g);
		player.render(g);
	}

}
