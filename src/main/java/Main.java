import java.sql.SQLException;

import com.example.IndexMenu;
import com.example.utils.AsciiArtUtils;

public class Main {
    public static void main(String[] args) throws SQLException {
//        AsciiArtUtils.show("test.txt");
    	
        AsciiArtUtils.show("hospital.txt");
        IndexMenu menu = new IndexMenu();
        menu.mainMenu();
    }
} 