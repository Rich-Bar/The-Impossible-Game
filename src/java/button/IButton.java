package button;

import java.awt.Graphics;

/**
 * 
 * @author Marco Dittrich
 * Interface for working with Buttons
 */
public interface IButton {

	public void render(Graphics g);
	
	public void mouseUpdate(double mx, double my);

	public boolean isHovered();
	
}
