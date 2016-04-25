package Main;
import java.awt.Canvas;
import GameState.Menu;
import GameState.MenuHelp;
import GameState.MenuLevel;
public class Game extends Canvas implements Runnable {

	public static void main(String args[]){
		new Window(new Game());
	}
private boolean running = false;
private Thread t1;
private GameMode gameMode;

private void init(){
	gameMode = new Menu(this);
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		this.requestFocus();
        long lastTime = System.nanoTime();
        long lastFPSTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double delta2 = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
            while(running){
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                delta2 += (now - lastTime) / ns;
                lastTime = now;
                while(delta >= 1){
                    tick();
                    updates++;
                    delta--;
                }
                while(delta2 >= 1){
                    render();
                    frames++;
                    delta2 = 0.75;
                }
                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames + " TICKS: " + updates);
                    updates = 0;
                    frames = 0;
                }
            }
	}
	
	public void start(){
		if(!running){
			running = true;
			t1 = new Thread(this);
			t1.start();
		}
	}

	public void tick(){
		
	}
	
	public void render(){
		
	}

}
