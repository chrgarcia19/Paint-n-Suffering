package mypaint;


import java.awt.image.*;
import javafx.embed.swing.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;


public class DrawCanvas extends Canvas{
    private static GraphicsContext gc;
    private static double w, h;
    private static Image nextSelect, select;
    private static BufferedImage mainImage, subImage;
    
    public DrawCanvas(){
        super();
        this.gc = this.getGraphicsContext2D();
    }
    
    /**This method takes the mouse points
     * and draws a square on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawSquare(double x1, double y1, double x2, double y2){
        if(ArtTools.getFill().isSelected()){
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRect(x1, y1, (x2 - x1), (x2 - x1));
                gc.fillRect(x1, y1, (x2 - x1), (x2 - x1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRect(x1, y2, (x2 - x1), (x2 - x1));
                gc.fillRect(x1, y2, (x2 - x1), (x2 - x1));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRect(x2, y1, (x1 - x2), (x1 - x2));
                gc.fillRect(x2, y1, (x1 - x2), (x1 - x2));
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRect(x2, y2, (x1 - x2), (x1 - x2));
                gc.fillRect(x2, y2, (x1 - x2), (x1 - x2));
            }
        }else{
           if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRect(x1, y1, (x2 - x1), (x2 - x1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRect(x1, y2, (x2 - x1), (x2 - x1));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRect(x2, y1, (x1 - x2), (x1 - x2));
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRect(x2, y2, (x1 - x2), (x1 - x2));
            } 
        }  
    }
    
    /**This method takes the mouse points
     * and draws a rectangle on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawRectangle(double x1, double y1, double x2, double y2){
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);
        if (ArtTools.getFill().isSelected()){
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRect(x1, y1, w, h);
                gc.fillRect(x1, y1, w, h);
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRect(x1, y2, w, h);
                gc.fillRect(x1, y2, w, h);
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRect(x2, y1, w, h);
                gc.fillRect(x2, y1, w, h);
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRect(x2, y2, w, h);
                gc.fillRect(x2, y2, w, h);
            }
        }else{
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRect(x1, y1, w, h);
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRect(x1, y2, w, h);
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRect(x2, y1, w, h);
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRect(x2, y2, w, h);
            }
        }       
    }
    
    /**This method takes the mouse points
     * and draws a round rectangle on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawRoundRectangle(double x1, double y1, double x2, double y2){
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);
        if (ArtTools.getFill().isSelected()){
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRoundRect(x1, y1, w, h, 25, 25);
                gc.fillRoundRect(x1, y1, w, h, 25, 25);
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRoundRect(x1, y2, w, h, 25, 25);
                gc.fillRoundRect(x1, y2, w, h, 25, 25);
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRoundRect(x2, y1, w, h, 25, 25);
                gc.fillRoundRect(x2, y1, w, h, 25, 25);
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRoundRect(x2, y2, w, h, 25, 25);
                gc.fillRoundRect(x2, y2, w, h, 25, 25);
            }
        }else{
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeRoundRect(x1, y1, w, h, 25, 25);
            }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
                gc.strokeRoundRect(x1, y2, w, h, 25, 25);
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeRoundRect(x2, y1, w, h, 25, 25);
            }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
                gc.strokeRoundRect(x2, y2, w, h, 25, 25);
            }
        }       
    }
    
    /**This method takes the mouse points
     * and draws an ellipse on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawEllipse(double x1, double y1, double x2, double y2){
        if (ArtTools.getFill().isSelected()){
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeOval(x1, y1, (x2 - x1), (y2 - y1));
                gc.fillOval(x1, y1, (x2 - x1), (y2 - y1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
                gc.strokeOval(x1, y2, (x2 - x1), (y1 - y2));
                gc.fillOval(x1, y2, (x2 - x1), (y1 - y2));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeOval(x2, y1, (x1 - x2), (y2 - y1));
                gc.fillOval(x2, y1, (x1 - x2), (y2 - y1));
            }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
                gc.strokeOval(x2, y2, (x1 - x2), (y1 - y2));
                gc.fillOval(x2, y2, (x1 - x2), (y1 - y2));
            } 
        }else{
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeOval(x1, y1, (x2 - x1), (y2 - y1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
                gc.strokeOval(x1, y2, (x2 - x1), (y1 - y2));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeOval(x2, y1, (x1 - x2), (y2 - y1));
            }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
                gc.strokeOval(x2, y2, (x1 - x2), (y1 - y2));
            } 
        }
    }
    
    /**This method takes the mouse points
     * and draws a circle on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawCircle(double x1, double y1, double x2, double y2){
        if (ArtTools.getFill().isSelected()){
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeOval(x1, y1, (x2 - x1), (x2 - x1));
                gc.fillOval(x1, y1, (x2 - x1), (x2 - x1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
                gc.strokeOval(x1, y2, (x2 - x1), (x2 - x1));
                gc.fillOval(x1, y2, (x2 - x1), (x2 - x1));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeOval(x2, y1, (x1 - x2), (x1 - x2));
                gc.fillOval(x2, y1, (x1 - x2), (x1 - x2));
            }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
                gc.strokeOval(x2, y2, (x1 - x2), (x1 - x2));
                gc.fillOval(x2, y2, (x1 - x2), (x1 - x2));
            } 
        }else{
            if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
                gc.strokeOval(x1, y1, (x2 - x1), (x2 - x1));
            }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
                gc.strokeOval(x1, y2, (x2 - x1), (x2 - x1));
            }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
                gc.strokeOval(x2, y1, (x1 - x2), (x1 - x2));
            }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
                gc.strokeOval(x2, y2, (x1 - x2), (x1 - x2));
            } 
        }
    }
    
    /**This method takes the mouse points and the sides 
     * and draws a n-sided polygon on the canvas.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawPolygon(double x1, double y1, double x2, double y2){
        if (Integer.parseInt(ArtTools.getPolySides().getEditor().getText()) < 3){
            ArtTools.getPolySides().getEditor().setText("3");
            ArtTools.getPolySides().setValue(3);
        }
        double[] xPoints = new double[Integer.parseInt(ArtTools.getPolySides().getEditor().getText())];
        double[] yPoints = new double[Integer.parseInt(ArtTools.getPolySides().getEditor().getText())];
        double r = Math.sqrt((Math.pow((x2 - x1), 2)) + Math.pow((y2-y1), 2));
        for (int i = 0; i < Integer.parseInt(ArtTools.getPolySides().getEditor().getText()); i++){
            xPoints[i] = x1 + (r * Math.cos((2*Math.PI*i)/Integer.parseInt(ArtTools.getPolySides().getEditor().getText())));
            yPoints[i] = y1 + (r * Math.sin((2*Math.PI*i)/Integer.parseInt(ArtTools.getPolySides().getEditor().getText())));
        }
        if (ArtTools.getFill().isSelected()){
            gc.strokePolygon(xPoints, yPoints, Integer.parseInt(ArtTools.getPolySides().getEditor().getText()));
            gc.fillPolygon(xPoints, yPoints, Integer.parseInt(ArtTools.getPolySides().getEditor().getText()));
        }else{
            gc.strokePolygon(xPoints, yPoints, Integer.parseInt(ArtTools.getPolySides().getEditor().getText()));
        }
    }
    
    /**This method takes the mouse points and draws a white 
     * rectangle while grabbing the content on the canvas that is selected.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public void drawCut(double x1, double y1, double x2, double y2){
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);
        select = this.snapshot(null, null);
        mainImage = SwingFXUtils.fromFXImage(select, null);
        subImage = new BufferedImage((int) w, (int) h, BufferedImage.OPAQUE);
        if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x1, (int) y1, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
            gc.fillRect(x1, y1, w, h);
            gc.strokeRect(x1, y1, w, h);
        }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x1, (int) y2, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
            gc.fillRect(x1, y2, w, h);
            gc.strokeRect(x1, y2, w, h);
        }else if(x1 >= x2 && y2 >= y1){//drawing diagonally up left
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x2, (int) y1, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
            gc.fillRect(x2, y1, w, h);
            gc.strokeRect(x2, y1, w, h);
        }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x2, (int) y2, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
            gc.fillRect(x2, y2, w, h);
            gc.strokeRect(x2, y2, w, h);
        }
    }
    
    /**This method takes the mouse points
     * and grabs the content on the canvas that is selected.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public void drawCopy(double x1, double y1, double x2, double y2){
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);
        select = this.snapshot(null, null);
        mainImage = SwingFXUtils.fromFXImage(select, null);
        subImage = new BufferedImage((int) w, (int) h, BufferedImage.OPAQUE);
        if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x1, (int) y1, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
        }else if (x2 >= x1 && y1 >= y2){//drawing diagnally up right
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x1, (int) y2, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
        }else if(x1 >= x2 && y2 >= y1){//drawing diagonally up left
            select = this.snapshot(null, null);
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x2, (int) y1, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
        }else if(x1 >= x2 && y1 >= y2){//drawing diagonally up left
            subImage.createGraphics().drawImage(mainImage.getSubimage((int) x2, (int) y2, (int) w, (int) h),0, 0, null);
            nextSelect = SwingFXUtils.toFXImage(subImage, null);
        }
    }
    
    /**This method takes the mouse points and draws a dashed
     * rectangle on the canvas to show what will be copied/cut.
     * @param x1, x value on first click
     * @param y1, y value on first click
     * @param x2, x value on second click
     * @param y2, y value on second click
     */
    public static void drawSelection(double x1, double y1, double x2, double y2){
        gc.setStroke(Color.BLACK);
        gc.setLineDashes(new double[] {25d, 10d, 25d, 10d});
        w = Math.abs(x2 - x1);
        h = Math.abs(y2 - y1);
        if (x2 >= x1 && y2 >= y1){//drawing diagonally down right
            gc.strokeRect(x1, y1, w, h);
        }else if (x2 >= x1 && y1 >= y2){//drawing diagonally up right
            gc.strokeRect(x1, y2, w, h);
        }else if (x1 >= x2 && y2 >= y1){//drawing diagonally down left
            gc.strokeRect(x2, y1, w, h);
        }else if (x1 >= x2 && y1 >= y2){//drawing diagnally up left
            gc.strokeRect(x2, y2, w, h);
        }      
        gc.setLineDashes(null);
    }
    
    public static GraphicsContext getGC(){return gc;}
    public static Image getImage(){return nextSelect;}
}
