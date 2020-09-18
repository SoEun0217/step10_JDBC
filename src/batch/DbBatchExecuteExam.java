package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import kosta.util.DbUtil;

public class DbBatchExecuteExam {

	/**
	 * �����ϱ�
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

				ps.addBatch();// �ϰ�ó���� �۾��� �߰��Ѵ�.(delete������ ���̰� �ȴ�.)
				ps.clearParameters();// ���ָ� �� ȿ������ ���� ����

			}
			int result[] = ps.executeBatch();// �ϰ�ó��(DB�� �����ϱ�)
			System.out.println("�����Ͽ����ϴ�...");
			System.out.println("���� ���� : " + result.length);
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
		List<Integer>list=Arrays.asList(7369,8888,9000,7900);//���¹�ȣ�� �ִ���
		//������ ��ü�� ������ �ƴϾ ������ �������� �ʰ� ����Ǿ��ٰ� ����. 
		
		new DbBatchExecuteExam().delete(list);
	}

}
