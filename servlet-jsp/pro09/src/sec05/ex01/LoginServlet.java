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
 * Servlet implementation class LoginServlet1
 */
@WebServlet("/login10")
public class LoginServlet extends HttpServlet {
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
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(vo); //MemberDAO의 isExisted() 메서드를 호출하면서 MemberVO를 전달합니다.
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true); //조회한 결과가 true이면 isLogOn 속성을 true로 세션에 저장
			session.setAttribute("login.id", user_id); //조회한 결과가 true면 ID와 비밀번호를 세션에 저장
			session.setAttribute("login.pwd", user_pwd);
			out.print("<html><body>");
			out.print("안녕하세요 " + user_id + " 님!!!!<br>");
			out.print("<a href='show2'>회원정보 보기</a>");
			out.print("</body></html>");
			
		} else {
			out.print("<html><body><center>회원 아이디가 틀립니다.");
			out.print("<a href='login3.html'>다시 로그인하기</a>");
			out.print("</body></html>");
		}
	}

}
