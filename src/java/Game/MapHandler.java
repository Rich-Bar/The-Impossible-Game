package Game;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Texture.ReadImage;

public class MapHandler implements Runnable{

	private Handler h;
	private int width;
	private Integer[][] imap;
	private BufferedImage map;
	private int sizeX, sizeY;
	public MapHandler(Handler h, int width, String path){
		this.h= h;
		this.width = width / 32;
		ReadImage r = new ReadImage();
		map = r.loadImage(path);
		for(int x = 0; x <= map.getWidth(); x++){
			for(int y = 0; y <= map.getHeight(); y++){
				imap[x][y] = getState(x,y);
				sizeY = y - 1;
			}
			sizeX = x;
		}
		map = null;
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
	
	Thread t1;
	private boolean running = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		update();
	}
	
	public void start(){
		if(!running){
			running = true;
			t1 = new Thread(this);
		}
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
	
	private GameObject addObject(int value, int x, int y){
	switch(value){
	case 0: return null;
	case 1: return new Block(x * 32, y * 32, 1, ObjectID.Block); 
	case 3: return new Block(x * 32, y * 32, 2, ObjectID.Finish);
	case 4: return new Block(x * 32, y * 32, 2, ObjectID.Finish);
	case 5: return new Block(x * 32, y * 32, 3, ObjectID.Ground);
	}
	return null;
	}
}
