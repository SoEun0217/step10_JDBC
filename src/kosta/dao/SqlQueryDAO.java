package kosta.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kosta.util.DbUtil;
import kosta.vo.Emp;

public class SqlQueryDAO {
	
	/**
	 * emp���̺��� ��� ����� ename������ ��������
	 */
	public void selectEname() {
		// �ε�->static �� ��
		Connection con = null;
		Statement st=null;
		ResultSet rs=null;
		// ����
		try {
			con = DbUtil.getConnection();
		// ����
			st=con.createStatement();
			rs=st.executeQuery("select ename from emp");//query���� ;������ �ȵȴ�.
			while(rs.next()) {
				String ename=rs.getString(1);
				System.out.println(ename);
			}
			System.out.println("*********�˻� �Ϸ�***********");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		// �ݱ�
			DbUtil.dbClose(con, st, rs);
		}
	}
	public void selectAll() {
		Connection con=null;//finally������ try���ۿ� �����ش�
		Statement st=null;
		ResultSet rs=null;
		
		//�ε� ���� ���� �ݱ�
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
		System.out.println("*****�˻� ��********");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st, rs);
		}
	}
	
	/**
	 * 1. empno�� �μ��� �޾� empno�� �ش��ϴ� ��� �����ϱ�
	 * */
	public void deleteEmp(int empno) {
		Connection con=null;
		Statement st=null;
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			int i=st.executeUpdate("delete from emp where empno="+empno);
			if(i==0) System.out.println(i+"  ���� �ȵ�.");
			else {
				System.out.println("�����Ǿ����ϴ�.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st);
		}
	}
	
	
	
	/**
	 * 2. empno�� �ش��ϴ� ����� ename,job,sal �����ϱ�
	 * �μ� : empno,ename,job,sal�޴´�
	 * */
	public void changeEmp(int empno,String ename,String job,int sal) {
		Connection con=null;
		Statement st=null;
		try {
			con=DbUtil.getConnection();
			st=con.createStatement();
			int i=st.executeUpdate("update emp set ename='"+ename+"', job='"+job+"', sal="+sal+" where empno="+empno);
			if(i>0) {
				System.out.println("����Ǿ����ϴ�.");
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
			if(i==0)System.out.println("���� �ȵ�");
			else System.out.println("������");
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st);
		}
	}
	
	/**
	 * 3. empno�� �ش��ϴ� ��� �˻��ϱ�
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
			System.out.println("��� : "+emp);
			System.out.println("�˻��Ϸ�Ǿ����ϴ�.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, st, rs);
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
	Statement st=null;
	String sql="insert into emp (empno,ename,job,hiredate) "
			+ "values ("+emp.getEmpno()+",'"+emp.getEname()+"','"+emp.getJob()+"',sysdate)";
	try {
		con=DbUtil.getConnection();
		st=con.createStatement();
		int i=st.executeUpdate(sql);
		if(i>0) {
			System.out.println("�߰��Ǿ����ϴ�.");
			System.out.println(sql);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		DbUtil.dbClose(con, st);
	}
	}

}
