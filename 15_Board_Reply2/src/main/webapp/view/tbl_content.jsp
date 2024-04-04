<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.js"></script>

</head>
<body>
		<div align="center">
			<c:set var="dto" value="${content }"/>
			<hr>
				<h2>${dto.getWriter() }이(가) 쓴 글</h2>
			<hr>
			<br><br>
			
			<table border="1">
				<tr>
					<th>번호</th>
					<td>${dto.getBno() }</td>
				</tr>
				
				<tr>
					<th>작성자</th>
					<td>${dto.getWriter() }</td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td>${dto.getTitle() }</td>
				</tr>
				
				<tr>
					<th>내용</th>
						<td>
						<textarea rows="7" cols="25" readonly>${dto.getContent() }</textarea>
						</td> 
				</tr>
				
				<tr>
					<th>비번</th>
					<td>
						<c:forEach begin="1" end="${dto.getPwd().length() }">
							*
						</c:forEach>
					</td>
				</tr>
				
				<tr>
					<th>작성일자</th>
					<td>${dto.getRegdate() }</td>
				</tr>
				
				<tr>
					<th>수정일자</th>
					<td>
					<c:if test="${empty dto.getReupdate() }">ㄴㄴ</c:if>
					<c:if test="${!empty dto.getReupdate() }">${dto.getReupdate() }</c:if>
					</td>
				</tr>
				<!--  데이터가 없으면 -->
				<c:if test="${empty dto }">
				<tr>
					<td>
					<h3>없음</h3>
					</td>
				</tr>
				</c:if>
			</table>
				<br><br>
			
				<input type="button" value="수정" 
				onclick="location.href='tbl_modify.go?no=${dto.getBno() }'">&nbsp;&nbsp;
				
				<input type="button" value="삭제" 
				onclick="if(confirm('ㄹㅇ?')) {
					location.href='tbl_modify.go?no=${dto.getBno() }'
				} else { return;}">&nbsp;&nbsp;
				
				<input type="button" value="목록으로" 
				onclick="location.href='tbl_list.go'">
				
				<br><br>
				<hr>
				<br><br>
				<%-- 여기는 댓글 폼 영역입니다 --%>
				<div>
					<table>
						<tr>
							<th>댓글 작성자</th>
							<td>
								<input type="text" name="re_writer" id="re_writer">
							</td>
						</tr>
						
						<tr>
							<th>댓글 내용</th>
							<td>
								<input type="text" name="re_content" id="re_content">
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="replyBtn" value="완료">
							</td>
						</tr>
					</table>
				
				</div><!--  댓글 form end -->
				<br>
				<h3>댓글 목록</h3>
				<div>
					<table class="replyList" width="400">
						<tr>
							<td colspan="2"><b>작성자</b></td>
						</tr>
						
						<tr>
							<td><b>댓글 내용</b></td> <td><b>작성일자</b></td>
						</tr>
					</table>
				</div>
				
			</div>
			<script type="text/javascript">
				$(function (){
					//ajax에서 동일하게 공통적으로 쓰이는 속성 설정
					$.ajaxSetup({
						//POST 방식으로 데이터를 보낼 땐 헤더에 데이터가 저장돼서 전송
						// 헤더의 콘텐트 타입이 form-urlencoded 임을 지정해야됨.
						ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
						type: "post"
					});
					//tbl_reply 테이블에 전체 댓글을 가져오는 함수
					function getList() {
						$.ajax({
							url: "<%=request.getContextPath() %>/reply_list.go",
							data: {rno: ${dto.getBno()}},
							dataType: "xml",
							success: function(res) {
								$(".replyList tr:gt(1)").remove();
								
								let table = "";
								
								$(res).find("reply").each(function() {
									table +="<tr>";
									table +="<td colspan='2'>"+$(this).find("rewriter").text()+"</td>"; 
									table +="</tr>";
									
									table +="<tr>";
									table += "<td>"+$(this).find("recont").text()+"</td>";
									table += "<td>"+$(this).find("redate").text()+"</td>";
									table += "</tr>";
									
									table +="<tr>";
									table += "<td colspan='2'>&nbsp;</td>";
									table += "</tr>";
								});
									$(".replyList tr:eq(1)").after(table);
									
								
							},
							error: function() {
								alert("데이터 통신 오류");
							}
						});
					}//함수 end
					
					//댓글 작성 버튼을 눌렀을 때 댓글 내용을 tbl_reply 테이블에 저장
					$("#replyBtn").on("click", function() {
						$.ajax({
							url:"<%=request.getContextPath()%>/reply_insert_ok.go",
							data: {
									writer: $("#re_writer").val(),
									content: $("#re_content").val(),
									 bno: ${dto.getBno() }
								},
							dataType: "text",
							success: function(res) {
								if(res>0) {
									alert("ㅀ");
									//댓글 작성 후 다시 전체 리스트를 화면에 보여주면 된다
									getList();
									//input 태그에 입력된 내용을 지워주자.
									$("input[type=text]").each(function() {
											$(this.val(""));
									});
								}
							},
							error: function() {
								alert("데이터 통신 오류");
							}
						});
					});
						getList();
					}); 
				
			</script>

</body>
</html>