package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 게시물 전체 목록 보여달라고 요청
		// 응답: board 테이블의 전체 게시물 목록을 조회해서 view page로 이동시키는 비지니스 로직
		// 이때 비지니스 로직 작업 시 페이징 처리 작업(?)도 같이 진행.
		
		//이전까지 하던 작업은 못씀
		//페이징 작업 진행.
		int rowsize = 3; //한 페이지당 보여줄 게시물의 수
		int block = 3; // 아래에 보여줄 페이지의 최대 블럭 수
		//예) [1][2][3] / [4][5][6] / [7][8][9]
		
		int totalRecord = 0;//board 테이블 상의 게시물의 총 수
		
		int allPage = 0;//전체 페이지 수
		
		int page = 0;//현재 페이지 변수 
		if(request.getParameter("page") != null) { // page 값이 넘어옴
			page = Integer.parseInt(request.getParameter("page").trim());
		} else {
			// 처음으로 main 페이지에서 "게시물 전체 목록" a 태그를 클릭한 경우
			page = 1;
		}
		//해당 페이지서 시작 번호 
		int startNo = (page * rowsize) - (rowsize -1);
		
		//해당 페이지에서 마지막 번호
		int endNo = (page*rowsize);
		
		//해당 페이지에서 시작 블럭 
		int startBlock = (((page -1)/block) *block) +1;
		
		//해당 페이지에서 끝 블럭
		int endBlock = (((page-1)/block)* block) +block; //(((1-1)/3)*3) +3; 
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//전체 게시물 수 확인 메서드 호출
		totalRecord = dao.getBoardCount();
		/*
		 * 전체 게시물 수를 한 페이지당 보여줄 게시물의 수로 나눠주면 전체 페이지 수가 나옴. 
		 * 이 때, 검색 전체 페이지 수가 나올 때 나머지가
		 * 있으면 무조건 전체 페이지 수를 하나 올려 주어야 함.
		 */
		allPage = (int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) { //
			endBlock = allPage;
		} 
		//현재 페이지에 해당하는 게시물을 가져오는 메서드
		List<BoardDTO> boardList = dao.getBoardList(page, rowsize);
		
		/*
		페이징 처리 작업 후에는 지금까지 작업했던 페이징 처리 시 
		모든 정보들을 view 페이지로 binding 시켜줌
		*/
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", boardList);
		
		ActionForward forward = new ActionForward();
		System.out.println("forward: "+forward);
		
		forward.setRedirect(false); //인자를 false로 하면 *.jsp 페이지로 넘어감
		forward.setPath("view/board_list.jsp"); //이동할 페이지 주소(경로)
		
		return forward; //주소값 리턴
	}

}
