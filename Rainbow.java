/*
 * Creates a range of colors for the Mandelbrot Set using a singleton class
 *
 * @author Harry Pinkerton
 * @version 1.0
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;


public class Rainbow extends JComponent{
    
    private static Rainbow instance;
    
    Color[] colors;
    Color[] seeds = {Color.RED, Color.ORANGE, Color.YELLOW, Color.ORANGE, Color.GREEN, Color.BLUE, Color.MAGENTA};;
    int limit;
    int index; 
    
    
    // Instance Variables
    
    public void setLimit(int limit){
        this.limit = limit;
        System.out.print("Rainbow " + limit);
    }
    
    
    public void setGradient(String gradient){
      if (gradient.equals("Rainbow")){
        seeds = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.ORANGE, Color.GREEN, Color.BLUE,
                       Color.MAGENTA};
      }
      if (gradient.equals("GreyScale")){
        seeds = new Color[] {Color.WHITE, Color.BLACK};
      }
      if (gradient.equals("GreenScale")){
        seeds = new Color[] {Color.GREEN, Color.BLACK};
      }
    }

    public static Rainbow getInstance(int n){
        if (instance == null){
            instance = new Rainbow(n);
        }
        return instance;
    }
    
    public void createGradient(int limit){
        this.limit = limit;
        colors = new Color[limit];
        double numSeeds = seeds.length;
        double numSpaces = (int)((limit - 1)/(numSeeds -1));
        
        for (int i =0; i < limit; i++){
            Color startColor = seeds[(int)(i / numSpaces)];
            Color endColor = seeds[Math.min((int)(i / numSpaces) + 1, (seeds.length -1))];
            double percent = (i % ((int) numSpaces)) / (numSpaces);
            int r = (int) (percent * (endColor.getRed() - startColor.getRed()) + startColor.getRed());
            int b = (int) (percent * (endColor.getBlue() - startColor.getBlue()) + startColor.getBlue());
            int g = (int) (percent * (endColor.getGreen() - startColor.getGreen()) + startColor.getGreen());
            colors[i] = new Color(r, g, b);
        }
        
    }
    
    public Color getColor(int index){
        if (index == limit){
            return Color.BLACK;
        }
        else{
            return colors[index];
        }
    }
    
    
    private Rainbow(int n){
        createGradient(n);
        
    }
}
