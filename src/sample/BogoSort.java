package sample;

import java.util.ArrayList;
import java.util.Collections;

public class BogoSort implements Runnable {


    private final MainView mainView;
    private final int speed;
    public BogoSort(MainView mainView){
        this.mainView = mainView;
        this.speed = mainView.getSpeed();
    }

    public void run() {

        try {
            bogoSort(mainView.listOfIntegers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(mainView.checkArray(mainView.getListOfIntegers())){
            System.out.println("Sucess!");
        }else{
            System.out.println("Failure");
        }
        mainView.drawSquare();
    }


    public void bogoSort(ArrayList<Integer> a) throws InterruptedException {
        while (!mainView.checkArray(a)){
            Collections.shuffle(a);
            mainView.incArrayAccess(a.size());
            mainView.setListOfIntegers(a);
            mainView.clearCanvas();
            mainView.drawPillars(a);
            Thread.sleep(speed);
        }

    }

}
