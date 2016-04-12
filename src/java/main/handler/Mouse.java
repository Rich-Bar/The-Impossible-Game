package main.handler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Mouse extends MouseAdapter
{
    private int posX, posY;
    public Mouse()
    {
        
    }
    
    public void mousePressed(MouseEvent e){
        //System.out.println(e.getPoint());
        posX = (int)e.getPoint().getX();
        posY = (int)e.getPoint().getY();
    }
    
    public void mouseRealeased(MouseEvent e){
        
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
}
