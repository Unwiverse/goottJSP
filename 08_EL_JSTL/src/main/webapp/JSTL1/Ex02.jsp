<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		포맷팅 라이브러리 종류
		- <fmt:formatNumber> : 표시할 숫자의 형식을 지정하는 태그.
		- <fmt:timeZone> : 지정한 국가의 시간을 지정하는 태그.
		                   태그를 열고 닫는 영역에서만 적용이 됨.
		- <fmt:setTimeZone> : 지정한 국가의 시간을 지정하는 태그.
		- <fmt:formatDate> : 지정한 형식의 날짜를 표시하는 태그.
	 --%>
	 
	 <%--
	 	 <fmt:formatNumber> 태그의 여러 가지 속성.
	 	 - value : 출력의 형식을 지정함.
	 	 - type : 츨력의 타입을 지정함.
	 	          * percent : %
	 	          * number : 숫자
	 	          * currency : 통화 형식
	 	 - currencyCode : 통화 코드를 지정함. 한국의 원화는 KRW임.
	 	 - currencySymbol : 통화를 표시할 때 사용할 기호를 표시함.
	 	 - var : <fmt:formatNumber> 태그의 결과를 저장할 변수의 이름을 지정.
	 	 - scope : 변수의 접근 범위를 지정함.
	 	 - pattern : 숫자가 출력될 양식을 지정함.
	  --%>
	  <div align="center">
	  		<hr color="lightblue">
	  			<h3>커피 주문 폼</h3>
	  		<hr color="lightred">
	  		<br>
	  		
	  		<form method="post" action="Ex03.jsp">
	  			<table border="1">
	  				<tr>
	  					<th>커피 종류</th>
	  					<td>
  						<select name="coffee_str">
  							<option value="1">아메리카노 - 3000원</option>
  							<option value="2">렜비 - 40000원</option>
  							<option value="3">고티카 - 600000원</option>
  							<option value="4">팟쿠찌 - 55000원</option>
	  						
	  						</select>
	  					</td>
	  				</tr>
	  				
	  				<tr>
	  					<th>커피 수량</th>	
	  						<td>
	  						<input type="number" min="1" max="100" name="coffee_amount">
	  						</td>
	  				</tr>
	  				
	  				<tr>
	  					<td colspan="2" align="center">
	  						<input type="submit" value="계산">&nbsp;&nbsp;&nbsp;
	  						<input type="reset" value="치소">
	  					</td>
	  				</tr>
	  			</table>
	  		
	  		</form>
	  </div>
	  
</body>
</html>