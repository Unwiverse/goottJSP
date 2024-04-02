/**
 *  customer 테이블을 쓴 ajax 실습
 */

$(function() {
	//여러 Ajax에서 공통적으로 사용되는 속성 설정.
	$.ajaxSetup({
		//POST 방식으로 데이터를 보낼 땐 헤더에 데이터가 저장돼서 전송
		// 헤더의 콘텐트 타입이 form-urlencoded 임을 지정해야됨.
		ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
		type: "post"
	});
	console.log('여기 오나?');
	//모든 데이터를 가져오는 함수.
	//customer 테이블에서 전체 데이터를 가져오는 함수.
	function getData() {
		$.ajax({
			url: "/13_Ajax/select.go",
			dataType: "xml",
			success: function(result) {
				console.log('여기 오나?2');
				//테이블 태그의 첫 번째 행(제목)을 제외하고 모든 행을 제거하는 명령어
				$("#listTable tr:gt(0)").remove();
				let table = "";

				$(result).find("customer").each(function() {

					table += "<tr>";

					table += "<td>" + $(this).find("num").text() + "</td>";
					table += "<td>" + $(this).find("id").text() + "</td>";
					table += "<td>" + $(this).find("name").text() + "</td>";
					table += "<td>" + $(this).find("age").text() + "</td>";
					table += "<td>" + $(this).find("phone").text() + "</td>";
					table += "<td>" + $(this).find("addr").text() + "</td>";

					table += "<td id='tdDel'> <input type='button' value='삭제'" +
						" id='del' num='" + $(this).find("num").text() + "'></td>";

					table += "</tr>";
				});
				//테이블의 첫 번째 행의 아래에 데이터를 추가해주면 됨
				$("#listTable tr:eq(0)").after(table);
			},
			error: function() {
				alert("데이터 동선 오류");
			}

		});
	}
	//아이디 중복 여부 확인
	$("#id").keyup(function() {

		$.ajax({

			url: "/13_Ajax/idCheck.go",
			data: { id: $("#id").val() },
			dataType: "text",
			success: function() {

			},
			error: function() {
				alert("데이터 통신 오류입니다");
			}
		});
	});
	//가입하기 버튼을 클릭했을 때 customer 테이블에 저장하기
	$("#btn").click(function() {
		$.ajax({
			url: "/13_Ajax/insert.go",
			data: $('#inForm').serialize(),
			dataType: "text",
			success: function(res) {
				if (res > 0) {
					alert('고객 등록 완료');
					//가입 완료 후엔 다시 전체 리스트를 받아서 화면에 출력해줘야함
					getData();

					//input 태그에 입력된 내용을 지워주자.
					$("input[type=text]").each(function() {
						$(this).val("");
					});
				} else {
					alert("고객 등록 실패");
				}
			},
			error: function() {
				alert("데이터 통신 오류입니다");
			}
		});
	});
});