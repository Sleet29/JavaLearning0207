package example_onmyown;
import java.sql.*;

public class DAO {
    public int insert(Student3 student) {
        int result = 0;
        String sql = "INSERT INTO student (NO, NAME, KOR, MATH, ENG, TOT, AVG, GRADE) VALUES (STUDENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getKor());
            pstmt.setInt(3, student.getMath());
            pstmt.setInt(4, student.getEng());
            pstmt.setInt(5, student.getTot());
            pstmt.setFloat(6, student.getAvg());
            pstmt.setString(7, student.getGrade());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
