--tbl_reply 테이블 생성

create table tbl_reply(
	rno number primary key,						--답변글 번호
	bno number not null,						--원글 번호
	rewriter varchar2(50) not null,             --답변글 작성자
	recont varchar2(1000) not null,             --답변글 내용
	redate date not null,                       --답변글 작성일
	reupdate date,                              --댓글 수정일자
	--constraint reply_board_fk foreign key(bno)
	--references tbl_board(bno) 
);
	alter table tbl_reply add contraint reply_board_fk --뒤늦게 외래키를 추가할 때 쓸 수 있음. 
	foreign key(bno) references tbl_board(bno);