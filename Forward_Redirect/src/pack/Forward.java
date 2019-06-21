package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forward
 */
@WebServlet("/Forward")
public class Forward extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("data");
		String data[] = {"Forward",name};
		
		//forwarding - Server to Server
		request.setAttribute("data", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Forward.jsp");
		dispatcher.forward(request, response);
		
	}

}
