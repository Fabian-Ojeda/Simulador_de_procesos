package view;

import controller.ControllerApp;
import model.MyProcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    private JLabel labelTitle;
    private JLabel labelQuantum;
    private JLabel labelTimeSimulation;
    private JLabel labelProcessEnded;
    private PanelActualProcess panelActualProcess;
    private PanelProcessList panelProcessList;
    private PanelProcessBloqued panelProcessBloqued;
    private Dimension screenSize;
    private JPanel panelUp;
    private JPanel panelMedium;
    private JPanel panelUpMed;
    private JPanel panelMediumMedium;
    private JPanel panelMediumDown;
    private int minutes;
    private int seconds;

    public MainView(ControllerApp controllerApp) {
        this.setLayout(new BorderLayout());
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Simulador de procesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents(controllerApp);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(ControllerApp controllerApp) {
        minutes=0;
        seconds=0;
        panelUp = new JPanel();
        panelUp.setBorder(new EmptyBorder(15,0,0,0));
        labelTitle = new JLabel("Simulador de procesos", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Abadi",Font.BOLD,25));
        panelUp.add(labelTitle);
        this.add(panelUp, BorderLayout.NORTH);
        panelMedium = new JPanel();
        panelMedium.setLayout(new BorderLayout());
        panelMedium.setBorder(new EmptyBorder(20,20,20,20));
        panelUpMed = new JPanel();
        panelUpMed.setLayout(new BoxLayout(panelUpMed, BoxLayout.X_AXIS));
        panelUpMed.setBorder(new EmptyBorder(20,20,20,20));
        labelQuantum = new JLabel("");
        labelQuantum.setFont(new Font("Abadi",Font.BOLD,20));
        panelUpMed.add(labelQuantum);
        labelTimeSimulation = new JLabel("Tiempo de simulación: "+minutes+":"+seconds);
        labelTimeSimulation.setBorder(new EmptyBorder(0, (int) (screenSize.width*0.7),0,0));
        labelTimeSimulation.setFont(new Font("Abadi",Font.BOLD,20));
        panelUpMed.add(labelTimeSimulation);
        panelMedium.add(panelUpMed, BorderLayout.NORTH);
        panelMediumMedium = new JPanel();
        panelMediumMedium.setLayout(new BoxLayout(panelMediumMedium, BoxLayout.X_AXIS));
        panelActualProcess = new PanelActualProcess();
        panelMediumMedium.add(panelActualProcess);
        panelProcessList = new PanelProcessList();
        panelMediumMedium.add(panelProcessList);
        panelProcessBloqued = new PanelProcessBloqued();
        panelMediumMedium.add(panelProcessBloqued);
        panelMedium.add(panelMediumMedium, BorderLayout.CENTER);
        panelMediumDown = new JPanel();
        panelMediumDown.setLayout(new BoxLayout(panelMediumDown, BoxLayout.X_AXIS));
        panelMediumDown.setBorder(new EmptyBorder(10,10,10,10));
        labelProcessEnded = new JLabel("Procesos terminados: ");
        labelProcessEnded.setFont(new Font("Abadi",Font.BOLD,20));
        panelMediumDown.add(labelProcessEnded);
        panelMedium.add(panelMediumDown, BorderLayout.SOUTH);
        this.add(panelMedium, BorderLayout.CENTER);
    }

    public void addSecond(){
        seconds++;
        if(seconds==60){
            minutes++;
            seconds=0;
        }
        if(seconds<10){
            labelTimeSimulation.setText("Tiempo de simulación: "+minutes+":"+"0"+seconds);
        }else {
            labelTimeSimulation.setText("Tiempo de simulación: " + minutes + ":" + seconds);
        }
        panelActualProcess.reduceTime();
    }

    public void setQuantum(int quantum){
        labelQuantum.setText("Quantum: "+quantum+"s");
    }

    public void setActualProcess(MyProcess p){
        panelActualProcess.setActualProcess(p);
    }

    public void setListProcess(ArrayList<MyProcess> arrayListProcess){
        panelProcessList.setListProcess(arrayListProcess);
    }

    public void setListBlock(ArrayList<MyProcess> arrayProcessBlock) {
        panelProcessBloqued.setBlockProcess(arrayProcessBlock);
    }

    public void setProcessEnded(String processEnded){
        labelProcessEnded.setText(labelProcessEnded.getText()+" "+processEnded);
    }

    public void rePaintBloqued(){
        panelProcessBloqued.rePaintBloqued();
    }
    public void rePaintList(){
        panelProcessList.rePaintList();
    }

    public String getTime(){
        if(seconds<10){
            return minutes+":0"+seconds;
        }
        return minutes+":"+seconds;
    }
}
