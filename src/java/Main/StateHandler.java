package Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StateHandler implements Runnable{
	private String OS = (System.getProperty("os.name")).toUpperCase();
    private File config;
    
    private boolean firstOpen = true, sound = true;
    private boolean level1, level2, level3, level4, level5 = false;
    private int dLevel1, dLevel2, dLevel3, dLevel4, dLevel5 = 0;
    private String quality = "high";
    private int tilePixel = 32;
    
    private double height, width;
    private int startY = 0;
    
    private Thread t;
    private boolean running = false;
    public void run(){
        long timer = System.currentTimeMillis();
        while(running){
            if(System.currentTimeMillis() - timer > 1000){//alle 3 secunden wird der status aktualisiert
                timer += 3000;
                write();
            }            
        }
    }
    
    public void start(){//start die thread wenn sie noch nicht gestarte wurde
        if(!running){
            t = new Thread(this);
            t.start();
            running = true;
        }
    }
    
    public StateHandler()//Konstruktor wird in der init() Methode aufgerufen
    {
        String fileLocation;
        if(OS.contains("WIN")){//wenn ein roming ortner verhanden ist dann werden die States dort eingespeichert
            fileLocation = System.getenv("AppData") + "/TheImpossibleGame/conf.ig";
        }else{
            fileLocation = System.getProperty("user.home");
            fileLocation += "/Library/Application Support/TheImpossibleGame/conf.ig";
        }
        
        config = new File(fileLocation);
        
        if(!config.exists() || config.length() == 0){//wenn noch keine datei vorhanden ist oder dies leer ist an der vorher festgelegten stelle dann wird eine neue erstellt
            config.getParentFile().mkdirs();
            try{
                config.createNewFile();//versucht neue datei zu erstellen
            }catch(IOException e){
                e.printStackTrace();
            }
            write();
            System.out.println("Created new ConfigFile in: " + fileLocation);
        }
        read();// wenn bereits eine datei vorhanden ist dann werden die Statisticen ausgelessen
        setDimensions();
    }
    
    private void setDimensions(){//setzt die Bildschirm auflössung
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth(); System.out.println(width + " width");
        height = screenSize.getHeight();System.out.println(height + " height");
        if(width / height != 1.77){//wenn das Bildschirm verhälltins nicht 16:9 ist dann wird das bild so verändert das die bilder im Menu nicht verzährt werden
            startY = (int)(height - (width / 1.77)) / 2;
            height = width / 1.77;
        }
    }
    
    public boolean getBoolean(String s){//gibt die boolean variablen aus 
        boolean r = false;
        switch(s){
            case "firstOpen": r = firstOpen; break;
            case "level1": r = level1; break;
            case "level2": r = level2; break;
            case "level3": r = level3; break;
            case "level4": r = level4; break;
            case "level5": r = level5; break;
            case "sound" : r = sound; break;
        }
        return r;
    }
    
    public int getInt(String s){//gibt die Integer Variablen aus
        int r = 0;
        switch(s){
            case "height" : r = (int)height; break;
            case "width" : r = (int)width; break;
            case "level1": r = dLevel1; break;
            case "level2": r = dLevel2; break;
            case "level3": r = dLevel3; break;
            case "level4": r = dLevel4; break;
            case "level5": r = dLevel5; break;
            case "startY": r = startY; break;
        }
        return r;
    }
    
    public void write(){//updatet die Datei in der die States gespeichert werden
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(config, false));
            
            writer.write(Boolean.toString(firstOpen));
            writer.newLine();
            //System.out.println(Boolean.toString(firstOpen));
            
            writer.write(Boolean.toString(level1));
            writer.newLine();
            //System.out.println(Boolean.toString(level1));
            
            writer.write(Boolean.toString(level2));
            writer.newLine();
            //System.out.println(Boolean.toString(level2));            
            
            writer.write(Boolean.toString(level3));
            writer.newLine();
            //System.out.println(Boolean.toString(level3));
            
            writer.write(Boolean.toString(level4));
            writer.newLine();
            //System.out.println(Boolean.toString(level3));
            
            writer.write(Boolean.toString(level5));
            writer.newLine();
            //System.out.println(Boolean.toString(level3));
            
            writer.write(String.valueOf(dLevel1));
            writer.newLine();
            
            writer.write(String.valueOf(dLevel2));
            writer.newLine();
            
            writer.write(String.valueOf(dLevel3));
            writer.newLine();
            
            writer.write(String.valueOf(dLevel4));
            writer.newLine();
            
            writer.write(String.valueOf(dLevel5));
            writer.newLine();
            
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("updated");
    }
    
    private void read(){//liest die datei wo die States gespeichert werden
        try{
            BufferedReader reader = new BufferedReader(new FileReader(config));
            
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){//geht jede zeie einmal durch
                switch(i){
                    case 0: firstOpen = Boolean.parseBoolean(line); System.out.println(line + " FirstOpen"); break;
                    case 1: level1 = Boolean.parseBoolean(line); System.out.println(line + " Level1"); break;
                    case 2: level2 = Boolean.parseBoolean(line); System.out.println(line + " Level2"); break;
                    case 3: level3 = Boolean.parseBoolean(line); System.out.println(line + " Level3"); break;
                    case 4: level4 = Boolean.parseBoolean(line); System.out.println(line + " Level4"); break;
                    case 5: level5 = Boolean.parseBoolean(line); System.out.println(line + " Level5"); break;
                    case 6: dLevel1 = Integer.valueOf(line); System.out.println(line + " dLevel1"); break;
                    case 7: dLevel2 = Integer.valueOf(line); System.out.println(line + " dLevel2"); break;
                    case 8: dLevel3 = Integer.valueOf(line); System.out.println(line + " dLevel3"); break;
                    case 9: dLevel4 = Integer.valueOf(line); System.out.println(line + " dLevel3"); break;
                    case 10: dLevel5 = Integer.valueOf(line); System.out.println(line + " dLevel3"); break;
                }
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void setBoolean(String value, boolean b){//hirmit können boolean Variablen gesetzt werden
        switch(value){
            case "firstOpen" : firstOpen = b; break;
            case "level1" : level1 = b; break;
            case "level2" : level2 = b; break;
            case "level3" : level3 = b; break;
            case "level4" : level4 = b; break;
            case "level5" : level5 = b; break;
        }
    }
    
    public void setDistance(String level, int distance){//hirmit kann die weiteste level distanz erneuert werden
        switch(level){
            case "level1": dLevel1 = distance; break;
            case "level2": dLevel2 = distance; break;
            case "level3": dLevel3 = distance; break;
            case "level4": dLevel4 = distance; break;
            case "level5": dLevel5 = distance; break;
        }
    }
}
