package kosta.util;

/**
 * DB���� �Ӽ� ������ �����ϴ� ��� �ʵ�
 * */
public interface DbProperty {
	public static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	String URL="jdbc:oracle:thin:@localhost:1521:xe";
	String USER="scott";
	String PASSWORD="tiger";
}
