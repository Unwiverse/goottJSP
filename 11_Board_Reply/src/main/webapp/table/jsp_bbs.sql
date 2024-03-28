-- jsp_bbs 테이블
-- BBS(Built-in Board System: 전자 게시판)

create table jsp_bbs(
	board_no number primary key, 					--게시판 글번호
	board_writer varchar2(50) not null,				--게시판 글 작성자
	board_title varchar2(200) not null.				-- 글 제목
	board_cont varchar2(2000) not null,				-- 내용
	board_pwd varchar2(50) not null,				-- 비밀번호
	board_hit number default 0,						--조회수
	board_date date not null,						--작성일
	board_update date,								--수정일
	board_group number,								--그룹
	board_step number,								--글 답변 단계
	board_indent number								--답글 들여쓰기
);
	--jsp_bbs 테이블에 데이터 추가
	insert into jsp_bbs values(
		1, '홍길동', '제목1', '내용1', '1111', default, sysdate, '', 1, 0, 0);
		
	insert into jsp_bbs values(
		2, '김유신', '장군글', '그렇습니다', '2222', default, sysdate, '', 2, 0, 0);
		
	insert into jsp_bbs values(
		3, '유관순', '열사입니다', '대한독립만세', '3333', default, sysdate, '', 3, 0, 0);
		
	insert into jsp_bbs values(
		4, '김원', '원입니다', '별거없습니다', '4444', default, sysdate, '', 4, 0, 0);
		
	insert into jsp_bbs values(
		5, '신사임당', '박제됐습니다', '이게많으면좋습니다', '5555', default, sysdate, '', 5, 0, 0);
		