package InPut;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter
{
    private int posX, posY;    
    private int Xpos, Ypos;
    private int posXR, posYR;
    public void mousePressed(MouseEvent e){//wird ausgeführt wenn die mouse taste gedrückt wurde
        //System.out.println(e.getPoint());
        posX = (int)e.getPoint().getX();//setzt denn punkt wo die gedrückt wurde
        posY = (int)e.getPoint().getY();
    }
    
    public void mouseReleased(MouseEvent e){//wird ausgeführt wenn die Mouse taste losgelassen wird
        posX = posY = 0;//setzt die Variable auf null da diese nur angeben wo die Mouse gedrückt wird
        posXR = (int)e.getPoint().getX();//setzt den Punkt wo die Mouse gedrückt wurde
        posYR = (int)e.getPoint().getY();
    }
    
    public void mouseMoved(MouseEvent e){//gibt an wo die Mouse hinbewegt wurde
        //System.out.println(e.getPoint());
        Xpos = (int)e.getPoint().getX();
        Ypos = (int)e.getPoint().getY();
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public int getXPos(){
        return Xpos;
    }
    
    public int getYPos(){
        return Ypos;
    }
    
    public int getPosXR(){
        return posXR;
    }
    
    public int getPosYR(){
        return posYR;
    }
    
    public void reSetPosXYR(){
        posXR = posYR = 0;
    }
}