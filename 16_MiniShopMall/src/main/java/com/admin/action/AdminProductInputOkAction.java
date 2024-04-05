package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼 페이지에서 넘어온 데이터들을 shop_products 테이블에 저장하는 비지니스 로직
		
		//첨부파일이 저장될 경로를 설정해줘
		String saveFolder = "C:\\NCS\\workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\16_MiniShopMall\\upload\\";
		
		//첨부파일 용량 제한(최대크기 설정)
		int fileSize = 1024*1024*10;
		
		//파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request, 	//request 객체
				saveFolder, //첨부파일이 저장될 경로
				fileSize,	//첨부파일의 최대 크기
				"UTF-8",  //한글 처리
				new DefaultFileRenamePolicy() //첨부파일 이름 중복 시 중복 안되게 고정
		);
		
		//상품 등록 폼 페이지서 넘어온 데이터들을 받자
		String product_name = multi.getParameter("p_name").trim();
		String product_category = multi.getParameter("p_category").trim();
		String product_company = multi.getParameter("p_company").trim();
		int product_pqty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int product_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String product_spec = multi.getParameter("p_spec").trim();
		String product_content = multi.getParameter("p_content").trim();
		int product_point = Integer.parseInt(multi.getParameter("p_point").trim());
		//getFilesystemName() ==> 업로드할 파일 이름을 문자열로 반환 쌉가능
		String product_image = multi.getFilesystemName("p_image");
		
		ProductDTO dto = new ProductDTO();
		
		dto.setPname(product_name);
		dto.setPcategory_fk(product_category);
		dto.setPcompany(product_company);
		dto.setPimage(product_image);
		dto.setPqty(product_pqty);
		dto.setPrice(product_price);
		dto.setPspec(product_spec);
		dto.setPcontent(product_content);
		dto.setPoint(product_point);
		
		
		ProductDAO dao = ProductDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		int check = dao.insertProduct(dto);
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('됨')");
			out.println("location.href='admin_product_list.go'");
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
