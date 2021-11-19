package mypaint;

import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javafx.embed.swing.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javax.imageio.*;

public class MainLayout{
    private static Menu file, helpMenu, view, edit, rotate, zoom, autosave;
    private static MenuItem open, save, saveAs, exit,
    rNotes, about, help, zoomIn, zoomOut, undoB, redoB,
    right90, left90, r180, flipV, flipH, reset, create, cut, copy;
    private static CheckMenuItem select, paste, autosaveDisplay;
    private static CheckMenuItem[] timeOptions;
    private static MenuBar menuBar;
    private static VBox menu, options;
    private static FileChooser pickFile;
    private static File saveFile;
    private static Image image;
    
    
    
    
    public MainLayout(){        
        //File Button in Toolbar
        file = new Menu("File");
        create = new MenuItem("New Project");
        open = new MenuItem("Open");
        save = new MenuItem("Save");
        saveAs = new MenuItem("Save As");
        exit = new MenuItem("Exit");
        //adds all menu items under file button
        file.getItems().addAll(create, open, save, saveAs, exit);
        
        edit = new Menu("Edit");
        undoB = new MenuItem("Undo");
        redoB = new MenuItem("Redo");
        select = new CheckMenuItem("Select");
        cut = new MenuItem("Cut");
        cut.setDisable(true);
        copy = new MenuItem("Copy");
        copy.setDisable(true);
        paste = new CheckMenuItem("Paste");
        paste.setDisable(true);
        edit.getItems().addAll(undoB, redoB, select, cut, copy, paste);
      
        
        //View Button
        view = new Menu("View");
        rotate = new Menu("Rotate"); 
        right90 = new MenuItem("Rotate Right 90°");
        left90 = new MenuItem("Rotate Left 90°");
        r180 = new MenuItem("Rotate 180°");
        flipV = new MenuItem("Flip Vertically");
        flipH = new MenuItem("Flip Horizontally");
        reset = new MenuItem("Reset Rotate");
        rotate.getItems().addAll(right90, left90, r180, flipV, flipH, reset);
        
        zoom = new Menu("Zoom");
        zoomIn = new MenuItem("Zoom In");
        zoomOut = new MenuItem("Zoom Out");
        zoom.getItems().addAll(zoomIn, zoomOut);
        
        autosaveDisplay = new CheckMenuItem("Display Autosave Timer");
        autosaveDisplay.setSelected(true);
        autosave = new Menu("Autosave Time Select");
        timeOptions = new CheckMenuItem[] {new CheckMenuItem("30 sec."), new CheckMenuItem("60 sec."), 
        new CheckMenuItem("90 sec."), new CheckMenuItem("120 sec."), new CheckMenuItem("5 min"), 
        new CheckMenuItem("10 min"), new CheckMenuItem("15 min"), new CheckMenuItem("20 min"),
        new CheckMenuItem("25 min"), new CheckMenuItem("30 min")};
        autosave.getItems().addAll(timeOptions);
        timeOptions[0].setSelected(true);
        view.getItems().addAll(zoom, rotate, autosaveDisplay, autosave);
        
         //Help Menu Button
        helpMenu = new Menu("Help");
        rNotes = new MenuItem("Release Notes");
        about = new MenuItem("About");
        help = new MenuItem("Help");
        //adds all menu items under help menu
        helpMenu.getItems().addAll(help, about, rNotes);
        
        
        //Menu bar for file, help, etc
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, edit, view, helpMenu);
        
        menu = new VBox(menuBar);
        
        pickFile = new FileChooser();
        //Allows for specific file types only
        pickFile.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
        new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"),
        new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"),
        new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp"));
        
        options = new VBox();

        
        right90.setOnAction(r90 -> {
            CanvasTabs.getPane().setRotate(CanvasTabs.getPane().getRotate() + 90);
        });
        
        left90.setOnAction(l90 -> {
            CanvasTabs.getPane().setRotate(CanvasTabs.getPane().getRotate() - 90);
        });
        
        r180.setOnAction(ro180 -> {
            CanvasTabs.getPane().setRotate(CanvasTabs.getPane().getRotate() + 180);
        });
        
