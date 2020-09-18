package kosta.util;

/**
 * DB관련 속성 정보를 관리하는 상수 필드
 * */
public interface DbProperty {
	public static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	String URL="jdbc:oracle:thin:@localhost:1521:xe";
	String USER="scott";
	String PASSWORD="tiger";
}
