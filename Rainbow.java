import java.awt.Color
import java.util.*;
import java.awt.Graphics2D;

/*
 * Creates a range of colors for the Mandelbrot Set using a singleton class
 *
 * @author Harry Pinkerton
 * @version 1.0
 *
 */

public class Rainbow{
    
    private static Rainbow instance;
    
    // Instance Variables
    Color[] color;
    Color[] seedcolors;
    
    // inset setcalc to store limit here
    
    int N = DEFAULT_LIMIT;
    
    private Color firstColor;
    private Color lastColor;
    private int startIndex
    private int endIndex;
    
    private double redStep;
    private double blueStep;
    private double greenStep;
    private double seedStep;
    
    public static Rainbow getInstance{
        if (instance == null){
            instance = new Rainbow();
        }
        return instance;
    }
    
    // Constructors
    
    private Rainbow(){
        seedcolors = Color[6];
        colors = new Color[N];
        startIndex = 0;
    }
    
    // Adding each seed color to the array
    
    public void addSeedColors{
        seedColors[0] = Color.RED;
        seedColors[1] = Color.ORANGE;
        seedColors[2] = Color.YELLOW;
        seedColors[3] = Color.GREEN;
        seedColors[4] = Color.BLUE;
        seedColors[5] = Color.MAGENTA; 
    }
    
    // Put the seed colors in the color array
    
    public void seedPosition{
        colors[0] = seedColors[0];
        colors[N-1] = seedColors[seedColors.length -1];
        seedStep = N / (seedColors.length - 1);
        for (int i = 1; i < seedColors.length; i++){
            colors[(int)(seedStep*1)] = getSeedColor(1);
        }
        
    }
    
    // Put the colors in the color array
    
    public void addColors(){
        
        
        
        
        
    }
    
    // returns the seed color given an integer between 0 and N-1
    public Color getSeedColor(int K){
        if(K < 0 || K >= seedColors.length){
            return null;
        }
        else{
            return seedColors[K];
        }
    }
    
    // returns the color given an integer between 0 and N-1
    public Color getColor(int K){
        if(K < 0 || K >= N){
            return null;
        }
        else{
            return colors[K];
        }
    }  

    public String toString(){
        String str = "Rainbow /n";
        str += "Limit:" + this.getN + "/n";
        return str;
    }
    
    
    
    
}