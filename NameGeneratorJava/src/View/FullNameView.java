package View;

import javax.swing.*;
import java.awt.*;

public class FullNameView extends JPanel {

    private JTextField nameField;
    private JTextField lastNameField;

    public FullNameView() {
        JLabel nameLabel = new JLabel("Name: ");
        JLabel lastNameLabel = new JLabel("Lastname: ");
        nameField = new JTextField();
        lastNameField = new JTextField();

        nameField.setEditable(false);
        lastNameField.setEditable(false);

        setLayout(new GridLayout(2,2));
        add(nameLabel);
        add(nameField);
        add(lastNameLabel);
        add(lastNameField);
    }

    public void showFullName(String name, String lastName) {
        nameField.setText(name);
        lastNameField.setText(lastName);
    }
}
