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
 * Servlet implementation class InsertServlet
 */
@WebServlet({ "/InsertServlet", "/insert" })
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원들을 폼 페이지로 넘어가기 전에 부서번호 리스트를 DB에서 조회하여 사원 등록 폼
		// 페이지로 해당 데이터를 넘겨주는 비지니스 로직.
		
		EmpDAO dao = new EmpDAO();
		
		//DEPT 테이블에서 부서 전체 리스트조회
		List<DeptDTO> deptList= dao.getDeptList();
		
		// EMP 테이블에서 담당업무 리스트를 조회해 보자.
		List<String> jobList = dao.getJobList();
		
		// EMP 테이블에서 관리자 사원 리스트를 조회해 보자.
		List<EmpDTO> mgrList = dao.getMgrList();
		
		//부서 전체 리스트를 부서등록 폼 페이지에 넘겨줘야 한다.
		request.setAttribute("DeptList", deptList);
		request.setAttribute("JobList", jobList);
		request.setAttribute("MgrList", mgrList);
		
		//페이지 이동 경로 설정
		RequestDispatcher rd =
		request.getRequestDispatcher("view/emp_insert.jsp");
		
		//페이지 이동
		rd.forward(request, response);
		
	}

}
