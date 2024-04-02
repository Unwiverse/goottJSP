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
		
		
		$("#idcheck_btn").mouseover(function() {//아이디 중복 체크 버튼에 마우스 올리면
			$("#idcheck").hide(); //span 태그를 숨기는 기능
			let userId = $("#member_id").val();
			
			//입력된 값의 길이 체크하는 방법
			if($.trim($("#member_id").val()).length< 4) {
				let warningTxt = '<font color="red">아이디는 4자 이상이어야 합니다.</font>';
				
				$("#idcheck").text(""); //span 태그 영역 초기화.
				$("#idcheck").show(); // span 태그 보여주는 기능.
				$("#idcheck").append(warningTxt);
				$("#member_id").val("").focus();
				
				return false; 
			}
			
			if($.trim($("#member_id").val()).length > 16) {
				let warningTxt = '<font color="red">아이디는 12+4자 이하여야 합니다.</font>';
				
				$("#idcheck").text(""); //span 태그 영역 초기화.
				$("#idcheck").show(); // span 태그 보여주는 기능.
				$("#idcheck").append(warningTxt);
				$("#member_id").val("").focus();
				
				return false; 
			}
			//아이디 중복 여부 확인 - ajax 기술을 써서 진행
			$.ajax({
				type: "post",
				url: "data/idCheck.jsp",
				data: {paramId: userId},
				dataType: "text", 
				success: function(res) {
					if(res ==-1) {
						//중복이면
						let warningTxt = '<font color="red">사용 불가</font>';
						
						$("#idcheck").text(""); //span 태그 영역 초기화.
						$("#idcheck").show(); // span 태그 보여주는 기능.
						$("#idcheck").append(warningTxt);
						$("#member_id").val("").focus();
					} else {
						//사용가능하면
						let warningTxt = '<font color="red">사용 가능</font>';
						
						$("#idcheck").text(""); //span 태그 영역 초기화.
						$("#idcheck").show(); // span 태그 보여주는 기능.
						$("#idcheck").append(warningTxt);
					}
				},
				error: function() {
					alert("데이터 통신 오류");ㅣ
				}
			});
			
		});
	});

</script>
</head>
<body>

		<div align="center">
			<hr>
				<h2>회원 가입 폼 페이지</h2>
			<hr>
			<br><br>
			
			<form method="post" action="">
				<table border="1">
					<tr>
						<th>회원 아이디</th>
						<td>
							<input name="member_id" id="member_id">
							<input type="button" value="아이디 중복 체크" id="idcheck_btn"><br>
							<span id="idcheck"></span>
							
						</td>
					</tr>
				</table>
			
			
			</form>
			
		</div>

</body>
</html>