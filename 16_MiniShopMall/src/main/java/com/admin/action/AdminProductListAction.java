package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 전체 상품 목록을 shpp_products 테이블서 조회해 목록을 뷰 페이지로 넘김
		ProductDAO dao = ProductDAO.getInstance();
		
		List<ProductDTO> list =  dao.getProductList();
		
		request.setAttribute("ProductList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_list.jsp");
		
		return forward;
	}

}
