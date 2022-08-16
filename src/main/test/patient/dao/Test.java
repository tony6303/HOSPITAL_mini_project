package patient.dao;

import static com.example.config.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.patient.model.dto.Patient;

public class Test {
	private Connection connectionTest() throws SQLException {
		String url = "jdbc:oracle:thin:@oracle.douzone-jo3.kro.kr:1521:XE";
        String username = "JO3";
        String password = "JO3";

        Connection connection = DriverManager.getConnection(url, username, password);
        
        if (connection == null) {
        	System.out.println("connect 실패");
        } else {
        	System.out.println("connect 성공");
        }
        
        return connection;
	}
	
	public List<Patient> findAll() {
        String sql = "select * from patient";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            List<Patient> patients = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery("select * from patient");

            while (rs.next()) {
                patients.add(
                        new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4))
                );
            }

            rs.close();
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static void main(String[] args) throws SQLException {
		Test t = new Test();
		List<Patient> patientList = t.findAll();
		
		for (Patient param : patientList) {
			System.out.println(param.toString());
		}
//		t.connectionTest();
	}
}
