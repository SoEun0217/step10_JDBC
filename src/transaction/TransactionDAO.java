package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kosta.util.DbUtil;

public class TransactionDAO {
	/**
	 * 계좌이체 기능 구현
	 */
	public void transfer(String inputAccount, String outputAccount, int money) {
		Connection con = null;

		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);// 자동커밋 해지

			// 출금하기
			result = withDraw(con, outputAccount, money);
			System.out.println("출금하기 : "+result);
			if (result == 0) {
				throw new SQLException("출금하기에서 예외발생하여 계좌이체 실패");
			}
			// 입금하기
			result = deposit(con, inputAccount, money);
			System.out.println("입금하기 : "+result);
			if (result == 0) {
				throw new SQLException("입금하기에서 예외발생하여 계좌이체 실패");
			}

			// 총액확인하기
			if(!balanceMaxCheck(con, inputAccount)) {//금액초과...
				throw new SQLException("입금계좌의 총액이 초과하였습니다.");
			}

			// 저장완료
			con.commit();
			System.out.println("계좌이체 성공하였습니다.");

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
	 * 출금하기
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
	 * 입금하기
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
	 * 입금계좌 총액 확인하기 return : -true이면 가능-commit -false 안됨-rollback
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
		System.out.println("--1. 출금계좌 오류----");
		 dao.transfer("A01", "A05",200);//입금, 출금, 금액

		System.out.println("--2. 입금계좌 오류----");
		dao.transfer("A04", "A01",200);//입금, 출금, 금액

		System.out.println("--3. 입금계좌의 총액 1000원 이상인경우----");
		 //dao.transfer("A02", "A01",700);//입금, 출금, 금액

		System.out.println("--4. 성공----------");
		//dao.transfer("A02", "A01", 100);// 입금, 출금, 금액
	}
}
