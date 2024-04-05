package com.shop.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ProductDAO {
	 private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs, rs1 = null;
	    private String sql = null;

	    private static ProductDAO instance = null;

	    private ProductDAO() {} //기본생성자

	    public static ProductDAO getInstance() {
	        if (instance == null) {
	            instance = new ProductDAO();
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
    	//shop_products 상품 목록에 뭔가 추가하는 메서드
    	public int insertProduct(ProductDTO dto) {
    		int result =0, count=0;
    		openConn();
    		sql ="select max(pnum) from shop_products";
    		try {
    			pstmt = con.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			
    			if(rs.next() ) {
    				count =rs.getInt(1)+1;
    			}
    			sql = "insert into shop_products values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
    			
    			pstmt = con.prepareStatement(sql);
    			
    			pstmt.setInt(1, count);
    			pstmt.setString(2, dto.getPname());
    			pstmt.setString(3, dto.getPcategory_fk());
    			pstmt.setString(4, dto.getPcompany());
    			pstmt.setString(5, dto.getPimage());
    			pstmt.setInt(6, dto.getPqty());
    			pstmt.setDouble(7, dto.getPrice());
    			pstmt.setString(8, dto.getPspec());
    			pstmt.setString(9, dto.getPcontents());
    			pstmt.setInt(10, dto.getPoint());
    			
    			result=pstmt.executeUpdate();
    			
    		}catch(Exception e) {
    			
    		} finally {
    			closeConn(pstmt, con, rs);
    		} return result;
    		
    		
    	} //메서드 end
    		//전체 상품 목록을 조회함
    		public List<ProductDTO> getProductList() {
    			 List<ProductDTO> list = new ArrayList<ProductDTO>(); 
    			 
    			 openConn();
    			 sql ="select * from shop_products order by pnum desc";
    			 
    			 try {
    				 pstmt = con.prepareStatement(sql);
    				 rs = pstmt.executeQuery();
    				 
    				 while(rs.next()) {
    					 ProductDTO dto = new ProductDTO();
    					 
    					 dto.setPnum(rs.getInt("pnum"));
    					 dto.setPname(rs.getString("pname"));
    					 dto.setPcategory_fk(rs.getString("pcategory_fk"));
    					 dto.setPcompany(rs.getString("pcompany"));
    					 dto.setPimage(rs.getString("pimage"));
    					 dto.setPqty(rs.getInt("pqty"));
    					 dto.setPrice(rs.getInt("price"));
    					 dto.setPspec(rs.getString("pspec"));
    					 dto.setPoint(rs.getInt("point"));
    					 dto.setPcontent(rs.getString("pcontents"));
    					 dto.setPinputdate(rs.getString("pinputdate"));
    					 list.add(dto);
    				 }
    				 
    			 } catch(Exception e) {
    				 e.printStackTrace();
    			 } finally {
    				 closeConn(pstmt, con, rs);
    			 } return list;
    		}
    		 
    			
    		
    		
    		
    		
    		
    		
    		
    		
	    
}
