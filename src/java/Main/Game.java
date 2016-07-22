package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import game.Handler;
import gamestate.Menu;
import input.*;
import io.Texture;
public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String args[]){
		new Window(new Game());
	}
private boolean running = false;
private Thread t1;
private GameMode gameMode;
private MouseInput mouse;
private KeyInput key;
private StateHandler stateHandler;
private Camara cam;

private BufferStrategy bs;
private Graphics g;
private Graphics2D g2d;
private static Texture tex;

private void init(){
	key = new KeyInput();
	this.addKeyListener(key);
	mouse = new MouseInput();
	this.addMouseListener(mouse);
	this.addMouseMotionListener(mouse);
	stateHandler = new StateHandler();
	gameMode = new Menu(this);
	tex = new Texture();
	cam = new Camara(this, 0,0);
	if(bs == null){//wenn noch keine BufferStrategy exestiert wird eine mit dem Wert 3 erstellt
        this.createBufferStrategy(3);
    }
}

	@Override
	public void run() {
		init();
		this.requestFocus();
        long lastTime = System.nanoTime();
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
		if(gameMode.playerObject() != null){
			cam.tick(gameMode.playerObject());
		}
		if(key.getEsc() == true){
			System.exit(0);
		}
		gameMode.tick();
	}
	
	public void render(){
        bs = this.getBufferStrategy();
        g = bs.getDrawGraphics();
        g2d = (Graphics2D) g;
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.translate(cam.getX(), cam.getY());
		gameMode.render(g);
        g2d.translate(-cam.getX(), -cam.getY());
		g.dispose();
		bs.show();
	}
	
	public void setGameMode(GameMode mode){
		gameMode = mode;
	}
	
	public void setGame(String level){
		setGameMode(new Handler(this, level));
	}
	
	public MouseInput getMouse(){
		return mouse;
	}
	
	public KeyInput getKey(){
		return key;
	}
	
	public StateHandler getStateHandler(){
		return stateHandler;
	}
	
	public static Texture getTextures(){
		return tex;
	}
}
