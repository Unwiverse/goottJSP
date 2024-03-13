<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
     <hr width="30%" color="blue">
         <h2>학생 정보 입력 폼 페이지</h2>
     <hr width="30%" color="blue">
     <br><br>
     <form method="post" action="student">
     <table border="1">
         <tr>
             <th>학생 학번</th>
             <td>
                 <label>
                     <input type="text" name="id">
                 </label>
             </td>
         </tr>
         <tr>
             <th>학생 이름</th>
             <td>
                 <label>
                     <input type="text" name="name">
                 </label>
             </td>
         </tr>
         <tr>
             <th>학생 나이</th>
             <td>
                 <label>
                     <input type="text" name="age">
                 </label>
             </td>
         </tr>
         <tr>
             <th>학생 연락처</th>
             <td>
                 <label>
                     <input type="text" name="ph">
                 </label>
             </td>
         </tr>
         <tr>
             <th>전공 과목</th>
             <td>
                 <label>
                     <input type="checkbox" name="subject" value="java">java &nbsp;
                 </label>
                 <label>
                     <input type="checkbox" name="subject" value="python">python &nbsp;
                 </label>
                 <label>
                     <input type="checkbox" name="subject" value="HTML">HTML <br>
                 </label>
                 <label>
                     <input type="checkbox" name="subject" value="math">math &nbsp;
                 </label>
                 <label>
                     <input type="checkbox" name="subject" value="computerScience">computerScience &nbsp;
                 </label>
                 <label>
                     <input type="checkbox" name="subject" value="AI">AI &nbsp;
                 </label>
             </td>
         </tr>
         <tr>
             <td colspan="2" align="center">
                 <input type="submit" value="전송">
                 <input type="reset" value="취소">
             </td>
         </tr>
     </table>
     </form>
 </div>


</body>
</html>