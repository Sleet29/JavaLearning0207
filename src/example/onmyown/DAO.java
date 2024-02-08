package example.onmyown;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    public int insert(Student3 student) {
        int result = 0;
        String sql = "INSERT INTO student VALUES (STUDENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, "
        + "(SELECT GRADE FROM HAKJUM WHERE ? BETWEEN LOWSCORE AND HISCORE))";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getKor());
            pstmt.setInt(3, student.getMath());
            pstmt.setInt(4, student.getEng());
            pstmt.setInt(5, student.getTot());
            pstmt.setFloat(6, student.getAvg());
            pstmt.setFloat(7, student.getAvg());

            result = pstmt.executeUpdate();
            System.out.println("db에 반영됨 . . . . . .");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
   
    public ArrayList<Student3> selectAll() {
        ArrayList<Student3> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student3 g = new Student3();
                int no = rs.getInt("no");
                g.setNo(no);
                String name = rs.getString("name");
                g.setName(name);
                int kor = rs.getInt("kor");
                g.setKor(kor);
                int math = rs.getInt("math");
                g.setMath(math);
                int eng = rs.getInt("eng");
                g.setEng(eng);
                int tot = rs.getInt("tot");
                g.setTot(tot);
                float avg = rs.getFloat("avg");
                g.setAvg(avg);
                String grade = rs.getString("grade");
                g.setGrade(grade);

                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
