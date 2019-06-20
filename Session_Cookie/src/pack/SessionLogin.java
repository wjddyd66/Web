package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest
 */
@WebServlet("/SessionLogin")
public class SessionLogin extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		HttpSession session=request.getSession(true); // 세션이 있으면 읽고, 없으면 생성
		
		session.setMaxInactiveInterval(10); // 10초동안 유효. 기본값은 30분.
		
		if(session!=null) {
			session.setAttribute("name", "kbs"); // 세션에 "name"="kbs" 저장하기
			session.setAttribute("pwd", "123"); // 세션에 "pwd"="123" 저장하기
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("Session id: "+session.getId());
			out.println("<br> Session에 저장된 ID: "+session.getAttribute("name"));
			out.println("<br> Session에 저장된 Passward:: "+session.getAttribute("pwd"));
			out.println("</body></html>");
			out.close();
		}
	}

}