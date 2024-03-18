package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.go")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectServlet() {
        super();
        
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청: 전체 회원 목록을 화면에 보여달라고 클라가 요청함.
		//응답: DB에서 member 테이블의 회원 전체 리스트를 조회해서 전체 회원을 view page로 이동시키는 
		//	   비지니스 로직
		
		//1단계: DAO 객체와 연동
		MemberDAO dao = MemberDAO.getInstance(); //싱글턴방식으로 객체 생성법
		
		//2단계: Member 테이블의 회원 전체 목록을 조회하는 메서드
		List<MemberDTO> memberlist = dao.getMemberList();
		
		//3단계: DB에서 가져온 회원 전체 목록을 view page로 이동
		// ==>바인딩 직업
		request.setAttribute("List", memberlist);
		
		//4단계: 페이지 이동 경로 설정 및 이동 진행
		request.getRequestDispatcher("view/member_list.jsp").forward(request, response);
		}

}
