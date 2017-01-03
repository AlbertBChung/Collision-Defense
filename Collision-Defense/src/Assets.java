

import java.awt.image.BufferedImage;

public class Assets {
 
	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet player = new SpriteSheet();
	
	
	public static BufferedImage stone_1;
	public static BufferedImage wall_1;
	public static BufferedImage wall_2;
	public static BufferedImage reload_1;
	public static BufferedImage bulletRight;
	public static BufferedImage bulletLeft;
	public static BufferedImage bomb_1;
	public static BufferedImage bomb_2;
	public static BufferedImage target;
	public static BufferedImage fire_1;
	public static BufferedImage fire_2;
	public static BufferedImage fire_3;

	
	public void init(){
		blocks.setSpriteSheet(LoadImageFrom.loadImageFrom(Applet.class, "spritesheet.png"));
		player.setSpriteSheet(LoadImageFrom.loadImageFrom(Applet.class, "playersprite.png"));
		stone_1 = blocks.getTile(0, 0, 16, 16);
		wall_1 = blocks.getTile(16, 0, 16, 16);
		wall_2 = blocks.getTile(32, 0, 16, 16);
		reload_1 = blocks.getTile(48, 0, 16, 16);
		bulletRight = player.getTile(32, 0, 16, 16);
		bulletLeft = player.getTile(48,0,16,16);
		bomb_1= blocks.getTile(64, 0, 16, 16);
		bomb_2= blocks.getTile(80, 0, 16, 16);
		target=blocks.getTile(96, 0, 16, 16);
		fire_1=blocks.getTile(112,0,16,16);
		fire_2=blocks.getTile(112+16,0,16,16);
		fire_3=blocks.getTile(112+32,0,16,16);
		
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
	public static BufferedImage getbomb_1() {
		return bomb_1;
	}
	public static BufferedImage getBomb_2() {
		return bomb_2;
	}
	public static BufferedImage getTarget(){
		return target;
	}
}
