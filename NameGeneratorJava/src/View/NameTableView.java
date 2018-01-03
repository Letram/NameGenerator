package View;

import Controller.NameTableController;
import Model.FullName;

import javax.swing.*;
import java.awt.*;

public class NameTableView extends JDialog {
    private FullName model;
    private NameTableController controller;
    private String tableName = "";

    public NameTableView(String tableName){
        model = new FullName();
        controller = new NameTableController(model, this);

        setTitle(tableName);
        setLayout(new FlowLayout());

        JScrollPane tablePanel = new JScrollPane();
        String[] columnName = {tableName};
        Object[] data = controller.getAll(tableName);
        Object[][] parsedData = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            parsedData[i][0] = data[i];
        }
        JTable table = new JTable(parsedData, columnName);
        tablePanel.setViewportView(table);

        add(tablePanel);
        pack();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
