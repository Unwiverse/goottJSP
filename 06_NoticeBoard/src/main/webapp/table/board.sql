-- board 테이블 만들기

create table board(
	board_no number(7) primary key,			--게시판 글 번호
	board_writer varchar2(50) not null,		--게시판 글 작성자
	board_title varchar2(200) not null,		--게시팔 글 제목
	board_cont varchar2(2000),				--게시팔 글 내용
	board_pwd varchar2(50) not null,		--게시판 글 비번
	board_hit number(5) default 0,			--게시팔 글 조회수
	board_date date,						--게시판 작성일자
	board_update date						--게시판 글 수정일자
	);
	--board 테이블에 게시글을 추가해보자
	insert into board 
		value(1, '홍길동', '제목1', '길동이 글입니다', '1111', default, sysdate, '');
	insert into board 
		value(2, '유관순', '만세 글', '대한독립만세글입니다', '2222', default, sysdate, '');
	insert into board 
		value(3, '세종대왕', '한글', '한글창제 글입니다', '3333', default, sysdate, '');
	insert into board 
		value(1, '이호성', '나랑께', '일가족 꽁끼깅깡범입니다', '4444', default, sysdate, '');
	insert into board 
		value(1, '김원', '제목2', '심영이 글입니다', '5555', default, sysdate, '');	
		
		
		