package patient.dao;

import com.example.patient.model.dao.PatientDao;
import com.example.patient.model.dto.Patient;

import java.sql.*;
import java.util.List;

public class PatientDaoTest {
    PatientDao repository = new PatientDao();

    // 연결 시험
    public Connection connectTest() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "ehek01";
        String password = "ehek12";

        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection == null) {
            System.out.println("test : connect 실패");
        }

        System.out.println("test : connect 성공");

        return connection;
    }

    // 환자 정보 조회
    public void findAllTest() {
        List<Patient> patientList = repository.findAll();

        for (Patient patient : patientList) {
            System.out.println(patient.toString());
        }
    }

    // 주민번호로 환자조회
    public void findByPatientNo() {
        String resNo = "11111-1111111"; // 우리가 입력할 주민번호
        Patient patient = repository.findByPatientNo(resNo);
        System.out.println("test : 환자정보 -> " + patient.toString());
    }

    // 환자 등록
    public void registerPatient() throws SQLException {
        Connection connection = connectTest();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO patient (patient_id, patient_no, patient_name, phone) VALUES (?,?,?,?)";

        Patient patient = new Patient(null, "990302-1832653", "콩1순이", "010-1111-2222");

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, (repository.getColumnCount()+1));
            pstmt.setString(2, patient.getPatientNo());
            pstmt.setString(3, patient.getPatientName());
            pstmt.setString(4, patient.getPhone());

            // insert
            pstmt.executeUpdate();

            // insert 된 환자 select
            PreparedStatement ps = connection.prepareStatement("select * from patient where patient_no = ?");
            ps.setString(1, patient.getPatientNo());
            ResultSet rs = ps.executeQuery();
            rs.next();

            System.out.println("test : 등록된 환자 ->" + new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            connection.rollback();
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        PatientDaoTest test = new PatientDaoTest();

        test.connectTest().close();
//        test.findAllTest();
//        test.findByPatientNo();
//        test.registerPatient();
    }
}