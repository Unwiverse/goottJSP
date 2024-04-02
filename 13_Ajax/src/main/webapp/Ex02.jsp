<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
function process() {
	$.ajax({
		type: "get",
		url: "data/test.jsp",
		data: {param: "Hello Ajax!"},
		dataType: "text",
		success: function(result) {
			$(".message").append(result);			
		},
		error: function() {
			alert("통신 요류인덧?");
		}
	});
	
}


</script>
</head>
<body>

	<div align="center">
		<br><br>
		<input type="button" value="전송" onclick="process()">
		<br><br>
		
		<div class="message">
			
		
		</div>
	
	</div>

</body>
</html>