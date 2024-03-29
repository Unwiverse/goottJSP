package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsDeleteOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 데이터들을 가지고
		// jsp_bbs 테이블에서 해당 게시글을 삭제하는 비지니스 로직.
		String bbs_pwd = request.getParameter("pwd").trim();
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO dto = new BbsDTO();
		
		int check = dao.deleteBbs(bbs_no, bbs_pwd, dto.getBoard_group(), dto.getBoard_step());
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			//게시글 원글이 삭제되면
			out.println("<script>");
			out.println("alert('목표를 포착했다')");
			out.println("location.href='bbs_list.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('무, 무슨?')");
			out.println("history.back()");
			out.println("</script>");
		} return null;
		
		
		
	}

}
