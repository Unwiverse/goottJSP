package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
	public class SelectServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// main.jsp 페이지에서 요청 내용
		// ==> EMP 테이블에 있는 전체 사원을 보여달라는 요청(비지니스 로직)
		
		//1.단계: DB와 연결작업 진행.
		EmpDAO dao = new EmpDAO();
		
		//2: DB에서 EMP 테이블의 전체 사원 목록을 조회
		List<EmpDTO> empList= dao.selectEmpList();
		//3: 페이지 이동 시 정보(데이터)를 넘겨줘야 함
		request.setAttribute("List", empList); 
		
		//4이동할 페이지 경로를 설정해줘야함
		RequestDispatcher rd = request.getRequestDispatcher("view/emp_list.jsp");
		
		// 5단계 : 실제 페이지로 이동
		rd.forward(request, response);
	}

}
