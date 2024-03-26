package com.board.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	  private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	    private String sql = null;

	    private static BoardDAO instance = null;

	    private BoardDAO() {} //기본생성자

	    public static BoardDAO getInstance() {
	        if (instance == null) {
	            instance = new BoardDAO();
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
	    	//board 테이블 전체 게시물 수 확인 메서드
	    	public int getBoardCount() {
	    		int count=0;
	    		openConn();
	    		
	    		try {
	    			sql="select count(*) from board";
	    			pstmt = con.prepareStatement(sql);
	    			rs = pstmt.executeQuery(); //select 쿼리문이라 executeQuery 씀
	    			
	    			if(rs.next()) {
	    				count= rs.getInt(1);
	    				
	    			}
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return count;
	    		
	    	} //메서드 end
    		//board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메서드
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		//해당 페이지서 시작 번호 
		int startNo = (page * rowsize) - (rowsize -1);
		
		//해당 페이지에서 마지막 번호
		int endNo = (page*rowsize);
		
		openConn();
		sql= "select * from"
				+ " (select row_number() over(order by board_no desc) as rnum, b.* from board b)"
				+ " where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				list.add(dto); // dto = 주소값
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con, rs);
		} return list;
    			
    } 
		//게시물 번호에 해당하는 게시물을 조회(내용 포함)하는 메서드
		public BoardDTO showBoard(int no) {
			BoardDTO dto = null;
			openConn();
			
			sql= "select * from board where board_no=?";
			
			try { 
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					}
				
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						closeConn(pstmt, con, rs);
					} return dto;
				}
}
