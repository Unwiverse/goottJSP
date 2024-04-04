-- shop_products 테이블 만들기

create table shop_products(
	pnum number primary key,			--상품 번호
	pname varchar2(100) not null,		--상품명
	pcategory_fk varchar2(15) not null, --카테고리 코드
	pcompany varchar2(100),				--상품 제조사
	pimage varchar2(200),				--이미지 
	pqty number default 0,				--수량
	price number default 0,				--가격
	pspec varchar2(30),					--스펙
	pcontents varchar2(2000),			--설명
	point number default 0,				--포인트
	pinputdate date  					--입고일
);
