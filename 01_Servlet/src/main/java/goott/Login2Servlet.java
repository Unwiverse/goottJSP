package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login2Servlet
 */
@WebServlet("/Login")
public class Login2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userId = request.getParameter("id");
		 String userPwd = request.getParameter("pwd");
		 System.out.println("입력받은 아이디: "+userId);
		 System.out.println("입력받은 비번: "+userPwd);
		 
		 //응답은 response 매개변수를 쓰면 됨.
		 //클라이언트 응답 시 한글을 작성하면 깨짐(쓰면안됨)
		 // 이를 해결해야됨.
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 
		 out.println("<html");
		 out.println("<head></head>");
		 out.println("<body>");
		 out.println("<h2>");
		 
		out.println("입력한 아이디: "+userId+"<br>");
		out.println("입력한 비번: "+userPwd+"<br>");
		
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
