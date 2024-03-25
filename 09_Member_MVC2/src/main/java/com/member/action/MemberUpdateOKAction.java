package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateOKAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int member_num = Integer.parseInt(request.getParameter("memno").trim());
		String member_id = request.getParameter("memid").trim();
		String member_name = request.getParameter("name").trim();
		int member_age = Integer.parseInt(request.getParameter("age").trim());
		String member_pwd = request.getParameter("pwd").trim();
		int member_mileage =  Integer.parseInt(request.getParameter("mileage").trim());
		String member_job = request.getParameter("job").trim();
		String member_addr = request.getParameter("addr").trim();
		String member_regdate = request.getParameter("regdate").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(member_num);
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setAge(member_age);
		dto.setPwd(member_pwd);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		dto.setRegdate(member_regdate);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.updateMember(dto);
		PrintWriter out = response.getWriter();
		
		if(check>0 ) {
			out.println("<script>");
			out.println("alert('완뇨')");
			out.println("location.href='info.go?num="+dto.getNum()+"'");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('ㅋㅋ')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
