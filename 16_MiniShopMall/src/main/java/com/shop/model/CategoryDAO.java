package com.shop.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class CategoryDAO {
	 private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs, rs1 = null;
	    private String sql = null;

	    private static CategoryDAO instance = null;

	    private CategoryDAO() {} //기본생성자

	    public static CategoryDAO getInstance() {
	        if (instance == null) {
	            instance = new CategoryDAO();
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
    	//카테고리 테이블에 카테고리를 추가하는 메서드
    	public int insertCategory(CategoryDTO dto) {
    		int result=0, count=0;
    		
    		openConn();
    		sql = "select max(category_num) from shop_category";
    		
    		try {
    			pstmt=con.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			
    			if(rs.next()) {
    				count = rs.getInt(1)+1;
    			}
    			sql = "insert into shop_category values(?, ?, ?)";
    			
    			pstmt=con.prepareStatement(sql);
    			pstmt.setInt(1, count);
    			pstmt.setString(2, dto.getCategory_code());
    			pstmt.setString(3, dto.getCategory_name());
    			
    			result = pstmt.executeUpdate();
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		} finally { 
    			closeConn(pstmt, con, rs);
    		} 
    		
    		return result;
    		
    	}//메서드 end
    	
		//shop_category 테이블에 있는 카테고리 전체 목록을 조회하는 메서드.
		public List<CategoryDTO> getCategoryList() {
			List<CategoryDTO> list = new ArrayList<CategoryDTO>();
			
			openConn();
			
			sql ="select * from shop_category order by category_num desc";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					CategoryDTO dto = new CategoryDTO();
					
					dto.setCategory_num(rs.getInt("category_num"));
					dto.setCategory_code(rs.getString("category_code"));
					dto.setCategory_name(rs.getString("category_name"));
					list.add(dto);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con, rs);
			} return list;
		} //메서드 end
		 //카테고리 번호에 해당하는 카테고리를 테이블에서 읎애버리는 메썯
		public int deleteCategory(int num) {
			int result=0;
			
			openConn();
			sql = "delete from shop_category where category_num =?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
				
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			} return result;
		} //메서드 end
		 //카테고리 삭제 시 번호 재정렬 메서드
		 public void updateSequence(int no) {
			 openConn();
			 sql = "update shop_category set category_num = category_num-1 where category_num >?";
			 
			 try {
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, no);
				 pstmt.executeUpdate();
				 
			 } catch(Exception e) {
				 e.printStackTrace();
			 } finally {
				 closeConn(pstmt, con);
			 } 
		 }
}
