package sample;

import java.util.ArrayList;
import java.util.Random;

public class Simulation implements Runnable {


    private final MainView mainView;
    public Simulation(MainView mainView){
        this.mainView = mainView;
    }

    public void runSimulation() {
        Thread t = new Thread(this);
        t.start();

    }

    public void run() {
        try {
            insertSort(mainView.listOfIntegers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Integer> insertSort(ArrayList<Integer> a) throws InterruptedException {
        Integer n = a.size();
        for (int i = 1; i < n; i++) {
            int currentNumber = a.get(i);
            int counter = i - 1;
            while(counter >= 0 && a.get(counter) > currentNumber){
                a.set(counter + 1, a.get(counter));

                counter -= 1;
                this.updateCanvas(a);
                Thread.sleep(10);

            }
            a.set(counter+1, currentNumber);


        }
        return a;
    }

    public void updateCanvas(ArrayList<Integer> listOfInts){
        mainView.setListOfIntegers(listOfInts);
    }
}
