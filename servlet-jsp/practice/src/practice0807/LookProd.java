package practice0807;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LookProd
 */
@WebServlet("/prod")
public class LookProd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ProdDAO dao = new ProdDAO();
		String command = request.getParameter("command");
		List list = null;
		
		
		if(command != null && command.equals("delProd")) {
			String id = request.getParameter("id");
			dao.delProd(id);
		} else if(command != null && command.equals("addProd")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			String desc = request.getParameter("desc");
			String vendId = request.getParameter("vend");
			
			dao.addProd(id, name, price, desc, vendId);
			
		}
		
		String prodId = request.getParameter("prodId");
		String vendId = request.getParameter("vendId");
		list = dao.ProdList(prodId, vendId);

		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightyellow'>");
		out.print("<td>제품번호</td><td>제품명</td><td>제품가격</td><td>제품설명</td><td>공급업체명</td><td>삭제</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			ProdVO vo = (ProdVO)list.get(i);
			String id = vo.getId();
			String name = vo.getName();
			String price = vo.getPrice();
			String desc = vo.getDesc();
			String vendNm = vo.getVendNm();

			out.print("<tr><td>" + id + "</td><td>" + name + "</td><td>" + price + "</td><td>" + desc + "</td><td>" + vendNm + "</td><td>" + "<a href='/practice/prod?command=delProd&id=" + id + "&prodId=" + prodId  + "&vendId=" + vendId + "'> 삭제 </a></td></tr>");
		}
		
		out.print("</table></body></html>");
		out.print("<a href='/practice/product.html'>새로운 상품 조회하기</a>");
	}
}
