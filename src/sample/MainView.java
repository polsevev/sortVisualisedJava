package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class MainView extends VBox {

    private Canvas canvas;
    public ArrayList<Integer> listOfIntegers;
    GraphicsContext context;

    public MainView(){
        listOfIntegers = new ArrayList<>();

        for(int i = 1; i <= 100; i++){
            listOfIntegers.add(i);
        }

        Collections.shuffle(listOfIntegers);

        Button button = new Button();
        button.setText("Run InsertSort");
        button.setOnAction(actionEvent -> {
            InsertSort ins = new InsertSort(this);
            Thread t = new Thread(ins);
            t.start();
        });

        Button button1 = new Button();
        button1.setText("Run QuickSort");
        button1.setOnAction(actionEvent -> {
            QuickSort quickSort = new QuickSort(this);
            Thread t = new Thread(quickSort);
            t.start();
        });

        Button button2 = new Button();
        button2.setText("Reset");
        button2.setOnAction(actionEvent -> {
            Collections.shuffle(listOfIntegers);
            clearCanvas();
            drawSquare();
        });

        canvas = new Canvas(1000, 1000);
        context = canvas.getGraphicsContext2D();
        this.getChildren().addAll(canvas, button, button1, button2);
    }

    public void drawSquare(){
        int counter = 0;
        for (Integer number: listOfIntegers) {
            context.setFill(Color.BLACK);
            context.fillRect(counter, canvas.getHeight()-(number*10), 10, number*10);
            context.setFill(Color.WHITE);
            context.fillRect(counter, 0, 1, canvas.getHeight());
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
        clearCanvas();
        drawSquare();
    }


}
