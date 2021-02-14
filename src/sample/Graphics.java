package sample;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;



public class Graphics extends Application {

    Canvas canvas;


    @Override
    public void start(Stage primaryStage){
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 500, 550);
        primaryStage.setScene(scene);
        primaryStage.show();

        mainView.drawSquare();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
