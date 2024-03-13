package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ex03 페이지에 넘어온 정보를 받자
		//입력된 정보 중 한글이 있다면 한글이 깨질 수 있다.
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8"); 
		
		String member_id = request.getParameter("id");
		String member_pwd = request.getParameter("password");
		String member_name = request.getParameter("name");
		String member_phone = request.getParameter("phone");
		String member_addr = request.getParameter("addr");
		// 웹 브라우저로 응답해주면됨
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		
		out.println("<h2>회원 정보</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr>");
		out.println("<th>회원 아이디</th>");
		out.println("<td>"+member_id+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>회원 비번</th>");
		out.println("<td>"+member_pwd+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>회원 이름</th>");
		out.println("<td>"+member_name+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>회원 전번</th>");
		out.println("<td>"+member_phone+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>회원 주소</th>");
		out.println("<td>"+member_addr+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
