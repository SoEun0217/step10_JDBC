package kosta.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kosta.util.DbUtil;
import kosta.vo.Emp;

public class SqlQueryDAO {
	
	/**
	 * emp테이블에서 모든 사원의 ename을정보 가져오기
	 */
	public void selectEname() {
		// 로드->static 블럭 끝
		Connection con = null;
		Statement st=null;
		ResultSet rs=null;
		// 연결
		try {
			con = DbUtil.getConnection();
		// 실행
			st=con.createStatement();
			rs=st.executeQuery("select ename from emp");//query끝에 ;찍으면 안된다.
			while(rs.next()) {
				String ename=rs.getString(1);
				System.out.println(ename);
			}
			System.out.println("*********검색 완료***********");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		// 닫기
			DbUtil.dbClose(con, st, rs);
		}
	}
	public void selectAll() {
		Connection con=null;//finally때문에 try블럭밖에 적어준다
		Statement st=null;
		ResultSet rs=null;
		
		//로드 연결 실행 닫기
		try {
		con=DbUtil.getConnection();
		st=con.createStatement();
		rs=st.executeQuery("select empno,ename,job,hiredate from emp");
		while(rs.next()) {
			int empno=rs.getInt(1);
			String ename=rs.getString(2);
			String job=rs.getString(3);
			String hiredate=rs.getString(4);
			
			System.out.println(empno+ " | "+ename+ " | "+job+" | "+hiredate);
		}
		System.out.println("*****검색 끝********");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st, rs);
		}
	}
	
	/**
	 * 1. empno를 인수로 받아 empno에 해당하는 사원 삭제하기
	 * */
	public void deleteEmp(int empno) {
		Connection con=null;
		Statement st=null;
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			int i=st.executeUpdate("delete from emp where empno="+empno);
			if(i==0) System.out.println(i+"  삭제 안됨.");
			else {
				System.out.println("삭제되었습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st);
		}
	}
	
	
	
	/**
	 * 2. empno에 해당하는 사원의 ename,job,sal 변경하기
	 * 인수 : empno,ename,job,sal받는다
	 * */
	public void changeEmp(int empno,String ename,String job,int sal) {
		Connection con=null;
		Statement st=null;
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			int i=st.executeUpdate("update emp set ename='"+ename+"', job='"+job+"', sal="+sal+" where empno="+empno);
			if(i>0) {
				System.out.println("변경되었습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st);
		}
	}
	
	public void update(Emp emp) {
		Connection con=null;
		Statement st=null;
		String sql="update emp set ename='"+emp.getEname()+"',job='"+emp.getJob()+"', "
				+ "sal="+emp.getSal()+"where empno="+emp.getEmpno();
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			int i=st.executeUpdate(sql);
			if(i==0)System.out.println("수정 안됨");
			else System.out.println("수정됨");
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st);
		}
	}
	
	/**
	 * 3. empno에 해당하는 사원 검색하기
	 * */
	public void searchEmp(int empno) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="select empno,ename,job,sal, hiredate from emp where empno="+empno;
		Emp emp=null;
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				emp=new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt("sal"),rs.getString(5));
				
			}
			System.out.println("결과 : "+emp);
			System.out.println("검색완료되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st, rs);
		}
	}
	
	
	/**
	 * 4.emp테이블에 레코드 추가하기
	 * 추가하기 :emono,ename,job,sal,hiredate
	 * *hiredate 는 현재날짜등록
	 * 변수로 들어오는 것에 문자값에는 싱글로 묶어줘야함
	 * */
	public void insertEmp(Emp emp) {
	Connection con=null;
	Statement st=null;
	String sql="insert into emp (empno,ename,job,hiredate) "
			+ "values ("+emp.getEmpno()+",'"+emp.getEname()+"','"+emp.getJob()+"',sysdate)";
	try {
		con=DbUtil.getConnection();
		st=con.createStatement();
		int i=st.executeUpdate(sql);
		if(i>0) {
			System.out.println("추가되었습니다.");
			System.out.println(sql);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		DbUtil.dbClose(con, st);
	}
	}

}
