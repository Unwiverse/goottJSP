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
 * Servlet implementation class InsertOKServlet
 */
@WebServlet("/insert_ok.go")
public class InsertOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertOKServlet() {
        super();
      
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청: member 테이블에 회원 등록 요청.
		//응답: member 테이블에 회원 등록 폼 페이지에서 넘어온 data를 저장.
		
		//한글 깨짐 방지 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); //==> 추가 및 수정 작업 시 필요한 작업
		
		//1단계: 회원 등록 폼 페이지에서 넘어온 데이터 받기
		String mem_id = request.getParameter("id").trim();
		String mem_name = request.getParameter("name").trim();
		String mem_pwd = request.getParameter("pwd").trim();
		int mem_age = Integer.parseInt(request.getParameter("age").trim());
		String mem_mileage = request.getParameter("mileage").trim();
		String mem_job = request.getParameter("job").trim();
		String mem_addr = request.getParameter("addr").trim();
		
		//2단계: DB에 전송할 DTO 객체의 setter 메서드에 인자로 파라미터로 받은 값들을 저장한다.
		MemberDTO dto = new MemberDTO();
		
		dto.setMemid(mem_id);
		dto.setMemname(mem_name);
		dto.setPwd(mem_pwd);
		dto.setAge(mem_age);
		dto.setMileage(Integer.parseInt(mem_mileage));
		dto.setJob(mem_job);
		dto.setAddr(mem_addr);
		
		//3단계: DB 접근 객체 생성
		MemberDAO dao = MemberDAO.getInstance();
		
		//4단계: DAO 접근 객체를 생성해 호출 시 인자로
		// 		DTO 객체를 넘겨주자
		int check = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회성')");
			out.println("location.href='select.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회실')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
