import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;
public class StateHandler implements Runnable
{
    private String OS = (System.getProperty("os.name")).toUpperCase();
    private File config;
    
    private boolean firstOpen = true;
    private boolean level1, level2, level3 = false;
    
    private double height, width;
    
    private Thread t;
    private boolean running = false;
    
    public void run(){
        long timer = System.currentTimeMillis();
        while(running){
            if(System.currentTimeMillis() - timer > 1000){
                timer += 3000;
                write();
            }            
        }
    }
    
    public void start(){
        if(!running){
            t = new Thread(this);
            t.start();
            running = true;
        }
    }
    
    public StateHandler()
    {
        String fileLocation;
        if(OS.contains("WIN")){
            fileLocation = System.getenv("AppData") + "/TheImpossibleGame/conf.ig";
        }else{
            fileLocation = System.getProperty("user.home");
            fileLocation += "/Library/Application Support/TheImpossibleGame/conf.ig";
        }
        
        config = new File(fileLocation);
        
        if(!config.exists() || config.length() == 0){
            config.getParentFile().mkdirs();
            try{
                config.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            write();
            System.out.println("Created new ConfigFile in: " + fileLocation);
        }
        read();
        System.out.println(level3 + " true!");
        setDimensions();
    }
    
    private void setDimensions(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
    }
    
    public boolean getBoolean(String s){
        boolean r = false;
        switch(s){
            case "firstOpen": r = firstOpen; break;
            case "level1": r = level1; break;
            case "level2": r = level2; break;
            case "level3": r = level3; break;
        }
        return r;
    }
    
    public int getInt(String s){
        int r = 0;
        switch(s){
            case "height" : r = (int)height;
            case "width" : r = (int)width;
        }
        return r;
    }
    
    public void write(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(config, false));
            
            writer.write(Boolean.toString(firstOpen));
            writer.newLine();
            System.out.println(Boolean.toString(firstOpen));
            
            writer.write(Boolean.toString(level1));
            writer.newLine();
            System.out.println(Boolean.toString(level1));
            
            writer.write(Boolean.toString(level2));
            writer.newLine();
            System.out.println(Boolean.toString(level2));            
            
            writer.write(Boolean.toString(level3));
            writer.newLine();
            System.out.println(Boolean.toString(level3));
            
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("updated");
    }
    
    private void read(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(config));
            
            String line;
            int i = 0;
            while((line = reader.readLine()) != null){
                switch(i){
                    case 0: firstOpen = Boolean.parseBoolean(line); System.out.println(line + " firstOpen"); break;
                    case 1: level1 = Boolean.parseBoolean(line); System.out.println(line + " level1"); break;
                    case 2: level2 = Boolean.parseBoolean(line); System.out.println(line + " level2"); break;
                    case 3: level3 = Boolean.parseBoolean(line); System.out.println(line + " level3"); break;
                }
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void setBoolean(String value, boolean b){
        switch(value){
            case "level1" : level1 = b; break;
            case "level2" : level2 = b; break;
            case "level3" : level3 = b; break;
        }
    }
}
