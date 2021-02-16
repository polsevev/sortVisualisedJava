package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Start extends Application {


    @Override
    public void start(Stage primaryStage){
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 1000, 1200);
        primaryStage.setScene(scene);
        primaryStage.show();

        mainView.drawSquare();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
