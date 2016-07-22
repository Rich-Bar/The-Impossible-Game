package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter
{
    private boolean isPressed;   
    private int Xpos, Ypos;
    public void mousePressed(MouseEvent e){//wird ausgeführt wenn die mouse taste gedrückt wurde
    	isPressed = true;
    }
    
    public void mouseReleased(MouseEvent e){//wird ausgeführt wenn die Mouse taste losgelassen wird
    	isPressed = false;
    }
    
    public void mouseMoved(MouseEvent e){//gibt an wo die Mouse hinbewegt wurde
        //System.out.println(e.getPoint());
        Xpos = (int)e.getPoint().getX();
        Ypos = (int)e.getPoint().getY();
    }
    
    public boolean isPressed(){
        return isPressed;
    }
    
    public int getXPos(){
        return Xpos;
    }
    
    public int getYPos(){
        return Ypos;
    }
}