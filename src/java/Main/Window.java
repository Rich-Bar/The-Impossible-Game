package main;
import javax.swing.JFrame;

public class Window {
	JFrame frame;
	public Window(Game game){
		frame = new JFrame();
		frame.add(game);
		frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.dispose();
        frame.setUndecorated(true);
        frame.setVisible(true);
        game.start();
	}
}
