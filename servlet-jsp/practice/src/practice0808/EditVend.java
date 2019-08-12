package practice0808;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditVend
 */
@WebServlet("/edit")
//public class EditVend extends HttpServlet {
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doHandle(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doHandle(request, response);
//	}
//	
//	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		String command = request.getParameter("command");
//		List list = null;
//		VendDAO dao = new VendDAO();
//		boolean yon = false;
//		
//	
//		if(command != null && command.equals("editVend")) {
//		
//			String searchId = request.getParameter("searchId");
//
//			String id = request.getParameter("id");
//			String name = request.getParameter("name");
//			String address = request.getParameter("address");
//			String city = request.getParameter("city");
//			String state = request.getParameter("state");
//			String zip = request.getParameter("zip");
//			String country = request.getParameter("country");
//			
//			out.print("<html><body><form name='frmedit' method='get' action='edit' encType='UTF-8'>");
//			out.print("공급업체 번호 : <input type='text' name='vend_id' value=" + id + "><br>");
//			out.print("공급업체 이름 : <input type='text' name='vend_name' value=" + name + "><br>");
//			out.print("공급업체 주소 : <input type='text' name='vend_address' value=" + address + "><br>");
//			out.print("공급업체 시 : <input type='text' name='vend_city' value=" + city + "><br>");
//			out.print("공급업체 주 : <input type='text' name='vend_state' value=" + state + "><br>");
//			out.print("공급업체 우편번호 : <input type='text' name='vend_zip' value=" + zip + "><br>");
//			out.print("공급업체 국가 : <input type='text' name='vend_country' value=" + country + "><br>");
//			out.print("<input type='submit' value='수정'>");
//			out.print("<input type='reset' value='다시입력'>");
//			out.print("<input type='hidden' name='searchId' value='" + searchId + "'>");
//			out.print("<input type='hidden' name='editId' value='" + id + "'>");
//			out.print("<input type='hidden' name='command' value='getVend'>");
//			out.print("</form></body></html>");
//			
//			yon = true;
//			
//		} else if(command != null && command.equals("getVend")) {
//			String editId = request.getParameter("editId");
//			
//			String id = request.getParameter("vend_id");
//			String name = request.getParameter("vend_name");
//			String address = request.getParameter("vend_address");
//			String city = request.getParameter("vend_city");
//			String state = request.getParameter("vend_state");
//			String zip = request.getParameter("vend_zip");
//			String country = request.getParameter("vend_country");
//			
//			dao.editVend(id, name, address, city, state, zip, country, editId);
//			
//			String searchId = request.getParameter("searchId");
//			list = dao.VendList(searchId);
//			VendVO vo = new VendVO();
//			vo.setSearchId(searchId);
//
//			out.print("<html><body>");
//			out.print("<table border=1><tr align='center' bgcolor='lightyellow'>");
//			out.print("<td>공급업체번호</td><td>공급업체이름</td><td>공급업체주소</td><td>공급업체 시</td><td>공급업체 주</td><td>공급업체 우편번호</td><td>공급업체 국가</td><td>수정</td><td>삭제</td></tr>");
//			
//			for(int i=0;i<list.size();i++) {
//				vo = (VendVO)list.get(i);
//				String id1 = vo.getId();
//				String name1 = vo.getName();
//				String address1 = vo.getAddress();
//				String city1 = vo.getCity();
//				String state1 = vo.getState();
//				String zip1 = vo.getZip();
//				String country1 = vo.getCountry();
//
//				out.print("<tr><td>" + id1 + "</td><td>" + name1 + "</td><td>" + address1 + "</td><td>" + city1 + "</td><td>" + state1 + "</td><td>" + zip1 + "</td><td>" + country1 + "</td><td>" + "<a href='/practice/edit?command=editVend&searchId=" + searchId + "&id=" + id1 + "&name=" + name1 + "&address=" + address1 + "&city=" + city1 + "&state=" + state1 + "&zip=" + zip1 + "&country=" + country1 + "'>수정</a></td><td>" + "<a href='/practice/vend?command=delVend&id=" + id1 + "&searchId=" + searchId + "'> 삭제 </a></td></tr>");
//			}
//			
//			out.print("</table></body></html>");
//			out.print("<a href='/practice/vend.html'>새로운 공급업체 조회하기</a>");
//		}		
//		
//	}
//
//}


    public class EditVend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
	
		if(command != null && command.equals("editVend")) {
		
			String searchId = request.getParameter("searchId");

			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			String country = request.getParameter("country");
			
			out.print("<html><body><form name='frmedit' method='get' action='vend' encType='UTF-8'>");
			out.print("공급업체 번호 : <input type='text' name='vend_id' value=" + id + "><br>");
			out.print("공급업체 이름 : <input type='text' name='vend_name' value=" + name + "><br>");
			out.print("공급업체 주소 : <input type='text' name='vend_address' value=" + address + "><br>");
			out.print("공급업체 시 : <input type='text' name='vend_city' value=" + city + "><br>");
			out.print("공급업체 주 : <input type='text' name='vend_state' value=" + state + "><br>");
			out.print("공급업체 우편번호 : <input type='text' name='vend_zip' value=" + zip + "><br>");
			out.print("공급업체 국가 : <input type='text' name='vend_country' value=" + country + "><br>");
			out.print("<input type='submit' value='수정'>");
			out.print("<input type='reset' value='다시입력'>");
			out.print("<input type='hidden' name='searchId' value='" + searchId + "'>");
			out.print("<input type='hidden' name='editId' value='" + id + "'>");
			out.print("<input type='hidden' name='command' value='getVend'>");
			out.print("</form></body></html>");
			
		}
	}

}
