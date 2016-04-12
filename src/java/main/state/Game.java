package main.state;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import main.handler.Handler;
import main.handler.KeyInput;
import main.handler.MapHandler;
import main.handler.Mouse;
import main.handler.ReadImage;
import main.handler.StateHandler;
import main.type.Block;
import main.type.Camara;
import main.type.ObjectID;
import main.type.Player;
import main.type.Texture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable
{
    private BufferStrategy bs;
    private Graphics g;
    private Graphics2D g2d;
    private boolean running = false;
    private static  Thread t;
    
    public static int WIDTH, HEIGHT;
    
    private BufferedImage level = null;
    private Menu menu;
    private MenuLevel menuLevel;
    static Texture tex;
    private Camara cam;
    private Mouse mouse;
    private KeyInput key;
    private Handler handler;
    private StateHandler stateHandler;
    private String playingLevel;
    private MapHandler mapHandler;
    public enum STATE{//Erstellt die Variable STATE die dazudient denn Status des Spieles festzulegen
        Menu,
        MenuLevel, 
        Game;
    };
    
    private STATE gameState = STATE.Menu;//Stetzt denn Status des Spieles auf Menu
    
    private void init(){//Diese Methode wird nur einmal am anfang des Spieles durchgeführt
        bs = this.getBufferStrategy();
        if(bs == null){//wenn noch keine BufferStrategy exestiert wird eine mit dem Wert 3 erstellt
            this.createBufferStrategy(3);
            //return;
        }
        stateHandler = new StateHandler();
        stateHandler.start();
        
        WIDTH = getWidth();//setzt die Breite
        HEIGHT = getHeight();//setzt die Höhe
        
        key = new KeyInput();//erstellt die Tasten eingabe
        handler = new Handler(key);//erlellt den Handler das das Spiel koadinirt
        
        mouse = new Mouse();//erstellt die Mous eingabe
        
        menu = new Menu(this, mouse, stateHandler);//erstellt das Menu
        menuLevel = new MenuLevel(this, mouse, stateHandler);//erstellt das LevelMenu
        
        tex = new Texture();//Die Bilder werden Importiert
                                
        cam = new Camara(0,0);//Erstellt die Kamera die das Bild hin und her bewegt
        
        mapHandler = new MapHandler(handler, this);
        
        this.addKeyListener(key);//Dem JFrame wirde der KeyListner der oben erstellt wurde hinzugefügt
        this.addMouseListener(mouse);//Dem JFrame wirde der MouseListner der oben erstellt wurde hinzugefügt
        
        if(gameState == STATE.Game){//Wenn der Status dem Spieles sich im Spiel befindet dann wird die map generiert 
            creatLevel(level);//ich habe sie nur importiert um beim Programieren Zeit zu Spahren
        }
    }
    
    public synchronized void start(){//Die Methode die das Spiel startet
        if(running == false){//Wird nur durchgeführt wenn das Spiel noch nicht am Laufen ist dies hilft um möglichen Fehlern vorzubeugen
            running = true;//Endert dann wert so das das Program weis das das Spiel bereits einmal gestartet wurde
            t = new Thread(this);//Erstellt die Spiel Thread
            t.start();//Startet die Thread
        }else{System.out.println("Thread olready started!");}//Fehlermeldung die Ausgegeben wird wenn die Thread mehrfach gestartet werden will
    }
    
    public void run(){//Diese Methode wird nur einmal aufgerufen. Die Methode Beinhaltet den GameLoop
        init();//Ruft die Methode auf die alles Generiert was am Anfang benötigt wird
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
                   
            if(System.currentTimeMillis() - timer > 1000){//Das folgende wird jede Secunde durchgeführt.
                timer += 1000;//Fögt dereit denn Wert von einer Secunde Hinzu
                System.out.println("FPS: " + frames + " TICKS: " + updates);//Diese Zeile ist nicht wichtig für das Programm
                frames = 0;//Setzt die Frames zurück
                updates = 0;
            }
        }
        
    }
    
    private void tick(){//Diese Methode aktualisiert die GameLogicks
        if(gameState == STATE.Game){//Wenn das Spiel sich im Spielstatus befindet werden diese Logicks durchgeführt
            handler.tick();
            for(int i = 0; i < handler.object.size(); i++){
                if(handler.object.get(i).getId() == ObjectID.Player){
                    cam.tick(handler.object.get(i));
                }
            }
            if(key.getEsc()){
                gameState = STATE.Menu;
                handler.clearObject();
            }
        }else if(gameState == STATE.Menu){//Wenn das Spiel sich im Menustatus befindet werden diese Logicks durchgeführt
            menu.tick();
        }else if(gameState == STATE.MenuLevel){//Wenn das Spiel sich im Menulevelstatus befindet werden diese Logicks durchgeführt
            menuLevel.tick();
        }
        
    }
    
    private void render(){//Diese Methode aktuallisiert das Bild welches auf dem Bildschirm erscheint
        bs = this.getBufferStrategy();
        if(bs == null){//wenn noch keine BufferStrategy exestiert wird eine mit dem Wert 3 erstellt
            this.createBufferStrategy(3);
            //return;
        }
        
        g = bs.getDrawGraphics();//Die Graphincs benutzen jetzt die Graphics der BufferedStategy
        g2d = (Graphics2D) g;//die Graphics2D sind das gleiche wie die Graphics
        
        g.setColor(Color.black);//Setzt denn hintergrund Schwartz
        g.fillRect(0, 0, getWidth(), getHeight());//Fühlt denn Ganzen JFrame
        
        //Dieser teil wird von der Kamera Beeinflusst
        if(gameState == STATE.Game){//Wenn das Spiel sich im Spielstatus befindet werden diese aufgaben Gerendert durchgeführt
            g2d.translate(cam.getX(), cam.getY());//start cam
             
            handler.render(g);
            
            g2d.translate(-cam.getX(), -cam.getY());//end cam
        }else if(gameState == STATE.Menu){//Wenn das Spiel sich im Menustatus befindet werden diese aufgaben Gerendert durchgeführt
            menu.render(g);
        }else if(gameState == STATE.MenuLevel){//Wenn das Spiel sich im Menulevelstatus befindet werden diese aufgaben Gerendert durchgeführt
            menuLevel.render(g);
        }
        //Hier endet der Teil den die Kamera Beeinflusst
        g.dispose();
        bs.show();
    }
    
    public void loadMap(int Nummber){//Läd die dausgewählte Map
        gameState = STATE.Game;//Setzt denn Status des Pragrammes auf im Spiel
        ReadImage loader = new ReadImage();//Erstellt ein Object mit dem man Bilder lesen kann
        switch(Nummber){//Sucht das Bild das zu der ausgewählten Welt passt
            case 1: level = loader.loadImage("/Level1Map.png"); playingLevel = "level1"; break;
            case 2: level = loader.loadImage("/Level2Map.png"); playingLevel = "level2"; break;
            case 3: level = loader.loadImage("/Level3Map.png"); playingLevel = "level3"; break;
        }
        //creatLevel(level);//Erstellt die Welt die ausgewählt wurde
        mapHandler.setLevel(level);
        mapHandler.start();
    }
    
    private void creatLevel(BufferedImage image){//Erstllt die Welt
        int h = image.getHeight();//Setzt h nach der anzahl der vertikalen Blöcke das Welt
        int x = 0;//setzt denn zähler x = 0
        while(x < h){//Wird für jede reihe von vertikalen blöcken einmal durchgeführt
            //System.out.println("x : " + x);
            //System.out.println("h : " + h);
            for(int y = 0; y < 100; y++){//Wird für jede Reihe von horizontalen Blöcken einmal durchgeführt
                //System.out.println("y : " + y);
                //System.out.println("w : " + w);
                int pixel   =  image.getRGB(x,y);//Liest denn wert inform einer Farbe das aktuellen Blockes aus dem Bild raus
                int red   = (pixel >> 16) & 0xff;//Filtert denn Rot anteil des Pixles heraus
                int green = (pixel >> 8) & 0xff;//Filtert denn Grün anteil des Pixles heraus
                int blue  =  pixel & 0xff;//Filtert denn Blau anteil des Pixles heraus
                if(red == 255 && green == 255 && blue == 255){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Block zu der Welt hinzugefügt
                    handler.addObject(new Block(x * 32, y * 32, 1, ObjectID.Block));
                }
                
                if(red == 255 && green == 0 && blue == 0){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Block zu der Welt hinzugefügt
                    handler.addObject(new Block(x * 32, y * 32, 0, ObjectID.Fence));
                }
                if(blue == 232){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Spieler zu der Welt hinzugefügt
                    handler.addObject(new Player(x * 32, y * 32, handler, ObjectID.Player, this));
                    //System.out.println(x + ":" + y);
                    handler.setPlayerReStartPoint(x * 32, y * 32);
                }
                if(red == 255 && green == 255 && blue == 0){//Wenn der Pixel Genau diese Werte Besitz dann wird ein Ziel zu der Welt hinzugefügt
                    handler.addObject(new Block(x * 32, y * 32, 2, ObjectID.Finish));
                    //System.out.println("Finish set");
                }
            }
            x++;
        }
    }
    
    public static Texture getInstance(){//Diese Methode gibt die Texturen den Klassen die sie benötigen
        return tex;
    }
    
    public void setSTATE(int state){//Diese Methode setzt den Status in dem Sich das Spiel befinden soll
        if(state == 1){//Wenn die Methode mit dem Paramiter 1 aufgerufen wurde dann wird der Status zu Menu gesetzt
            gameState = STATE.Menu;
        }
        if(state == 2){//Wenn die Methode mit dem Paramiter 2 aufgerufen wurde dann wird der Status zu MenuLevel gesetzt
            gameState = STATE.MenuLevel;
        }
    }
    
    public int getSTATE(){//Diese Methode gibt einen int aus der darauf basiert in wlechem status sich das spiel gerade befindet
        if(gameState == STATE.Game){
            return 0;
        }
        if(gameState == STATE.Menu){
            return 1;
        }
        if(gameState == STATE.MenuLevel){
            return 2;
        }else{return 100;}//dieser teil wird ausgegeben wennirgend ein fehler vorligt
    }
    
    public String getPlayingLevel(){//Diese Methode gibt denn namen des Aktuellen levels aus
        return playingLevel;
    }
    
    public void rightState(String s, boolean b){//Diese methode ermöglicht es states zu schreiben
        stateHandler.setBoolean(s, b);
    }
    
    public void saveStates(){//mit dieser Methode kann man manuel das Spiel Speichern
        stateHandler.write();
    }
    
    public void setLevel(){
        
    }
}