        flipH.setOnAction(fh -> {
            CanvasTabs.getPane().setScaleX(CanvasTabs.getPane().getScaleX() * -1);
        });
        
        flipV.setOnAction(fv -> {
            CanvasTabs.getPane().setScaleY(CanvasTabs.getPane().getScaleY() * -1);
        });
        
        reset.setOnAction(re -> {
            CanvasTabs.getPane().setRotate(CanvasTabs.getPane().getRotate() * 0);
            CanvasTabs.getPane().setScaleX(1);
            CanvasTabs.getPane().setScaleY(1);
        });
        
        timeOptions[0].setOnAction(t0 -> {
            toggleTime();
            timeOptions[0].setSelected(true);
        });
        
        timeOptions[1].setOnAction(t1 -> {
            toggleTime();
            timeOptions[1].setSelected(true);
        });
        
        timeOptions[2].setOnAction(t2 -> {
            toggleTime();
            timeOptions[2].setSelected(true);
        });
        
        timeOptions[3].setOnAction(t3 -> {
            toggleTime();
            timeOptions[3].setSelected(true);
        });
        
        timeOptions[4].setOnAction(t4 -> {
            toggleTime();
            timeOptions[4].setSelected(true);
        });
        
        timeOptions[5].setOnAction(t5 -> {
            toggleTime();
            timeOptions[5].setSelected(true);
        });
        
        timeOptions[6].setOnAction(t6 -> {
            toggleTime();
            timeOptions[6].setSelected(true);
        });
        
        timeOptions[7].setOnAction(t7 -> {
            toggleTime();
            timeOptions[7].setSelected(true);
        });
        
        timeOptions[8].setOnAction(t8 -> {
            toggleTime();
            timeOptions[8].setSelected(true);
        });
        
        timeOptions[9].setOnAction(t9 -> {
            toggleTime();
            timeOptions[9].setSelected(true);
        });
        
        create.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        create.setOnAction(c -> {
            newTab();
        });
        
        //File Operations
        open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        open.setOnAction(t -> {//Opening an image
            open();
        });
        
        //normal save
        save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        save.setOnAction(svAS -> {
            save();
        });
        
        //save as 
        saveAs.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        saveAs.setOnAction(sv -> {
            saveAs();
        });
        
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        exit.setOnAction(e -> {//exiting program without saving
            Windows.popupWindow("Exit");
        });
               
        
        //Help Operations
        //goes to help screen if help button clicked
        help.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        help.setOnAction(hp -> {
           Windows.popupWindow("Help");
        });
        
        //about button is clicked
        about.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        about.setOnAction(ab -> {
           Windows.popupWindow("About");
        });
        
        //release notes button is clicked
        rNotes.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        rNotes.setOnAction(r -> {
           Windows.popupWindow("Release Notes");
        });
        
        select.setOnAction(sl -> {
            ArtTools.toggleOff();
            select.setSelected(true);
        });
        
        
        cut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        cut.setOnAction(sm -> {
            ArtTools.toggleOff();
            CanvasTabs.getCanvas().undo();
            CanvasTabs.getCanvas().drawCut(NextCanvas.getX1(), NextCanvas.getY1(), NextCanvas.getX2(), NextCanvas.getY2());
            paste.setDisable(false);
        });
        
        copy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        copy.setOnAction(sm -> {
            ArtTools.toggleOff();
            CanvasTabs.getCanvas().undo();
            CanvasTabs.getCanvas().drawCopy(NextCanvas.getX1(), NextCanvas.getY1(), NextCanvas.getX2(), NextCanvas.getY2());
            paste.setDisable(false);
        });
        
        paste.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        paste.setOnAction(ps -> {
            ArtTools.toggleOff();
            paste.setSelected(true);
            cut.setDisable(true);
            copy.setDisable(true);
        });
        
        redoB.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
        redoB.setOnAction(re -> {
            CanvasTabs.getCanvas().redo();
        });
        
        undoB.setAccelerator(KeyCombination.keyCombination("Ctrl+Z")); 
        undoB.setOnAction(un -> {
            CanvasTabs.getCanvas().undo();
        });
        
        zoomIn.setAccelerator(KeyCombination.keyCombination("Ctrl+]"));
        zoomIn.setOnAction(zi -> {
            NextCanvas.zoomIn();
        });
        
