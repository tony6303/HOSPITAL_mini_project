import com.example.IndexMenu;
import com.example.patient.model.dao.PatientDao;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("develop branch add");
        IndexMenu menu = new IndexMenu();
        menu.mainMenu();
    }
} 