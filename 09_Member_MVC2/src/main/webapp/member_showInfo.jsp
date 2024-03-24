<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
</head>
<body>
    <div align="center">
        <hr>
        <h3>개인정보 무단 도용 시 법적 처벌 받습니다</h3>
        <hr>
        <br><br>
        
        <table border="1">
            <tr>
                <th>회원 번호</th>
                <th>회원 이름</th>
                <th>회원 직업</th>
                <th>회원 가입일</th>
            </tr>
            
            <c:set var="member" value="${Member}" />
            <c:if test="${!empty member}">
                    <tr>
                        <td>${member.getNum()}</td>
                        <td>${member.getMemname()}</td>
                        <td>${member.getJob()}</td>
                        <td>${member.getRegdate().substring(0, 10)}</td>
                    </tr>
                  
            </c:if>
            
            <c:if test="${empty member}">
            <tr>
                <td colspan="4" align="center">
                    <h3>회원이 없습니다.</h3>
                </td>
            </tr>
            </c:if>
          </table>
		<br><br> 
		
		  <c:url var="deleteURL" value="delete.go">
	       	<c:param name="num" value="${member.getNum()}" />
	   	  </c:url>   
    	      		
          <input type="button" value="회원삭제" 
          onclick="if(confirm('삭제하시겠습니까?')) {
           location.href='${deleteURL}' 
           } else {return;}">&nbsp;&nbsp;
        </div>
</body>
</html>
