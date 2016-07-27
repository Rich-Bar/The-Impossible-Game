package button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import io.ReadImage;
import io.SpriteSheet;
import main.StateHandler;

/**
 * 
 * @author Marco Dittrich
 * This button changes texture when hovered over
 * Image Allocation: (1|1) Unhovered, (1|2) Hovered
 */
public class HoverButton implements IButton{

	private List<BufferedImage> imgStates;
	private int imageWidth, imageHeight;
	private double 
			screenWidth = StateHandler.width,
			screenHeight = StateHandler.height,
			x, y, scale, relativeWidth, relativeHeight;
	private boolean hovered = false;
	
	/**
	 * 
	 * @param path - Filepath
	 * @param x, y - Top left corners of button given in percentage of screen [ eg. 12.3 ]
	 * @param scale - scaling of the button [ 1 = 100% ]
	 */
	public HoverButton(String path, double x, double y, double scale) {
		ReadImage loader = new ReadImage();  
		BufferedImage composedImg = loader.loadImage(path); 
		SpriteSheet imgSheet = new SpriteSheet(composedImg);
		
		imgStates = imgSheet.getImages(1, 2);
		
		imageWidth = composedImg.getWidth();
		imageHeight = composedImg.getHeight()/2;
		
		//Calculate and set variables to save CPU
		
		this.x = x / 100;
		this.y = y / 100;
		this.scale = scale;
		
		relativeWidth = (imageWidth / 1920.) * screenWidth;
		relativeHeight = (imageHeight / 1080.) * screenHeight;
	}
	
	public void render(Graphics g){
		g.drawImage(imgStates.get(hovered? 1 : 0), 
				(int)(screenWidth * x), (int)(screenHeight * y), 
				(int)(relativeWidth * scale), (int)(relativeHeight * scale), 
				null);
		
	}
	
	/**
	 * 
	 * @param mx, my - Mouse Pointer Locations
	 */
	public void mouseUpdate(double mx, double my){
		if(mx > (screenWidth * x) && mx < (screenWidth * x + relativeWidth * scale)){
			if(my >(screenHeight * y) && my < (screenHeight * y + relativeHeight * scale)){
				hovered = true;
				return;
			}
		}
		hovered = false;
	}
	
	public boolean isHovered(){
		return hovered;
	}
	
}
