package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentInfo2
 */
@WebServlet("/score")
public class StudentInfo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfo2() {
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
		
		String StudName = request.getParameter("name");
		int Studscore1 = Integer.parseInt(request.getParameter("korean"));
		int Studscore2 = Integer.parseInt(request.getParameter("english"));
		int Studscore3 = Integer.parseInt(request.getParameter("math"));
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		
		out.println("<h2>시험 점수</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr>");
		out.println("<th>국어 점수</th>");
		out.println("<td>"+Studscore1+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>영어 점수</th>");
		out.println("<td>"+Studscore2+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>수학 점수</th>");
		out.println("<td>"+Studscore3+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>총점</th>");
		out.println("<td>"+(Studscore1+Studscore2+Studscore3)+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>평균</th>");
		out.println("<td>"+((Studscore1+Studscore2+Studscore3)/3.0)+"</td>");
		out.println("</tr>");
		
	}

}
