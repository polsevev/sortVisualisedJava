package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainView extends VBox {

    private Canvas canvas;
    private Button button;
    public ArrayList<Integer> listOfIntegers;
    GraphicsContext context;

    public MainView(){
        listOfIntegers = new ArrayList<>();

        for(int i = 1; i <= 50; i++){
            listOfIntegers.add(i);
        }
        Collections.shuffle(listOfIntegers);
        button = new Button();
        this.button.setOnAction(actionEvent -> {
            Simulation sim = new Simulation(this);
            sim.runSimulation();
        });
        canvas = new Canvas(500, 500);
        context = canvas.getGraphicsContext2D();
        this.getChildren().addAll(canvas, button);
    }

    public void drawSquare(){


        System.out.println(listOfIntegers.toString());
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
