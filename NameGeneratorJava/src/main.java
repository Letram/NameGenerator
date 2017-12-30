import Controller.FullNameController;
import Model.FullName;
import Persistence.SQLite;
import View.FullNameView;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class main {
    static SQLite sqLite = new SQLite();

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Name generator");
        mainFrame.setLayout(new BorderLayout());

        FullName model = new FullName();
        FullNameView view = new FullNameView();
        FullNameController controller = new FullNameController(view, model);

        mainFrame.add(view, BorderLayout.NORTH);

        JButton generateNameButton = new JButton("Generate!");
        generateNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FullName model = retrieveNewFullName();
                controller.setFullName(model);
                controller.updateView();
            }
        });
        JButton addToDatabaseButton = new JButton("Insert new names / lastnames");
        addToDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField firstName = new JTextField();
                JTextField lastName = new JTextField();
                final JComponent[] inputs = new JComponent[] {
                        new JLabel("Name"),
                        firstName,
                        new JLabel("Lastname"),
                        lastName,
                };
                int result = JOptionPane
                        .showConfirmDialog(null, inputs, "Insert to database", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    insertData(firstName.getText(), lastName.getText());
                }
            }
        });
        mainFrame.add(generateNameButton, BorderLayout.CENTER);
        mainFrame.add(addToDatabaseButton, BorderLayout.SOUTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static FullName retrieveNewFullName() {
        FullName fullName = new FullName();
        Statement statement;
        try{
            Connection connection = sqLite.connect();
            statement = connection.createStatement();

            List<String> randomFullName = sqLite.getRandomFullName(connection, statement);
            if(!randomFullName.isEmpty()){
                fullName.setName(randomFullName.get(0));
                fullName.setLastName(randomFullName.get(1));
            }

            statement.close();
            connection.close();

            System.out.println(fullName);
        }catch (Exception e){
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
        return fullName;
    }

    private static void insertData(String name, String lastname){
        Connection connection = sqLite.connect();
        if(sqLite.insertData(connection, name, lastname)) JOptionPane.showMessageDialog(null, "Done!");
    }
}
