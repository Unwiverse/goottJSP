package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/student")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfo() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String student_hakbun = request.getParameter("id");
		String student_name = request.getParameter("name");
		String student_age = request.getParameter("age");
		String student_phone = request.getParameter("ph");
		String[] student_majors = request.getParameterValues("subject");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		
		out.println("<h2>학생 정보</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr>");
		out.println("<th>학번</th>");
		out.println("<td>"+student_hakbun+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>이름</th>");
		out.println("<td>"+student_name+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>나이</th>");
		out.println("<td>"+student_age+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>학번</th>");
		out.println("<td>");
		for(int i=0; i<student_majors.length; i++) {
			out.println(student_majors[i]+ "&nbsp;");
		}
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
