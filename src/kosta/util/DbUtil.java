package kosta.util;

/**
 * DB연동에 필요한 로드,연결,닫기 기능 구현
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	/**
	 * 로드
	 */
	static {
		try {
			Class.forName(DbProperty.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 연결
	 */
	// 커넥션은 공유하면 안된다.항상close를 해준다.예외처리 던져주자(왜냐?어쩌피 DAO에서 예외처리해야하니까
	// 한쪽에서 몰아서하자
	// static붙이는 이유:호출할때마다 생성하기보다 바로 접근하는게 좋다.지금은 멤버변수에 접근할 일이 없으니까
	// static으로 만들어도 된다.
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbProperty.URL, DbProperty.USER, DbProperty.PASSWORD);
	}

	/**
	 * 닫기(select인경우)
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
	 * 닫기 (insert,update,delete 인 경우)
	 */
	public static void dbClose(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();// 썼으니까 닫으라는것!
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
