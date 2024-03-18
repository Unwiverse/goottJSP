package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class InsertOKServlet
 */
@WebServlet("/insert_ok")
public class InsertOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertOKServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원 등록 홈페이지에서 넘어온 데이터들을 EMP 테이블에 저장하는 비지니스 로직
		
		//요청과 응답 시 한글깨짐 방지 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//1단계: 사원등록 폼 페이지에서 넘어온 데이터들을 받아줘야한다.
		int emp_no = Integer.parseInt(request.getParameter("num").trim());
		String emp_name = request.getParameter("name").trim();
		String emp_job = request.getParameter("job").trim();
		int emp_mgr = Integer.parseInt(request.getParameter("mgrNo").trim());
		int emp_sal = Integer.parseInt(request.getParameter("sal").trim());
		int emp_comm = Integer.parseInt(request.getParameter("comm").trim());
		int emp_deptno = Integer.parseInt(request.getParameter("deptNo").trim());
		
		//2단계: 넘어온 데이터들을 DB에 전송해줘야함.
		//		전송 시 DTO 이용한다.
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(emp_no);
		dto.setEname(emp_name);
		dto.setJob(emp_job);
		dto.setMgr(emp_mgr);
		dto.setSal(emp_sal);
		dto.setComm(emp_comm);
		dto.setDeptno(emp_deptno);
		
		//3단계: DTO 객체를 DB에 전송
		EmpDAO dao = new EmpDAO();
		int check = dao.insertEmp(dto);
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('사원 등록 성공')");
			out.println("location.href='select'");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('사원 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
