package model;

import view.MainView;

import java.util.ArrayList;

public class Scheduler  extends Thread{

    private Cronometer cronometer;
    private int quantum;
    private int iteratorProcessCreated;
    private MyProcess actualProcess;
    private ArrayList<MyProcess> arrayProcessList;
    private ArrayList<MyProcess> arrayProcessBlock;
    private MainView mainView;
    private int execution;
    private ArrayList<Integer> arrayIO;
    private boolean searchingIO;
    private boolean indicatorMainThead;
    private boolean indicatorReducer;
    private boolean indicatorDemon;

    public Scheduler(MainView mainView, int quantum) {
        indicatorMainThead = true;
        indicatorReducer = true;
        indicatorDemon = true;
        execution=0;
        searchingIO=false;
        cronometer = new Cronometer(mainView);
        cronometer.start();
        this.mainView = mainView;
        this.quantum=quantum;
        mainView.setQuantum(quantum);
        iteratorProcessCreated = 1;
        arrayProcessList = new ArrayList<>();
        arrayProcessBlock = new ArrayList<>();
        MyProcess p = new MyProcess("Proceso bandera", 10, 5);
        arrayProcessList.add(p);
        executeProcess();
        analizeIO();
        generateIODemon();
    }

    public void createProcess(){
        int timeLife = (int) (Math.random()*19)+15;
        int nextIO = (int) (Math.random()*5)+1;
        MyProcess p = new MyProcess("Proceso "+iteratorProcessCreated, timeLife, nextIO);
        if (actualProcess==null){
            actualProcess=p;
        }else {
        arrayProcessList.add(p);
        }
        iteratorProcessCreated++;
    }

    public void sendDataToMainWindow(){
        mainView.setListProcess(arrayProcessList);
    }

    @Override
    public void run() {

        mainView.setActualProcess(actualProcess);
        actualProcess.analizeDependingIO();
        while (indicatorMainThead){
            if (execution>0){
                this.endQuantum();
            }
            execution++;
            try {
                Thread.sleep( quantum*1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private void endQuantum(){
        if(actualProcess.isIoDependence()){
            System.out.println("El proceso "+actualProcess.getName()+" pasa a bloqueado");
            arrayProcessBlock.add(actualProcess);
        }else {
            arrayProcessList.add(actualProcess);
        }
        try {
            actualProcess=arrayProcessList.get(0);
            actualProcess.analizeDependingIO();
            searchingIO=false;
            arrayProcessList.remove(0);
            updateBoard();
        } catch (IndexOutOfBoundsException e){
            System.out.println("se acabo la simulación");
            stopThreads();
        }

    }

    private void updateBoard() {
        mainView.setActualProcess(actualProcess);
        mainView.setListProcess(arrayProcessList);
        mainView.setListBlock(arrayProcessBlock);
    }

    private void executeProcess(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (indicatorReducer){
                    try {
                        Thread.sleep( 1000);
                        actualProcess.reduceTimeLife(1);
                        if(!actualProcess.verifyAlive()){
                            stopAndDeleteProcess();
                        }

                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();
    }

    private void stopAndDeleteProcess(){
        System.out.println("el proceso "+actualProcess.getName()+" ha finalizado");
        mainView.setProcessEnded(actualProcess.getName());
        try{
            actualProcess=arrayProcessList.get(0);
            arrayProcessList.remove(0);
            updateBoard();
        }catch (IndexOutOfBoundsException e){
            System.out.println("se acabo esta vuelta");
            stopThreads();
        }
    }

    private void stopThreads(){
        indicatorMainThead=false;
        indicatorReducer=false;
        indicatorDemon=false;
        cronometer.close();
    }

    private void analizeIO(){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep( 1000);
                        if(!searchingIO){
                            if (actualProcess.isIoDependence()){
                                searchingIO=true;
                                generateIO();
                            }
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();
    }

    private void generateIO(){
        //System.out.println("tenemos "+actualProcess.getMaxIO()+" segundos para generar una entrada para "+actualProcess.getName());
        Thread t = new Thread(new Runnable() {
            int max = actualProcess.getMaxIO();
            int count = 0;
            @Override
            public void run() {
                while (count<max){
                    count++;
                    try {
                        Thread.sleep( 1000);
                        int io = (int) (Math.random() * 9) + 1;
                        if (io>7){
                            //System.out.println("Se ha generado una entrada para "+actualProcess.getName());
                            count=max;
                            actualProcess.setIO(1);
                        }else {
                            //System.out.println("Sin entrada para "+actualProcess.getName());
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();
    }

    private void generateIODemon(){
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep( 1000);
                        if (!arrayProcessBlock.isEmpty()) {
                            int io = (int) (Math.random() * 9) + 1;
                            if (io>7){
                                int quantityBlock = arrayProcessBlock.size();
                                int randomIO = (int) (Math.random() * quantityBlock) + 1;
                                arrayProcessBlock.get(randomIO-1).setIO(2);
                                arrayProcessList.add(arrayProcessBlock.get(randomIO-1));
                                System.out.println("El proceso demonio ha generado la I/O del proceso "+arrayProcessBlock.get(randomIO-1).getName());
                                arrayProcessBlock.remove(randomIO-1);
                                updateBoard();
                                mainView.rePaintBloqued();
                            }else {
                            }
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();
    }

}
