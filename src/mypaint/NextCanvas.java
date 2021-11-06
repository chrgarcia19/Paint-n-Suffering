package mypaint;

import java.util.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;


public class NextCanvas extends DrawCanvas{
    private static Canvas canvas;
    private static GraphicsContext gc;
    private static Scale scale;
    private static double zoom, x1, y1, x2, y2;
    private static Stack<Image> undo, redo;
    private static Image show;
    private static Pane canvasPane;
    private static ScrollPane canvasScroll;
    
    public NextCanvas(){
        super();
        canvas = new Canvas(800, 750);
        canvasPane = new Pane();
        canvasScroll = new ScrollPane();
        canvasScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        canvasScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        gc = canvas.getGraphicsContext2D();
        scale = new Scale(canvas.getWidth(), canvas.getHeight());
        zoom = 1;
        undo = new Stack<Image>();
        redo = new Stack<Image>();
        undo.push(MainLayout.getImage());
        
        
        this.gc.setFill(Color.WHITE);
        this.gc.setStroke(Color.WHITE);
        ArtTools.getFill().setSelected(true);
        this.gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        ArtTools.getFill().setSelected(false);
        
        this.canvas.setOnMousePressed((MouseEvent cmp) -> {
            initDraw(this.gc, ArtTools.getOutlineWidth(), ArtTools.getCap(), ArtTools.getDash(), 
                    ArtTools.getOutline(), ArtTools.getFillColor());
            //updates first coordinates
            x1 = cmp.getX();
            y1 = cmp.getY();
            if (ArtTools.getLine().isSelected()){//line tool
                addUndo(undo, this.canvas);
            }else if (ArtTools.getPencil().isSelected()){//pencil tool
                this.gc.beginPath();
                //tracks initial x and y
                this.gc.moveTo(cmp.getX(), cmp.getY());
                this.gc.stroke();
                addUndo(undo, this.canvas);
            }else if(ArtTools.getSquare().isSelected()){//square tool
                addUndo(undo, this.canvas);
            }else if(ArtTools.getRectangle().isSelected()){//rectangle tool
                addUndo(undo, this.canvas);
            }else if (ArtTools.getRoundRectangle().isSelected()){//round rectangle tool
                addUndo(undo, this.canvas);
            }else if(ArtTools.getEllipse().isSelected()){//ellipse tool
                addUndo(undo, this.canvas);
            }else if(ArtTools.getCircle().isSelected()){//circle tool
                addUndo(undo, this.canvas);
            }else if(ArtTools.getPolygon().isSelected()){//polygon tool
                addUndo(undo, this.canvas);
            }else if(ArtTools.getEraser().isSelected()){//eraser tool
                this.gc.beginPath();
                //tracks initial x and y
                this.gc.moveTo(cmp.getX(), cmp.getY());
                this.gc.stroke();
                addUndo(undo, this.canvas);
            }else if(MainLayout.getSelect().isSelected()){//select
                addUndo(undo, this.canvas);
            }
        });    
        this.canvas.setOnMouseDragged((MouseEvent cmd) -> {
            //gets end points
            x2 = cmd.getX();
            y2 = cmd.getY();
            if (ArtTools.getLine().isSelected()){//line tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                //makes line using 4 points
                this.gc.strokeLine(x1, y1, x2, y2);
            }else if (ArtTools.getPencil().isSelected()){//pencil tool
                //gets end x and y
                this.gc.lineTo(cmd.getX(), cmd.getY());
                this.gc.stroke();
            }else if(ArtTools.getSquare().isSelected()){//square tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawSquare(x1, y1, x2, y2);
            }else if(ArtTools.getRectangle().isSelected()){//rectangle tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawRectangle(x1, y1, x2, y2);
            }else if (ArtTools.getRoundRectangle().isSelected()){//round rectangle tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawRoundRectangle(x1, y1, x2, y2);
            }else if(ArtTools.getEllipse().isSelected()){//ellipse tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawEllipse(x1, y1, x2, y2);
            }else if(ArtTools.getCircle().isSelected()){//circle tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawCircle(x1, y1, x2, y2);
            }else if(ArtTools.getPolygon().isSelected()){//polygon tool
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawPolygon(x1, y1, x2, y2);
            }else if(ArtTools.getEraser().isSelected()){//eraser tool
                //gets end x and y
                this.gc.setStroke(Color.WHITE);
                this.gc.lineTo(cmd.getX(), cmd.getY());
                this.gc.stroke();
            }else if(MainLayout.getSelect().isSelected()){//select
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawSelection(x1, y1, x2, y2);
            }
        });
        this.canvas.setOnMouseReleased((MouseEvent cmr) -> {
            //gets end points
            x2 = cmr.getX();
            y2 = cmr.getY();
            if(ArtTools.getLine().isSelected()){//line tool
                //makes line using 4 points
                this.gc.strokeLine(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getPencil().isSelected()){//pencil tool
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getSquare().isSelected()){//square tool
                DrawCanvas.drawSquare(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getRectangle().isSelected()){//rectangle tool  
                DrawCanvas.drawRectangle(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getRoundRectangle().isSelected()){//round rectangle tool  
                DrawCanvas.drawRoundRectangle(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getEllipse().isSelected()){//ellipse tool
                DrawCanvas.drawEllipse(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getCircle().isSelected()){//circle tool
                DrawCanvas.drawCircle(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getPolygon().isSelected()){//polygon tool
                DrawCanvas.drawPolygon(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getEraser().isSelected()){//eraser tool
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(MainLayout.getSelect().isSelected()){//select
                Image show = (Image) undo.peek();
                this.gc.drawImage(show, 0, 0);
                DrawCanvas.drawSelection(x1, y1, x2, y2);
                CanvasTabs.setChanges(true);
                redo.clear();
                MainLayout.getCut().setDisable(false);
                MainLayout.getCopy().setDisable(false);
            }
        });
        this.canvas.setOnMouseClicked((MouseEvent cmc) -> { 
            if(ArtTools.getText().isSelected()){//text tool
                initDraw(this.gc, ArtTools.getFontSize(), ArtTools.getOutline());
                addUndo(undo, this.canvas);
                String input = ArtTools.getType().getText();
                this.gc.fillText(input, cmc.getX(), cmc.getY());
                CanvasTabs.setChanges(true);
                redo.clear();
            }else if(ArtTools.getColorGrabber().isSelected()){//color grabber
                WritableImage wI = new WritableImage((int) this.canvas.getWidth(),(int) this.canvas.getHeight());
                this.canvas.snapshot(null, wI);
                PixelReader pr = wI.getPixelReader();
                ArtTools.getFillColor().setValue(pr.getColor((int) cmc.getX(), (int)cmc.getY()));
                ArtTools.getOutline().setValue(pr.getColor((int) cmc.getX(), (int)cmc.getY()));
                CanvasTabs.setChanges(true);
            }else if(MainLayout.getPaste().isSelected()){//paste
                this.gc.drawImage(DrawCanvas.getImage(), cmc.getX(), cmc.getY());
                CanvasTabs.setChanges(true);
                redo.clear();
            }
        });
    }  
    
    public static Node canvasSetup(){
        canvasPane.getChildren().add(canvas);
        canvasScroll.setContent(canvasPane);
        return canvasScroll;
    }
    
    /**This method sets the fill, stroke, and width 
     * for each shape and drawing tool
     * @param gc, graphics context object
     * @param cb, integer combo box
     * @param cp, string combo box
     * @param ds, integer combo box
     * @param o, outline color picker
     * @param f, fill color picker 
     */
    public static void initDraw(GraphicsContext gc, ComboBox<Integer> cb, ChoiceBox<String> cp, ComboBox<Double> ds, ColorPicker o, ColorPicker f){  
        //sets fill and stroke using color picker value
        gc.setLineCap(StrokeLineCap.valueOf(cp.getValue().toUpperCase()));
        gc.setFill(f.getValue());
        gc.setStroke(o.getValue());
        if (Integer.parseInt(cb.getEditor().getText()) <= 0){
            cb.setValue(1);
            gc.setLineWidth(1);
        }else{//value>0
            gc.setLineWidth(Integer.parseInt(cb.getEditor().getText()));
            gc.setFont(Font.font(Integer.parseInt(cb.getEditor().getText())));
        }
        double ld = Double.parseDouble(ds.getEditor().getText());
        if (Double.parseDouble(ds.getEditor().getText()) < 0d){
            ds.setValue(0d);
            gc.setLineDashes(0d);
        }else if (Double.parseDouble(ds.getEditor().getText()) == 0d || ArtTools.getEraser().isSelected()){
            gc.setLineDashes(null);
        }else if (Double.parseDouble(ds.getEditor().getText()) <= Integer.parseInt(cb.getEditor().getText())){
            ld = (Integer.parseInt(cb.getEditor().getText()) + 1);
            gc.setLineDashes(new double[] {ld, (ld*1.3), ld, (ld*1.3)});
            ds.setValue(ld);
        }else{
            gc.setLineDashes(new double[] {ld, (ld*1.3), ld, (ld*1.3)});
        }
    }
    
    public static void initDraw(GraphicsContext gc, ComboBox<Integer> cb, ColorPicker o){
        gc.setStroke(o.getValue());
        if (Integer.parseInt(cb.getEditor().getText()) <= 0){
            cb.setValue(12);
            gc.setFont(Font.font(12));
        }else{//value>0
            gc.setFont(Font.font(Integer.parseInt(cb.getEditor().getText())));
        }
    }
    
    
    /**This method sets fill, stroke, and width to
     * 0 and makes nothing occur if a mouse action occurs
     * @param gc, graphics context object
     * @param c, canvas object 
     */
    public static void endDraw(GraphicsContext gc, Canvas c){
        //no color for fill or stroke
        gc.setFill(null);
        gc.setStroke(null);
        //sets to 0 so nothing appears
        gc.setLineWidth(0);
        gc.fillText(null, 0, 0);
        //causes nothing to happen if canvas senses press or release
        c.setOnMousePressed(cv -> { 
        });
        c.setOnMouseReleased(cr -> {
        });  
        c.setOnMouseDragged(mde -> {
        });
        c.setOnMouseClicked(ttmc -> {
        });
    }
    
    /**This method zooms in the canvas by a
     * factor of .1 while keeping the scale
     */
    public static void zoomIn(){
        canvasPane.getTransforms().remove(scale);
        zoom += .1;
        scale = new Scale(zoom, zoom, 0, 0);
        canvasPane.getTransforms().add(scale);        
    }
    
    /**This method zooms out the canvas by
     * a factor of -.1 while keeping the scale
     */
    public static void zoomOut(){
        canvasPane.getTransforms().remove(scale);
        zoom -= .1;
        scale = new Scale(zoom, zoom, 0, 0);
        canvasPane.getTransforms().add(scale);
    }
    
    /**This method removes an edit made
     * on the canvas
     */
    public static void undo(){
        if(!undo.empty()){
            WritableImage store = new WritableImage((int) canvas.getWidth(),(int) canvas.getHeight());
            canvas.snapshot(null, store);
            Image storedImage = undo.pop();
            gc.drawImage(storedImage, 0, 0);
            redo.push(store);
        }
    }
    
    /**This method restores a change that
     * was undone previously
     */
    public static void redo(){
        if(!redo.empty()){
            WritableImage store = new WritableImage((int) canvas.getWidth(),(int) canvas.getHeight());
            canvas.snapshot(null, store);
            Image storedImage = redo.pop();
            gc.drawImage(storedImage, 0, 0);
            undo.push(store);
        }
    }
    
    /**This method adds edits made on canvas
     * to the stack
     * @param s, a Stack of images
     * @param c, a canvas object
     */
    public static void addUndo(Stack<Image> s, Canvas c){
        Image prev = new WritableImage((int) c.getWidth(),(int) c.getHeight());
        canvas.snapshot(null, (WritableImage) prev);
        s.push(prev);
        redo.clear();
    }  
    
    public static Canvas getCanvas(){return canvas;}
    public static GraphicsContext getGC(){return gc;}
    public static double getX1(){return x1;}
    public static double getY1(){return y1;}
    public static double getX2(){return x2;}
    public static double getY2(){return y2;}
    public static Pane getPane(){return canvasPane;}
}