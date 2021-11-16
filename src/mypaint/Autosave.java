package mypaint;

import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javafx.application.*;
import javafx.embed.swing.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javax.imageio.*;




public class Autosave extends TimerTask{
    
    private int time;
    private File s; 
    private Label countdown;
    private CanvasTabs tab;
    final private static String autosaveDirectory = "C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Test Pics\\";
   
    
    public Autosave(){
        Platform.runLater(() -> {
            time = setTimer();
        });
    }
    
    public void run(){
        Platform.runLater(() -> {
            countdown = new Label("Autosave in: " + time + " seconds");
            ArtTools.getTimer().getChildren().setAll(countdown);
            if (MainLayout.getAutosave().isSelected()){
                countdown.setVisible(true);
            }else{
                countdown.setVisible(false);
            }
        });
        time--;
        
        
        if (time == 0){
            Platform.runLater(() -> {
                try{
                    s = new File(autosaveDirectory + "Paint Autosave.png");
                    WritableImage autoImage = new WritableImage((int) tab.getCanvas().getWidth(), (int) tab.getCanvas().getHeight());
                    tab.getCanvas().snapshot(null, autoImage);
                    RenderedImage ri = SwingFXUtils.fromFXImage(autoImage, null);
                    ImageIO.write(ri, "png", s);
                } catch (IOException ex) {
                    Logger.getLogger(MyPaint.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Platform.runLater(() -> {
                time = setTimer();
            });
        }
    }
    
    /**This method checks the input from the timer
     * options and sets t to it based on what is selected.
     * @return time
     */
    private int setTimer(){
        int t = 0;
        if (MainLayout.getTimes()[0].isSelected()){
            t = 30;
        }else if (MainLayout.getTimes()[1].isSelected()){
            t = 60;
        }else if (MainLayout.getTimes()[2].isSelected()){
            t = 90;
        }else if (MainLayout.getTimes()[3].isSelected()){
            t = 120;
        }else if (MainLayout.getTimes()[4].isSelected()){
            t = 300;
        }else if (MainLayout.getTimes()[5].isSelected()){
            t = 600;
        }else if (MainLayout.getTimes()[6].isSelected()){
            t = 900;
        }else if (MainLayout.getTimes()[7].isSelected()){
            t = 1200;
        }else if (MainLayout.getTimes()[8].isSelected()){
            t = 1500;
        }else if (MainLayout.getTimes()[9].isSelected()){
            t = 1800;
        }
        return t;
    }
}
