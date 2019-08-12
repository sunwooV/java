package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//prepareStatement
public class MemberDAO {
	
	private static final String driver= "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user ="scott";
	private static final String pwd = "tiger";
	private Connection con;
	private PreparedStatement pstmt;
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			connDB(); //네 가지 정보로 데이터베이스를 연결한다.
			String query = "select * from t_member";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; //조회한 레코드의 개수만큼 MemberVO 객체를 저장한 ArrayList 를 반환합니다.
	}

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

//public class MemberDAO {
//	
//	private static final String driver= "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static final String user ="scott";
//	private static final String pwd = "tiger";
//	private Connection con;
//	private Statement stmt;
//	
//	public List listMembers() {
//		List list = new ArrayList();
//		try {
//			connDB(); //네 가지 정보로 데이터베이스를 연결한다.
//			String query = "select * from t_member";
//			System.out.println(query);
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()) {
//				String id = rs.getString("id");
//				String pwd = rs.getString("pwd");
//				String name = rs.getString("name");
//				String email = rs.getString("email");
//				Date joinDate = rs.getDate("joinDate");
//				MemberVO vo = new MemberVO();
//				vo.setId(id);
//				vo.setPwd(pwd);
//				vo.setName(name);
//				vo.setEmail(email);
//				vo.setJoinDate(joinDate);
//				list.add(vo);
//			}
//			rs.close();
//			stmt.close();
//			con.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list; //조회한 레코드의 개수만큼 MemberVO 객체를 저장한 ArrayList 를 반환합니다.
//	}
//
//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			con = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
//			stmt = con.createStatement();
//			System.out.println("Statement 생성 성공");
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
