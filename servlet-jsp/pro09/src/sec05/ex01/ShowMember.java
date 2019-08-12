package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/show2")
public class ShowMember extends HttpServlet {
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "", pwd = "";
		Boolean isLogon=false;
		HttpSession session = request.getSession(false); //이미 세션이 존재하면 세션을 반환, 없으면 null을 반환
		if(session != null) { //먼저 세션이 생성되어 있는지 확인
			isLogon = (Boolean)session.getAttribute("isLogon"); //isLogon 속성을 가져와 로그인 상태를 확인
			if(isLogon==true) {
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pwd");
				out.print("<html><body>");
				out.print("아이디 : " + id + "<br>");
				out.print("비밀번호 : " + pwd + "<br>");
				out.print("</body></html>");
			} else { //로그인 상태가 아니면
				response.sendRedirect("login3.html");
			}
		} else { //세션이 생성되지 않았으면 로그인 창으로 이동
			response.sendRedirect("login3.html");
		}
	}

}
