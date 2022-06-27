package model;

public class MyProcess {

    private String name;
    private char status;
    private int timeLife;
    private int timeLifeResting;
    private int maxIO;
    private boolean IoDependence;

    public MyProcess(String name, int timeLife, int maxIO) {
        this.name = name;
        this.status = 'r';
        this.timeLife = timeLife;
        this.timeLifeResting = timeLife;
        this.maxIO=maxIO;
        this.IoDependence = false;
    }

    public String getName() {
        return this.name;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public int getMaxIO() {
        return maxIO;
    }

    public int getTimeLifeResting() {
        return timeLifeResting;
    }

    public void reduceTimeLife(int quantum){
        this.timeLifeResting =this.timeLifeResting-quantum;
    }

    public boolean verifyAlive(){
        if(timeLifeResting==0){
            return false;
        }
        return true;
    }

    public void analizeDependingIO(){
        int depending = 0;
        if (!IoDependence) {
            depending = (int) (Math.random() * 2) + 1;
            if (depending>1){
                IoDependence = true;
                System.out.println("desde el proceso: "+this.name+" dependo de una I/O");
            }
        }
    }

    public boolean isIoDependence() {
        return IoDependence;
    }

    public void setIO(int origin){
        this.IoDependence=false;
        if (origin==1){
            System.out.println("desde el proceso: "+this.name+" mi I/O ha sido suplida dentro del quantum");
        }else {
            System.out.println("desde el proceso: "+this.name+" mi I/O ha sido suplida por el demonio");
        }
    }
}
