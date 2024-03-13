package goott;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력 폼 페이지에서 method가 post인 경우에 처리하는 메서드
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//1단계: Ex04 파일에서 넘어온 데이터들을 받는다
		String member_id = request.getParameter("id");
		String member_pwd = request.getParameter("pwd");
		String member_name = request.getParameter("name");
		String member_phone = request.getParameter("phone");
		String member_addr = request.getParameter("addr");
		//여러 개의 데이터 저장되어 배열로 넘어오는 경우
		String[] member_hobbies = request.getParameterValues("hobby");
		
		//2단계: 웹 브라우저에 요청한 결과를 보여주자.
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
		
		out.println("<tr>");
		out.println("<th>회원 취미</th>");
		
		out.println("<td>");
		for(int i=0; i<member_hobbies.length; i++) {
			out.println(member_hobbies[i]+ "&nbsp;");
		}
		
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
		
}}
