import java.awt.Dimension;
import javax.swing.JFrame;

public class Window{//Diese Klasse dient dazu denn JFrame aufzusetzen
    public JFrame frame;
    public Window(int h, int w, Game game){
        game.setPreferredSize(new Dimension(w,h));//Hier wird die erwünschte gröse des JFrame gesetzt
        
        JFrame frame = new JFrame("TheImpossibleGame");//Der Jframe wird erschafen
        frame.add(game);//Das Spiel wir zu dem JFrame hinzugefügt
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Hiermit kann aus der anwendung gegangen werden
        frame.setResizable(false);//Die Grösse das JFrame kann nicht verendert werden
        frame.setLocationRelativeTo(null);//Der JFrame wird in die mitte gesetzt
        frame.setAlwaysOnTop(true);//Die anwendung ist immer im Vordergrung
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//Der JFrame wird auf denn ganzen bildschirm gesetzt
        frame.dispose();//Der JFrame wird gelehrt
        frame.setUndecorated(true);//Der JFrame wird cored das ist notwendig da sonnst eine fehlermeldung erscheint
        frame.setVisible(true);//Der Jrame ist nun sichtbar
        game.start();//Die Spiel Thread wird gestartet
    }
        
    
    
    
}
