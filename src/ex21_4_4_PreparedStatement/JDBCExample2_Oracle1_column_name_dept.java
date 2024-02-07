package ex21_4_4_PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class JDBCExample2_Oracle1_column_name_dept {
	public static void main(String args[]) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			
			//1단계 : JDBC 드라이버를 로드한다.
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);

		    //2단계 : DB에 연결한다.
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		    conn = DriverManager.getConnection(url, "scott", "tiger");

		    String select_sql = "select * from dept";
		    
		    pstmt = conn.prepareStatement(select_sql);
		    rs = pstmt.executeQuery();
		    
		    
		    
		    System.out.println("번호\t부서번호\t부서명\t\t지역");
		    System.out.println("---------------------------------------------------");
		    
		    /*
		    ResultSet 객체로부터 select문의 실행 결과를 얻기 위해서는 먼저
		    next() 메소드를 호출해야 합니다.
		    rs.next() - 다음 행 위치로 이동하는 메소드
		    			리턴값은 boolean으로 실제로 행을 읽어 왔는지의 여부
		     */
		    
		    int i = 0;
		    while (rs.next()) { // 더 이상 읽을 데이터가 없을 때까지 반복
		    	//getInt("deptno") : 컬럼 deptno 값을 int 형으로 리턴하는 메소드
		    	int deptno = rs.getInt("deptno");
		    	
		    	//getString("dname") : 컬럼 dname 값을 String 형으로 리턴하는 메소드
		    	String dname = rs.getString("dname");
		    	
		    	String loc = rs.getString("loc");
		    	
		    	System.out.printf("%5d\t%5d\t%-15s%s\n",
		    						++i,deptno,dname,loc);
		    }
		    
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
