package View;

import Persistence.SQLite;

import javax.swing.*;
import java.awt.*;

public class FullNameView extends JPanel {

    private JTextField nameField;
    private JTextField lastNameField;
    private JPanel statsPanel;
    private JLabel nameCount;
    private JLabel lastnameCount;
    private JPanel nameGeneratorPanel;
    public FullNameView() {
        JLabel nameLabel = new JLabel("Name: ");
        JLabel lastNameLabel = new JLabel("Lastname: ");
        nameField = new JTextField();
        lastNameField = new JTextField();
        nameCount = new JLabel("nº of names: ");
        lastnameCount = new JLabel("nº of lastnames: ");

        nameField.setEditable(false);
        lastNameField.setEditable(false);

        nameGeneratorPanel = new JPanel();
        nameGeneratorPanel.setLayout(new GridLayout(2,2));
        nameGeneratorPanel.add(nameLabel);
        nameGeneratorPanel.add(nameField);
        nameGeneratorPanel.add(lastNameLabel);
        nameGeneratorPanel.add(lastNameField);

        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2,1));
        statsPanel.add(nameCount);
        statsPanel.add(lastnameCount);

        add(nameGeneratorPanel);
        add(statsPanel);

        reloadStats();
    }

    public void showFullName(String name, String lastName) {
        nameField.setText(name);
        lastNameField.setText(lastName);
    }

    public void reloadStats() {
        int count = SQLite.getCount("names");
        nameCount.setText(nameCount.getText().substring(0, nameCount.getText().lastIndexOf(' ')) + " " + count);
        count = SQLite.getCount("lastnames");
        lastnameCount.setText(lastnameCount.getText().substring(0, lastnameCount.getText().lastIndexOf(' ')) + " " + count);
    }
}
