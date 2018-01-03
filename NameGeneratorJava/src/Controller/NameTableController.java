package Controller;

import Model.FullName;
import Persistence.SQLite;
import View.NameTableView;

import java.sql.SQLException;

public class NameTableController {
    private FullName model;
    private NameTableView view;

    public NameTableController(FullName model, NameTableView view) {
        this.model = model;
        this.view = view;
    }

    public void setModel(FullName fullName){
        model = fullName;
    }

    public Object[] getAll(String tableName){
        SQLite sqLite = new SQLite();
        try {
            return sqLite.getAll(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
