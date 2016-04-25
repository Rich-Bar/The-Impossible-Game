package InPut;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter 
{
    private boolean d, a, w, s, space, spaceR, esc, enter;//Dies sind die Tatsten die unterstützt werde    
    public void keyPressed(KeyEvent e){//Wird ausgeführt wenn eine taste gedrückt wurde
        int key = e.getKeyCode();
        //testet welche taste gedrückt wurde
        if(key == KeyEvent.VK_D){
            d = true;
        }
        if(key == KeyEvent.VK_A){
            a = true;
        }
        if(key == KeyEvent.VK_W){
            System.exit(0);
        }
        if(key == KeyEvent.VK_S){
            s = true;
        }
        if(key == KeyEvent.VK_SPACE){
            space = true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            //esc = true;
        }
        if(key == KeyEvent.VK_ENTER){
            enter = true;
        }
    }
    
    public void keyReleased(KeyEvent e){//Wird ausgeführt wenn eine Taste losgelassen wird
        int key = e.getKeyCode();
        //Testet welche taste losgelassen wurde
            if(key == KeyEvent.VK_D){
                d = false;
            }
            if(key == KeyEvent.VK_A){
                a = false;
            }
            if(key == KeyEvent.VK_W){
                w = false;
            }
            if(key == KeyEvent.VK_S){
                s = false;
            }
            if(key == KeyEvent.VK_SPACE){
                space = false;
                spaceR = true;
            }
            if(key == KeyEvent.VK_ESCAPE){
                esc = true;
            }
            if(key == KeyEvent.VK_ENTER){
                enter = false;
            }
        }
        
    public boolean getSpaceR(){
        return spaceR;
    }
    
    public void setSpaceR(boolean b){//wenn Space losgelassen wird dann muss die taste zurückgesetzt werden 
        spaceR = b;
    }
    
    public boolean getD(){
        return d;
    }
    
    public boolean getA(){
        return a;
    }
    
    public boolean getS(){
        return s;
    }
    
    public boolean getW(){
        return w;
    }
    
    public boolean getSpace(){
        return space;
    }
    
    public boolean getEsc(){
     return esc;
    }
    
    public void setEsc(boolean esc){
        this.esc = esc;
    }
    
    public boolean getEnter(){
        return enter;
    }
    
    public void setEnter(boolean b){
        enter = b;
    }
    
}
