package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 제품 번호에 맞는 제품의 정보를 
		// shop_product 테이블에서 조회해 수정 폼 페이지로 이동시키는 니비지스 조릭
		
		int product_no = Integer.parseInt(request.getParameter("num").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		
		ProductDTO content = dao.getProductContent(product_no);
		request.setAttribute("content", content);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_modify.jsp");
		
		return forward;
	}

}
