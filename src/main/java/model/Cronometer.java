package model;

import view.MainView;

public class Cronometer  extends Thread{
    MainView mainView;
    private boolean indicator;
    public Cronometer(MainView mainView) {
        this.mainView = mainView;
        indicator=true;
    }

    @Override
    public void run() {


        while (indicator){
            mainView.addSecond();
            try {
                Thread.sleep( 1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void close(){
        indicator=false;
    }
}
