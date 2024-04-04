package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.AdminDTO;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 관리자 로그인 페이지에서 입력한 아이디와 비밀번호가
		// shop_admin 테이블의 id와 pwd가 일치하는지 여부를 확인해 
		//view 페이지(관리자 메인)로 이동시키는 비지니스 로직.
		String admin_id = request.getParameter("admin_id").trim();
		
		String admin_pwd = request.getParameter("admin_pwd").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		
		int check = dao.adminCheck(admin_id, admin_pwd);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		/*
		 * -쿠키보다 세션을 더 많이 쓰는 이유는 세션이 쿠키보다 보안이
		 *  좋아서다. 쿠키의 이름이나 데이터는 네트워크를 통해 전달이 돼서 
		 *  Http 프로토콜을 쓰는 경우 중간에 누군가 쿠키의 값을 읽어갈 수 있다.
		 *  근데 세션 값은 오로지 서버에만 저장돼서 중요한 데이터를 저장하기에
		 *  알맞다.
		 *  
		 * - 세션 설정법
		 *   request.getSession() ==> HttpSession이 있으면 현재 HttpSession을
		 *   반환하고 없으면 세션을 생성하는 메서드
		 *   
		 */
		HttpSession session = request.getSession();
		
		if(check >0) {
			//id랑 pwd가 일치하는 관리자. 그런 경우 그 자의 정보를 받자
			AdminDTO cont = dao.getAdmin(admin_id);
			
			//관리자 정보를 세션에 저장을 해서 앞으로 계속 쓰자
			session.setAttribute("adminId", cont.getAdmin_id());
			session.setAttribute("adminName", cont.getAdmin_name());
			//세션에 저장된 정보로 view page로 이동하자
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
			
		} else if(check == -1) {
			//아이디는 맞는데 비번이 틀림
			out.println("<script>");
			out.println("alert('관리자 pwd 불일치')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('그런 사람 없습니다')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
