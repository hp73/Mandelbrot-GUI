import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mandelbrot extends JFrame {
    
    // Class Variables   
    private Canvas canvas;
    private JButton increaseButton;
    private JButton decreaseButton;
    private JButton resetButton;
    private JButton saveImageButton;
    private JButton savePosButton;
    private JButton loadButton;
    private JButton gradientButton;



   
    public Mandelbrot() {

        
        // Use a GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints positionConst = new GridBagConstraints();
        positionConst.insets = new Insets(10, 10, 10, 10);
        
        // Set up the window
        setSize(1100,800);        
        setTitle("Mandelbrot App");        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add the canvas
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        positionConst.fill = GridBagConstraints.BOTH;
        positionConst.gridwidth = GridBagConstraints.REMAINDER;
        positionConst.weightx = 1;
        positionConst.weighty = 1;
        
        canvas = new Canvas(3); // Scaled up by 3x       
        add(canvas, positionConst);

        //adding buttons

        // Create "Increase Limit" Button
        increaseButton = new JButton("Increase Limit");
        //increaseButton.addActionListener(this);
        increaseButton.getText();
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        positionConst.gridwidth = 1;
        positionConst.weighty = .05;
        add(increaseButton, positionConst);

        // Create "Decrease Limit" Button
        decreaseButton = new JButton("Decrease Limit");
        //decreaseButton.addActionListener(this);
        decreaseButton.getText();
      
        positionConst.gridx = 1;
        positionConst.gridy = 1;
        positionConst.gridwidth = 1;
        add(decreaseButton, positionConst);


        // Create "Reset" Button
        resetButton = new JButton("Reset");
        //resetButton.addActionListener(this);
        resetButton.getText();

        positionConst.gridx = 2;
        positionConst.gridy = 1;
        add(resetButton, positionConst);


        // Create "Save Image" Button
        saveImageButton = new JButton("Save Image");
        //saveImageButton.addActionListener(this);
        saveImageButton.getText();
        
        positionConst.gridx = 0;
        positionConst.gridy = 2;
        add(saveImageButton, positionConst);
        
        
        // Create "Triangle" Button
        savePosButton = new JButton("Save Position");
        //savePosButton.addActionListener(this);

        savePosButton.getText();
        positionConst.gridx = 1;
        positionConst.gridy = 2;
        add(savePosButton, positionConst);
        
        
        // Create "Load Position" Button
        loadButton = new JButton("Load Position");
        //loadButton.addActionListener(this);
        loadButton.getText();
        
        positionConst.gridx = 2;
        positionConst.gridy = 2;
        add(loadButton, positionConst);
    
    
        // Create "Edit Gradient" Button
        gradientButton = new JButton("Edit Gradient");
        //gradientButton.addActionListener(this);
        gradientButton.getText();
        
        positionConst.gridx = 3;
        positionConst.gridy = 2;
        add(gradientButton, positionConst);
        
    
        



        
    }


    
    public static void main(String[] args) {
        
        // Main frame
        Mandelbrot appFrame = new Mandelbrot();                
        
        // Show window
        appFrame.setVisible(true);
        
    }
}