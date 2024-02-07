package ex21_4_4_PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class JDBCExample4_Oracle_insert_direct {
	public static void main(String args[]) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			/*
			 * 1. 직접 데이터를 입력하는 경우
			 * insert into goodsinfo
			 * values('A5000','키보드',300,'손의나라'
			 * 
			 */
			
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		    conn = DriverManager.getConnection(url, "scott", "tiger");

		    String sql = "insert into goodsinfo "
					+ "values(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "A5000");
			pstmt.setString(2, "키보드");
			pstmt.setInt(3, 300);
			pstmt.setString(4, "손의나라");
			int rowNum = pstmt.executeUpdate();
			System.out.println(rowNum + "행이 추가되었습니다.");
		} catch (ClassNotFoundException classNotFoundException) {
		    System.out.println("해당 클래스를 찾을 수 없습니다." + classNotFoundException.getMessage());
		} catch (SQLException sqlException) {
		    System.out.println(sqlException.getMessage());
		} finally {
		    if (rs != null)
		        try {
		            rs.close(); 
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		    
		    if (pstmt != null)
		        try {
		            pstmt.close(); 
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		    
		    if (conn != null)
		        try {
		            conn.close(); //4단계 : DB연결을 끊는다.
		        } catch (SQLException sqlException) {
		            System.out.println(sqlException.getMessage());
		        }
		}
	}
}
