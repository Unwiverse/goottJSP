<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(f) {
			//아이디 입력 여부 확인 방법
			if(f.id.value == "") {
				alert("회원 아이디를 입력하세요");
				f.id.focus();
				return false; //액션 페이지로 이동 방지
				
			}
			
			if(f.password.value == "") {
				alert("회원 비밀번호를 입력하세요");
				f.password.focus();
				return false; //액션 페이지로 이동 방지
				
			}
			
			if(f.name.value == "") {
				alert("회원 이름을 입력하세요");
				f.name.focus();
				return false; //액션 페이지로 이동 방지
				
			}
			
			if(f.phone.value == "") {
				alert("회원 전화번호를 입력하세요");
				f.phone.focus();
				return false; //액션 페이지로 이동 방지
				
			}
			if(f.addr.value == "") {
				alert("회원 주소를 입력하세요");
				f.addr.focus();
				return false; //액션 페이지로 이동 방지
				
			}
			f.method = "post";
			f.action= "/01_Servlet/member";
			f.submit();
			
	}

</script>
</head>
<body>
<div align="center">
     <hr>
     <h2>회원 정보 입력 폼 페이지</h2>
     <hr>
     <br><br>
     <form>
     <table border="1">
         <tr>
             <th>회원 아이디</th>
             <td>
                 <input type="text" name="id">
             </td>
         </tr>
         <tr>
             <th>회원 비밀번호</th>
             <td>
                 <input type="password" name="password">
             </td>
         </tr>
         <tr>
             <th>회원 이름</th>
             <td>
                 <input type="text" name="name">
             </td>
         </tr>
         <tr>
             <th>회원 연락처</th>
             <td>
                 <input type="text" name="phone">
             </td>
         </tr>
         <tr>
             <th>회원 주소</th>
             <td>
                 <input type="text" name="addr">
             </td>
         </tr>
         <tr>
             <td colspan="2" align="center">
                 <input type="button" value="회원가입"
                 onclick="check(this.form)">&nbsp;&nbsp;&nbsp;
                 <input type="reset" value="다시 작성">
             </td>
         </tr>
     </table>
     </form>
 </div>

</body>
</html>