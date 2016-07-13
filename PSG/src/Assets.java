package src;

import java.awt.image.BufferedImage;

public class Assets {
 
	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet player = new SpriteSheet();
	
	
	public static BufferedImage stone_1;
	public static BufferedImage wall_1;
	public static BufferedImage wall_2;
	public static BufferedImage reload_1;

	
	public void init(){
		blocks.setSpriteSheet(LoadImageFrom.loadImageFrom(Main.class, "spritesheet.png"));
		player.setSpriteSheet(LoadImageFrom.loadImageFrom(Main.class, "playersprite.png"));
		stone_1 = blocks.getTile(0, 0, 16, 16);
		wall_1 = blocks.getTile(16, 0, 16, 16);
		wall_2 = blocks.getTile(32, 0, 16, 16);
		reload_1 = blocks.getTile(48, 0, 16, 16);
	}
	
	public static BufferedImage getStone_1() {
		return stone_1;
	}
	public static BufferedImage getWall_1(){
		return wall_1;
	}
	public static BufferedImage getWall_2() {
		return wall_2;
	}
	public static BufferedImage getReload_1() {
		return reload_1;
	}
}
