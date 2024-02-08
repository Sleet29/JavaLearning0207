package example;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    public int insert(Student3 student) {
        int result = 0;
        String sql = "INSERT INTO student VALUES (STUDENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, " +
            "(SELECT GRADE FROM HAKJUM WHERE ? BETWEEN LOWSCORE AND HISCORE))";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger"); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    public ArrayList < Student3 > selectAll() {
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "select * from student order by no";

        ArrayList < Student3 > arrst = new ArrayList < Student3 > ();
        try {

            // 1단계 : JDBC 드라이버를 로드한다.
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);

            // 2단계 : DB에 연결한다.
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "scott", "tiger");

            // Statement 객체 얻기
            pstmt = conn.prepareStatement(sql.toString());

            // 쿼리 실행
            rs = pstmt.executeQuery();
            while (rs.next()) {

                Student3 st = new Student3();
                st.setNo(rs.getInt("no")); // 조회 결과 첫번째 컬럼 값을 가져옵니다.
                st.setName(rs.getString("name"));
                st.setKor(rs.getInt("kor"));
                st.setMath(rs.getInt("math"));
                st.setEng(rs.getInt("eng"));
                st.setTot(rs.getInt("tot"));
                st.setAvg(rs.getInt("avg"));
                st.setGrade(rs.getString("grade"));
                arrst.add(st);
            }

        } catch (ClassNotFoundException cnfe) {
            System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            if (conn != null)
                try {
                    conn.close(); //4단계 : DB연결을 끊는다.
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
        return arrst;

    }
}