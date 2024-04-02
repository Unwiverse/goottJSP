-- customer 테이블 생성
create table customer(
	num number unique,					--고객 번호
	id varchar2(50) primary key,        --고객 아이디
	name varchar2(50) not null,			--이름
	age number(3),						--나이
	phone varchar2(20) not null,		--연락처
	addr varchar2(500) 					--주소
	);
	