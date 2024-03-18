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
 * Servlet implementation class UpdateOKServlet
 */
@WebServlet("/update_ok")
public class UpdateOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateOKServlet() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사원 수정 폼 페이지에서 넘어온 데이터를 DB에 수정해서 저장시키는 비지니스 로직
		
		System.out.println("여기 오나?");
		//요청과 응답 시에 한글 깨짐을 방지하는 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//1단계: emp_modify.jsp에서 넘어온 데이터들을 받아줌
		int emp_no = Integer.parseInt(request.getParameter("num").trim());
		
		String emp_name = request.getParameter("name").trim();
		
		String emp_job = request.getParameter("job").trim();
		
		int emp_mgrNo = Integer.parseInt(request.getParameter("mgrNo").trim());
		
		int emp_sal = Integer.parseInt(request.getParameter("sal").trim());
		
		int emp_comm = Integer.parseInt(request.getParameter("comm").trim());
		
		int emp_deptNo = Integer.parseInt(request.getParameter("deptNo").trim());
		
		//2단계: 넘어온 데이터들을 DB에 전송해줘야함.
		//전송 시에 DTO 객체들을 써서 전송한다.
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(emp_no);
		dto.setEname(emp_name);
		dto.setJob(emp_job);
		dto.setMgr(emp_mgrNo);
		dto.setSal(emp_sal);
		dto.setComm(emp_comm);
		dto.setDeptno(emp_deptNo);
		
		//3단계: DAO객체를 생성해서 수정 작업 진행.
		EmpDAO dao = new EmpDAO();
		int check = dao.updateEmp(dto);
		System.out.println(check);
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('사원정보수정성')");
			out.println("location.href='content?no="+dto.getEmpno()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('사원정보수정실')");
			out.println("history.back()");
			out.println("</script>");
			
			
		}
	}

}
