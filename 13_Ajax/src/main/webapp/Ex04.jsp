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
			type:"post",
			url: "data/book.json",
			dataType: "json",
			success: function(result) {
				$.each(result, function(i) {
					let txt = "<tr><td>"+result[i].title+"</td>"+"<td>"+result[i].author+
					"</td>"+"<td>"+result[i].price+"</td></tr>";
					
				$(".table_body").append(txt);
				});
						
				
			},
			
			error: function() {
				alert("통신 오류");
			}
			
		});
	});

</script>
</head>
<body>
	<div align="center">
	
		<h1>BOOK 리스트</h1>
		
		<table border="1">
			<thead>
				<tr>
					<th>책 제목</th>
					<th>책 저자</th>
					<th>책 가격</th>
				</tr>
			</thead>
			
			<tbody class="table_body">
				
			</tbody>
			
			<tfoot>
			</tfoot>
		</table>
	
	</div>

</body>
</html>