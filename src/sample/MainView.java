package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainView extends VBox {

    private final Canvas canvas;
    public ArrayList<Integer> listOfIntegers;
    public HashMap<Integer, Color> listOfColors;
    GraphicsContext context;
    public Integer arrayAccesses = 0;
    public Text arrayAccessesText;
    Thread currentlyRunning;
    private int speed = 10;
    private Double canvasHeight;

    public MainView(){

        listOfIntegers = new ArrayList<>();
        listOfColors = new HashMap<>();
        arrayAccessesText = new Text();
        arrayAccessesText.setText("Array accesses: " + arrayAccesses.toString());
        currentlyRunning = new Thread();

        for(int i = 1; i <= 100; i++){
            listOfIntegers.add(i);
            listOfColors.put(i, Color.rgb(0, i, i));
        }

        Collections.shuffle(listOfIntegers);

        Button button = new Button();
        button.setText("Run InsertSort");
        button.setOnAction(actionEvent -> {
            InsertSort ins = new InsertSort(this);
            Thread t1 = new Thread(ins);
            currentlyRunning = t1;
            t1.start();
        });

        Button button1 = new Button();
        button1.setText("Run QuickSort");
        button1.setOnAction(actionEvent -> {
            QuickSort quickSort = new QuickSort(this);
            Thread t = new Thread(quickSort);
            currentlyRunning = t;
            t.start();
        });
        Button button4 = new Button();
        button4.setText("Run Bogosort");
        button4.setOnAction(actionEvent -> {
            BogoSort bogo = new BogoSort(this);
            Thread t2 = new Thread(bogo);
            currentlyRunning = t2;
            t2.start();
        });


        Button button2 = new Button();
        button2.setText("Reset");
        button2.setOnAction(actionEvent -> {
            if(currentlyRunning.isAlive()){
                currentlyRunning.stop();
            }
            arrayAccesses = 0;
            listOfIntegers = makeNewList();
            Collections.shuffle(listOfIntegers);
            clearCanvas();
            drawSquare();
        });

        TextField textField = new TextField();

        Button button3 = new Button();
        button3.setText("Update speed (lower is faster)");
        button3.setOnAction(actionEvent -> {
            String theIn = textField.getCharacters().toString();
            if (theIn.matches("[0-9]+") && theIn.length() > 0) {
                speed = Integer.parseInt(theIn);
            }
        });
        canvas = new Canvas(1000, 500);
        context = canvas.getGraphicsContext2D();
        canvasHeight = canvas.getHeight();
        this.getChildren().addAll(canvas, button, button1, button4, button2, arrayAccessesText, textField, button3);
    }

    public void drawSquare(){
        arrayAccessesText.setText("Array accesses: " + arrayAccesses.toString());
        int counter = 0;
        for (Integer number: listOfIntegers) {
            context.setFill(listOfColors.get(number));
            context.fillRect(counter, canvasHeight-(number*5), 10, number*10);
            counter +=10;
        }
    }

    public void drawPillars(ArrayList<Integer> a ){

        arrayAccessesText.setText("Array accesses: " + arrayAccesses.toString());
        int counter = 0;
        for (Integer number: a) {
            context.setFill(listOfColors.get(number));
            context.fillRect(counter, canvasHeight-(number*5), 10, number*10);
            counter +=10;
        }
    }

    public void clearCanvas(){
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public ArrayList<Integer> getListOfIntegers(){
        return this.listOfIntegers;
    }
    public void setListOfIntegers(ArrayList<Integer> listOfInts){
        listOfIntegers = listOfInts;

    }

    public void incArrayAccess(int number){
        this.arrayAccesses+=number;
    }

    public int getSpeed(){
        if(this.speed < 10){
            return 10;
        }
        return this.speed;
    }

    public Boolean checkArray(ArrayList<Integer> a){
        Integer lastNumber = 0;
        for (Integer number: a) {
            if(number <= lastNumber){
                return false;
            }
            lastNumber = number;
        }
        return true;
    }

    public ArrayList<Integer> makeNewList(){
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            a.add(i);
        }
        return a;
    }

}
