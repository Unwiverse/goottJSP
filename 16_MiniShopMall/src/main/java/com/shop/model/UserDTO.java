package com.shop.model;

public class UserDTO {
		private int num;
		private String memid;
		private String memname;
		private String pwd;
		private int mileage;
		private int age;
		private String job;
		private String addr;
		private String regdate;
		
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getMemid() {
			return memid;
		}
		public void setMemid(String memid) {
			this.memid = memid;
		}
		public String getMemname() {
			return memname;
		}
		public void setMemname(String memname) {
			this.memname = memname;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public int getMileage() {
			return mileage;
		}
		public void setMileage(int mileage) {
			this.mileage = mileage;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getRegdate() {
			return regdate;
		}
		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}
}
