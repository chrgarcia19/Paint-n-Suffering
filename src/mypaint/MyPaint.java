package mypaint;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MyPaint extends Application {
    private static ArtTools artTools;
    private static NextCanvas canvas;
    private static MainLayout layout;
    public static TabPane tabPane;
    private static BorderPane window;
    private static Stage mainStage;
    final private String name = "Paint n' Suffering";
    final private String version = "V. 1.0.0";
    
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle(name + " - " + version);
        
        tabPane = new TabPane();
        
        layout = new MainLayout();
        artTools = new ArtTools();
        canvas = new NextCanvas();
        
        window = new BorderPane();
        layoutSetup();
        
        TimerTask autosave = new Autosave();
        try{
            Timer a = new Timer();
            a.scheduleAtFixedRate(autosave, 0, 600);
        }catch(Exception e){
            System.out.println("Timer exception caught!");
        }
        
        mainStage.setOnCloseRequest(psc -> {
            psc.consume();
            Windows.exitPaint(mainStage);
        });
       
        
        
        Scene scene = new Scene(window, 1100, 950);
        
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**This method sets up the elements
     * to be displayed correctly in the scene
     */
    public static void layoutSetup(){
        layout.getOptions().getChildren().addAll(layout.getMenu(), artTools.getMenu());
        tabPane.getTabs().add(new CanvasTabs());
        tabPane.getSelectionModel().selectFirst();
        window.setTop(layout.getOptions());
        window.setCenter(tabPane);
        window.setBottom(artTools.getDisplay());
    }
    
    /**This method logs the current
     * active tool in use. 
     */
    public static void logPaint(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        sdf.format(date);
        File modifyFile = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\LoggingOutput.log");
        FileHandler fh;  
        try {  
            // This block configures the logger with handler and formatter  
            fh = new FileHandler("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\LoggingOutput.log");  
            ArtTools.logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
            ArtTools.logger.info("(" + date + ") - Active Tool: " + ArtTools.getTool());
            ArtTools.getActive().setText("Active Tool: " + ArtTools.getTool() + "\t\t\t");
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
    
    /**This method returns the 
     * current Canvas Tab
     * @return, CanvasTab
     */
    public static CanvasTabs getCurrentTab(){
        return (CanvasTabs)tabPane.getSelectionModel().getSelectedItem();
    }
    
    /**This method removes a tab
     * if a user exits it.
     */
    public static void removeTab(){
        tabPane.getTabs().remove(MyPaint.getCurrentTab());
    }
    
    public static Stage getStage(){return mainStage;}
}
