package practice0802;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
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
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		String command = request.getParameter("command");
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String operator = request.getParameter("operator");
		
		if(command != null && command.equals("calculate")){
			String result = calculate(Float.parseFloat(first), Float.parseFloat(second), operator);
			pw.print("<html><font size=10>계산 결과</font></br>");
			pw.print("<html><font size=10>" + result + "</font><br>");
			pw.print("<a href='/practice0802/calculator.html'>사칙연산 계산기</a>");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static String calculate(float first, float second, String operator) {
		String result = null;
		if(operator.equals("plus")) {
			result = String.format("%.1f", first + second);
		} else if(operator.equals("minus")) {
			result = String.format("%.1f", first - second);
		} else if(operator.equals("multi")) {
			result = String.format("%.1f", first * second);
		} else if(operator.equals("div")) {
			result = String.format("%.1f", first / second);
		}
		
		return result;
	}

}
