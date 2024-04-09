package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;

public class UserCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 장바구니 번호에 해당하는 
		// 장바구니 내역을 shop_cart 테이블에서 삭제하는 또직
		int cart_no = Integer.parseInt(request.getParameter("num").trim());
		
		CartDAO dao = CartDAO.getInstance();
		
		int check = dao.deleteCart(cart_no);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			dao.updateSequence(cart_no);
			
			out.println("<script>");
			out.println("alert('됨')");
			out.println("location.href='user_cart_list.go'");
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
