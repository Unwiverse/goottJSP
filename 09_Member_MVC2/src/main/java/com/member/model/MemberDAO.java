package com.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
		
	
	    private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	    private String sql = null;

	    private static MemberDAO instance = null;

	    private MemberDAO() {}

	    public static MemberDAO getInstance() {
	        if (instance == null) {
	            instance = new MemberDAO();
	        }
	        return instance;
	    }

	    public void openConn() { // -JDBC 방식으로 DB와 연결했는데 이제 DBCP로 DB 연동작업 할거임
	        //1단계: JNDI 서버 객체 생성.
	    	try {
	    		//1단계: JNDI 서버객체 생성
	    		Context initCtx = new InitialContext();
	    		//2단계: Context 객체를 얻어와야함
	    		Context ctx = (Context)initCtx.lookup("java:comp/env");
	    		//3단계: lookup() 메서드를 이용해 매칭되는 커넥션을 찾아옴
	    		DataSource ds = (DataSource)ctx.lookup("jdbc/myoracle");
	    		//4단계: DataSource 객체를 이용해 커넥션을 하나 가져오면 됨
	    		con = ds.getConnection();
	    		
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    } //메서드 end

	    public void closeConn(PreparedStatement pstmt, Connection con, ResultSet rs) {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	            if (rs != null) rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } //메서드 end
	    
	    public void closeConn(PreparedStatement pstmt, Connection con) {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } //메서드 end
	    	//member 테이블에서 전체 회원 목록을 조회하는 메서드.
	    	public List<MemberDTO> getMemberList() {
	    		List<MemberDTO> list = new ArrayList<MemberDTO>();
	    		
	    		openConn();
	    		
	    		sql = "select * from member order by memno desc";
	    		try {
	    			pstmt = con.prepareStatement(sql);
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
    				list.add(dto);
	    			}
	    			
		    		} catch(Exception e) {
		    			e.printStackTrace();
		    		} finally {
		    			closeConn(pstmt, con, rs);
		    		} return list;
	    		
	    	} //메서드 end
	    		//회원 목록에서 이름에 해당하는 회원의 상세정보를 보여주는 메서드
    		public MemberDTO showInfo(int no) {
				MemberDTO content = new MemberDTO();
    			openConn();
    			sql = "select * from member where memno=? ";
    			
			  try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs= pstmt.executeQuery();
				
				while(rs.next()) {
				content.setNum(rs.getInt("MEMNO"));
				content.setMemid(rs.getString("MEMID"));
				content.setMemname(rs.getString("MEMNAME"));
				content.setAge(rs.getInt("AGE"));
				content.setPwd(rs.getString("MEMPWD"));
				content.setMileage(rs.getInt("MILEAGE"));
				content.setJob(rs.getString("JOB"));
				content.setAddr(rs.getString("ADDR"));
				content.setRegdate(rs.getString("REGDATE"));
				}
    				
    			} catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con, rs);
    			} return content;
			} //메서드 end
    		
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
    			
    			openConn();
    			sql= "update member set memno = memno-1 where memno > ?";
					
					try {
						
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
}
