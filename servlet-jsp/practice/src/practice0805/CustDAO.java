package practice0805;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustDAO {
	
	private static final String driver= "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user ="scott";
	private static final String pwd = "tiger";
	private Connection con;
	private PreparedStatement pstmt;
	
	public List listCustomers(String input_id) {
		List list = new ArrayList();
		try {
			connDB();
			String query = "SELECT c.cust_id, c.cust_name, o.order_num, oi.order_item, p.prod_name" + 
					" FROM customers c, orders o, orderitems oi, products p" + 
					" WHERE c.cust_id = o.cust_id" + 
					" AND o.order_num = oi.order_num" + 
					" AND oi.prod_id = p.prod_id" + 
					" AND c.cust_id = '" + input_id + "'";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String cust_id = rs.getString("cust_id");
				String cust_name = rs.getString("cust_name");
				String order_num = rs.getString("order_num");
				String order_item = rs.getString("order_item");
				String prod_name = rs.getString("prod_name");
				CustVO vo = new CustVO();
				vo.setCust_id(cust_id);
				vo.setCust_name(cust_name);
				vo.setOrder_num(order_num);
				vo.setOrder_item(order_item);
				vo.setProd_name(prod_name);
				list.add(vo);
				
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
