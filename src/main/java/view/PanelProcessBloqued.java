package view;

import model.MyProcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelProcessBloqued extends JPanel {

    private JPanel mainPanel;
    private JPanel panelList;
    private JLabel labelTitle;

    public PanelProcessBloqued(){
        this.setPreferredSize(new Dimension(130, 50));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10,20,10,20));
        this.initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new LineBorder(Color.BLACK, 3, true));
        labelTitle = new JLabel("Cola de procesos bloqueados");
        labelTitle.setFont(new Font("Abadi",Font.BOLD,18));
        labelTitle.setBorder(new EmptyBorder(10,30,10,0));
        mainPanel.add(labelTitle,BorderLayout.NORTH);
        panelList = new JPanel();
        panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

        mainPanel.add(panelList, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public void setBlockProcess(ArrayList<MyProcess> arrayListProcess){
        panelList.removeAll();
        //System.out.println("Procesos bloqueados actuales: "+arrayListProcess.size());
        for (MyProcess iterator:arrayListProcess){
            PanelProcess p = new PanelProcess(iterator.getName(), iterator.getTimeLife(), iterator.getTimeLifeResting());
            panelList.add(p);
        }
    }

    public void rePaintBloqued(){
        panelList.repaint();
    }
}
