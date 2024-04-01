package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * get 방식으로 넘어온 게시글 번호에 해당하는 게시글을 
		 * upload 테이블에서 조회해 view page로 이동시키는 비지니스 로직
		 */
		int upload_no =  Integer.parseInt(request.getParameter("no").trim());
		
		UploadDAO dao = UploadDAO.getInstance();
		
		//조회수를 증가시키는 메서드 호출
		dao.readCount(upload_no);
		
		//번호에 해당하는 게시글 상세내역을 조회하는 메서드
		UploadDTO cont = dao.uploadContent(upload_no);
		
		request.setAttribute("cont", cont);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_content.jsp");
		
		return forward;
	}

}
