package kosta.util;

/**
 * DB������ �ʿ��� �ε�,����,�ݱ� ��� ����
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	/**
	 * �ε�
	 */
	static {
		try {
			Class.forName(DbProperty.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����
	 */
	// Ŀ�ؼ��� �����ϸ� �ȵȴ�.�׻�close�� ���ش�.����ó�� ��������(�ֳ�?��¼�� DAO���� ����ó���ؾ��ϴϱ�
	// ���ʿ��� ���Ƽ�����
	// static���̴� ����:ȣ���Ҷ����� �����ϱ⺸�� �ٷ� �����ϴ°� ����.������ ��������� ������ ���� �����ϱ�
	// static���� ���� �ȴ�.
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbProperty.URL, DbProperty.USER, DbProperty.PASSWORD);
	}

	/**
	 * �ݱ�(select�ΰ��)
	 */
	public static void dbClose(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			dbClose(con, st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ݱ� (insert,update,delete �� ���)
	 */
	public static void dbClose(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();// �����ϱ� ������°�!
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
