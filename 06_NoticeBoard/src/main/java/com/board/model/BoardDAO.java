package com.board.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



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

    public void openConn() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "goott";
        String password = "992992";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConn(PreparedStatement pstmt, ResultSet rs, Connection con) {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConn(PreparedStatement pstmt, Connection con) {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBoardCount() {
        int count = 0;
        openConn();
        sql = "select count(*) from board";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(pstmt, rs, con);
        }
        return count;
    }

    public List<BoardDTO> getBoardList() {
        List<BoardDTO> list = new ArrayList<BoardDTO>();
        try {
            openConn();
            sql = "select * from board order by board_no desc";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(pstmt, rs, con);
        }
        return list;
    }
    	//board 테이블에 게시물을 추가하는 메서드
    	public int insertBoard(BoardDTO dto) {
    		int result=0, count=0;
    		sql = "select max(board_no) from board";
    		
    	try {
    		openConn();
    		pstmt = con.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			count = rs.getInt(1)+1; //밑에서 +1 안해줘도됨
    		}
    		sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, '')";
    		pstmt = con.prepareStatement(sql);
    		
    		pstmt.setInt(1, count);
    		pstmt.setString(2, dto.getBoard_writer());
    		pstmt.setString(3, dto.getBoard_title());
    		pstmt.setString(4, dto.getBoard_cont());
    		pstmt.setString(5, dto.getBoard_pwd());
    		
    		result = pstmt.executeUpdate();
    		
    		} catch(Exception e) {
    			e.printStackTrace();
    		} finally {
    			closeConn(pstmt, rs, con);
    		}
    		return result;
    		
    		} //메서드 end
    	
    		//board 테이블의 게시글 번호에 해당하는 게시글의 조회수를 증가시켜주는 메서드
    		public void readCount(int num) {
    			sql = "update board set board_hit = "
    					+ "board_hit+1 "
    					+ "where board_no=?";
    			try {
    				openConn();
    			pstmt = con.prepareStatement(sql);
    			pstmt.setInt(1, num);
    			pstmt.executeUpdate();
    			} catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con);
    			}
    		}//메소드 end
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
    				closeConn(pstmt, rs, con);
    			}
    			return dto;
    		}
    		//글번호에 해당하는 작성자의 글을 수정하는 메서드
    		public int updateBoard(BoardDTO dto) {
    			int commence = 0;
    			
    			sql = "update board set board_title=?, board_cont=?,"
    					+ "board_update=sysdate where board_no=?";
    			try {
    				openConn();
    				pstmt = con.prepareStatement(sql);
    				pstmt.setString(1, dto.getBoard_title());
    				pstmt.setString(2, dto.getBoard_cont());
    				pstmt.setInt(3, dto.getBoard_no());
    				commence = pstmt.executeUpdate();
    				
    			} catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con);
    			}
    			  return commence;
    		} //메서드 end
    		
    		//board 테이블에서 게시글 번호에 게시글을 삭제하는 메서드
    		public int deleteBoard(int no, String pwd) {
    			int result=0;
    			
    			
    			sql = "select * from board where board_no = ?";
    			
    			try {
    				openConn();
    				pstmt = con.prepareStatement(sql);   
    				pstmt.setInt(1, no);
    				
    				rs= pstmt.executeQuery();
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
    				closeConn(pstmt, rs, con);
    			} 
    			return result;
    			
    		}//deleteBoard() 메서드 end
    	
    		//board 테이블에서 중간에 게시글이 삭제되는 경우
    		// 게시글 번호를 재정렬하는 메서드.
    		
    		public void updateBoardSequence(int no) {
    			openConn();
    			
    			sql= "update board set board_no = board_no -1 where board_no > ?";
    			try {
    			pstmt= con.prepareStatement(sql, no);
    			pstmt.setInt(1, no);
    			pstmt.executeUpdate();
    			
    			}catch(Exception e) {
    				e.printStackTrace();
    			} finally {
    				closeConn(pstmt, con);
    			}
    			
    			
    		}
    			//검색 메서드 
    		/*public List<BoardDTO> searchMemberList(String field, String keyword) {
    			List<BoardDTO> searchList = new ArrayList<BoardDTO>();
    			
    			openConn();
    			sql = "select * from board";
    	
    			try {
    				//if(field.equals("))
    				
    			} catch(Exception e) {
    				e.printStackTrace();
    			
 }*/
    	
    	
    	
    	
    	
    	
    	
    	
}

