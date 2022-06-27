package controller;

import model.Cronometer;
import model.Scheduler;
import view.InterfaceParameters;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class ControllerApp implements ActionListener {

    private InterfaceParameters interfaceParameters;
    private MainView mainView;
    private Scheduler scheduler;

    public ControllerApp() {
        interfaceParameters = new InterfaceParameters(this);
        /**/
    }

    private void initSimulation(int numberProcess, int quantum){
        mainView=new MainView(this);
        scheduler = new Scheduler(mainView, quantum);

        for (int i = 0; i <numberProcess ; i++) {
            scheduler.createProcess();
        }
        scheduler.sendDataToMainWindow();
        scheduler.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.valueOf(e.getActionCommand())){
            case C_START_SIMULATION:
                initSimulation(interfaceParameters.getNumberProcess(),interfaceParameters.getQuantum());
                /*int quantum = interfaceParameters.getQuantum();
                int numberProcess = interfaceParameters.getNumberProcess();
                System.out.println("Tenemos "+quantum+" de quantum y "+numberProcess+ " procesos");*/

                break;
            default:
                break;
        }
    }
}
