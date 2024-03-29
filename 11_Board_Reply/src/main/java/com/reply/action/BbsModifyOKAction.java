package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 data들을 받아 jsp_bbs 테이블에 수정하는 비지니스 로직.
		
		String bbs_writer = request.getParameter("writer").trim();
		String bbs_title = request.getParameter("title").trim();
		String bbs_cont = request.getParameter("cont").trim();
		String bbs_pwd = request.getParameter("pwd").trim();
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO dto = new BbsDTO(); //실 데이터 전송 객체
		
		dto.setBoard_no(bbs_no);
		dto.setBoard_writer(bbs_writer);
		dto.setBoard_title(bbs_title);
		dto.setBoard_cont(bbs_cont);
		dto.setBoard_pwd(bbs_pwd);
		int check = dao.updateBbs(dto);
		
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('됐음')");
			out.println("location.href='bbs_content.go?no="+dto.getBoard_no()+"'");
			out.println("</script>");
		} else if(check==-1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요~~~')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 수정 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		} return null;
	}

}
