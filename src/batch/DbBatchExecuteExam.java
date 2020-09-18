package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import kosta.util.DbUtil;

public class DbBatchExecuteExam {

	/**
	 * 삭제하기
	 */
	public void delete(List<Integer> empNoList) {// 7499,7369...
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from copy_emp where empno=?";
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (int empno : empNoList) {
				ps.setInt(1, empno);

				ps.addBatch();// 일괄처리할 작업을 추가한다.(delete문장이 모이게 된다.)
				ps.clearParameters();// 해주면 더 효율적인 관리 가능

			}
			int result[] = ps.executeBatch();// 일괄처리(DB에 전송하기)
			System.out.println("성공하였습니다...");
			System.out.println("쿼리 개수 : " + result.length);
			for (int re : result) {
				System.out.println(re);
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			DbUtil.dbClose(con, ps);
		}
	}

	public static void main(String[] args) {
		List<Integer>list=Arrays.asList(7369,8888,9000,7900);//없는번호가 있더라도
		//쿼리문 자체의 문제가 아니어서 오류가 떨어지지 않고 실행되었다고 본다. 
		
		new DbBatchExecuteExam().delete(list);
	}

}
