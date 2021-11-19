package mypaint;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.*;

public class UnitTest {
    
    /**This method tests the initial width of the canvas
     * when it is initialized.
     */
    @Test
    public void widthTest(){
        NextCanvas canvas = new NextCanvas();
        double width = 800; //Width when canvas is created
        Assert.assertEquals(width, canvas.getWidth(), 0);
    }
    
    /**This method tests the initial Boolean value of
     * the variable changes made
     */
    @Test
    public void zoomTest(){
        NextCanvas canvas = new NextCanvas();
        double actualZoom = 1; //set to false when initialized.
        Assert.assertEquals(actualZoom, canvas.getZoom(), 0);
    }
    
    /**This method test the initial value of x1 
     * when it is initialized
     */
    @Test
    public void x1Test(){
        NextCanvas tool = new NextCanvas();
        double x1 = 0; //x1 is 0 because it is not initialized
        Assert.assertEquals(x1, tool.getX1(), 0);
    }
    
    /**This method tests if the program works properly
     * and allows for the other tests to work properly.
     * @throws InterruptedException 
     */
    @Test
    public void testPaint() throws InterruptedException{
        Thread t = new Thread(new Runnable() {
            
            @Override
            public void run(){
                //initializes JavaFX
                new JFXPanel();
                Platform.runLater(new Runnable() {
                    
                    @Override
                    public void run(){
                        try{
                            new MyPaint().start(new Stage());
                        }catch (Exception e){
                            System.out.println("The program broke!");
                        }
                    }
                });
            }
        });
        t.start();
        t.sleep(10000);
    }
    
}
