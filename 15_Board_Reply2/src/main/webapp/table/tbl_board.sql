-- tbl_board 테이블
create table tb_board(
	bno number primary key,	
	writer varchar2(50) not null,
	title varchar2(100) not null,
	content varchar2(1000) not null,
	pwd varchar2(50) not null,
	regdate date not null, 
	reupdate date 
);
insert into tbl_board values(1, '홍길동', '제목1', '내용1', '1111', sysdate, '');
insert into tbl_board values(2, '테일러스위프트', '제목2', '내용3', '2222', sysdate, '');
insert into tbl_board values(3, '티리엘', '제목3', '내용3', '3333', sysdate, '');

