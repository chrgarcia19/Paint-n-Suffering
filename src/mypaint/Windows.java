package mypaint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Windows {
    private static Separator split = new Separator(Orientation.HORIZONTAL);

    /**This method creates a confirmation alert when the
     * user tries to exit the program.
     * @param primaryStage, to allow for new window to open
     */
    public static void exitPaint(Stage primaryStage){
        ButtonType[] exit = new ButtonType[] {new ButtonType("Yes"), new ButtonType("Save"), new ButtonType("No")};
        Alert exitPaint = new Alert(Alert.AlertType.CONFIRMATION, "Exit Paint n' Suffering?", exit[0],
                exit[1], exit[2]);
        exitPaint.setTitle("Exit Paint n' Suffering?");
        primaryStage.setOnCloseRequest(psc -> {
            psc.consume();
            ButtonType next = exitPaint.showAndWait().get();
            if(next == exit[0]){
                primaryStage.close();
                Platform.exit();
                System.exit(0);
            }else if (next == exit[1]){
                MainLayout.save();
                primaryStage.close();
                Platform.exit();
                System.exit(0);
            }else if (next == exit[2]){
                psc.consume(); 
            }
        });
    }
    
    /**This method creates a window if a tab is 
     * attempted to be closed. 
     * @param changesMade, edits made on the canvas
     * @param c, event passed from tab closing event
     */
    public static void exitTab(Boolean changesMade, Event c){
        if (changesMade){
            ButtonType[] quit = new ButtonType[] {new ButtonType("Cancel"),
            new ButtonType("Save"), new ButtonType("Close")};
            Alert tabExit = new Alert(Alert.AlertType.WARNING, "Do you wish to close this tab?", 
            quit[0], quit[1], quit[2]);
            tabExit.setTitle("Close tab?");
            ButtonType next = tabExit.showAndWait().get();
            if(next == quit[0]){
                c.consume();
            }else if(next == quit[1]){
                MainLayout.save();
            }else if(next == quit[2]){
                MyPaint.removeTab();
            }
        }else{
            MyPaint.removeTab();
        }
    }
    
    /**This method takes the type and creates a window
     * based on the specified type
     * @param type, for conditional
     */
    public static void popupWindow(String type){
        Stage window = new Stage();
        Label prompt;
        Button button1 = new Button(), button2 = new Button(), 
                button3 = new Button();
        File text = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\Paint Help.txt");
        HBox holdButton, holdLabel;
        VBox contents = new VBox();
        ScrollPane pane = new ScrollPane();
        String[] info = windowInfo(type);
        if (type == "Exit"){
            window.setTitle(info[0]);
            window.setHeight(100);
            window.setWidth(340);
            prompt = new Label(info[1]);
            button1.setText(info[3]);
            button2.setText(info[4]);
            button3.setText(info[2]);
            holdLabel = new HBox(prompt);
            if (CanvasTabs.getChanges()){
                button1.setPrefWidth(165);
                button2.setPrefWidth(165);
                button3.setPrefWidth(165);
                holdButton = new HBox(button1, button2, button3);
            }else{ //if no changes are made
                button1.setPrefWidth(247.5);
                button3.setPrefWidth(247.5);
                holdButton = new HBox(button1, button3);
            }
            contents.getChildren().addAll(holdLabel, holdButton);
            window.setScene(new Scene(contents));
        }else if (type == "Help" || type == "Release Notes" || type == "About"){
            window.setTitle(info[0]);
            window.setWidth(342);
            if (type != "About"){
                window.setHeight(575);
            }else{
                window.setHeight(467);
            }
            button1.setText(info[2]);
            button1.setPrefWidth(305);
            if (type == "Help"){
                text = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\Paint Help.txt");
            }else if (type == "Release Notes"){
                text = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\Paint Release Notes.txt");
            }else if (type == "About"){
                text = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Text Files\\Paint About.txt");
            }
            
        }
        readText(text, contents);
        pane.setContent(contents);
        contents.getChildren().addAll(split, button1);      
        window.setScene(new Scene(pane));
        window.show();
        button1.setOnAction(b1 -> {
            if (type == "Exit"){
                window.close();
            }else if (type == "Help" || type == "Release Notes" || type == "About"){
                window.close();
            }
        });
        button2.setOnAction(b2 -> {
            if (type == "Exit"){
                MainLayout.normalSave(CanvasTabs.getCanvas());
                window.close();
                MyPaint.getStage().close();
            }
        });
        button3.setOnAction(b3 -> {
            window.close();
            MyPaint.getStage().close();
        });
    }
    
    /**
     * 
     * @param type, for info for each type of window
     * @return info, to be filled in creation of the window
     */
    private static String[] windowInfo(String type){
        String[] info = new String[] {"Title", "Label", "Button 1",
            "Button 2", "Button 3"}; 
        if (type == "Exit"){
            info[0] = "Close Program";
            info[1] = "  Do you wish to exit?";
            info[2] = "Exit";
            info[3] = "Cancel";
            info[4] = "Save and Exit";
        }else if (type == "Help"){
            info[0] = "Help";
            info[2] = "Return to Program";
        }else if (type == "Release Notes"){
            info[0] = "Release Notes";
            info[2] = "Return to Program";
        }else if (type == "About"){
            info[0] = "About";
            info[2] = "Return to Program";
        }
        return info;
    }
    
    /**This method uses the scanner to read a text file
    * and converts it to text to be displayed in a window
    * @param f, File object
    * @param w, Vbox for text to be displayed 
    */
    private static void readText(File f, VBox w){
       Scanner console = null;
       try {
           //sets scanner to scan file contents
           console = new Scanner(f);
       } catch (FileNotFoundException ex) {
           Logger.getLogger(MyPaint.class.getName()).log(Level.SEVERE, null, ex);
       }
       //text to display on screen
       Text text = new Text();
       //loop to convert string to text
       while(console.hasNextLine()){
           text.setText(text.getText() + console.nextLine() + "\n");
       }
       w.getChildren().add(text);
    }
}

