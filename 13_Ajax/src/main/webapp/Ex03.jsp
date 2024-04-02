<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
$(function() {
	$.ajax({
		type: "post",
		url: "data/book.xml",
		dataType: "xml",
		
		success: function(result) {
			$(result).find("book").each(function() {
				let title = $("title", this).text();
				let author = $("author", this).text();
				let price = $("price", this).text();
				
				let txt = "<li>책 제목: "+title+"</li>"+"<li>책 저자: "+author+"</li>"+"<li>책 가격:"+price+"</li><hr>";
				$("#display").append(txt);
			});
		},
		
		error: function(){
			alert("통신 오류입니다");
		}
	});
});


</script>


</head>
<body>
		<div align="center">
			<br><Br>
			
			<h1>BOOK 리스트</h1>
			<div id="display"></div>
		
		</div>

</body>
</html>