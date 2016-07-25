package button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import io.ReadImage;
import io.SpriteSheet;
import main.StateHandler;

/**
 * 
 * @author Marco Dittrich
 * This button combines the features of the hover and toggle button
 * !! Image Allocation: (1|1) Hovered & Toggled, (1|2) Hovered, (2|1) Toggled, (2|2) Untouched 
 */
public class ToggleHoverButton implements IButton{

	private List<BufferedImage> imgStates = new ArrayList<BufferedImage>();
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
		BufferedImage composedImg = loader.loadImage(path); 
		SpriteSheet imgSheet = new SpriteSheet(composedImg);
		
		imageWidth = composedImg.getWidth()/2;
		imageHeight = composedImg.getHeight()/2;
		
		imgStates.add(imgSheet.grabImage(1, 1, imageWidth, imageHeight));
		imgStates.add(imgSheet.grabImage(1, 2, imageWidth, imageHeight));
		imgStates.add(imgSheet.grabImage(2, 1, imageWidth, imageHeight));
		imgStates.add(imgSheet.grabImage(2, 2, imageWidth, imageHeight));
		
		this.scale = scale;
		this.toggled = toggled;
		
		//Calculate and set variables to save CPU
		this.x = x / 100;
		this.y = y / 100;
		
		relativeWidth = imageWidth * screenWidth / 1920;
		relativeHeight = imageHeight * screenHeight / 1080;
	}
	
	public void render(Graphics g){
		//drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
		g.drawImage(imgStates.get(hovered? toggled? 0 : 1 : toggled? 2 : 3), 
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
	
	public void setToggled(boolean val){
		toggled = val;
	}
	
}
