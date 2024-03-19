<%@page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberDTO member = (MemberDTO)request.getAttribute("member");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding: 5px;
        text-align: center;
    }
    table {
        width: 20%;
    }
</style>
</head>
<body>
	
<div align="center">
    <br>
    <hr style="color: blue" width="40%">
    <h1>MEMBER 상세정보</h1>
    <hr style="color: blue" width="40%">

    <table>
        <tr>
            <td>넘    버</td>
            <td><%=member.getNum()%></td>
        </tr>
        <tr>
            <td>아 이 디</td>
            <td><%=member.getMemid()%></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><%=member.getPwd()%></td>
        </tr>
        <tr>
            <td>나   이</td>
            <td><%=member.getAge()%></td>
        </tr>
        <tr>
            <td>이   름</td>
            <td><%=member.getMemname()%></td>
        </tr>
        <tr>
            <td>마일리지</td>
            <td><%=member.getMileage()%></td>
        </tr>
        <tr>
            <td>직 업</td>
            <td><%=member.getJob()%></td>
        </tr>
        <tr>
            <td>주 소</td>
            <td><%=member.getAddr()%></td>
        </tr>
        <tr>
            <td>가입 날짜</td>
            <td><%=member.getRegdate()%></td>
        </tr>

    </table>
    
    <br>
		<input type="button" value="회원수정" 
		onclick="location.href='update.go?num=<%= member.getNum() %>'">&nbsp;&nbsp;
		&nbsp;&nbsp;
		
		<input type="button" value="회원삭제" 
		onclick="if(confirm('ㄹㅇ?')) {
			location.href='delete.go?num=<%=member.getNum() %>' 
			}else {return;}">&nbsp;&nbsp;
			
		<input type="button" value="회원목록" onclick="location.href='select.go'">
		
</div>
	

</body>
</html>