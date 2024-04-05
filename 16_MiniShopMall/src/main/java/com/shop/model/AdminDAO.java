package com.shop.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class AdminDAO {
	 	private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs, rs1 = null;
	    private String sql = null;

	    private static AdminDAO instance = null;

	    private AdminDAO() {} //기본생성자

	    public static AdminDAO getInstance() {
	        if (instance == null) {
	            instance = new AdminDAO();
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
		/*
		 * 관리자 로그인 화면에서 입력한 id와 pwd로 관리자인지 여부를 확인하는 메서드
		 */
	    	public int adminCheck(String id, String pwd) {
	    		int result = 0;
	    		
	    		openConn();
	    		
	    		sql = "select * from shop_admin where admin_id =?";
	    		
	    		try {
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setString(1, id);
	    			
	    			rs = pstmt.executeQuery();
	    			
	    			if(rs.next()) {
	    				if(pwd.equals(rs.getString("admin_pwd"))) {
	    					//관리자인 경우(id와 pwd가 일치하는 경우)
	    					result = 1;
	    				} else { //pwd만 불일치
	    					result = -1;
	    				}
	    			} 
	    			
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return result;
	    	} //메서드 end
    		//관리자에 대한 상세 정보를 조회하는 메서드
    		public AdminDTO getAdmin(String id) {
    			AdminDTO dto = null;
    			openConn();
    			
    			sql = "select * from shop_admin where admin_id=?";
    			
    			try {
    				pstmt = con.prepareStatement(sql);
    				
    				pstmt.setString(1, id);
    				rs= pstmt.executeQuery();
    				
    				if(rs.next()) {
    					dto = new AdminDTO();
    					
    					dto.setAdmin_id(rs.getString(1));
    					dto.setAdmin_pwd(rs.getString(2));
    					dto.setAdmin_name(rs.getString(3));
    					dto.setAdmin_email(rs.getString(4));
    					dto.setAdmin_phone(rs.getString(5));
    					dto.setAdmin_date(rs.getString(6));
    				}
    				
    			} catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con, rs);
    			} return dto;
    		}//메섣 end
}
