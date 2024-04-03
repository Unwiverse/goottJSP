package com.reply2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.TblDAO;
import com.reply.model.TblDTO;

public class TblWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// jsp 파일에서 넘어온 내용을 받아 처리하는 비지니스 로직
		String tbl_writer = request.getParameter("writer");
		String tbl_title = request.getParameter("title");
		String tbl_content = request.getParameter("content");
		String tbl_pwd = request.getParameter("pwd");
		
		TblDTO dto = new TblDTO();
		
		PrintWriter out = response.getWriter();
		
		dto.setWriter(tbl_writer);
		dto.setTitle(tbl_title);
		dto.setContent(tbl_content);
		dto.setPwd(tbl_pwd);
		
		TblDAO dao = TblDAO.getInstance();
		
		int check = dao.insertTbl(dto);
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('됨')");
			out.println("location.href='tbl_list.go'");
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
