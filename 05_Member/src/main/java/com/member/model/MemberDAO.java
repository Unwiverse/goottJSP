package com.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MemberDAO {
	//DB와 연결하는 객체
	Connection con = null;
	//DB에 sql을 전송하는 객체
	PreparedStatement pstmt = null;
	//sql을 실행한 후 결과값을 갖고 있는 객체
	ResultSet rs = null;
	//sql문을 저장할 객체
	String sql= null;
	
	//MemberDAO 객체를 싱글턴 방식으로 만들자
	//1단계: MemberDAO객체를 정적(static) 멤버로 선언해줘야됨
	private static MemberDAO instance = null;
	//2단계: 싱글턴 방식으로 객체를 만드려면 우선 기본생성자의 접근지정자를 public 말고 
	//		private으로 바꿔줘야됨. 즉, 외부에서 직접 기본생성자에 호출 못하게 하는 방법이다.
	private MemberDAO() {} //public에서 바꿨음, 기본생성자
		// TODO Auto-generated constructor stub
	//3단계: 기본생성자 대신에 싱글턴 객체를 return해주는 getInstance()메서드를 만들어서 해당
	//		getInstance() 메서드를 외부에서 접근할수있게 됨.
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	//Db연동 작업 메서드
	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "operation";
		String password = "1234";
		
		try {
			//1단계: 오라클 드라이버를 메모리로 로딩 작업 진행.
			Class.forName(driver);
			//2단계: 오라클 DB와 연결 
			con = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	//openConn 메서드 end
	//DB에 연결된 자원 종료 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt != null)  pstmt.close();
			if(con != null) con.close();
			if(rs != null)  rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}//closeConn() 메서드 end
	} 
	public void closeConn(PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt != null)  pstmt.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}//closeConn() 메서드 end
	} 
		//member 테이블에서 회원 전체 목록을 조회하는 메서드.
		public List<MemberDTO> getMemberList() {
			   List<MemberDTO> list = new ArrayList<MemberDTO>();
			  //1~2단계: 오라클 드라이버 로딩 및 DB 연결 진행(한꺼번에)
			   
			 
			 try {
				 openConn();
			   //3단계:DB에 전송할 sql문
			   sql = "select * from member order by memno";
			   //4단계: sql문을 DB 전송 객체에 인자로 전달
			   pstmt = con.prepareStatement(sql);
			   //5단계: sql문을 DB에 전송 및 실행
			   rs= pstmt.executeQuery();
			   
			   while(rs.next()) {
				   MemberDTO dto = new MemberDTO();
				   dto.setNum(rs.getInt("memno"));
				   dto.setMemid(rs.getString("memid"));
				   dto.setMemname(rs.getString("memname"));
				   dto.setPwd(rs.getString("mempwd"));
				   dto.setAge(rs.getInt("age"));
				   dto.setMileage(rs.getInt("mileage"));
				   dto.setJob(rs.getString("job"));
				   dto.setAddr(rs.getString("addr"));
				   dto.setRegdate(rs.getString("regdate"));
				   list.add(dto);
				   }
			   
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//6단계: DB와 연결된 자원 종료
				closeConn(rs, pstmt, con);
			}
			return list;
			
		} //getMemberList() 메서드 end
		
		//member 테이블에 회원을 추가하는 메서드
		public int insertMember(MemberDTO dto) {
			int result = 0, count = 0;
			
			//1~2단계: 오라클 드라이버 로딩 및 DB연결 시행
			openConn();
			
			try {
			//3단계: DB에 전송할 sql문 작성
			sql = "select max(memno) from member";
			//4단계: sql문을 dB 전송 객체에 인자로 전달.
			pstmt = con.prepareStatement(sql);
			//5단계: sql문을 dB에 전송 및 실행
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			//3단계: DB에 전송할 sql문 작성
			sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			//4단계: sql문을 DB전송 객체에 인자로 전달
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count+1); 
			pstmt.setString(2, dto.getMemid()); 
			pstmt.setString(3, dto.getMemname()); 
			pstmt.setString(4, dto.getPwd()); 
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			//5단계: sql문을 dB에 전송 및 실행
			result= pstmt.executeUpdate();
			
			
					
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				//6단계: DB에 연결돼있던 자원 종료
				closeConn(rs, pstmt, con);
			}
			return result;
		}
		//멤버번호에 해당하는 회원의 상세정보를 띄우는 메서드
		public MemberDTO contentMember(int num) {
			MemberDTO member = new MemberDTO();
			//1~2단계: 오라클 드라이버 로딩 및 DB연결 시행
			openConn();
			
			sql = "select * from member where MEMNO=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					member.setNum(rs.getInt("MEMNO"));
					member.setMemid(rs.getString("MEMID"));
					member.setMemname(rs.getString("MEMNAME"));
					member.setAge(rs.getInt("AGE"));
					member.setPwd(rs.getString("MEMPWD"));
					member.setMileage(rs.getInt("MILEAGE"));
					member.setJob(rs.getString("JOB"));
					member.setAddr(rs.getString("ADDR"));
					member.setRegdate(rs.getString("REGDATE"));
				}
					
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return member;
			
		}
		//멤버번호에 해당하는 회원 정보 수정 메서드
		public int updateMember(MemberDTO dto) {
			int result = 0;
			
			openConn();
			
			// 3단계: 데이터베이스에 전송할 SQL문 작성.
			sql = "update member set memid=?, memname=?, mempwd=?, age=?, "
					+ "mileage=?, job=?, addr=? where memno=?";
			
			try {
			//4단계: sql문을 DB 전송 객체에 저장.
			pstmt = con.prepareStatement(sql);
			
			//4-1단계: SQL문을 DB데이터베이스 전송 객체에 저장
			pstmt.setString(1, dto.getMemid());
			pstmt.setString(2, dto.getMemname());
			pstmt.setString(3, dto.getPwd());
			pstmt.setInt(4, dto.getAge());
			pstmt.setInt(5, dto.getMileage());
			pstmt.setString(6, dto.getJob());
			pstmt.setString(7, dto.getAddr());
			pstmt.setInt(8, dto.getNum());
			
			//5단계: SQL문을 DB에 전송 및 실행.
			result = pstmt.executeUpdate();
			
	
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				//6단계: DB에 연결돼있던 자원 종료.
				closeConn(rs, pstmt, con);
			}
			return result;
			
		} //updateMember() 메서드 end
		//member 테이블에서 회원번호에 해당하는 회원을 삭제하는 메서드
		public int deleteMember(int no) {
			int result = 0;
			//1~2단계: 오라클 드라이버 로딩 및 연동 작업
			openConn();
			
			//3단계: DB에 전송할  sql문
			sql= "delete from member where memno=?";
			
			try {
			//4단계: SQL문을 DB 전송 객체의 인자로 전달 
			pstmt=con.prepareStatement(sql);
			//4-1단계: 플레이스 홀더에 데이터 배정
			pstmt.setInt(1, no);
			
			//5단계: sql문을 DB에 전송 및 실행
			result = pstmt.executeUpdate();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				//6단계: DB와 연결된 자원 종료
				closeConn(pstmt, con);
			}
			return result;
			
		} //deleteMember() 메서드 end
		
		//회원 번호 순번 작업 하는 메서드
		public void updateSequence(int no) {
			
			
			sql= "update member set memno = memno-1 where memno > ?";
			
			try {
				openConn();
			pstmt=con.prepareStatement(sql);
			//4-1단계: 플레이스 홀더에 데이터 배정
			pstmt.setInt(1, no);
			
			//5단계: sql문을 DB에 전송 및 실행
			pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			}
		} //메서드end
		
		//검색어에 해당하는 회원을 조회하는 메서드
		public List<MemberDTO> searchMemberList(String field, String keyword) {
			List<MemberDTO> searchList = new ArrayList<MemberDTO>();
			
			openConn();
			
			//3단계: Db에 전송할 sql문 작성
			sql= "select * from member";
			
			try {
			if(field.equals("id")) {
				sql += "where memid like ? ";
			} else if(field.equals("name")) {
				sql+= "where memname like ? ";
			} else if(field.equals("job")) {
				sql += "where job like ?";
			} else {
				sql += "where addr like ?";
			}
			
			sql += "order by memno desc";
					
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			
		
			rs = pstmt.executeQuery();
			
			  while(rs.next()) {
				   MemberDTO dto = new MemberDTO();
				   dto.setNum(rs.getInt("memno"));
				   dto.setMemid(rs.getString("memid"));
				   dto.setMemname(rs.getString("memname"));
				   dto.setPwd(rs.getString("mempwd"));
				   dto.setAge(rs.getInt("age"));
				   dto.setMileage(rs.getInt("mileage"));
				   dto.setJob(rs.getString("job"));
				   dto.setAddr(rs.getString("addr"));
				   dto.setRegdate(rs.getString("regdate"));
				   searchList.add(dto);
				   }
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return searchList;
		}
		
}
			
			
			
