<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<String> jList = (List<String>)request.getAttribute("jList");

	List<EmpDTO> mList = (List<EmpDTO>)request.getAttribute("mList");
	
	List<DeptDTO> dList = (List<DeptDTO>)request.getAttribute("dList");
	
	EmpDTO content = (EmpDTO)request.getAttribute("Modify");

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
	      <h3>EMP 테이블 사원 정보 수정 폼 페이지</h3>
	   <hr width="30%" color="gray">
	   <br> <br>
	   
  <form method="post"
     action="<%=request.getContextPath() %>/update_ok">
  
     <table border="1" width="350">
        <%
          if(content != null) {
        %>
        	  <tr>
        	     <th>사원 No.</th>
        	     <td>
        	        <input type="text" name="num" readonly
        	        	value="<%=content.getEmpno() %>">
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>사원 이름</th>
        	     <td>
        	        <input type="text" name="name" readonly
        	        	value="<%=content.getEname() %>">
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>담당 업무</th>
        	     <td>
        	        <select name="job">
        	           <%
        	              if(jList.size() == 0) {
        	           %>
        	           	  <option value="">:::담당업무 없음:::</option>
        	            	  
        	           <% }else {  // 담당업무 전체 리스트가 있는 경우
        	        	   
        	        	   for(int i=0; i<jList.size(); i++) {
        	        			String strJob = jList.get(i);
        	        			
        	        			if(strJob.equals(content.getJob())) {
        	           %>
         	           			<option value="<%=strJob %>" selected>
         	           				<%=strJob %> </option>
        	           <%		}else {  %>
        	        	   			<option value="<%=strJob %>">
         	           				<%=strJob %> </option>
        	          <% }
        	        	   }
        	           }
        	           
        	           %>
        	        </select>
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>관리자 No.</th>
        	     <td>
        	        <select name="mgrNo">
        	           <%
        	             if(mList.size() == 0) {
        	           %> 
        	                <option value="">:::담당 관리자 없음:::</option>	
        	           <%}else {  // 담당 관리자 리스트가 있는 경우
        	        	   
        	        	   for(int i=0; i<mList.size(); i++) {
        	        		   
		         	       EmpDTO mgrCont = mList.get(i);
		         	       
		         	       if(mgrCont.getEmpno() == content.getMgr()) {
		           %>
		           			<option value="<%=mgrCont.getEmpno() %>" selected>
		           				<%=mgrCont.getEmpno() %>&nbsp; [<%=mgrCont.getEname() %>] </option> 	   
		           <%      }else {  %>
		        	        <option value="<%=mgrCont.getEmpno() %>">
		           				<%=mgrCont.getEmpno() %>&nbsp; [<%=mgrCont.getEname() %>] </option> 	   
		           <%}
        	        	   }
        	           } %>
        
        	        </select>
        	  
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>사원 급여</th>
        	     <td>
        	        <input type="text" name="sal"
        	        	value="<%=content.getSal() %>">
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>사원 보너스</th>
        	     <td>
        	        <input type="text" name="comm"
        	        	value="<%=content.getComm() %>">
        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <th>부서 No.</th>
        	     <td>
        	        <select name="deptNo">
        	           <%
        	              if(dList.size() == 0) {
        	           %>
        	           		<option value="">:::부서번호 없음:::</option>
        	           
        	           <% }else {
        	        	   
        	        	    for(int i=0; i<dList.size(); i++) {
        	        	    	
        	        	    	DeptDTO dCont =	dList.get(i);
        	        	    	
        	        	    	if(dCont.getDeptno() == content.getDeptno()) {
        	           %>
        	           				<option value="<%=dCont.getDeptno() %>" selected>
        	           					<%=dCont.getDeptno() %>&nbsp;[<%=dCont.getDname() %>]</option>
        	           <%	   	}else {  %>
        	        	   			<option value="<%=dCont.getDeptno() %>">
        	           					<%=dCont.getDeptno() %>&nbsp;[<%=dCont.getDname() %>]</option>
        	           <%}
        	           
        	        	    }
        	           }
        	           %>
        	        </select>

        	     </td>
        	  </tr>
        	  
        	  <tr>
        	     <td colspan="2" align="center">
        	        <input type="submit" value="사원수정">&nbsp;&nbsp;&nbsp;
        	        <input type="reset" value="다시작성">
        	     </td>
        	  </tr>
        <%  }
        %>
	          
	      </table>
	   
	   </form>
	   
	</div>

</body>
</html>