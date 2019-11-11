import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.lang.Thread;


/*
 * Draws the pixels stored in the BufferedImage variable image in chunks.
 * Yields thread control for visual updates between chunks.
 * Selects a 3.5:2 rectangle when clicked on
 * 
 * @author Liz Matthews
 * 
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
   
   
   // Variables
   private boolean doneRendering;
   private int renderX, renderY;
   private int width, height;
   private Point posStart;
   private Point posEnd;   
   private Rectangle drawRect;
   private BufferedImage image;
   private Graphics2D gImg;
   private double scale;
   
   // Final variables
   final private Color colorSelect = new Color(0, 200, 200);
   final private int chunkSize = 50;
   
   /*
    * Default constructor for the canvas. Sets the scale to 1.
    * @author Liz Matthews
    * 
    */   
   public Canvas() {
      super();
      
      scale = 1;
      
     
      
      setup();
      
      setupCanvas();
      
      // Start the first render
      resetRender();
      
      
      
   }
   
   /*
    * Scaled constructor for the canvas. Sets the scale to the parameter passed in.
    * @author Liz Matthews
    * @param scale   how much to scale up the canvas from the default size of 350 by 200
    * 
    */   
   public Canvas(double scale) {
      super();
      
      this.scale = scale;
      
      setup();
      
      setupCanvas();
      
      // Start the first render
      resetRender();
      
      
   }
   
   /*
    * Method to set up certain variables. Kept separate from the constructor so that setupCanvas and resetRender can be used elsewhere.
    * @author Liz Matthews
    * 
    */
   private void setup() {
      
      // Initial state of variables
      doneRendering = false;
      
      renderX = 0;
      renderY = 0;
      drawRect = null;
      
      // Listen for mouse movement or input
      addMouseListener(this);      
      addMouseMotionListener(this);
      
   }
   
   /*
    * Method to create the images for the canvas
    * @author Liz Matthews
    * 
    */
   public void setupCanvas() {
      // Solid dimensions
      width = (int)(350 * scale);
      height = (int)(width * (2 / 3.5));
      
      // Image which is drawn upon
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      gImg = image.createGraphics();
      
      
   }
   
   /*
    * Overridden paintComponent to draw the BufferedImage variable to the panel
    * @author Liz Matthews
    * @param g Graphics variable linked to this panel
    * 
    */
   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      // Draw the image so far
      g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
      
      // Draw drag rectangle if it is there
      if (drawRect != null) {
         g.setColor(colorSelect);
         g.drawRect((int)drawRect.getX(), (int)drawRect.getY(),
                    (int)drawRect.getWidth(), (int)drawRect.getHeight());
      }
      
   }
   
   // Methods needed for mouse listeners but not needed to implement
   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }
   
   @Override
   public void mouseMoved(MouseEvent e) {
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      
   }
   
   
   /*
    * Method to detect click on the canvas. Sets up a position start and end and invokes {@link #updateRectangle()} to update the drag rectangle dimensions.
    * @author Liz Matthews
    * @param e Mouse event that occured
    * 
    */
   @Override
   public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
         posStart = new Point(e.getX(), e.getY());
         posEnd = new Point(e.getX(), e.getY());
         updateRectangle();
      }
   }

   /*
    * Method to detect the mouse button is no longer held down. Frees up the drag variables and invokes {@link #resetRender()}.
    * @author Liz Matthews
    * @param e Mouse event that occured
    * 
    */
   @Override
   public void mouseReleased(MouseEvent e) {
      posEnd.setLocation(e.getX(), e.getY());
      updateRectangle();
      
      // Resize the viewing area here
      
      // Free up the draw variables
      drawRect = null;
      posStart = null;
      posEnd = null;
      
      // Start the drawing process over again if we're not already rendering.
      if (doneRendering) {
         
         resetRender();
      }
         
   }
   
   /*
    * Method to detect mouse movement on the canvas. Invokes {@link #updateRectangle()}.
    * @author Liz Matthews
    * @param e Mouse event that occured
    * 
    */   
   @Override
   public void mouseDragged(MouseEvent e) {
      if (drawRect != null) {
         posEnd.setLocation(e.getX(), e.getY());
         updateRectangle();
      }
   }

   
   /*
    * Method which updates the drag rectangle. Maintains a ratio of 3.5:2.
    * @author Liz Matthews
    * 
    */
   public void updateRectangle() {
      int distX, distY;
      
      // If we don't have a drag rectangle already, make one
      if (drawRect == null) {
         drawRect = new Rectangle(0, 0, 0, 0);
      }
      
      int width = (int)Math.abs(posEnd.getX() - posStart.getX());
      int height = (int)Math.abs(posEnd.getY() - posStart.getY());
      int left = (int)Math.min(posStart.getX(), posEnd.getX());
      int top = (int)Math.min(posStart.getY(), posEnd.getY());
      
      // Calculate Y-value based on x and ratio
      distX = Math.abs(width);
      distY = (int)(distX * (2 / 3.5)); 
      
      // Set up rectangle to the correct four corners
      drawRect.setLocation(left, top);
      
      drawRect.setSize(distX,
                       distY);
      
      
      // Let paintComponent handle this later
      repaint();
   }
   
   /*
    * Method which resets the chunk rendering. Clears out the canvas with black before invoking {@link #renderAll()}.
    * @author Liz Matthews
    * 
    */   
   public void resetRender() {
      
      // Start the rendering over again, set current x,y render chunk to 0      
      renderX = 0;
      renderY = 0;
      doneRendering = false;
            
      // Clear out whatever is on the image
      gImg.setPaint(Color.BLACK);
      gImg.fillRect(0, 0, width , height );
      
      // Start up the render
      renderAll();
      
      // Re-draw the panel
      repaint();

   }
   
   /*
    * Method which renders chunks of an image at a time. Yields control of the thread for visualization of each chunk.
    * @author Liz Matthews
    * 
    */
   public void renderAll() {
      
      // Continue until the entire image is done
      while (!doneRendering) {
         try { 
             // relinquish control 
             Thread.yield();
             
         } 
         // Catch anything that goes wrong
         catch (Exception e) { 
             System.out.println(e); 
         }
         
         // Render next chunk
         render();
      
      }
    
   }
   
   /*
    * Renders the next chunk.
    * @author Liz Matthews
    * 
    */   
   private void render() {
      
      // Variables
      Color color;
      
      // If we're not done with the entire image...
      if (!doneRendering) {
         
         // Iterate over each pixel in the render chunk
         for(int x = renderX; x < renderX + chunkSize; x++) {
             for(int y = renderY; y < renderY + chunkSize; y++) {
               // Get the mandelbrot limit for that x/y
               // ???
               
               
               // Set the pixel in the image to the appropriate color
               image.setRGB(x, y, Color.BLACK.getRGB());
             }
         }
         
         // Move to next chunk
         renderX += chunkSize;
         if (renderX >= width ) {
            renderX = 0;
            renderY += chunkSize;
         }
         
         // If our next y-coordinate is off the end of the image then the entire image is rendered
         if (renderY >= height ) {
            doneRendering = true;
            renderX = 0;
            renderY = 0;
         }
      }
      
      // Paint NOW to force the chunk visualization
      paintImmediately(0, 0, width, height);
      
   }
   
}