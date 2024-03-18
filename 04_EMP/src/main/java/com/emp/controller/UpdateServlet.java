package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateServlet() {
        super();
        
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get방식으로 넘어온 사원번호에 해당하는 사원 정보를 DB에서 조회해서 수정 폼 페이지로 이동하는 비지니스 로직
		
		int emp_no = Integer.parseInt(request.getParameter("no").trim());
		
		EmpDAO dao = new EmpDAO();
		
		List<String> jobList =  dao.getJobList();
		
		List<EmpDTO> mgrList = dao.getMgrList();
		
		List<DeptDTO> deptList = dao.getDeptList();
		
		EmpDTO modify = dao.contentEmp(emp_no); 
		
		//모든 정보를 view page(수정 폼 페이지)로 이동시키자
		request.setAttribute("jList", jobList);
		request.setAttribute("mList", mgrList);
		request.setAttribute("dList", deptList);
		request.setAttribute("Modify", modify);
		
		// view page 이동경로를 만들자, 위 4개 정보도 같이 넘어감
		//페이지 이동 
		request.getRequestDispatcher("view/emp_modify.jsp")
		.forward(request, response);
		
		}

}
