package ex21_4_4_PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class JDBCExample3_Oracle1_where_input {
	public static void main(String args[]) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		if (args.length !=1) {
			System.out.println("상품명 입력하세요");
			return;
		}
		try {
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		    conn = DriverManager.getConnection(url, "scott", "tiger");

		    String sel_where_sql
		    = "select code, name, price, maker "
		    + "from goodsinfo "
		    + "where name= ?";
		    
		    pstmt = conn.prepareStatement(sel_where_sql);
		    pstmt.setString(1, args[0]);
		    rs = pstmt.executeQuery();
		    
		    System.out.println("번호\t상품코드\t상품명\t\t가격\t제조사");
		    System.out.println("---------------------------------------------------");
		    
		    /*
		    ResultSet 객체로부터 select문의 실행 결과를 얻기 위해서는 먼저
		    next() 메소드를 호출해야 합니다.
		    rs.next() - 다음 행 위치로 이동하는 메소드
		    			리턴값은 boolean으로 실제로 행을 읽어 왔는지의 여부
		     */
		    
		    int i = 0;
		    while (rs.next()) { // 더 이상 읽을 데이터가 없을 때까지 반복
		    	//String code = rs.getString("code");
		    	String code = rs.getString("CODE"); // the column value; if the value is SQL NULL
		    									// the value returned is null
		    	String name = rs.getString("NAME"); // the column value; if the value is SQL NULL,
		    										  // the value returned is null
		    	int price = rs.getInt("PRICE");
		    	
		    	String maker = rs.getString("MAKER"); // 1980-12-17 00:00:00
		    	
		    	System.out.printf("%5d\t%s\t%-10s\t%d\t%s",
	                        ++i, code, name, price, maker);
		    	System.out.println();
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
