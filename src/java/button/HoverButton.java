package button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.ReadImage;
import main.StateHandler;

/**
 * 
 * @author Marco Dittrich
 * This button changes texture when hovered over
 * Image Allocation: (1|1) Unhovered, (1|2) Hovered
 */
public class HoverButton implements IButton{

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
	public HoverButton(String path, double x, double y, double scale) {
		ReadImage loader = new ReadImage();  
		buttonImg = loader.loadImage(path); 
		
		imageWidth = buttonImg.getWidth();
		imageHeight = buttonImg.getHeight();
		
		//Calculate and set variables to save CPU
		
		this.x = x / 100;
		this.y = y / 100;
		this.scale = scale;
		
		relativeWidth = (imageWidth / 1920.) * screenWidth;
		relativeHeight = (imageHeight / 1080.) * screenHeight / 2 ;
	}
	
	public void render(Graphics g){
		//drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
		g.drawImage(buttonImg, 	(int)(screenWidth * x), (int)(screenHeight * y),
								(int)(screenWidth * x + relativeWidth * scale), (int)(screenHeight * y + relativeHeight * scale),
								0, hovered ? imageHeight / 2 : 0, 
								imageWidth, hovered ? imageHeight : imageHeight / 2,
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
