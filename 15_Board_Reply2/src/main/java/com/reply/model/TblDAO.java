package com.reply.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class TblDAO {
	 private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs, rs1 = null;
	    private String sql = null;

	    private static TblDAO instance = null;

	    private TblDAO() {} //기본생성자

	    public static TblDAO getInstance() {
	        if (instance == null) {
	            instance = new TblDAO();
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
	    	//전체 게시물 조회 메서드
	    	public List<TblDTO> showBoard() {
	    		List<TblDTO> list = new ArrayList<TblDTO>();
	    		
	    		openConn();
	    		
	    		sql ="select * from tbl_board order by bno desc";
	    		
	    		try {
	    			pstmt = con.prepareStatement(sql);
	    			rs = pstmt.executeQuery();
	    			
	    			while(rs.next()) {
	    				TblDTO dto = new TblDTO();
	    				
	    				dto.setBno(rs.getInt("bno"));
	    				dto.setWriter(rs.getString("writer"));
	    				dto.setTitle(rs.getString("title"));
	    				dto.setContent(rs.getString("content"));
	    				dto.setPwd(rs.getString("pwd"));
	    				dto.setRegdate(rs.getString("regdate"));
	    				dto.setReupdate(rs.getString("reupdate"));
	    				
	    				sql = "select count(*) from tbl_board t join tbl_reply r on t.bno"
	    						+ "= r.bno where t.bno=?";
	    				
	    				pstmt=con.prepareStatement(sql);
	    				
	    				pstmt.setInt(1, rs.getInt("bno"));
	    				rs1 = pstmt.executeQuery();
	    				
	    				if(rs1.next()) {
	    					dto.setRegCnt(rs1.getInt(1));
	    				}
	    						
	    				list.add(dto);
	    			}
	    			rs1.close();
	    		}catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return list;
	    	} //메서드 end
	    		//새 글쓰기 메서드
	    	public int insertTbl(TblDTO dto) {
				int result = 0, count=0;
				openConn();
				
				sql ="select max(bno) from tbl_board";
				
				try {
					pstmt= con.prepareStatement(sql);
					rs= pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1)+1;
					}
					
					sql ="insert into tbl_board values(?, ?, ?, ?, ?, sysdate, '')";
					pstmt = con.prepareCall(sql);
					
					pstmt.setInt(1, count);
					pstmt.setString(2, dto.getWriter());
					pstmt.setString(3, dto.getTitle());
					pstmt.setString(4, dto.getContent());
					pstmt.setString(5, dto.getPwd());										
					
					result = pstmt.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					closeConn(pstmt, con, rs);
				} return result;
				
			} //메서드 end 
	    	//tbl_board() 메서드 게시글 번호에 해당하는 게시글의 내역을 조회하는 메서드
	    	public TblDTO getBoardContent(int no) {
	    		TblDTO dto = null;
	    		
	    		openConn();
	    		
	    		sql = "select * from tbl_board where bno =?";
	    		
	    		try {
	    			pstmt= con.prepareStatement(sql);
	    			pstmt.setInt(1, no);
	    			rs = pstmt.executeQuery();
	    			if(rs.next()) {
	    				dto = new TblDTO();
	    				dto.setBno(rs.getInt("bno"));
	    				dto.setWriter(rs.getString("writer"));
	    				dto.setTitle(rs.getString("title"));
	    				dto.setContent(rs.getString("content"));
	    				dto.setPwd(rs.getString("pwd"));
	    				dto.setRegdate(rs.getString("regdate"));
	    				dto.setReupdate(rs.getString("reupdate"));
	    			}
	    		}
	    		catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return dto;
	    		
	    	}//메서드 end
	    		//글번호에 해당하는 댓글 전체 리스트를 조회하는 메서드
	    		public String getReplyList(int no) {
	    			String str = "";
	    			openConn();
	    			
	    			sql = "select * from tbl_reply where bno=? order by redate";
	    			
	    			try {
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setInt(1, no);
	    			rs = pstmt.executeQuery();
	    			
	    			str += "<replys>";
	    			while(rs.next()) {
	    				
	    				str += "<reply>";
	    				
	    				str += "<rno>" + rs.getInt("rno")+"</rno>";
	    				str += "<bno>" + rs.getInt("bno")+"</bno>";
	    				str += "<rewriter>" + rs.getString("rewriter")+"</rewriter>";
	    				str += "<recont>" + rs.getString("recont")+"</recont>";
	    				str += "<redate>" + rs.getString("redate")+"</redate>";
	    				str += "<reupdate>" + rs.getString("reupdate")+"</reupdate>";
	    				
	    				str += "</reply>";
	    			}
	    			
	    			str += "</replys>";
	    			}catch(Exception e) {
	    				e.printStackTrace();
	    			} finally {
	    				closeConn(pstmt, con, rs);
	    			} return str;
	    			
	    		}//메서드 end
	    			//댓글 내용을 tbl_reply 테이블에 저장하는 메서드
	    			public int replyInsert(ReplyDTO dto) {
	    				int result=0, count=0;
	    				openConn();
	    				
	    				sql = "select max(rno) from tbl_reply";
	    				
	    				try {
	    				pstmt = con.prepareStatement(sql);
	    				rs = pstmt.executeQuery();
	    				
	    				if(rs.next()) {
	    					count = rs.getInt(1)+1;
	    				}
	    				sql = "insert into tbl_reply values(?, ?, ?, ?, sysdate, '')";
	    				
	    				pstmt = con.prepareStatement(sql);
	    				
	    				pstmt.setInt(1, count);
	    				pstmt.setInt(2, dto.getBno());
	    				pstmt.setString(3, dto.getRewriter());
	    				pstmt.setString(4, dto.getRecont());
	    				 result = pstmt.executeUpdate();
	    				
	    				}catch(Exception e) {
	    					e.printStackTrace();
	    					
	    				} finally {
	    					closeConn(pstmt, con, rs);
	    				} return result;
	    			}

}
