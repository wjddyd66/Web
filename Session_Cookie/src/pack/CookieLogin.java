package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieLogin")
public class CookieLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		String id=null;
		String pwd=null;
		
		// 쿠키를 만들때는 try-catch 사용
		try {
			Cookie[] cookies=request.getCookies(); // 클라이언트의 모든 쿠키 읽기
			for (int i = 0; i < cookies.length; i++) {
				String name=cookies[i].getName();

				// 쿠키찾기
				if(name.equals("id")) { 
					id=URLDecoder.decode(cookies[i].getValue(), "utf-8");
				}
				if(name.equals("pwd")) {
					pwd=URLDecoder.decode(cookies[i].getValue(), "utf-8");
				}
			}
		} catch (Exception e) {
			
		}
		
		if(id!=null && pwd!=null) {
			out.println("Cookie에 저장된 ID:"+id+"<br>");
			out.println("Cookie에 저장된 Passward:"+pwd);
			out.println("<html><body>");
			out.close();
			return;
		}
		
		out.println(" * 로그인 * ");
		out.println("<form method='post'>");
		out.println("id: <input type='text' name='id'><br>");
		out.println("pwd: <input type='text' name='pwd'><br>");
		out.println("<input type='submit' value='전송'>");
		out.println("</form></body></html>");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		
		//임의로 id와 비밀번호가 kbs,123이라 가정한다.
		if(id.equals("kbs")&&pwd.equals("123")) {
			// 쿠키 제작시에는 트라이캐치를!
			try {
				id=URLEncoder.encode(id, "utf-8"); // 암호화
				Cookie idCookie=new Cookie("id", id);
				idCookie.setMaxAge(60 * 60 * 24 * 365); // 쿠키 유효기간: 1년 
				
				pwd=URLEncoder.encode(pwd, "utf-8"); // 암호화
				Cookie pwdCookie=new Cookie("pwd", pwd);
				pwdCookie.setMaxAge(60 * 60 * 24 * 365); // 쿠키 유효기간: 1년 
				
				response.addCookie(idCookie); // 클라이언트의 pc에 저장
				response.addCookie(pwdCookie);
				out.println("로그인 성공: 쿠키 작성됨");
			} catch (Exception e) {}
		} else {
			out.println("로그인 실패");
		}
		out.println("</body></html>");
		out.close();
		
	}
}
