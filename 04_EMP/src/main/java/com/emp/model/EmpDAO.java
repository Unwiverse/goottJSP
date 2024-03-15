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
	
	public EmpDAO() { //기본 생성자, 자바에서 했던 call by reference, call by value 참조해서 공부할것
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
	}	//기본 생성자 end
	
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
				System.out.println("rs dto:"+dto);
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setHiredate(rs.getString("hiredate"));
				
				list.add(dto);
			}
			//6단계: DB와 연결돼있던 자원 종료
			rs.close(); pstmt.close(); con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		  return list;
		
		} //selectEmpList() 메서드 end
		  //DEPT 테이블의 전체 부서 리스트를 조회하는 메서드.
	public List<DeptDTO> getDeptList() {
		List<DeptDTO> list = new ArrayList<DeptDTO>();
		
	try {
			//3단계: sql문 작성.
			sql = "select * from dept order by deptno";
			
			//4단계: SQL문을 데이터베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			//5단계: SQL 문을 DB에 전송 및 실행.
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				System.out.println("rs dto: "+dto);
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
			
				
				list.add(dto);
				
				
			}
			//6단계: DB와 연결돼있던 자원 종료
			rs.close(); pstmt.close(); con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}// 메섣 end
	
		//EMP 테이블에 사원을 등록하는 메서드
		public int insertEmp(EmpDTO dto) {
		int result= 0;
		
		try {
		// 3단계: DB에 전송할 sql문
		sql = "insert into emp values(?, ?, ?, ?, sysdate, ?, ?, ?)";
		
		//4단계: sql문을 DB 전송 객체에 저장
		pstmt = con.prepareStatement(sql);
		
		//4-1단계: ?(플레이스홀더)에 데이터 저장
		pstmt.setInt(1, dto.getEmpno()); //setInt(), setString: 플레이스 홀더에 들어갈 데이터와 인덱스를 지정하는 메서드
		pstmt.setString(2, dto.getEname());
		pstmt.setString(3, dto.getJob());
		pstmt.setInt(4, dto.getMgr());
		pstmt.setInt(5, dto.getSal());
		pstmt.setInt(6, dto.getComm());
		pstmt.setInt(7, dto.getDeptno());
		
		
		
		//5단계: sql문을 dB에 전송 및 실행
		result = pstmt.executeUpdate();
		//6단계: dB와 연결돼있는 자원 종료
		pstmt.close(); con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
			
		}	//insertEmp() 메서드 end
	
		
		//사원번호에 대항하는 사원의 상세 정보를 조회하는 메서드.
		public EmpDTO contentEmp(int num) {
			EmpDTO dto = null;
			
			
		try {
			//3단계: DB에 전송할 SQL문 작성
			sql = "select * from emp where empno= ?";
			//4단계: sql문을 DB 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			//4-1단계: ?(플레이스 홀더)에 데이터 저장
			pstmt.setInt(1, num);
			
			//5단계: sql문을 dB에 전송 및 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
			dto	=	new EmpDTO();
			
			dto.setEmpno(rs.getInt("empno"));
			dto.setEname(rs.getString("ename"));
			dto.setJob(rs.getString("job"));
			dto.setMgr(rs.getInt("mgr"));
			dto.setSal(rs.getInt("sal"));
			dto.setComm(rs.getInt("comm"));
			dto.setDeptno(rs.getInt("deptno"));
			
			}
				//6단계: dB와 연결돼있는 자원 종료
			rs.close(); 
			pstmt.close(); 
			con.close();
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dto;
			
			}//메서드 end
	
	
	
}
