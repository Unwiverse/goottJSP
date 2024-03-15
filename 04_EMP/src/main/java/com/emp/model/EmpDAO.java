package com.emp.model;

import java.sql.*;
import java.util.*;

/*
 * DAO(Data Access Object)
 * - 데이터 접근 객체 ==> DB에 접속(연동)하는 객체.
 * - DAO란 DB에 접속해 데이터를 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스.
 * - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 할 수 있지만, 중복 코드 발생 및 유지보수
 *   코드의 모듈화 등을 위해 일반적으로 DAO 클래스를 따로 만들어 씀
 */

public class EmpDAO {
	//DB와 연동하는 객체
	Connection con = null;
	
	//Db에 SQL문을 전송하는 객체 
	PreparedStatement pstmt = null;
	
	//SQL문을 실행한 후에 결과값을 갖고 있는 객체
	ResultSet rs = null;
	
	//SQL문을 저장할 문자열 변수
	String sql = null;
	
	public EmpDAO() {
		// TODO Auto-generated constructor stub
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "goott";
		String password = "992992";
		
		try {
			//1단계: 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);
			System.out.println("오라클드라이버 메모리 로딩 완료");
			
			//2단계: 오라클 DB와 연결작업 진행.
			con= DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB와 연결됨");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	//기본 생성자
	
	//EMP 테이블에서 사원 전체 목록을 조회하는 메서드
	public List<EmpDTO> selectEmpList() {
		
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		//3단계: 데이터베이스에 전송할 SQL문 작성
		sql= "select * from emp order by hiredate desc";
		try {
			pstmt = con.prepareStatement(sql);
			//5단계: SQL 문을 DB에 전송 및 실행.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				list.add(dto);
			}
			//6단계: DB와 연결돼있던 자원 종료
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return list;
		
		
	} //selectEmpList() 메서드 end
	
}
