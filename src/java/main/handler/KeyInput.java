package main.handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter 
{
    private boolean d, a, w, s, space, esc, enter;//Dies sind die Tatsten die unterstützt werde    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            d = true;
        }
        if(key == KeyEvent.VK_A){
            a = true;
        }
        if(key == KeyEvent.VK_W){
            w = true;
        }
        if(key == KeyEvent.VK_S){
            s = true;
        }
        if(key == KeyEvent.VK_SPACE){
            space = true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            esc = true;
            System.exit(0);
        }
        if(key == KeyEvent.VK_ENTER){
            enter = true;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
            if(key == KeyEvent.VK_D){d = false;}
            if(key == KeyEvent.VK_A){a = false;}
            if(key == KeyEvent.VK_W){w = false;}
            if(key == KeyEvent.VK_S){s = false;}
            if(key == KeyEvent.VK_SPACE){space = false;}
            if(key == KeyEvent.VK_ESCAPE){esc = false;}
            if(key == KeyEvent.VK_ENTER){enter = false;}
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
