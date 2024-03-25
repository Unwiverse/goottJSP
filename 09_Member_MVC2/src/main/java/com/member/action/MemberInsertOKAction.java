package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberInsertOKAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원 추가 폼 페이지에서 넘어온 데이터들을 member 테이블에 회원으로 추가시키는 비지니스 로직
		
		//1단계: 회원추가 폼 페이지에서 넘어온 데이터들을 받아줘야됨
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age =  Integer.parseInt(request.getParameter("mem_age").trim());
		String member_job = request.getParameter("mem_job").trim();
		String member_addr = request.getParameter("mem_addr").trim();
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		
		MemberDTO dto = new MemberDTO();
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		MemberDAO  dao = MemberDAO.getInstance();
		
		int check = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('됨')");
			out.println("location.href='select.go'");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('안됨')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
