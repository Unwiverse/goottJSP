package com.shop.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	
	private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String sql = null;

    private static UserDAO instance = null;

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
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
    
    //아이디와 비번을 넘겨받아 회원인지 뭐시깽인지 판별하는 메서드
    public int userCheck(String id, String pwd) {
    	int result=0;
    	
    	openConn();
    	
    	sql="select * from member where memid=?";
    	
    	try {
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs= pstmt.executeQuery();
    		
    		if(rs.next()) {
    			if(pwd.equals(rs.getString("mempwd"))) {//전부 다 일치할 때
    				result = 1;
    			} else {//비번이 틀림
    				result = -1;
    			}
    		} 
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		closeConn(pstmt, con, rs);
    	} return result;
    } //메서드 end
    //아이디에 해당하는 회원 정보를 조회하는 메서드
    public UserDTO getMember(String id) {
    	UserDTO dto = null;
    	
    	openConn();
    	sql = "select * from member where memid=?";
    	
    	try {
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs= pstmt.executeQuery();
    		if(rs.next()) {
    			dto = new UserDTO();
    			dto.setNum(rs.getInt("memno"));
    			dto.setMemid(rs.getString("memid"));
    			dto.setMemname(rs.getString("memname"));
    			dto.setPwd(rs.getString("mempwd"));
    			dto.setAge(rs.getInt("age"));
    			dto.setMileage(rs.getInt("mileage"));
    			dto.setJob(rs.getString("job"));
    			dto.setAddr(rs.getString("addr"));
    			dto.setRegdate(rs.getString("regdate"));
    		}
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		closeConn(pstmt, con, rs);
    	} return dto;
    } //메서드 end
		
}
