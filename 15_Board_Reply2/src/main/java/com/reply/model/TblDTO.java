package com.reply.model;

public class TblDTO {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private String pwd;
	private String regdate;
	private String reupdate;
	private int regCnt;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReupdate() {
		return reupdate;
	}
	public void setReupdate(String reupdate) {
		this.reupdate = reupdate;
	}
	public int getRegCnt() {
		return regCnt;
	}
	public void setRegCnt(int regCnt) {
		this.regCnt = regCnt;
	}
	
}
