package button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.ReadImage;
import main.StateHandler;

/**
 * 
 * @author Marco Dittrich
 * This Button won't change texture but can be used for easy mouse listening...
 * Image Allocation: (1|1) Image
 */
public class StaticButton implements IButton{

	private BufferedImage buttonImg;
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
	public StaticButton(String path, double x, double y, double scale) {
		ReadImage loader = new ReadImage();  
		buttonImg = loader.loadImage(path); 
		
		imageWidth = buttonImg.getWidth();
		imageHeight = buttonImg.getHeight();
		
		//Calculate and set variables to save CPU
		
		this.x = x / 100;
		this.y = y / 100;
		this.scale = scale;
		
		relativeWidth = imageWidth * screenWidth / 1920;
		relativeHeight = imageHeight * screenHeight / 1080 / 2;
	}
	
	public void render(Graphics g){
		g.drawImage(buttonImg, 	(int)(screenWidth * x), (int)(screenHeight * y),
					(int)(relativeWidth * scale), (int)(relativeHeight * scale), null);
		
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
