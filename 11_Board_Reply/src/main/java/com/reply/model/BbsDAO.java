package com.reply.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BbsDAO {
	 private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	    private String sql = null;

	    private static BbsDAO instance = null;

	    private BbsDAO() {} //기본생성자

	    public static BbsDAO getInstance() {
	        if (instance == null) {
	            instance = new BbsDAO();
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
	    
	    	//jsp_bbs 테이블의 전체 게시물을 조회하는 메서드
	    	public List<BbsDTO> getBbsList() {
	    		List<BbsDTO> list = new ArrayList<BbsDTO>();
	    		
	    		openConn();
	    		sql = "select * from jsp_bbs order by board_group desc, board_step";
	    		try {
	    			pstmt = con.prepareStatement(sql);
	    			rs= pstmt.executeQuery();
	    			
    			while(rs.next()) {
    				BbsDTO dto = new BbsDTO();
    				
    				dto.setBoard_no(rs.getInt("board_no"));
    				dto.setBoard_writer(rs.getString("board_writer"));
    				dto.setBoard_title(rs.getString("board_title"));
    				dto.setBoard_cont(rs.getString("board_cont"));
    				dto.setBoard_pwd(rs.getString("board_pwd"));
    				dto.setBoard_hit(rs.getInt("board_hit"));
    				dto.setBoard_date(rs.getString("board_date"));
    				dto.setBoard_update(rs.getString("board_update"));
    				dto.setBoard_group(rs.getInt("board_group"));
    				dto.setBoard_step(rs.getInt("board_step"));
    				dto.setBoard_indent(rs.getInt("board_indent"));
    				list.add(dto);
    			}
	    			
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);;
	    		} return list;
	    	} //메서드 end
	    		//리스트에서 하는 게시물 상세내역 조회 메서드
	    		public BbsDTO showBoard(int no) {
	    			BbsDTO dto = null;
	    			
	    			openConn();
	    			sql = "select * from jsp_bbs where board_no=?";
	    			try {
	    				pstmt = con.prepareStatement(sql);
	    				pstmt.setInt(1, no);
	    				rs = pstmt.executeQuery();
	    			
	    			if(rs.next()) {
	    				dto= new BbsDTO();
	    				dto.setBoard_no(rs.getInt("board_no"));
	                    dto.setBoard_writer(rs.getString("board_writer"));
	                    dto.setBoard_title(rs.getString("board_title"));
	                    dto.setBoard_cont(rs.getString("board_cont"));
	                    dto.setBoard_pwd(rs.getString("board_pwd"));
	                    dto.setBoard_hit(rs.getInt("board_hit"));
	                    dto.setBoard_date(rs.getString("board_date"));
	                    dto.setBoard_update(rs.getString("board_update"));
	    				dto.setBoard_group(rs.getInt("board_group"));
	    				dto.setBoard_step(rs.getInt("board_step"));
	    				dto.setBoard_indent(rs.getInt("board_indent"));
	    			} } catch(Exception e) {
	    				e.printStackTrace();
	    			} finally {
	    				closeConn(pstmt, con, rs);;
	    			} return dto;
	    		} //메서드 end
	    			
	    	//Bbs 테이블에 게시물을 추가하는 메서드
        	public int insertBoard(BbsDTO dto) {
        		int result=0, count=0;
        		sql = "select max(board_no) from jsp_bbs";
        		
        	try {
        		openConn();
        		pstmt = con.prepareStatement(sql);
        		rs = pstmt.executeQuery();
        		if(rs.next()) {
        			count = rs.getInt(1)+1; //밑에서 +1 안해줘도됨
        		}
        		sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, '', ?, ?, ?)";
        		pstmt = con.prepareStatement(sql);
        		
        		pstmt.setInt(1, count);
        		pstmt.setString(2, dto.getBoard_writer());
        		pstmt.setString(3, dto.getBoard_title());
        		pstmt.setString(4, dto.getBoard_cont());
        		pstmt.setString(5, dto.getBoard_pwd());
        		pstmt.setInt(6, count);
        		pstmt.setInt(7, dto.getBoard_step());
        		pstmt.setInt(8, dto.getBoard_indent());
        		
        		result = pstmt.executeUpdate();
        		
        		} catch(Exception e) {
        			e.printStackTrace();
        		} finally {
        			closeConn(pstmt, con, rs);
        		}
        		return result;
        		
        		} //메서드 end
        		//jsp_bbs 테이블 게시판에 원글에 대한 답변글이 있으면 답변글 step을 1 증가시키는 메서드
        		public void replyUpdate(int group, int step) {
        			openConn();
        			sql = "update jsp_bbs set board_step = board_step + 1 where board_group = ?"
        					+ "and board_step > ?";
        			
        			try {
        				pstmt=con.prepareStatement(sql);
        				pstmt.setInt(1, group);
        				pstmt.setInt(2, step);
        				
        				pstmt.executeUpdate();
        				
        			} catch(Exception e) {
        				e.printStackTrace();
        			} finally {
        				closeConn(pstmt, con);
        			}
        			
        		}//메서드 end 
        		//jsp_bbs 테이블의 게시글 원글에 답변글을 추가하는 메서드.
        		public int replyBbs(BbsDTO dto) {
        			int result=0, count=0;
        			openConn();
        			sql ="select max(board_no) from jsp_bbs";
        			try {
        				pstmt=con.prepareStatement(sql);
        				rs= pstmt.executeQuery();
        				if(rs.next()) {
        					count=rs.getInt(1) +1;
        				}
        				sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, '', ?, ?, ?)";
        				pstmt =con.prepareStatement(sql);
        				
        				pstmt.setInt(1, count);
        				pstmt.setString(2, dto.getBoard_writer());
        				pstmt.setString(3, dto.getBoard_title());
        				pstmt.setString(4, dto.getBoard_cont());
        				pstmt.setString(5, dto.getBoard_pwd());
        				pstmt.setInt(6, dto.getBoard_group());
        				pstmt.setInt(7, dto.getBoard_step()+1);
        				pstmt.setInt(8, dto.getBoard_indent()+1);
        				
        				result = pstmt.executeUpdate();
        				
        				
        			} catch(Exception e) {
        				e.printStackTrace();
        			} finally {
        				closeConn(pstmt, con, rs);;
        			} return result;
        		 }//replyBbs() 메서드 end
        		//게시글 삭제 메서드(만들어라)
        		
        		//게시글 수정 메서드(만들어라)
        		
    
        	
        	
        	
        	
        	
	        	
}
