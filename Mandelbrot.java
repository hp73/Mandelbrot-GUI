import javax.swing.*;

//import com.apple.eawt.ApplicationListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Mandelbrot extends JFrame implements ActionListener {
    
    // Class Variables   
    private Canvas canvas;
    private JButton increaseButton;
    private JButton decreaseButton;
    private JButton resetButton;
    private JButton saveImageButton;
    private JButton savePosButton;
    private JButton loadButton;
    private JButton gradientButton;
    private JComboBox comboBox;
    private JFileChooser fc;



   
    public Mandelbrot() {

        //Action Listeners
    
    ActionListener increaseLimit = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            System.out.println("Increase Limit");
            canvas.increaseLimit();
            canvas.resetRender();
            
        }
    };
    
    
    ActionListener decreaseLimit = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            System.out.println("decrease limit");
            canvas.decreaseLimit();
            canvas.resetRender();
        }
    };
    
    ActionListener reset = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            System.out.println("Reset");
            canvas.resetLimit();
            setCalculator.resetButton();
            canvas.resetRender();

            }
    };    

    

   ActionListener saveImage = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            System.out.println("save image");

            File cool;

            JFileChooser fc = new JFileChooser();
            int r = fc.showSaveDialog(null); 
            
            
            if (r == JFileChooser.APPROVE_OPTION){
                // the path to put into imageIO
                cool =  fc.getSelectedFile();
                try {
                    ImageIO.write(canvas.getIMG(), "png", cool);
                } catch (Exception b) {
                    // TODO Auto-generated catch block
                    System.out.println("error");
                }
            } 

        }
    };        

    
     ActionListener combo = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            
            JComboBox cb = (JComboBox)e.getSource();
            String setName = (String)cb.getSelectedItem();
            
        }
    };
    
    
    ActionListener combo2 = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            
            JComboBox cb2 = (JComboBox)e.getSource();
            String gradientName = (String)cb2.getSelectedItem();
            
            canvas.setGradient(gradientName);
            canvas.resetRender();
      
        }
    };
    
    
    
    /*
    ActionListener editGradient = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
        System.out.println("Edit Gradient");
       
        UIManager.put("OptionPane.minimumSize",new Dimension(1000,300));    
        String[] gradients= {"Rainbow Set", "GreyScale", "GreenScale"};

        JComboBox jcd = new JComboBox(gradients);

        JOptionPane jop = new JOptionPane(null,
                                        JOptionPane.PLAIN_MESSAGE,
                                        JOptionPane.OK_OPTION,
                                        null);

        //add combos to JOptionPane
        jop.add(jcd);

        //create a JDialog and add JOptionPane to it 
        JDialog diag = new JDialog();
        diag.getContentPane().add(jop);
        diag.pack();
        diag.setVisible(true);
            
            

            
           
        }
    };
    
    */
    
    //put method into canvas


        
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
        increaseButton.addActionListener(increaseLimit);
        increaseButton.getText();
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        positionConst.gridwidth = 1;
        positionConst.weighty = .05;
        add(increaseButton, positionConst);

        // Create "Decrease Limit" Button
        decreaseButton = new JButton("Decrease Limit");
        decreaseButton.addActionListener(decreaseLimit);
        decreaseButton.getText();
      
        positionConst.gridx = 1;
        positionConst.gridy = 1;
        positionConst.gridwidth = 1;
        add(decreaseButton, positionConst);


        // Create "Reset" Button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(reset);
        resetButton.getText();

        positionConst.gridx = 2;
        positionConst.gridy = 1;
        add(resetButton, positionConst);


        // Create "Save Image" Button
        saveImageButton = new JButton("Save Image");
        saveImageButton.addActionListener(saveImage);
        saveImageButton.getText();
        
        positionConst.gridx = 0;
        positionConst.gridy = 2;
        add(saveImageButton, positionConst);
        
        
        // Create "Triangle" Button
        savePosButton = new JButton("Save Position");
        savePosButton.addActionListener(this);

        savePosButton.getText();
        positionConst.gridx = 1;
        positionConst.gridy = 2;
        add(savePosButton, positionConst);
        
        
        // Create "Load Position" Button
        loadButton = new JButton("Load Position");
        loadButton.addActionListener(this);
        loadButton.getText();
        
        positionConst.gridx = 2;
        positionConst.gridy = 2;
        add(loadButton, positionConst);
    
    /*
        // Create "Edit Gradient" Button
        gradientButton = new JButton("Edit Gradient");
        gradientButton.addActionListener(editGradient);
        gradientButton.getText();
        
        positionConst.gridx = 3;
        positionConst.gridy = 2;
        add(gradientButton, positionConst);
        
        */
        
         // Create Julia/Mandelbrot Combo Box
         String[] setStrings = {"Mandelbrot Set", "Julia Set"};
        
         JComboBox comboBox = new JComboBox<String>(setStrings);
         comboBox.addActionListener(combo);
         comboBox.setSelectedIndex(0);
         
         positionConst.gridx = 3;
         positionConst.gridy = 1;
         add(comboBox, positionConst);
         
         // Create Julia/Mandelbrot Combo Box
         String[] gradientStrings = {"Rainbow", "BlueScale", "GreyScale"};
        
         JComboBox comboBox2 = new JComboBox<String>(gradientStrings);
         comboBox2.addActionListener(combo2);
         comboBox2.setSelectedIndex(0);
         
         positionConst.gridx = 3;
         positionConst.gridy = 2;
         add(comboBox2, positionConst);
    }

    
    @Override
   public void actionPerformed(ActionEvent e){
    
   }

    
    public static void main(String[] args) {
        
        // Main frame
        Mandelbrot appFrame = new Mandelbrot();                
        
        // Show window
        appFrame.setVisible(true);
        
    }
}