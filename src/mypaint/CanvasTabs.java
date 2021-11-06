package mypaint;

import java.io.*;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.*;




public class CanvasTabs extends Tab{
    private static int projects = 0;
    private static File file;
    private static String tabName;
    private static NextCanvas nextCanvas;
    private static Boolean changesMade;
    private static Pane canvasPane;
    private static ScrollPane canvasScroll;
    private static ArrayList<ScrollPane> storeCanvas;
    
    public CanvasTabs(){
        super();
        changesMade = false;
        file = null;
        tabName = "Project " + projects;
        this.setText(tabName);
        projects += 1;
        nextCanvas = new NextCanvas();
        tabSetup();
    }
    
    public CanvasTabs(File p){
        super();
        changesMade = false;
        file = p;
        tabName = p.getName();
        this.setText(tabName);
        nextCanvas = new NextCanvas();
        tabSetup();
    }
    
    private void tabSetup(){
        this.canvasPane = new Pane(nextCanvas.getCanvas());
        this.canvasScroll = new ScrollPane(canvasPane);
        this.setContent(canvasScroll);
        
        
        this.setOnCloseRequest((Event c) -> {
            changesMade = true;
            c.consume();
            Windows.exitTab(this.changesMade, c);
            
        });
    }
    
    
    
    
    public static Boolean getChanges(){return changesMade;}
    public static void setChanges(Boolean cm){changesMade = cm;}
    public static NextCanvas getCanvas(){return nextCanvas;}
    
}
