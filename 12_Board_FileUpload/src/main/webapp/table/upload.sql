create table upload(
	upload_no number primary key, --자료실 글 번호 
	upload_writer varchar2(50) not null, -- 자료실 글 작성자
	upload_title varchar2(200) not null, -- 자료실 글 제목
	upload_cont varchar2(2000) not null, -- 자료실 글 내용
	upload_pwd varchar2(50) not null, -- 자료실 글 비밀번호
	upload_file varchar2(2000), -- 자료실 글 파일명
	upload_hit number default 0, -- 글 조회수
	upload_date date not null, -- 글 작성일자
	upload_update date  -- 글 수정일자
);