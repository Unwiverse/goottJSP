package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 제품 상세 내역에서 장바구니 버튼을 클릭하면 폼 태그 안에 있는 데이터들을 shop_cart 테이블에
		// 저장하는 비지니스 로직.
		
		//먼저 아래 세개는 폼에서 넘어온 정보들
		String product_name = request.getParameter("p_name").trim();
		
		int product_price = Integer.parseInt(request.getParameter("p_price").trim());
		
		int product_pqty = Integer.parseInt(request.getParameter("p_qty").trim());
		
		//type="hidden"으로 넘어온 정보들도 받아줘야 한다.
		int product_num = Integer.parseInt(request.getParameter("p_num").trim());
		String product_pspec = request.getParameter("p_spec").trim();
		String product_image = request.getParameter("p_image").trim();
		String user_id = request.getParameter("userId").trim();
		
		CartDTO dto = new CartDTO();
		
		dto.setCart_pnum(product_num);
		dto.setCart_userId(user_id);
		dto.setCart_pname(product_name);
		dto.setCart_pqty(product_pqty);
		dto.setCart_price(product_price);
		dto.setCart_pspec(product_pspec);
		dto.setCart_pimage(product_image);
		
		CartDAO dao = CartDAO.getInstance();
		
		int check = dao.insertCart(dto);
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
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
