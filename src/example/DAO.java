package example;
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
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Student3> list = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM emp";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
            	Student3 g = new Student3();
                int empno = rs.getInt("empno");
                g.setNo(rs.getInt(empno));
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
