package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;

public class AdminCategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 카테고리 번호에 맞는 카테고리를 shop_category 테이블에서 삭제하는 비지니스 로직
		int category_no = Integer.parseInt(request.getParameter("cnum").trim());
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.deleteCategory(category_no);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			dao.updateSequence(category_no);
			
			out.println("<script>");
			out.println("alert('카테고리 코드 삭제 성공')");
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
