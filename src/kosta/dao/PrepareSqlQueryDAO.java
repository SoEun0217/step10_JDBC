package kosta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.util.DbUtil;
import kosta.vo.Emp;

public class PrepareSqlQueryDAO {
	
	/**
	 * emp테이블에서 모든 사원의 ename을정보 가져오기
	 */
	public void selectEname() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("select ename from emp");
			rs=ps.executeQuery();
			while(rs.next()) {
				String ename=rs.getString("ename");
				System.out.println(ename);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		
	}
	
	public void selectAll() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("select empno,ename,job,hiredate from emp");
			rs=ps.executeQuery();
			while(rs.next()) {
				int empno=rs.getInt(1);
				String ename=rs.getString(2);
				String job=rs.getString(3);
				String hiredate=rs.getString(4);
				
				System.out.println(empno+ " | "+ename+ " | "+job+" | "+hiredate);
			}
			System.out.println("검색 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		
	}
		
	
	/**
	 * 1. empno를 인수로 받아 empno에 해당하는 사원 삭제하기
	 * */
	public void deleteEmp(int empno) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("delete from emp where empno=?");
			ps.setInt(1, empno);
			int i=ps.executeUpdate();
			if(i==0)System.out.println("삭제 안됨");
			else System.out.println("삭제 완료");
		}catch(SQLException e) {
			
		
		}finally {
			DbUtil.dbClose(con,ps);
		}
	}
	
	
	/**
	 * 2. empno에 해당하는 사원의 ename,job,sal 변경하기
	 * 인수 : empno,ename,job,sal받는다
	 * */
	public void changeEmp(Emp emp) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("update emp set ename=?,job=?,sal=? where empno=?");
			ps.setString(1, emp.getEname());
			ps.setString(2,emp.getJob());
			ps.setInt(3, emp.getSal());
			ps.setInt(4, emp.getEmpno());
			int i=ps.executeUpdate();
			if(i==0)System.out.println("수정불가");
			else System.out.println("수정완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps);
		}
	}
	
	/**
	 * 3. empno에 해당하는 사원 검색하기
	 * */
	public void searchEmp(int empno) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Emp emp=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("select empno,ename,job,sal, hiredate from emp where empno=?");
			ps.setInt(1, empno);
			rs=ps.executeQuery();
			if(rs.next()) {
				emp=new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt("sal"),rs.getString(5));
				
			}
			System.out.println("검색 결과 : "+emp);
			System.out.println("검색완료되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps, rs);
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
		PreparedStatement ps=null;
		String sql="insert into emp (empno,ename,job,hiredate) values(?,?,?,sysdate)";
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setString(3, emp.getJob());
			
			int i=ps.executeUpdate();
			if(i==0)System.out.println("추가안됨");
			else System.out.println("추가됨");
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps);
		}
	}
	
}
