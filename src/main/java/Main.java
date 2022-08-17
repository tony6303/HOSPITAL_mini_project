import com.example.IndexMenu;
import com.example.utils.AsciiArtUtils;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        AsciiArtUtils.show("test.txt");
        IndexMenu menu = new IndexMenu();
        menu.mainMenu();
    }
} 