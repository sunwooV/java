package practice0807;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProdDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ProdDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List ProdList(String prodId, String vendId) {
		List list = new ArrayList();
		try {
			Connection con = dataFactory.getConnection();
			
			System.out.println(prodId + vendId);
			
			String query = "select p.prod_id, p.prod_name, p.prod_price, p.prod_desc, v.vend_name, v.vend_id"
					+ " from products p, vendors v "
					+ " where p.vend_id = v.vend_id";
//			if(prodId != null && !"".equals(prodId)){ //제품번호 입력했을 때
//				query += " and p.prod_id like '%" + prodId + "%'";
//			} if(vendId != null && !"".equals(vendId)){ //공급업체번호 입력했을 때
//				query += " and v.vend_id like '%" + vendId + "%'";
//			}
			
			//위에꺼 혹은 아래꺼 (둘 다 가능)
			if(prodId != null && prodId.length() != 0){ //제품번호 입력했을 때
				query += " and p.prod_id like '%" + prodId + "%'";
			} if(vendId != null && vendId.length() != 0){ //공급업체번호 입력했을 때
				query += " and v.vend_id like '%" + vendId + "%'";
			}
			
			
			//모든 경우의 수를 나누지 않아도 된다. 
			//-> 이렇게 하면 제품번호, 공급업체 번호 입력 안하면 null 값이 아닌 ""여서 둘 다 조건이 있는 where 절로 넘어감
//			if(prodId != null && vendId == null){ //제품번호만 입력했을 때
//				query += " and p.prod_id like '%" + prodId + "%'";
//			} else if(vendId != null && prodId == null){ //공급업체번호 입력했을 때
//				query += " and v.vend_id like '%" + vendId + "%'";
//			} else if(vendId != null && prodId != null){ //공급업체번호 입력했을 때
//				query += " and v.vend_id like '%" + vendId + "%'"
//						+ " and p.prod_id like '%" + prodId + "%'";
//			}
			
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate(); //회원 정보를 테이블에 추가합니다.
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				String id = rs.getString("prod_id");
				String name = rs.getString("prod_name");
				String price = rs.getString("prod_price");
				String desc = rs.getString("prod_desc");
				String vendNm = rs.getString("vend_name");
				String searchId = rs.getString("prod_id");
				String searchVendId = rs.getString("vend_id");
				ProdVO vo = new ProdVO();
				vo.setId(id);
				vo.setName(name);
				vo.setPrice(price);
				vo.setDesc(desc);
				vo.setVendNm(vendNm);
				vo.setSearchId(searchId);
				vo.setSearchVendId(searchVendId);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void addProd(String id, String name, double price, String desc, String vendId) {
		try {
			Connection con = dataFactory.getConnection();
			
			String query = "insert into products"
					+ " (prod_id, prod_name, prod_price, prod_desc, vend_id)"
					+ " values (?, ?, ?, ?, ?)";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);
			pstmt.setString(4, desc);
			pstmt.setString(5, vendId);

			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delProd(String id) {
		try {
			ProdVO vo = new ProdVO();
			vo.setDelId(id);
			Connection con = dataFactory.getConnection();

			String query = "delete from products where prod_id=?";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
