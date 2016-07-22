package main;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import game.GameObject;

public abstract class GameMode {
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract String modeName();
	public abstract GameObject playerObject();
	public abstract Map<Integer, List<GameObject>> getObject();
}
