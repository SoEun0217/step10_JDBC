package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.util.DbUtil;

public class TransactionDAO {
	/**
	 * ������ü ��� ����
	 */
	public void transfer(String inputAccount, String outputAccount, int money) {
		Connection con = null;

		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);// �ڵ�Ŀ�� ����

			// ����ϱ�
			result = withDraw(con, outputAccount, money);
			System.out.println("����ϱ� : "+result);
			if (result == 0) {
				throw new SQLException("����ϱ⿡�� ���ܹ߻��Ͽ� ������ü ����");
			}
			// �Ա��ϱ�
			result = deposit(con, inputAccount, money);
			System.out.println("�Ա��ϱ� : "+result);
			if (result == 0) {
				throw new SQLException("�Ա��ϱ⿡�� ���ܹ߻��Ͽ� ������ü ����");
			}

			// �Ѿ�Ȯ���ϱ�
			if(!balanceMaxCheck(con, inputAccount)) {//�ݾ��ʰ�...
				throw new SQLException("�Աݰ����� �Ѿ��� �ʰ��Ͽ����ϴ�.");
			}

			// ����Ϸ�
			con.commit();
			System.out.println("������ü �����Ͽ����ϴ�.");

		} catch (SQLException e) {
			try {
				con.rollback();
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DbUtil.dbClose(con,null);
			}
		}

	}

	/**
	 * ����ϱ�
	 */
	public int withDraw(Connection con, String outputAccount, int money) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update bank set balance=balance-? where account = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, money);
			ps.setString(2, outputAccount);
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(null, ps);
		}

		return result;
	}

	/**
	 * �Ա��ϱ�
	 */
	public int deposit(Connection con, String inputAccount, int money) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update bank set balance=balance+? where account = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, money);
			ps.setString(2, inputAccount);
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(null, ps);
		}

		return result;
	}

	/**
	 * �Աݰ��� �Ѿ� Ȯ���ϱ� return : -true�̸� ����-commit -false �ȵ�-rollback
	 */
	public boolean balanceMaxCheck(Connection con, String inputAccount) throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean result=false;
		String sql="select balance from bank where account=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,inputAccount);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)<1000) {
					result=true;
				}
			}
			
		}finally {
			DbUtil.dbClose(null, ps, rs);
		}
		return result;
	}

	public static void main(String[] args) {
		TransactionDAO dao = new TransactionDAO();
		System.out.println("--1. ��ݰ��� ����----");
		 dao.transfer("A01", "A05",200);//�Ա�, ���, �ݾ�

		System.out.println("--2. �Աݰ��� ����----");
		dao.transfer("A04", "A01",200);//�Ա�, ���, �ݾ�

		System.out.println("--3. �Աݰ����� �Ѿ� 1000�� �̻��ΰ��----");
		 //dao.transfer("A02", "A01",700);//�Ա�, ���, �ݾ�

		System.out.println("--4. ����----------");
		//dao.transfer("A02", "A01", 100);// �Ա�, ���, �ݾ�
	}
}
