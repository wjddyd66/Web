package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/hi.do")
public class TestServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		// 최초접속자에 의해 1최 수행 - 초기화작업
		System.out.println("초기화 작업");
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>서블릿 start -doGet</h1>");
		out.println("</body></html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>서블릿 start -doPost</h1>");
		out.println("</body></html>");
		out.close();
	}

	public void destroy() {
		//서비스 종료 시 1회 수행 - 마무리 담당
		System.out.println("destory");
	}
}
