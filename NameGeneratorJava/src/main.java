import Controller.FullNameController;
import Model.FullName;
import View.FullNameView;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Name generator");
        mainFrame.setLayout(new GridLayout(2,1));

        FullName model = new FullName();
        FullNameView view = new FullNameView();
        FullNameController controller = new FullNameController(view, model);

        mainFrame.add(view);

        JButton generateNameButton = new JButton("Generate!");
        generateNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FullName model = retrieveNewFullName();
                controller.setFullName(model);
                controller.updateView();
            }
        });

        mainFrame.add(generateNameButton);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static FullName retrieveNewFullName() {
        FullName fullName = new FullName();
        Connection connection;
        Statement statement;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:../names.db");
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name FROM names ORDER BY RANDOM() LIMIT 1");
            fullName.setName(resultSet.getString("name"));
            resultSet = statement.executeQuery("SELECT lastname FROM lastnames ORDER BY RANDOM() LIMIT 1");
            fullName.setLastName(resultSet.getString("lastname"));

            resultSet.close();
            statement.close();
            connection.close();

            System.out.println(fullName);
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
        return fullName;
    }
}
