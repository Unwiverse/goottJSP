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
		String user = "goott";
		String password = "992992";
		
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
			   openConn();
			 
			 try {
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
		
		//
		
}
			
			
			
