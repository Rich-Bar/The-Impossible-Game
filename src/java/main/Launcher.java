package main;

import main.state.Game;
import main.type.Window;

public class Launcher {

	public static void main(String args[]){//Start Methode
        
        new Window(800, 800 , new Game());//Erstellt dan JFrame
        
    }

}
