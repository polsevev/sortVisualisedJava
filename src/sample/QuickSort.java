package sample;

import java.util.ArrayList;
import java.util.Random;

public class QuickSort implements Runnable{

    private final MainView mainView;
    public QuickSort(MainView mw){
        this.mainView = mw;
    }
    public void run(){
        try {
            quickSort(mainView.getListOfIntegers(), 0, mainView.getListOfIntegers().size()-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void quickSort(ArrayList<Integer> a, int low, int high) throws InterruptedException {
        if(low >= high){
            return;
        }
        int p = a.get(new Random().nextInt(high));
        mainView.incArrayAccess(1);
        int index = partition(a, low, high, p);
        quickSort(a, low, index-1);
        quickSort(a, index, high);
    }

    private Integer partition(ArrayList<Integer> a, int low, int high, int p) throws InterruptedException {
        while(low <= high){
            while(a.get(low) < p){
                mainView.incArrayAccess(1);
                low++;
            }
            while(a.get(high) > p){
                mainView.incArrayAccess(1);
                high--;
            }
            if (low <= high) {
                int temp = a.get(high);
                a.set(high, a.get(low));
                a.set(low, temp);
                mainView.incArrayAccess(4);
                low++;
                high--;
                mainView.setListOfIntegers(a);
                int speed = mainView.getSpeed();
                Thread.sleep(speed);
            }

        }
        return low;
    }
}
