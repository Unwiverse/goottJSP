--shop_cart 테이블 

create table shop_cart(
	cart_num number primary key,			-- 장바구니 번호
	cart_pnum number not null,				-- 장바구니 제품번호
	cart_userid varchar2(50) not null,		-- 장바구니 사용자 아이디
	cart_pname varchar2(200) not null, 		-- 장바구니 제품 이름
	cart_pqty number not null,				-- 장바구니 제품 수량
	cart_price number not null,				-- 장바구니 제품 가격
	cart_pspec varchar2(500), 				-- 장바구니 제품 스펙
	cart_pimage varchar2(300)				-- 장바구니 제품 이미지
);