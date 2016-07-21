package Game;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Texture.ReadImage;
import Game.Player;

public class MapHandler{

	private Handler h;
	private int width;
	private Integer[][] imap;
	private BufferedImage map;
	private int sizeX, sizeY;
	private int playerX, playerY;
	private int renderPlayerX;
	public MapHandler(Handler h, int width, String path){
		this.h= h;
		this.width = width / 32;
		ReadImage r = new ReadImage();
		map = r.loadImage(path);
		imap = new Integer[map.getWidth()][map.getHeight()];
		for(int x = 0; x <= map.getWidth() - 1; x++){
			for(int y = 0; y <= map.getHeight() - 1; y++){
				imap[x][y] = getState(x,y);
				sizeY = y - 1;
			}
			sizeX = x;
		}
		
		//map = null;
		addPlayer();
		firstSet();
		/*for(int y = 0; y <= map.getHeight()- 1; y++){
			for(int x = 0; x <= map.getWidth() -1; x++){
			
				System.out.print(imap[x][y].intValue());
			}
			System.out.println();
		}*/
	}
	
	private void firstSet(){
		int left = 1;
		if(h.getPlayerX() - width > 0) left = h.getPlayerX() - width;
		for(int x = left; x <= h.getPlayerX() + width; x++){

			LinkedList<GameObject> list = new LinkedList<>();
			for(int y = 0; y <= sizeY; y++){
				GameObject object = addObject(imap[x][y], y , x);
				if(object != null)list.add(object);
			}
			if(list == null) return;
			h.addListObject(x, list);
			System.out.println(x + " : List: " + list);
		}
		
		h.blabla = true;
		//System.out.println(h.getObject().get(60));
	}
	
	private int getState(int x, int y){
		int rgb;
		int pixel = 0;
        try{
            pixel = map.getRGB(x, y);
        }catch(Exception e){
        	
        }
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue  =  pixel & 0xff;
        if(red == 0 && green == 0 && 0 == blue)return 0;
        if(red == 255 && green == 255 && blue == 255){//Block
            return 1;
        }
        if(red == 255 && green == 0 && blue == 0){//Fence
            return 2;
        }
        if(blue == 232 /*&& firstRender*/){//Player
            return 3;
            //System.out.println(x + ":" + y);
            
        }
        if(red == 255 && green == 255 && blue == 0){//Finish
            return 4;
            //System.out.println("Finish set");
        }
        if(red == 0 && green == 255 && blue == 0){//Ground
            return 5;
        }
		return 6;
	}
		
	public void update(){
		if(h.getPlayerX() <= width + 1)return;
		h.getObject().remove(h.getPlayerX() - (width / 4) - 1);
		updateR();
	}
	
	private void updateR(){
		LinkedList<GameObject> list = new LinkedList<>();
		int pos = h.getPlayerX() + width;
		for(int x = 0; x <= sizeY; x++){
			list.add(addObject(imap[pos][x], pos, x));
		}
		h.getObject().put(pos, list);
	}
	
	private void addPlayer(){
		for(int x = 0; x <= imap.length - 1; x++){
			for(int y = 0; y <= imap[x].length - 1;y++){
				if(imap[x][y] == 3){
					playerX = x;
					playerY = y;
					h.addPlayer(x *32, y*32);
					System.out.println("Player set " + x + " : " + y);
				}
			}
		}
	}
	
	private GameObject addObject(int value, int x, int y){
		//System.out.println(value + "##########");
	switch(value){
	case 0: 
		return null;
	case 1: 
		return new Block(y * 32, x *32, 1, ObjectID.Block); 
	case 2: 
		return new Block(y * 32, x *32 , 0, ObjectID.Fence);
	case 4: 
		return new Block(y * 32, x *32, 2, ObjectID.Finish);
	case 5: 
		return new Block(y *32, x *32 , 3, ObjectID.Ground);
	}
	return null;
	}
	
	
	public void tick(){
		int playerX = h.getPlayerX();
		if(playerX / 32 > renderPlayerX / 32){
			LinkedList<GameObject> list = new LinkedList<>();
			for(int y = 0; y <= sizeY - 1; y++){
				//System.out.println(playerX + width);
				GameObject object = addObject(imap[playerX / 32 + width][y], y, playerX / 32 + width);
				if(object != null)list.add(object);
			}
			h.addListObject(playerX / 32 + width, list);
			h.getObject().remove(playerX / 32 - width - 1);
		}
		renderPlayerX = playerX;
	}
}
