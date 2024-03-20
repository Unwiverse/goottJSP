<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<BoardDTO> Search = (List<BoardDTO>)request.getAttribute("Search");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
		<div align="center">
	   <hr width="30%" color="tomato">
	      <h3>BOARD 테이블 검색 게시물 목록 페이지</h3>
	   <hr width="30%" color="tomato">
	   <br> <br>
	   
	   <table border="1" width="450">
	      <tr>
	         <th>게시글 No.</th> <th>글제목</th>
	         <th>작성자</th> <th>조회수</th> <th>작성일자</th>
	      </tr>
	      
	      <%
	        if(Search.size() != 0) {
	        	
	        	for(BoardDTO dto : Search) {
	      %>
	      			<tr>
	      			   <td> <%=dto.getBoard_no() %> </td>
	      			   <td> <%=dto.getBoard_title() %> </td>
	      			   <td> <%=dto.getBoard_writer() %> </td>
	      			   <td> <%=dto.getBoard_hit() %> </td>
	      			   <td> <%=dto.getBoard_date().substring(0, 10) %> </td>
	      			</tr>  		
	      			
	      <%    }  // for 문 end
	        	
	        }else {
	        	// 게시물 전체 목록이 없는 경우
	      %>
	      			<tr>
	      			   <td colspan="5" align="center">
	      			      <h3>검색 게시물 목록이 없습니다.....</h3>
	      			   </td>
	      			</tr>
	     <% } %>
	   </table>
	   
	   <br> <br>
	   
	   <input type="button" value="전체 게시물" onclick="location.href='list.go'">
	   
	</div>
		
</body>
</html>