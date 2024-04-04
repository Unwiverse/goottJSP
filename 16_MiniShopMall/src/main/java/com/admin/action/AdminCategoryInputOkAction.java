package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminCategoryInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 등록 폼 페이지에서 넘어온 데이터들을 
		// shop_category 테이블에 저장하는 비지니스 로직.
		String category_code =request.getParameter("category_code").trim();
		String category_name =request.getParameter("category_name").trim();
		
		CategoryDTO dto = new CategoryDTO();
		
		dto.setCategory_code(category_code);
		dto.setCategory_name(category_name);
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.insertCategory(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('됨')");
			out.println("location.href='admin_category_list.go'");
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
