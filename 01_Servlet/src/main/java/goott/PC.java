package goott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PC
 */
@WebServlet("/PC")
public class PC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String CPU_p = request.getParameter("CPU");
		String MB_p = request.getParameter("M/B");
		String VGA_p = request.getParameter("VGA");
		String SSD_p = request.getParameter("SSD");
		String Hdd_y = request.getParameter("HDD");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		
		out.println("<h2>PC 정보</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr>");
		out.println("<th>CPU</th>");
		out.println("<td>"+CPU_p+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>MB</th>");
		out.println("<td>"+MB_p+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>VGA</th>");
		out.println("<td>"+VGA_p+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>SSD</th>");
		out.println("<td>"+SSD_p+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>HDD?</th>");
		out.println("<td>"+Hdd_y+"</td>");
		out.println("</tr>");
	}

}
