package Main;

import java.awt.Graphics;

import Game.GameObject;

public abstract class GameMode {
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract String modeName();
	private void init(){
	
	}
	public abstract GameObject playerObject();
}
