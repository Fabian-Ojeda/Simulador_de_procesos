package view;

import controller.Commands;
import controller.ControllerApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceParameters extends JFrame {
    private Dimension screenSize;
    private JPanel panelTitle;
    private JPanel panelInfo;
    private JPanel panelQuantityProcess;
    private JPanel mainPanel;
    private JPanel panelQuantum;
    private JPanel panelButton;
    private JLabel labelTitle;
    private JTextArea textAreaInfo;
    private JLabel labelQuantum;
    private JLabel labelQuantityProcess;
    private JTextField textFieldQuantum;
    private JTextField textFieldNumberProcess;
    private JButton buttonAccept;
    public InterfaceParameters(ControllerApp controllerApp){
        this.setLayout(new BorderLayout());
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Simulador de procesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents(controllerApp);
        this.setPreferredSize(new Dimension((int)(screenSize.width*0.3), (int) (screenSize.height*0.6)));
        this.getContentPane().setBackground(Color.WHITE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents(ControllerApp controllerApp) {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        panelTitle = new JPanel();
        panelTitle.setBackground(Color.WHITE);
        labelTitle = new JLabel("Simulador de procesos", SwingConstants.CENTER);
        labelTitle.setBackground(Color.WHITE);
        labelTitle.setFont(new Font("Abadi",Font.BOLD,25));
        labelTitle.setOpaque(true);
        panelTitle.add(labelTitle);
        this.add(panelTitle, BorderLayout.NORTH);

        panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout());
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(new EmptyBorder(10,10,10,10));
        textAreaInfo = new JTextArea("Por favor llene los siguientes campos e inicie con la simulación");
        textAreaInfo.setWrapStyleWord(true);
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setEditable(false);
        textAreaInfo.setFont(new Font("Abadi",Font.BOLD,20));
        panelInfo.add(textAreaInfo, BorderLayout.CENTER);
        mainPanel.add(panelInfo);

        panelQuantum = new JPanel();
        panelQuantum.setLayout(new BorderLayout());
        panelQuantum.setBackground(Color.WHITE);
        labelQuantum = new JLabel("Tiempo del quantum en segundos");
        labelQuantum.setBackground(Color.WHITE);
        labelQuantum.setBorder(new EmptyBorder(10,10,10,10));
        labelQuantum.setFont(new Font("Abadi",Font.BOLD,18));
        labelQuantum.setOpaque(true);
        panelQuantum.add(labelQuantum, BorderLayout.NORTH);

        textFieldQuantum = new JTextField();
        textFieldQuantum.setFont(new Font("Abadi",Font.BOLD,18));
        textFieldQuantum.setOpaque(true);
        textFieldQuantum.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        panelQuantum.add(textFieldQuantum, BorderLayout.CENTER);
        mainPanel.add(panelQuantum);

        panelQuantityProcess = new JPanel();
        panelQuantityProcess.setLayout(new BorderLayout());
        panelQuantityProcess.setBackground(Color.WHITE);
        labelQuantityProcess = new JLabel("Cantidad de procesos");
        labelQuantityProcess.setBackground(Color.WHITE);
        labelQuantityProcess.setBorder(new EmptyBorder(20,10,10,10));
        labelQuantityProcess.setFont(new Font("Abadi",Font.BOLD,18));
        labelQuantityProcess.setOpaque(true);
        panelQuantityProcess.add(labelQuantityProcess, BorderLayout.NORTH);

        textFieldNumberProcess = new JTextField();
        textFieldNumberProcess.setFont(new Font("Abadi",Font.BOLD,18));
        textFieldNumberProcess.setOpaque(true);
        textFieldNumberProcess.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        panelQuantityProcess.add(textFieldNumberProcess, BorderLayout.CENTER);
        mainPanel.add(panelQuantityProcess);

        panelButton = new JPanel();
        panelButton.setLayout(new BorderLayout());
        panelButton.setBackground(Color.WHITE);
        panelButton.setBorder(new EmptyBorder(40,100,20,100));
        buttonAccept = new JButton("Iniciar Simulación");
        buttonAccept.setOpaque(true);
        buttonAccept.setBackground(Color.GREEN);
        buttonAccept.setForeground(Color.WHITE);
        buttonAccept.setFont(new Font("Abadi",Font.BOLD,20));
        buttonAccept.setActionCommand(Commands.C_START_SIMULATION.name());
        buttonAccept.addActionListener(controllerApp);
        panelButton.add(buttonAccept, BorderLayout.CENTER);
        mainPanel.add(panelButton);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    public Integer getQuantum(){
        return Integer.parseInt(textFieldQuantum.getText());
    }

    public Integer getNumberProcess(){
        return Integer.parseInt(textFieldNumberProcess.getText());
    }

}
