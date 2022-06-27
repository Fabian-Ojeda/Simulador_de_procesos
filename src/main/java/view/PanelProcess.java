package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelProcess extends JPanel {

    private JPanel panelDetailsProcess;
    private JPanel panelProcessVariables;
    private JLabel labelName;
    private JLabel labelTimeLifeProcess;
    private JLabel labelRestTimeLife;
    private JLabel labelMaxTimeNextIO;
    private JLabel labelMaxTimeNextProcess;

    public PanelProcess(String name, int timeLife, int restLife) {

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20,20,0,20));
        this.initComponents(name, timeLife, restLife);
        this.setVisible(true);
    }

    private void initComponents (String name, int timeLife, int restLife){
        panelDetailsProcess = new JPanel();
        panelDetailsProcess.setBorder(new LineBorder(Color.BLACK,3, true));
        panelDetailsProcess.setLayout(new BorderLayout());
        labelName = new JLabel("Nombre del proceso: "+name);
        labelName.setBorder(new EmptyBorder(10,10,10,10));
        labelName.setFont(new Font("Abadi",Font.BOLD,16));
        panelDetailsProcess.add(labelName, BorderLayout.NORTH);

        panelProcessVariables = new JPanel();
        panelProcessVariables.setLayout(new BoxLayout(panelProcessVariables, BoxLayout.Y_AXIS));

        labelTimeLifeProcess = new JLabel("Tiempo de vida: "+timeLife);
        labelTimeLifeProcess.setFont(new Font("Abadi",Font.BOLD,14));
        labelTimeLifeProcess.setBorder(new EmptyBorder(0,10,0,0));
        panelProcessVariables.add(labelTimeLifeProcess);
        labelRestTimeLife = new JLabel("Tiempo restante de vida: "+restLife);
        labelRestTimeLife.setFont(new Font("Abadi",Font.BOLD,14));
        labelRestTimeLife.setBorder(new EmptyBorder(10,10,0,0));
        panelProcessVariables.add(labelRestTimeLife);
        panelDetailsProcess.add(panelProcessVariables, BorderLayout.CENTER);
        this.add(panelDetailsProcess, BorderLayout.CENTER);
    }

}
