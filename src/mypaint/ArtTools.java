package mypaint;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;


public class ArtTools{
    protected static Logger logger;
    private static VBox lineWidth, artMenu, colors, tools, polyText, drawBox,
            shapeBox, realTimer, outlineBox;
    private static ToggleButton line, pencil, sqr, rect, ell, cir, rRect, poly,
            cGrab, eraser, text;
    private static HBox fBox, oBox, dTools, sTools, msTools, colorBox, 
            polyBox, widthBox, textTools, fontBox, capBox, dashBox;
    private static ToolBar art, display;
    private static Label fillLabel, outlineLabel, drawLabel, shapeLabel, 
            polyLabel, widthLabel, countdown, active, fontLabel, capLabel,
            dashLabel;
    private static ColorPicker outline, fill;
    private static Boolean gSelected, dSelected;
    private static Color grabbedColor;
    private static Button randomColor, swap;
    private static TextField type, polyN;
    private static ComboBox<Integer> width, polySides, fontSize;
    private static ComboBox<Double> dashes;
    private static ChoiceBox<String> lineCap;
    private static Random rand;
    private static Separator shapeSep, drawSep, split1, split2, split3, split4, split5;
    private static CheckBox fillOn;
    private static Tooltip lineTip, pencilTip, sqrTip, rectTip, rRectTip,
            ellTip, cirTip, polyTip, textTip, textfTip, widthTip, pSidesTip,
            eraserTip, rcTip, swapTip, grabTip, outlineTip, fillTip, fSizeTip,
            lCapTip, dashTip;
    private static String currentTool;
    final private static File lineIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Line Icon.png");
    final private static File freehandIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Pencil Icon.png");
    final private static File sqrIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Square Icon.png");
    final private static File rectIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Rectangle Icon.png");
    final private static File rRectIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Round Rectangle Icon.png");
    final private static File ellIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Ellipse Icon.png");
    final private static File cirIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Circle Icon.png");
    final private static File polyIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Polygon Icon.png");
    final private static File rcIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Random Color Icon.jpg");
    final private static File cgIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Color Grabber.png");
    final private static File swapIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Swap Color.jpg");
    final private static File eraserIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Eraser Icon.png");
    final private static File textIcon = new File("C:\\Users\\chris\\OneDrive\\Documents\\Valpo Semester 1 Stuff\\CS 250\\Labs\\Java\\Paint\\Button Icons\\Text Icon.png");
    
    
    public ArtTools(){
        logger = Logger.getLogger(this.getClass().getName());
        rand = new Random();
        shapeSep = new Separator();
        drawSep = new Separator();
        
        split1 = new Separator(Orientation.VERTICAL);
        split2 = new Separator(Orientation.VERTICAL);
        split3 = new Separator(Orientation.VERTICAL);
        split4 = new Separator(Orientation.VERTICAL);
        split5 = new Separator(Orientation.VERTICAL);
        
        
        art = new ToolBar();//creation of toolbar
           
        eraser = new ToggleButton();
        eraser.setGraphic(openImage(eraserIcon));
        
        //Split menu button for line tool
        drawLabel = new Label("     Drawing Tools");
        lineTip = new Tooltip("Tool that draws straight lines.");
        lineTip.setTextAlignment(TextAlignment.RIGHT);
        line = new ToggleButton();
        line.setGraphic(openImage(lineIcon));
        line.setTooltip(lineTip);
        
        pencilTip = new Tooltip("Tool that draws freehand lines.");
        pencilTip.setTextAlignment(TextAlignment.RIGHT);
        pencil = new ToggleButton();
        pencil.setGraphic(openImage(freehandIcon));
        pencil.setTooltip(pencilTip);
            
        dTools = new HBox(line, pencil, eraser);
        drawBox = new VBox(drawLabel, drawSep, dTools);
         
        widthTip = new Tooltip("Sets width for drawing tools.");
        widthTip.setTextAlignment(TextAlignment.RIGHT);
        widthLabel = new Label(" Outline Width");
        width = new ComboBox();
        width.setPromptText("Width");
        width.setPrefWidth(90);
        width.setEditable(true);
        width.getItems().addAll(1, 3, 5, 10, 15, 20, 25, 30, 50, 75, 100);
        width.setValue(1);
        width.setTooltip(widthTip);
        widthBox = new HBox(width, widthLabel);
        
        capLabel = new Label(" Line Cap");
        lCapTip = new Tooltip("Sets the cap of the line.");
        lineCap = new ChoiceBox<String>();
        lineCap.getItems().addAll("Square", "Round", "Butt");
        lineCap.setValue("Square");
        lineCap.setTooltip(lCapTip);
        lineCap.setPrefWidth(90);
        capBox = new HBox(lineCap, capLabel);
        
        dashLabel = new Label(" Outline Dash");
        dashTip = new Tooltip("Sets the dash on the outline of drawing tools.");
        dashes = new ComboBox();
        dashes.setPromptText("Outline Dash");
        dashes.setPrefWidth(90);
        dashes.setEditable(true);
        dashes.getItems().addAll(0d, 1d, 3d, 5d, 10d, 15d, 20d, 25d, 30d, 40d, 50d);
        dashes.setValue(0d);
        dashes.setTooltip(dashTip);
        dashBox = new HBox(dashes, dashLabel);
        
        outlineBox = new VBox(widthBox, capBox, dashBox);   
        
        
        shapeLabel = new Label("      Shape Tools");
        sqrTip = new Tooltip("Tool that draws squares.");
        sqrTip.setTextAlignment(TextAlignment.RIGHT);
        sqr = new ToggleButton();
        sqr.setTooltip(sqrTip);
        sqr.setGraphic(openImage(sqrIcon));
        
        rectTip = new Tooltip("Tool that draws rectangles.");
        rectTip.setTextAlignment(TextAlignment.RIGHT);
        rect = new ToggleButton();
        rect.setGraphic(openImage(rectIcon));
        rect.setTooltip(rectTip);
        
        rRectTip = new Tooltip("Tool that draws rounded rectangles.");
        rRectTip.setTextAlignment(TextAlignment.RIGHT);
        rRect = new ToggleButton();
        rRect.setGraphic(openImage(rRectIcon));
        rRect.setTooltip(rRectTip);
        
        ellTip = new Tooltip("Tool that draws ellipses.");
        ellTip.setTextAlignment(TextAlignment.RIGHT);
        ell = new ToggleButton();
        ell.setGraphic(openImage(ellIcon));
        ell.setTooltip(ellTip);
        
        cirTip = new Tooltip("Tool that draws circles.");
        cirTip.setTextAlignment(TextAlignment.RIGHT);
        cir = new ToggleButton();
        cir.setGraphic(openImage(cirIcon));
        cir.setTooltip(cirTip);
        
        polyTip = new Tooltip("Tool that draws squares.");
        polyTip.setTextAlignment(TextAlignment.RIGHT);
        poly = new ToggleButton();
        poly.setGraphic(openImage(polyIcon));
        poly.setTooltip(polyTip);
        
        sTools = new HBox(sqr, rect, rRect);
        msTools = new HBox(ell, cir, poly);
        shapeBox = new VBox(shapeLabel, shapeSep, sTools, msTools);
        
        
        //creation of color picker
        outlineTip = new Tooltip("Color picker for shape outlines.");
        outlineTip.setTextAlignment(TextAlignment.RIGHT);
        outline = new ColorPicker();
        outline.getStyleClass().add("split-button");
        outline.setPrefWidth(180);
        outline.setTooltip(outlineTip);
        outlineLabel = new Label(" Outline");
        oBox = new HBox(outline, outlineLabel);
        
        
        fillOn = new CheckBox("Fill");
        fillTip = new Tooltip("Color picker for shape fill.");
        fillTip.setTextAlignment(TextAlignment.RIGHT);
        fill = new ColorPicker();
        fill.getStyleClass().add("split-button");
        fill.setPrefWidth(160);
        fill.setTooltip(fillTip);
        fBox = new HBox(fill, fillOn);
        
        rcTip = new Tooltip("Random colors for fill and outline.");
        rcTip.setTextAlignment(TextAlignment.RIGHT);
        randomColor = new Button();
        randomColor.setPrefWidth(60);
        randomColor.setGraphic(openImage(rcIcon));
        randomColor.setTooltip(rcTip);
        
        
        polyLabel = new Label(" Polygon Sides");
        polyTip = new Tooltip("Sets amount of sides for polygon tool.");
        polyTip.setTextAlignment(TextAlignment.RIGHT);
        polySides = new ComboBox<Integer>();
        polySides.setEditable(true);
        polySides.setPrefWidth(75);
        polySides.getItems().addAll(3, 4, 5, 6, 7, 8, 9, 10);
        polySides.setPromptText("Sides");
        polySides.setValue(3);
        polySides.setTooltip(polyTip);
        polyBox = new HBox(polySides, polyLabel);
        
        
        fontLabel = new Label(" Font Size");
        textTip = new Tooltip("Enables text to be put on workspace.");
        textTip.setTextAlignment(TextAlignment.RIGHT);
        text = new ToggleButton();
        text.setGraphic(openImage(textIcon));
        text.setTooltip(textTip);
        fSizeTip = new Tooltip("Sets the size of your font for the text tool.");
        fontSize = new ComboBox<Integer>();
        fontSize.setEditable(true);
        fontSize.setPrefWidth(75);
        fontSize.getItems().addAll(8, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72);
        fontSize.setValue(12);
        fontSize.setTooltip(fSizeTip);
        fontBox = new HBox(fontSize, fontLabel);
        
        textfTip = new Tooltip("Type in anything to display on workspace.");
        textfTip.setTextAlignment(TextAlignment.RIGHT);
        type = new TextField("Type Here");
        type.setPrefWidth(130);
        type.setTooltip(textfTip);
        textTools = new HBox(text, type);
        
        tools = new VBox(polyBox, fontBox, textTools);
        
        
        grabTip = new Tooltip("Grabs color to replace fill and outline.");
        grabTip.setTextAlignment(TextAlignment.RIGHT);
        cGrab = new ToggleButton();
        cGrab.setPrefWidth(60);
        cGrab.setGraphic(openImage(cgIcon));
        cGrab.setTooltip(grabTip);
        
        
        swapTip = new Tooltip("Swaps fill and outline.");
        swapTip.setTextAlignment(TextAlignment.RIGHT);
        swap = new Button();
        swap.setPrefWidth(60);
        swap.setGraphic(openImage(swapIcon));
        swap.setTooltip(swapTip);
        
        
        colorBox = new HBox(randomColor, swap, cGrab);
        
        colors = new VBox(oBox, fBox, colorBox);
       
        realTimer = new VBox();
        
        currentTool = "None";
        active = new Label("Active Tool: " + currentTool + "\t\t\t");
        display = new ToolBar();
        display.getItems().addAll(active, realTimer);
        
        //main toolbar art adding all elements
        art.getItems().addAll(colors, split1, drawBox, split2, outlineBox, split3, shapeBox, split4, tools, split5, realTimer);
        artMenu = new VBox(art);
       
        
        line.setOnAction(l -> {
            toggleOff();
            line.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Line Tool";
            MyPaint.logPaint();
        });
        
        pencil.setOnAction(l -> {
            toggleOff();
            pencil.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Pencil Tool";
            MyPaint.logPaint();
        });
        
        sqr.setOnAction(l -> {
            toggleOff();
            sqr.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Square Tool";
            MyPaint.logPaint();
        });
        
        rect.setOnAction(l -> {
            toggleOff();
            rect.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Rectangle Tool";
            MyPaint.logPaint();
        });
        
        rRect.setOnAction(l -> {
            toggleOff();
            rRect.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Round Rectangle Tool";
            MyPaint.logPaint();
        });
        
        ell.setOnAction(l -> {
            toggleOff();
            ell.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Ellipse Tool";
            MyPaint.logPaint();
        });
        
        cir.setOnAction(l -> {
            toggleOff();
            cir.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Circle Tool";
            MyPaint.logPaint();
        });
        
        poly.setOnAction(l -> {
            toggleOff();
            poly.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Polygon Tool";
            MyPaint.logPaint();
        });
        
        eraser.setOnAction(l -> {
            toggleOff();
            eraser.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Eraser Tool";
            MyPaint.logPaint();
        });
        
        cGrab.setOnAction(l -> {
            toggleOff();
            cGrab.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Color Grabber";
            MyPaint.logPaint();
        });
        
        text.setOnAction(l -> {
            toggleOff();
            text.setSelected(true);
            MainLayout.getPaste().setDisable(true);
            currentTool = "Text Tool";
            MyPaint.logPaint();
        });
       
        
        randomColor.setOnAction(rc -> {
            fill.setValue(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            outline.setValue(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        });
        
        swap.setOnAction(sw -> {
            Color temp = fill.getValue();
            fill.setValue(outline.getValue()); 
            outline.setValue((Color) temp);
        });
        
    }
    
    
    /**This method takes a file to open an image to
     * the size of 25, 25 so it can be used as an icon
     * @param p, file object
     * @return an image view
     */
    private static ImageView openImage(File p){
        Image i = new Image(p.toURI().toString(), 25, 25, false, false);
        ImageView iv = new ImageView(i);
        return iv;
    }
    
    /**This method sets all toggle buttons and
     * check menu items to false
     */
    protected static void toggleOff(){
        line.setSelected(false);
        pencil.setSelected(false);
        sqr.setSelected(false);
        rect.setSelected(false);
        rRect.setSelected(false);
        ell.setSelected(false);
        cir.setSelected(false);
        poly.setSelected(false);
        cGrab.setSelected(false);
        text.setSelected(false);
        eraser.setSelected(false);
        MainLayout.getSelect().setSelected(false);
        MainLayout.getPaste().setSelected(false);
    }
    
    //Massive group of accessors
    public static VBox getTimer(){return realTimer;}
    public static CheckBox getFill(){return fillOn;}
    public static ComboBox<Integer> getPolySides(){return polySides;}
    public static ComboBox<Integer> getOutlineWidth(){return width;}
    public static ColorPicker getOutline(){return outline;}
    public static ColorPicker getFillColor(){return fill;}
    public static ToggleButton getLine(){return line;}
    public static ToggleButton getPencil(){return pencil;}
    public static ToggleButton getSquare(){return sqr;}
    public static ToggleButton getRectangle(){return rect;}
    public static ToggleButton getRoundRectangle(){return rRect;}
    public static ToggleButton getEllipse(){return ell;}
    public static ToggleButton getCircle(){return cir;}
    public static ToggleButton getPolygon(){return poly;}
    public static ToggleButton getColorGrabber(){return cGrab;}
    public static ToggleButton getText(){return text;}
    public static ToggleButton getEraser(){return eraser;}
    public static TextField getType(){return type;}
    public static VBox getMenu(){return artMenu;}
    public static ToolBar getDisplay(){return display;}
    public static Label getActive(){return active;}
    public static String getTool(){return currentTool;}
    public static ComboBox<Integer> getFontSize(){return fontSize;}
    public static ChoiceBox<String> getCap(){return lineCap;}
    public static ComboBox<Double> getDash(){return dashes;}
    //end of massive group of accessors
}