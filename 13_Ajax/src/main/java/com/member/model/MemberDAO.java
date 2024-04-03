package com.member.model;

import java.sql.*;
import java.util.*;

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
	    //회원 가입 시 중복 아이디 체크를 처리하는 메서드. 
	    public int checkMemberId(String id) {
	    	int result = 0; //아이디 중복 여부 체크 변수
	    	openConn();
	    	
	    	sql = "select * from member where memid =?";
	    	try {
	    		
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1, id);
	    	 rs = pstmt.executeQuery();
	    	 if(rs.next()) { //중복이 되는 경우
	    		 result = -1;
	    	 }
	    	 
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} finally {
	    		closeConn(pstmt, con, rs);
	    	} return result;
	    } //메서드 end
	    	//customer 테이블의 전체 고객을 조회하는 메서드.
	    	public String getCustomerList() {
	    		String result = "";
	    		
	    		openConn();
	    		
	    		sql = "select * from customer order by num desc";
	    		
	    		try {
	    			pstmt = con.prepareStatement(sql);
	    			rs = pstmt.executeQuery();
	    			
	    			result += "<customers>";
	    			
	    			while(rs.next()) {
	    				result += "<customer>";
	    				
	    				result += "<num>" + rs.getInt("num")+"</num>";
	    				result += "<id>" + rs.getString("id")+"</id>";
	    				result += "<name>" + rs.getString("name")+"</name>";
	    				result += "<age>" + rs.getInt("age")+"</age>";
	    				result += "<phone>" + rs.getString("phone")+"</phone>";
	    				result += "<addr>" + rs.getString("addr")+"</addr>";
	    				
	    				result += "</customer>";
	    			}
	    			
	    			result += "</customers>";
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return result;
	    	} //메서드 end
	    		//입력 폼에서 넘어온 아이디가 중복인지 아닌지 확인하는 메서드
	    		public String idCheck(String id) {
	    			String result = "";
	    			openConn();
	    			
	    			sql="select * from customer where id =?";
	    			
	    			try {
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setString(1, id);
	    			rs = pstmt.executeQuery();
	    			
	    			if(rs.next()) {
	    				//입력한 아이디가 테이블에 존재하는 경우 - 중복인 경우
	    				result = "중복 아이디입니다";
	    			}
	    			
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			} finally {
	    				closeConn(pstmt, con, rs);
	    			} return result;
	    			
	    		}//idCheck() 메서드 end
	    			//입력 폼에서 넘어온 데이터들을 customer 테이블에 저장하는 메서드
	    		public int insertCustomer(CustDTO dto) {
	    			int result=0, count=0;
	    			
	    			openConn();
	    			
	    			sql= "select max(num) from customer";
	    			
	    			try {
	    				pstmt= con.prepareStatement(sql);
	    				rs = pstmt.executeQuery();
	    				
	    				if(rs.next()) {
	    					count = rs.getInt(1)+1;
	    				}
	    				sql = "insert into customer values(?, ?, ?, ?, ?, ?)";
	    				pstmt = con.prepareStatement(sql);
	    				
	    				pstmt.setInt(1, count);
	    				pstmt.setString(2, dto.getId());
	    				pstmt.setString(3, dto.getName());
	    				pstmt.setInt(4, dto.getAge());
	    				pstmt.setString(5, dto.getPhone());
	    				pstmt.setString(6, dto.getAddr());
	    				result = pstmt.executeUpdate();
	    				
	    			}catch(Exception e) {
	    				e.printStackTrace();
	    			} finally {
	    				closeConn(pstmt, con, rs);
	    			} return result;
	    			
	    		} //메서드 end
	    			//넘겨받은 고객 번호에 해당하는 사람을 customer 테이블에서 없애버리는 메서드
	    		public int deleteCustomer(int no) {
	    			int result=0;
	    			
	    			openConn();
	    			sql ="delete from customer where num=?";
	    			try {
	    				pstmt = con.prepareStatement(sql);
	    				pstmt.setInt(1, no);
	    				result = pstmt.executeUpdate();
	    				
	    				if(result > 0) {
	    					sql = "update customer set num = num -1 "
	    							+ "where num > ?";
	    					pstmt = con.prepareStatement(sql);
		    				pstmt.setInt(1, no);
		    				pstmt.executeUpdate();
	    				} else { 
	    					
	    				}
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			} finally {
	    				closeConn(pstmt, con);
	    			} return result;
	    		} //메서드 end
	    		
	    	
}
