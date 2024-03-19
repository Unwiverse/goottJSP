package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class UpdateOKServlet
 */
@WebServlet("/update_ok")
public class UpdateOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOKServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 수정 폼 페이지에서 넘어온 데이터를 DB에 수정해서 저장시키는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//1단계: member_modify에서 넘어온 값 받기
		String member_num = request.getParameter("memno").trim();
		
		String member_id = request.getParameter("memid").trim();
		
		String member_name = request.getParameter("memname").trim();
		
		String member_pwd = request.getParameter("mempwd").trim();
		
		String member_age = request.getParameter("age").trim();
		
		String member_mileage = request.getParameter("mileage").trim();
		
		String member_job = request.getParameter("job").trim();
		
		String member_addr = request.getParameter("addr").trim();
		
		String member_regdate = request.getParameter("regdate");
		
		// 2단계 : DB에 전송할 DTO 객체의 setter() 메서드의 인자로
				//        파라미터로 받은 값들을 저장한다.
		MemberDTO dto = new MemberDTO();
		
		dto.setNum(Integer.parseInt(member_num));
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(Integer.parseInt(member_age));
		dto.setMileage(Integer.parseInt(member_mileage));
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		dto.setRegdate(member_regdate);
		
		// 3단계 : DB 접근 객체를 생성해 주자.
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4단계 : DAO 객체의 메소드 호출 시 인자로
				//        DTO 객체를 넘겨주자.
		int check = dao.updateMember(dto);
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원 정보 수정 성공!!!')");
			out.println("location.href='content.go?num="+dto.getNum()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 정보 수정 실패~~~')");
			out.println("history.go(-1)");
			out.println("</script>");
	}

}}
