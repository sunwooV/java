package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet1
 */
@WebServlet("*.do")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet3() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String context = request.getContextPath(); //컨텍스트 이름만 가져온다.
		String url = request.getRequestURL().toString(); //전체 url을 가져온다.
		String mapping = request.getServletPath(); //서블릿 매핑 이름만 가져온다.
		String uri = request.getRequestURI(); //URI를 가져온다.
		out.println("<html><head>");
		out.println("<title>Test Servlet3</title>");
		out.println("</head>");
		out.println("<body bgcolor = 'yellow'");
		out.println("<b>TestServlet3입니다.</b><br>");
		out.println("<b>컨텍스트 이름 : " + context + "</b><br>");
		out.println("<b>전체 경로 : " + url + "<b><br>");
		out.println("<b>매핑 이름 : " + mapping + "<b><br>");
		out.println("<b>URI : " + uri + "<br>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
