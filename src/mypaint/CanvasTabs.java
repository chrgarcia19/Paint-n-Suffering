package mypaint;

import java.io.*;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CanvasTabs extends Tab{
    private static int projects = 1;
    private static File file;
    private static String tabName;
    private static NextCanvas canvas;
    private static Boolean changesMade;
    private static Pane canvasPane;
    private ScrollPane canvasScroll;
    
    public CanvasTabs(){
        super();
        changesMade = false;
        file = null;
        tabName = "Project " + projects;
        this.setText(tabName);
        projects += 1;
        canvas = new NextCanvas();
        tabSetup();
    }
    
    public CanvasTabs(File p){
        super();
        changesMade = false;
        file = p;
        tabName = file.getName();
        this.setText(tabName);
        canvas = new NextCanvas();
        tabSetup();
    }
    
    
    /**This method puts the canvas in the
     * current tab and adds a close request for each tab.
     */
    private void tabSetup(){
        this.canvasPane = new Pane(this.canvas);
        this.canvasScroll = new ScrollPane(this.canvasPane);
        this.setContent(this.canvasScroll);
       
        
        this.setOnCloseRequest((Event c) -> {
            changesMade = true;
            c.consume();
            Windows.exitTab(this.changesMade, c);
        });
    }
    

    
    public static Pane getPane(){return canvasPane;}
    public static Boolean getChanges(){return changesMade;}
    public static void setChanges(Boolean cm){changesMade = cm;}
    public static NextCanvas getCanvas(){return canvas;}
    
}
