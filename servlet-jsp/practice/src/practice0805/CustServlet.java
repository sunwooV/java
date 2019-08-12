package practice0805;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustServlet
 */
@WebServlet("/cust")
public class CustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		CustDAO dao = new CustDAO();
		
		String input_id = request.getParameter("input_id");
		List list = dao.listCustomers(input_id);
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightyellow'>");
		out.print("<td>고객번호</td><td>고객명</td><td>주문번호</td><td>주문상품번호</td><td>주문상품명</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			CustVO vo = (CustVO)list.get(i);
			String cust_id = vo.getCust_id();
			String cust_name = vo.getCust_name();
			String order_num = vo.getOrder_num();
			String order_item = vo.getOrder_item();
			String prod_name = vo.getProd_name();
			out.print("<tr><td>" + cust_id + "</td><td>" + cust_name +  "</td><td>" + order_num + "</td><td>" + order_item + "</td><td>" + prod_name + "</td></tr>");
			
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
