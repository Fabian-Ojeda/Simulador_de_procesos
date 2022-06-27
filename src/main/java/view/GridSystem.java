package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

public class GridSystem {

    private static final int DEFAULT_COLUMN_WIDTH = 1;
    private static final int COLUMNS_NUMBER = 5;

    private GridBagConstraints constraints;

    public GridSystem(Container component) {
        component.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        generateBasicGrid(component);
    }

    private void generateBasicGrid(Container component){
        constraints.weightx = DEFAULT_COLUMN_WIDTH;
        for (int i = 0; i < COLUMNS_NUMBER; i++) {
            constraints.gridx = i;
            component.add(new JLabel(), constraints);
        }
    }

    public GridBagConstraints insertComponent(int row, int column, int width,int height, double weightx ,double weighty){
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weighty = weighty;
        constraints.weightx = weightx;
        return constraints;
    }

    public GridBagConstraints insertComponent(int column, int width){
        constraints.gridx = column;
        constraints.gridwidth = width;
        return constraints;
    }

    public void addExternalBorder(int top, int right, int bottom, int left){
        constraints.insets = new Insets(top,right,bottom,left);
    }

}
