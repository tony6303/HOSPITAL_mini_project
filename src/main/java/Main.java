import com.example.IndexMenu;
import com.example.patient.model.dao.PatientDao;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        IndexMenu menu = new IndexMenu();
        menu.mainMenu();
    }
} 