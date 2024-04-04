-- shep_admin 테이블
create table shop_admin(
	admin_id varchar2(50) primary key, --관리자 아이디
	admin_pwd varchar2(50) not null, --비번
	admin_name varchar2(50) not null, --이름
	admin_email varchar2(100), --이메일
	admin_phone varchar2(20), --전번
	admin_date date  -- 등록일자
);

-- 관리자 등록(데이터 삽입)
insert into shop_admin 
	values('admin', 'admin1234', '관리자1', 'admin@gmail.com', '010-1111-1234', sysdate);


