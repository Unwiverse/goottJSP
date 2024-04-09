package com.shop.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDAO {
	private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs, rs1 = null;
    private String sql = null;

    private static CartDAO instance = null;

    private CartDAO() {} //기본생성자

    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
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
     // shop_cart 테이블에 제품을 저장하는 메서드
     public int insertCart(CartDTO dto) {
    	 int result = 0, count =0;
    	 
    	 openConn();
    	 
    	 sql = "select max(cart_num) from shop_cart";
    	 
    	 try {
    		 pstmt = con.prepareStatement(sql);
    		 rs = pstmt.executeQuery();
    		 
    		 if(rs.next()) {
    			 count = rs.getInt(1)+1;
    		 }
    		 sql="insert into shop_cart values(?, ?, ?, ?, ?, ?, ?, ?)";
    		 
    		 pstmt = con.prepareStatement(sql);
    		 pstmt.setInt(1, count);
    		 pstmt.setInt(2, dto.getCart_pnum());
    		 pstmt.setString(3, dto.getCart_userId());
    		 pstmt.setString(4, dto.getCart_pname());
    		 pstmt.setInt(5, dto.getCart_pqty());
    		 pstmt.setInt(6, dto.getCart_price());
    		 pstmt.setString(7, dto.getCart_pspec());
    		 pstmt.setString(8, dto.getCart_pimage());
    		 
    		 result = pstmt.executeUpdate();
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 } finally {
    		 closeConn(pstmt, con, rs);
    	 } return result;
    	 
     } //메서드 end
      //shop_cart 테이블의 사용자 아이디에 해당하는 장바구니 목록을 조회하는 메서드
      public List<CartDTO> getCartList(String user_id) {
    	  List<CartDTO> list = new ArrayList<CartDTO>();
    	  
    	  openConn();
    	  System.out.println(user_id);
    	  sql = "select * from shop_cart where CART_USERID = ?";
    	  try {
    		  pstmt = con.prepareStatement(sql);
    		  pstmt.setString(1, user_id);
    		  rs = pstmt.executeQuery();
    		  
    		  System.out.println("ddsd");
    		  while(rs.next()) {
    			  System.out.println("dd");
    			  CartDTO dto = new CartDTO();
    			  
    			  dto.setCart_num(rs.getInt("cart_num"));
    			  dto.setCart_pnum(rs.getInt("cart_pnum"));
    			  dto.setCart_userId(rs.getString("cart_userid"));
    			  dto.setCart_pname(rs.getString("cart_pname"));
    			  dto.setCart_pqty(rs.getInt("cart_pqty"));
    			  dto.setCart_price(rs.getInt("cart_price"));
    			  dto.setCart_pspec(rs.getString("cart_pspec"));
    			  dto.setCart_pimage(rs.getString("cart_pimage"));
    			  
    			  list.add(dto);
    		  }
    		  
    	  } catch(Exception e) {
    		  e.printStackTrace();
    	  } finally {
    		  closeConn(pstmt, con, rs);
    	  } return list;
    	  
      }//메서드 end
      //장바구니 번호에 맞는 목록을 shop_cart 테이블에서 삭제하는 메서드
      public int deleteCart(int cart_no) {
    	  int result = 0;
    	  openConn();
    	  
    	  sql ="delete from shop_cart where cart_num=?";
    	  
    	  try {
    		  pstmt = con.prepareStatement(sql);
    		  pstmt.setInt(1, cart_no);
    		  result = pstmt.executeUpdate();
    		  
    	  } catch(Exception e) {
    		  e.printStackTrace();
    	  } finally {
    		  closeConn(pstmt, con);
    	  } return result;
      } //메섣 end
       //장바구니 삭제 시 순번 재정렬 메서드
       public void updateSequence(int no) {
    	   
    	   openConn();
    	   
    	   sql="update shop_cart set cart_num = cart_num-1 where cart_num >?";
    	   
    	   try {
    		   pstmt = con.prepareStatement(sql);
    		   pstmt.executeQuery();
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   closeConn(pstmt, con);
    	   }
       }//메섣 end
}
