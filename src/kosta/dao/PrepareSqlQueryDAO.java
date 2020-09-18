package kosta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.util.DbUtil;
import kosta.vo.Emp;

public class PrepareSqlQueryDAO {
	
	/**
	 * emp���̺��� ��� ����� ename������ ��������
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
			System.out.println("�˻� �Ϸ�");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		
	}
		
	
	/**
	 * 1. empno�� �μ��� �޾� empno�� �ش��ϴ� ��� �����ϱ�
	 * */
	public void deleteEmp(int empno) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement("delete from emp where empno=?");
			ps.setInt(1, empno);
			int i=ps.executeUpdate();
			if(i==0)System.out.println("���� �ȵ�");
			else System.out.println("���� �Ϸ�");
		}catch(SQLException e) {
			
		
		}finally {
			DbUtil.dbClose(con,ps);
		}
	}
	
	
	/**
	 * 2. empno�� �ش��ϴ� ����� ename,job,sal �����ϱ�
	 * �μ� : empno,ename,job,sal�޴´�
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
			if(i==0)System.out.println("�����Ұ�");
			else System.out.println("�����Ϸ�");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps);
		}
	}
	
	/**
	 * 3. empno�� �ش��ϴ� ��� �˻��ϱ�
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
			System.out.println("�˻� ��� : "+emp);
			System.out.println("�˻��Ϸ�Ǿ����ϴ�.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
	}
	
	
	/**
	 * 4.emp���̺� ���ڵ� �߰��ϱ�
	 * �߰��ϱ� :emono,ename,job,sal,hiredate
	 * *hiredate �� ���糯¥���
	 * ������ ������ �Ϳ� ���ڰ����� �̱۷� ���������
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
			if(i==0)System.out.println("�߰��ȵ�");
			else System.out.println("�߰���");
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps);
		}
	}
	
}
