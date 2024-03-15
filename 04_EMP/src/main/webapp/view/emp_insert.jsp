<%@page import="com.emp.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   List<DeptDTO> dList =
                  (List<DeptDTO>)request.getAttribute("DeptList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <div align="center">
      <hr width="30%" color="gray">
         <h3>EMP 테이블 사원 등록 폼 페이지</h3>
      <hr width="30%" color="gray">
      <br> <br>
      
      <form method="post" action="<%=request.getContextPath() %>/insert_ok">
      
         <table border="1" width="350">
            <tr>
               <th>사원 No.</th>
               <td>
                  <input type="text" name="num">
               </td>
            </tr>
            
            <tr>
               <th>사원 이름</th>
               <td>
                  <input type="text" name="name">
               </td>
            </tr>
            
            <tr>
               <th>담당업무</th>
               <td>
                  <input type="text" name="job">
               </td>
            </tr>
            
            <tr>
               <th>관리자 No.</th>
               <td>
                  <input type="text" name="mgrNo">
               </td>
            </tr>
            
            <tr>
               <th>급 여</th>
               <td>
                  <input type="text" name="sal">
               </td>
            </tr>
            
            <tr>
               <th>보너스</th>
               <td>
                  <input type="text" name="comm">
               </td>
            </tr>
            
            <tr>
               <th>부서 No.</th>
               <td>
                  <select name="deptno">
                     <%
                        if(dList.size() == 0){
                           // 데이터가 없는 경우
                     %>
                        <option value= "">부서번호 없음</option>
                     
                     <%   
                     	   }else{
                           // 데이터가 있는 경우
                           
                           for(int i = 0; i<dList.size(); i++){
                              DeptDTO dto = dList.get(i);
                     %>
                           <option value="<%=dto.getDeptno() %>">
                              <%=dto.getDname() %>&nbsp;&nbsp;[<%=dto.getDeptno() %>]</option>
                     <%      
                     }
                           
                     }
                     %>
                  </select>
               </td>
               </tr>
               <tr>
                  <td colspan="2" align="center"> 
                   <input type="submit" value="사원등록">&nbsp;&nbsp;&nbsp;
                   <input type="reset" value="다시작성">
               </tr>
         </table>
         
      </form>
   </div>
</body>
</html>