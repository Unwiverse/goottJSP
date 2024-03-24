package com.member.action;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		// 요청: 제거하라 제거하라
		response.setContentType("text/html; charset=UTF-8");
		
		int member_no = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.deleteMember(member_no);
		
		
		try {
			PrintWriter out = response.getWriter();
			
			if(check >0) {
				dao.updateSequence(member_no);
				
				out.println("<script>");
				out.println("alert('완료')");
				out.println("location.href='select.go'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('에러')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return null;
			}

}
