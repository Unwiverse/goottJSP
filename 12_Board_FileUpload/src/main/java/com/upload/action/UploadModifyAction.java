package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 번호에 해당하는 게시글을 upload 테이블에서 조회해서 해당 게시글 상세 내역을 
		// view page(수정 폼 페이지)로 이동시키는 비지니스 로직.
		
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		
		UploadDAO dao = UploadDAO.getInstance();
		
		//번호에 해당하는 게시글 상세내역을 조회하는 메서드 호출
		UploadDTO content = dao.uploadContent(upload_no);
		
		request.setAttribute("content", content);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_modify.jsp");
		
		return forward;
	}

}
