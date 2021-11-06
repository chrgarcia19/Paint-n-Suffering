package mypaint;

import org.junit.*;

public class UnitTest {
    
    /**This method tests the initial width of the canvas
     * when it is initialized.
     */
    @Test
    public void widthTest(){
        NextCanvas canvas = new NextCanvas();
        double width = 800; //Width when canvas is created
        Assert.assertEquals(width, canvas.getCanvas().getWidth(), 0);
    }
    
    /**This method tests the initial Boolean value of
     * the variable changes made
     */
    @Test
    public void changesTest(){
        CanvasTabs canvas = new CanvasTabs();
        Boolean changes = false; //set to false when initialized.
        Assert.assertEquals(changes, canvas.getChanges().booleanValue());
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
    
}
