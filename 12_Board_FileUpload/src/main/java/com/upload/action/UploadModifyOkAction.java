package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 자료실 게시판 폼 페이지에서 넘어온 데이터들을 upload 테이블에 저장시키는 비지니스 로직.
		UploadDTO dto = new UploadDTO();
		
		//파일 업로드 할 땐 설정할 내용이 있음.
		// 1. 첨부파일이 저장될 경로 설정.
		String saveFolder = "C:\\NCS\\workspace(jsp)\\12_Board_FileUpload\\src\\main\\webapp\\upload";
		
		// 2. 첨부 파일의 크기 설정.
		int fileSize = 10 *  1024 * 1024; //1024 = 1KB, 1024*1024 = 1MB, 10 * 1024 * 1024 = 10MB
		
		//3. MultipartRequest 객체 생성
		// ==> 파일 업로드를 진행하기 위한 객체
		MultipartRequest multi = new MultipartRequest(
				request, //일반적인 request 객체
				saveFolder,  //첨부파일이 저장될 경로
				fileSize,  //  업로드할 첨부파일의 최대 크기
				"UTF-8", //문자 인코딩 방식
				new DefaultFileRenamePolicy() //첨부파일 이름이 같은 경우 중복안되게 설정하는 생성자
			); 
			//자료실 수정 게시판 폼 페이지에서 넘어온 데이터들을 받아줘야 한다.
			String upload_writer = multi.getParameter("upload_writer").trim();
			String upload_title = multi.getParameter("upload_title").trim();
			String upload_cont = multi.getParameter("upload_cont").trim();
			String upload_pwd = multi.getParameter("upload_pwd").trim();
			// 자료실 게시판 폼 페이지에서 type="file" 속성으로 된 데이터가 넘어온 경우엔 
			// getFile() 메서드를 받아줘야 한다.
			File upload_file =  multi.getFile("upload_file"); 
			
			//type="hidden"으로 넘어온 데이터들도 받아줘야한다.
			int upload_no = Integer.parseInt(multi.getParameter("upNo"));
			
			if(upload_file != null) { //첨부파일이 있으면
				//우선 첨부파일의 이름을 알아야된다. getName(): 첨부파일의 이름을 문자열로 반환해주는 메서드
				String fileName = upload_file.getName();
				
				System.out.println("첨부파일 이름: "+fileName);
				
				//날짜 객체 생성
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH)+1;
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				String homedir = saveFolder+"/"+year+"-"+month+"-"+day;
				
				//날짜 폴더 만들기
				File path1 = new File(homedir);
				
				if(!path1.exists()) { //폴더가 없으면
					path1.mkdir(); //make directory 약자 => 실제 폴더 만들기 메서드
				}
				//파일 만들기 --> 예) 홍길동_파일명
				String reFileName = upload_writer+"_"+fileName;
				upload_file.renameTo(new File(homedir+"/"+reFileName));
				
				//upload 테이블에 upload_file 컬럼에 들어가는 파일 이름.
				//"2024_04_01/홍길동_파일명"으로 저장할 거 ㅎ
				
				String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
				
				dto.setUpload_file(fileDBName);
			}
				dto.setUpload_no(upload_no);
				dto.setUpload_writer(upload_writer);
				dto.setUpload_title(upload_title);
				dto.setUpload_cont(upload_cont);
				dto.setUpload_pwd(upload_pwd);
				
				UploadDAO dao = UploadDAO.getInstance();
				
				int check = dao.modifyUpload(dto);
				
				PrintWriter out = response.getWriter();
				
				if(check>0) {
					out.println("<script>");
					out.println("alert('됨')");
					out.println("location.href='upload_content.go?no="+upload_no+"'");
					out.println("</script>");
				} else if(check == -1){
					out.println("<script>");
					out.println("alert('비번틀림')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('실패')");
					out.println("history.back()");
					out.println("</script>");
				}
	
				return null;		
	}
	}


