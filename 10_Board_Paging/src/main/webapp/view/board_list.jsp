<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@
        taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%>
	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
    
    <script type="text/javascript"></script>
<style>
        .pagination {
            justify-content: center;
		 }
        .table{
            width: 70%;
        }
    </style>
</head>
<body>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="<%=request.getContextPath() %>/write_ok.go">
			<table border="1">
				<tr>
					<th>작성자</th>
						<td>
							<input type="text" name="writer">
						</td>
				</tr>
				
				<tr>
					<th>글제목</th>
						<td>
							<input type="text" name="title">
						</td>
				</tr>
				
				<tr> 
					<th>글내용</th>
						<td>
							<textarea rows="7" cols="25" name="cont"></textarea>
						</td>
				</tr>
				
					<tr>
					
					<th>글비번</th>
						<td>
							<input type="password" name="pwd">
						</td>
					</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="완료">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			
			</table>
			<br><br>
		
		</form>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<div align="center">
    <hr>
    <h3>BOARD 테이블 게시물</h3>
    <hr>
    <br><br>
    
    <!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
	  Launch demo modal
	</button>

    <table border="1" width="800" class="table">
        <tr>
            <td colspan="5" align="left">
                전체 게시물 수: ${totalRecord }개
            </td>
        </tr>
        <tr>
            <th>게시물 번호</th> <th>게시글 제목</th>
            <th>게시글 조회수</th> <th>작성일자</th> <th>상세정보</th>
        </tr>

        <c:set var="list" value="${List }"></c:set>
        <c:if test="${!empty list }">
            <c:forEach items="${list }" var="dto">
                <tr>
                    <td> ${dto.getBoard_no() }</td>
                    <td> ${dto.getBoard_title() }</td>
                    <td> ${dto.getBoard_hit() }</td>
                    <td> ${dto.getBoard_date().substring(0, 10) }</td>
                    <td> <input type="button" value="상세정보"
                                onclick="location.href='content.go?num=${dto.getBoard_no()}'">
                    </td>
                </tr>

            </c:forEach>
        </c:if>
        <c:if test="${empty list }">
            <tr>
                <td colspan="5" align="center">
                    <h3>가져올 게시물이 없습니다</h3>
                </td>
            </tr>
        </c:if>
    </table>

    <nav aria-label="Page navigation example" style="text-align: center">
        <ul class="pagination">
            <c:if test="${page > block }">
            <li class="page-item"><a href="select.go?page=${startBlock -1 }">Previous</a></li>
            </c:if>
            <c:forEach begin="${startBlock}" end="${endBlock}" var="i">
                <c:if test="${i == page}">
                    <li class="page-item"><a class="page-link" href="select.go?page=${i}">[${i}]</a></li>
                </c:if>

                <c:if test="${i != page}">
                <li class="page-item"><a class="page-link" href="select.go?page=${i}">[${i}]</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${endBlock < allPage}">
            <li class="page-item"><a href="select.go?page=${page + 1}">Next</a></li>
            </c:if>
        </ul>
    </nav>
    <br><br>
    <input type="button" value="글쓰기" onclick="location.href='write.go'">
    <br><br>
    

    <br><br>

    <%-- 검색 관련 처리 부분 --%>
    <form method="post"
          action="<%=request.getContextPath() %>/search.go">
          

        <select name="field">
            <option value="title">제목</option>
            <option value="cont">내용</option>
            <option value="title_cont">제목+내용</option>
            <option value="writer">작성자</option>
        </select>

        <input type="text" name="keyword">&nbsp;&nbsp;&nbsp;
        <input type="submit" value="검색">
    </form>

</div>


</body>
</html>