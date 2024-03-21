package com.board.model;

import java.sql.*;
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

    private BoardDAO() {}

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
    
    	//board 테이블의 전체 게시물을 조회하는 메서드
    	public List<BoardDTO> getBoardList() {
    		List<BoardDTO> list = new ArrayList<BoardDTO>();
    		
    		openConn(); //DBCP 방식으로 DB와 연동 진행
    		
    		sql = "select * from board order by board_no desc";
    		try {
    		
    		pstmt = con.prepareStatement(sql);
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
				list.add(dto);
			
			}
    		} catch(Exception e) {
    			e.printStackTrace();
    		} finally {
    			closeConn(pstmt, con);
    		}
    			return list;
    	}//getBoardList() 메서드 end
    		//클릭한 글 제목에 해당하는 글의 정보를 띄우는 메서드
    	public BoardDTO showContent(int no) {
    			BoardDTO dto = null;
    		try { 
    			openConn();
        		sql= "select * from board where board_no=? ";
    			pstmt =con.prepareStatement(sql);
    			pstmt.setInt(1, no);
    			rs= pstmt.executeQuery();
    			
    			if(rs.next()) {
    				dto = new BoardDTO();
    				dto.setBoard_no(rs.getInt("board_no"));
    				dto.setBoard_writer(rs.getString("board_writer"));
    				dto.setBoard_title(rs.getString("board_title"));
    				dto.setBoard_cont(rs.getString("board_cont"));
    				dto.setBoard_hit(rs.getInt("board_hit"));
    				dto.setBoard_pwd(rs.getString("board_pwd"));
    				dto.setBoard_date(rs.getString("board_date"));
    				dto.setBoard_update(rs.getString("board_update"));
    			}
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		} finally {
    			closeConn(pstmt, con, rs);
    		} return dto;
    	}
    		//게시판 목록 글쓰기 처리 메서드
		public int insertBoard(BoardDTO dto) {
			int result=0, count=0;
			sql = "select max(board_no) from board ";
			
			try {
				openConn();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
				sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, '')";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, count+1);
				pstmt.setString(2, dto.getBoard_writer());
				pstmt.setString(3, dto.getBoard_title());
				pstmt.setString(4, dto.getBoard_cont());
				pstmt.setString(5, dto.getBoard_pwd());
				
				result = pstmt.executeUpdate();
				
    			} catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con, rs);
    			} return result;
    			
    		} //메서드 end
		
		//글번호에 해당하는 게시글을 조회하는 메서드
		public BoardDTO contentBoard(int no) {
			BoardDTO dto = null;
			
		try {
		openConn();
		
		sql = "select * from board where board_no =?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
				
		rs = pstmt.executeQuery();
		if(rs.next()) {
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
			}
			return dto;
		}
		
    		//게시판 목록 글 삭제 메서드
			public int deleteBoard(int no, String pwd) {
				int result=0;
				openConn();
				sql= "select * from board where board_no=? ";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					
					rs = pstmt.executeQuery();	
					if(rs.next()) {
    					if(pwd.equals(rs.getString("board_pwd"))) {
    						sql ="delete from board where board_no=?";
    						pstmt = con.prepareStatement(sql);   
    	    				pstmt.setInt(1, no);
    	    				
    	    				result = pstmt.executeUpdate();
    						
    					} else {//비번 틀림
    						result=-1;
    						
    					}
    				}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					closeConn(pstmt, con, rs);
				} return result;
			} //메서드 end
			
				//삭제된 글 때문에 꼬인 게시글 순번 재정렬 메서드
				public void refreshingBoard(int no) {
					openConn();
					
					sql="update board set board_no = board_no -1 where board_no > ?";
					
					try {
						pstmt = con.prepareStatement(sql, no);
						pstmt.setInt(1, no);
						pstmt.executeUpdate();
						
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						closeConn(pstmt, con);
					} 
				} //메서드 end
				
				//글번호에 해당하는 작성자의 글을 수정하는 메서드
				public int updateBoard(BoardDTO dto) {
					int commence = 0;
					
					openConn();
					sql = "update board set board_title=?, board_cont=?,"
	    					+ "board_update=sysdate where board_no=?";
					
					try {
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getBoard_title());
						pstmt.setString(2, dto.getBoard_cont());
						pstmt.setInt(3, dto.getBoard_no());
						
						commence = pstmt.executeUpdate();
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						closeConn(pstmt, con);					
					} return commence;
				}
    	
}
    	
    	
    	
    	
    	

