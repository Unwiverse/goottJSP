<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	let xmlHttp; //전역변수 
	
	function process() {
		//1. XMLhttpRequest 객체 생성 
		xmlHttp = new XMLHttpRequest();
		
		//2. 요청에 대한 응답 처리 이벤트 리스너 등록 
		xmlHttp.onreadystatechange =  on_ReadyStateChange;
		
		//3. 서버로 보낼 데이터 생성
		let data = "param=Hello Ajax!";
		
		/* 4. get 방식으로 데이터를 보내고 응답은 비동기식으로 받음
		   클라이언트와 서버 간의 연결 요청 준비
		   open(): 요청 방식(get, post)과 요청되는 페이지(url)
		           그리고 동기 또는 비동기식 방식을 설정하는데 쓰는 함수
		   ==> data/test.jsp?param=Hello Ajax!
		*/
	    xmlHttp.open("GET", "data/test.jsp?"+data);
		   
		//5. 실제 데이터 전송
		//   send(): open 함수에서 지정한 url에 대한 연결을 진행함.
		xmlHttp.send(null);
	}
	
	//응답 처리 이벤트 리스너 함수
	function on_ReadyStateChange(){
		//4. 데이터 전송 완료(0: 초기화 전, 1: 로딩 중, 2: 로딩 완료, 3:대화상태)
		if(xmlHttp.readyState ==4) {
			if(xmlHttp.status == 200) { //200: 서버 응답 성공
				let msg = document.querySelector(".message");
				msg.append(xmlHttp.responseText);
			} else {
				alert("처리 중 에러 발생");
			}
			}
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