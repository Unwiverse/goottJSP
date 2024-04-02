package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글 번호와 비밀번호를 갖고 upload 테이블에서 게시글을 삭제하는 비지니스 로직
		String upload_pwd = request.getParameter("pwd").trim();
		int upload_no = Integer.parseInt(request.getParameter("upload_no").trim());
		
		UploadDAO dao = UploadDAO.getInstance();
		
		//글번호에 해당하는 게시글의 상세 내역을 조회하는 메서드 호출
		UploadDTO cont=  dao.uploadContent(upload_no);
		//upload 폴더에 올라간 파일까지 삭제
		String upload = "C:\\Users\\goott3\\git\\goottJSP\\12_Board_FileUpload\\src\\main\\java\\com\\upload\\controller\\mapping.properties";
		
		//상세내역에서 업로드돼있던 첨부파일 가져오기
		String filename = cont.getUpload_file();
		
		PrintWriter out = response.getWriter();
		
		if(upload_pwd.equals(cont.getUpload_pwd())) {
			int check = dao.deleteUpload(upload_no);
			//실제 첨부파일까지 삭제
			if(filename != null) { //있으면 
				File file = new File(upload+filename);
				file.delete(); //파일 제거 메소드
				
			} 
			if(check >0) {
				dao.updateSequence(upload_no);
				
				out.println("<script>");
				out.println("alert('성공')");
				out.println("location.href='upload_list.go'");
				out.println("</script>");
				
			} else {
				out.println("<script>");
				out.println("alert('실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} else {
			out.println("<script>");
			out.println("alert('비번틀림')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
