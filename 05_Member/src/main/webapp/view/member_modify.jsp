<%@page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberDTO member = (MemberDTO)request.getAttribute("dto");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
    <br>
    <hr style="color: blue" width="40%">
    <h1>MEMBER 상세정보</h1>
    <hr style="color: blue" width="40%">
	<form method="get" action="<%=request.getContextPath()%>/update_ok">
	
    <table>
        <tr>
            <td>넘    버</td>
            <td><input type="text" name="memno" readonly value="<%=member.getNum()%>"></td>
        </tr>
        <tr>
            <td>아 이 디</td>
            <td><input type="text" name="memid" readonly value="<%=member.getMemid()%>"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name ="mempwd" value="<%=member.getPwd()%>"></td>
        </tr>
        <tr>
            <td>나   이</td>
            <td><input type="text" name ="age" value="<%=member.getAge()%>"></td>
        </tr>
        <tr>
            <td>이   름</td>
            <td><input type="text" name ="memname" value="<%=member.getMemname()%>"></td>
        </tr>
        <tr>
            <td>마일리지</td>
            <td><input type="text" name ="mileage" value="<%=member.getMileage()%>"></td>
        </tr>
        <tr>
            <td>직 업</td>
            <td><input type="text" name ="job" value="<%=member.getJob()%>"></td>
        </tr>
        <tr>
            <td>주 소</td>
            <td><input type="text" name ="addr" value="<%=member.getAddr()%>"></td>
        </tr>
        <tr>
            <td>가입 날짜</td>
            <td><input type="text" name ="regdate" value="<%=member.getRegdate()%>"></td>
        </tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="제출">
			</td>
		</tr>
    </table>
    </form>
    
   </div>

</body>
</html>