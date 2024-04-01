package com.upload.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class UploadDAO {
	 private Connection con = null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs = null;
	    private String sql = null;

	    private static UploadDAO instance = null;

	    private UploadDAO() {} //기본생성자

	    public static UploadDAO getInstance() {
	        if (instance == null) {
	            instance = new UploadDAO();
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
    	//Upload 테이블에서 전체 게시물을 조회하는 메서드
    	public List<UploadDTO> getUploadList() { 
    		
    		List<UploadDTO> list = new ArrayList<UploadDTO>();
    		
    		openConn();
    		
    		sql = "select * from upload order by upload_no desc";
    		
    		try {
    			pstmt = con.prepareStatement(sql);
    			rs= pstmt.executeQuery();
    			
    			while(rs.next()) {
    				UploadDTO dto = new UploadDTO();
    				
    				dto.setUpload_no(rs.getInt("upload_no"));
    				dto.setUpload_writer(rs.getString("upload_writer"));
    				dto.setUpload_title(rs.getString("upload_title"));
    				dto.setUpload_cont(rs.getString("upload_cont"));
    				dto.setUpload_pwd(rs.getString("upload_pwd"));
    				dto.setUpload_file(rs.getString("upload_file"));
    				dto.setUpload_hit(rs.getInt("upload_hit"));
    				dto.setUpload_date(rs.getString("upload_date"));
    				dto.setUpload_update(rs.getString("upload_update"));
    				list.add(dto);
    			}
    			
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		} finally {
	    			closeConn(pstmt, con, rs);
	    		} return list;
    	}//메서드 end
		//upload 테이블에 게시글을 추가하는 메서드
		public int insertUpload(UploadDTO dto) {
			int result = 0, count=0;
			openConn();
			
			sql ="select max(upload_no) from upload";
			
			try {
				pstmt= con.prepareStatement(sql);
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1)+1;
				}
				
				sql ="insert into upload values(?, ?, ?, ?, ?, ?, default, sysdate, '')";
				pstmt = con.prepareCall(sql);
				
				pstmt.setInt(1, count);
				pstmt.setString(2, dto.getUpload_writer());
				pstmt.setString(3, dto.getUpload_title());
				pstmt.setString(4, dto.getUpload_cont());
				pstmt.setString(5, dto.getUpload_pwd());
				pstmt.setString(6, dto.getUpload_file());
				
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con, rs);
			} return result;
			
			
		} //메서드 end
			//조회수 증가시키는 메서드
			public void readCount(int no) {
				openConn();
				
				sql ="update upload set upload_hit = upload_hit + 1 where upload_no = ?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					pstmt.executeQuery();
					
				}catch(Exception e) {
					e.printStackTrace();
				} finally {
					closeConn(pstmt, con);
				}
				
			}//메서드 end
		//upload 테이블에서 글번호에 해당하는 게시글의 상세내역을 조회하는 메서드
		public UploadDTO uploadContent(int no) {
			UploadDTO dto = null;
			openConn();
			sql = "select *from upload where upload_no = ?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, no);
				
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new UploadDTO();
					
					dto.setUpload_no(rs.getInt("upload_no"));
    				dto.setUpload_writer(rs.getString("upload_writer"));
    				dto.setUpload_title(rs.getString("upload_title"));
    				dto.setUpload_cont(rs.getString("upload_cont"));
    				dto.setUpload_pwd(rs.getString("upload_pwd"));
    				dto.setUpload_file(rs.getString("upload_file"));
    				dto.setUpload_hit(rs.getInt("upload_hit"));
    				dto.setUpload_date(rs.getString("upload_date"));
    				dto.setUpload_update(rs.getString("upload_update"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(pstmt, con);
			} return dto;
			} //메서드 end
			 // upload 테이블의 게시글 번호에 해당하는 게시글을 수정하는 메서드
			 public int modifyUpload(UploadDTO dto) {
				 int result=0;
				 
				 openConn();
				 sql = "select * from upload where upload_no=?";
				 
				 try {
					 pstmt = con.prepareStatement(sql);
					 
					 pstmt.setInt(1, dto.getUpload_no());
					 
					 rs=pstmt.executeQuery();
					 
					 if(rs.next()) {
						 if(dto.getUpload_pwd().equals(rs.getString("upload_pwd"))) {
							 //비밀번호가 일치하는 경우
							 if(dto.getUpload_file() == null) { //첨부파일이 없으면
								 sql = "update upload set upload = ?, upload_cont =?"
								 		+ "upload_update = sysdate where upload_no=?";
								pstmt = con.prepareStatement(sql);
								
								pstmt.setString(1, dto.getUpload_title());
								pstmt.setString(2, dto.getUpload_cont());
								pstmt.setInt(3, dto.getUpload_no());
							 }else {
								 //첨부파일이 있으면 
								 sql = "update upload set upload_title = ?, upload_cont =?,"
								 		+ "upload_file=?, "
									 		+ "upload_update = sysdate where upload_no=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setString(1, dto.getUpload_title());
								pstmt.setString(2, dto.getUpload_cont());
								pstmt.setString(3, dto.getUpload_file());
								pstmt.setInt(4, dto.getUpload_no());
								
								result = pstmt.executeUpdate();
							 }
						 } else {
							 result = -1;
						 }
					 } 
					 
				 } catch(Exception e) {
					 e.printStackTrace();
				 } finally {
					 closeConn(pstmt, con, rs);
				 } return result;
			 }
}
