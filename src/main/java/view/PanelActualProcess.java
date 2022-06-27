package view;

import model.MyProcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelActualProcess extends JPanel {

    private JPanel mainPanel;
    private JPanel panelDetailsProcess;
    private JPanel panelContDetailsProcess;
    private JPanel panelProcessVariables;
    private JLabel labelActualProcess;
    private JLabel labelNameProcess;
    private JLabel labelTimeLifeProcess;
    private JLabel labelRestTimeLife;
    private JLabel labelMaxTimeNextIO;
    private JLabel labelMaxTimeNextProcess;
    private int secondsTimeLifeProcess;

    public PanelActualProcess() {
        this.setPreferredSize(new Dimension(130, 50));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10,20,10,20));
        this.initComponents();
        this.setVisible(true);
        secondsTimeLifeProcess=0;
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new LineBorder(Color.BLACK, 3, true));
        labelActualProcess= new JLabel("Proceso actual");
        labelActualProcess.setFont(new Font("Abadi",Font.BOLD,18));
        labelActualProcess.setBorder(new EmptyBorder(10,30,10,0));
        mainPanel.add(labelActualProcess,BorderLayout.NORTH);
        //-----------------Panel con los detalles del proceso-----------------
        panelContDetailsProcess = new JPanel();
        panelContDetailsProcess.setLayout(new BorderLayout());
        panelContDetailsProcess.setBorder(new EmptyBorder(20,20,340,20));
        panelDetailsProcess = new JPanel();
        panelDetailsProcess.setBorder(new LineBorder(Color.BLACK,3, true));
        panelDetailsProcess.setLayout(new BorderLayout());
        labelNameProcess = new JLabel("");
        labelNameProcess.setBorder(new EmptyBorder(10,10,10,10));
        labelNameProcess.setFont(new Font("Abadi",Font.BOLD,16));
        panelDetailsProcess.add(labelNameProcess, BorderLayout.NORTH);
        panelProcessVariables = new JPanel();
        panelProcessVariables.setLayout(new BoxLayout(panelProcessVariables, BoxLayout.Y_AXIS));
        labelTimeLifeProcess = new JLabel("");
        labelTimeLifeProcess.setFont(new Font("Abadi",Font.BOLD,14));
        labelTimeLifeProcess.setBorder(new EmptyBorder(0,10,0,0));
        panelProcessVariables.add(labelTimeLifeProcess);
        labelRestTimeLife = new JLabel("");
        labelRestTimeLife.setFont(new Font("Abadi",Font.BOLD,14));
        labelRestTimeLife.setBorder(new EmptyBorder(10,10,0,0));
        panelProcessVariables.add(labelRestTimeLife);
        labelMaxTimeNextIO = new JLabel("");
        labelMaxTimeNextIO.setFont(new Font("Abadi",Font.BOLD,14));
        labelMaxTimeNextIO.setBorder(new EmptyBorder(10,10,0,0));
        panelProcessVariables.add(labelMaxTimeNextIO);
        labelMaxTimeNextProcess = new JLabel("Maximo tiempo para el siguiente proceso: 15s");
        labelMaxTimeNextProcess.setFont(new Font("Abadi",Font.BOLD,14));
        labelMaxTimeNextProcess.setBorder(new EmptyBorder(10,10,0,0));
        panelProcessVariables.add(labelMaxTimeNextProcess);


        panelDetailsProcess.add(panelProcessVariables, BorderLayout.CENTER);




        panelContDetailsProcess.add(panelDetailsProcess, BorderLayout.CENTER);
        mainPanel.add(panelContDetailsProcess, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public void setActualProcess(MyProcess p){
        secondsTimeLifeProcess=p.getTimeLifeResting();
        labelNameProcess.setText(p.getName());
        labelTimeLifeProcess.setText("Tiempo de vida: "+p.getTimeLife()+"s");
        labelRestTimeLife.setText("Tiempo restate de vida: "+secondsTimeLifeProcess+"s");
        labelMaxTimeNextIO.setText("Maximo tiempo para la proxima entrada y salida: "+p.getMaxIO()+"s");
    }
    public void reduceTime(){
        secondsTimeLifeProcess--;
        labelRestTimeLife.setText("Tiempo restate de vida: "+secondsTimeLifeProcess+"s");
    }
}