        zoomOut.setAccelerator(KeyCombination.keyCombination("Ctrl+["));
        zoomOut.setOnAction(zo -> {
            NextCanvas.zoomOut();
        });   
        
    }
    
    /**This method toggles all time
     * options in the menu to off.
     */
    private void toggleTime(){
        timeOptions[0].setSelected(false);
        timeOptions[1].setSelected(false);
        timeOptions[2].setSelected(false);
        timeOptions[3].setSelected(false);
        timeOptions[4].setSelected(false);
        timeOptions[5].setSelected(false);
        timeOptions[6].setSelected(false);
        timeOptions[7].setSelected(false);
        timeOptions[8].setSelected(false);
        timeOptions[9].setSelected(false);
    }
    
    /**This method creates a new
     * blank tab. 
     */
    private void newTab(){
        CanvasTabs temp = new CanvasTabs();
        MyPaint.tabPane.getTabs().add(temp);
        MyPaint.tabPane.getSelectionModel().select(temp);
    }
    
    
    /**Opens the file chooser so a
     * user can open an image
     */
    protected void open(){
        CanvasTabs temp;
        File selection = pickFile.showOpenDialog(MyPaint.getStage());
        //opens image from path
        if (selection != null){
            image = new Image(selection.toURI().toString());
            //sets savefile, important for saving
            saveFile = selection;
            temp = new CanvasTabs(selection);
            //sets canvas to size of image
            CanvasTabs.getCanvas().setWidth(image.getWidth());
            CanvasTabs.getCanvas().setHeight(image.getHeight());
            //draws image to canvas
            NextCanvas.getGC().drawImage(image, 0, 0);
            MyPaint.tabPane.getTabs().add(temp);
            MyPaint.tabPane.getSelectionModel().select(temp);
        }else{
            newTab();
        }    
    }
    
    /**This method calls the normal save method if a file exists
     * and initSaveNAs if there is not an existing save file
     */       
    protected static void save(){
        //pulls up save as if no file is present
        if (saveFile == null){
            initSaveNAs(pickFile, MyPaint.getStage(), CanvasTabs.getCanvas());
        }else{//saveFile != false
            normalSave(CanvasTabs.getCanvas());
        }
    }
    
    /**This method calls the initSaveNAs method
     * to save a new file
     */
    private static void saveAs(){
        initSaveNAs(pickFile, MyPaint.getStage(), CanvasTabs.getCanvas());
    }
    
    /**This method takes a snapshot of the canvas and converts
     * it to an image which is saved to a new file
     * @param pf, file chooser
     * @param s, stage for files
     * @param c, canvas
     */
    private static void initSaveNAs(FileChooser pf, Stage s, Canvas c){
        //opens files
        File fileS = pf.showSaveDialog(s);
        if(fileS != null){
            try {
                WritableImage writableImage = new WritableImage((int) c.getWidth(),(int) c.getHeight());
                c.snapshot(null, writableImage);//snapshot of canvas
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", fileS);
            } catch (IOException ex) {
                Logger.getLogger(MyPaint.class.getName()).log(Level.SEVERE, null, ex);
            }
            saveFile = fileS;
        }
    }
    
    /**This method takes a snapshot of a canvas and converts
     * it to an image which is saved to an existing file
     * @param c, canvas 
     */
    protected static void normalSave(Canvas c){
        try {
            WritableImage writableImage = new WritableImage((int) c.getWidth(),(int) c.getHeight());
            c.snapshot(null, writableImage);//snapshot of canvas
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", saveFile);
        } catch (IOException ex) {
            Logger.getLogger(MyPaint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Image getImage(){return image;}
    public static CheckMenuItem getSelect(){return select;}
    public static MenuItem getCut(){return cut;}
    public static MenuItem getCopy(){return copy;}
    public static CheckMenuItem getPaste(){return paste;}
    public static CheckMenuItem[] getTimes(){return timeOptions;}
    public static VBox getMenu(){return menu;}
    public static VBox getOptions(){return options;}
    public static CheckMenuItem getAutosave(){return autosaveDisplay;}
    public static MenuItem getExit(){return exit;}
}