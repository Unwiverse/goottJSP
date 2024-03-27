<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    BoardDTO Content = (BoardDTO)request.getAttribute("Content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 내용</title>
</head>
<body>
    <div align="center">
        <hr>
        <h3>게시물 상세 내용</h3>
        <hr>
        <br><br>
        
        <table border ="1">
            <tr>
                <th>게시글 번호</th>
                <th>글 제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일자</th>
            </tr>
            
            <%
            if (Content != null) {
            %>
            <tr>
                <td> <%=Content.getBoard_no() %> </td>
                <td><%=Content.getBoard_title() %> </td> 
                <td> <%=Content.getBoard_writer() %> </td>
                <td> <%=Content.getBoard_hit() %> </td>
                <td> <%=Content.getBoard_date() %> </td>
            </tr>
            <%
            } else {
            %>
            <tr>
                <td align="center">
                    <h3>게시물이 없습니다.</h3>
                </td>
            </tr>
            <%
            }
            %>
        </table>
        
        <br>
        <input type="button" value="수정" onclick="location.href='modify.go?no=<%=Content.getBoard_no() %>'">&nbsp;&nbsp;
    	<input type="button" value="삭제" onclick="if(confirm('ㄹㅇ?')) { 
    	location.href='view/board_delete.jsp?no=<%=Content.getBoard_no() %>'
    	} else {return;}">&nbsp;&nbsp;
    	<input type="button" value="게시글목록" onclick="location.href='list.go'">
    </div>
</body>
</html>