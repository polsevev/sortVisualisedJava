package sample;

import java.util.ArrayList;

public class InsertSort implements Runnable {


    private final MainView mainView;
    private final int speed;
    public InsertSort(MainView mainView){
        this.mainView = mainView;
        this.speed = mainView.getSpeed();
    }

    public void run() {
        try {
            insertSort(mainView.listOfIntegers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(mainView.checkArray(mainView.getListOfIntegers())){
            System.out.println("Sucess!");
        }else{
            System.out.println("Failure");
        }
        mainView.drawSquare();
        mainView.updateArrayAccesses();
    }


    public void insertSort(ArrayList<Integer> a) throws InterruptedException {
        int n = a.size();
        for (int i = 1; i < n; i++) {
            int currentNumber = a.get(i);
            mainView.incArrayAccess(1);
            int counter = i - 1;
            while(counter >= 0 && a.get(counter) > currentNumber){

                a.set(counter + 1, a.get(counter));

                mainView.incArrayAccess(3);
                counter -= 1;
                mainView.setListOfIntegers(a);
                mainView.clearCanvas();
                mainView.drawPillars(a);
                Thread.sleep(speed);

            }
            a.set(counter+1, currentNumber);
            mainView.incArrayAccess(1);


        }
        mainView.setListOfIntegers(a);
        mainView.drawSquare();
    }

}
