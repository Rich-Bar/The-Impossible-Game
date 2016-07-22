package button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.ReadImage;
import main.StateHandler;

/**
 * 
 * @author Marco Dittrich
 * This button combines the features of the hover and toggle button
 * !! Image Allocation: (1|1) Hovered & Toggled, (1|2) Hovered, (2|1) Toggled, (2|2) Untouched 
 */
public class ToggleHoverButton implements IButton{

	private BufferedImage buttonImg;
	private int imageWidth, imageHeight;
	private double 
			screenWidth = StateHandler.width,
			screenHeight = StateHandler.height,
			x, y, scale, relativeWidth, relativeHeight;
	private boolean toggled = false, hovered = false;
	
	/**
	 * 
	 * @param path - Filepath
	 * @param x, y - Top left corners of button given in percentage of screen [ eg. 12.3 ]
	 * @param scale - scaling of the button [ 1 = 100% ]
	 * @param toggle - initial value of the toggle
	 */
	public ToggleHoverButton(String path, double x, double y, double scale, boolean toggled) {
		ReadImage loader = new ReadImage();  
		buttonImg = loader.loadImage(path); 
		
		imageWidth = buttonImg.getWidth();
		imageHeight = buttonImg.getHeight();
		this.scale = scale;
		this.toggled = toggled;
		
		//Calculate and set variables to save CPU
		this.x = x / 100;
		this.y = y / 100;
		
		relativeWidth = imageWidth * screenWidth / 1920 / 2;
		relativeHeight = imageHeight * screenHeight / 1080 / 2;
	}
	
	public void render(Graphics g){
		//drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
		g.drawImage(buttonImg, 	(int)(screenWidth * x), (int)(screenHeight * y),
								(int)(screenWidth * x + relativeWidth * scale), (int)(screenHeight * y + relativeHeight * scale),
								hovered ? 0 : imageWidth / 2, toggled ? 0 : imageHeight / 2, 
								hovered ? imageWidth / 2 : imageWidth, toggled ? imageHeight / 2 : imageHeight,
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
	
	public void setToggled(boolean val){
		toggled = val;
	}
	
}
