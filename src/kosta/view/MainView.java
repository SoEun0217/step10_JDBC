package kosta.view;

import kosta.dao.PrepareSqlQueryDAO;
import kosta.vo.Emp;

public class MainView {

	public static void main(String[] args) {
//		System.out.println("1. ��ü ����̸� �˻��ϱ�");
//		
//		SqlQueryDAO dao=new SqlQueryDAO();
//		dao.selectEname();
//		
//		System.out.println("2. �����ȣ, �̸�,job,hiredate �˻��ϱ�");
//		dao.selectAll();
//		
////		System.out.println("empno�� �μ��� �޾� empno�� �ش��ϴ� ��� �����ϱ�");
////		dao.deleteEmp(7369);
//		
//		System.out.println(" empno�� �ش��ϴ� ����� ename,job,sal �����ϱ�");
//		dao.changeEmp(7499, "����", "singer", 5000);
//		
//		System.out.println("���� �������");
//		dao.update(new Emp(7566,"����","�ĳ�",3000,null));
//		
//		System.out.println(" empno�� �ش��ϴ� ��� �˻��ϱ�");
//		dao.searchEmp(7499);
//		dao.searchEmp(7800);
//		
//		System.out.println("emp���̺� ���ڵ� �߰��ϱ�");
//		dao.insertEmp(new Emp(7878,"����","manager",0,null));
		
		
		
		
		
	System.out.println("1. ��ü ����̸� �˻��ϱ�");
		
		PrepareSqlQueryDAO pso=new PrepareSqlQueryDAO();
		pso.selectEname();
		
		System.out.println("2. �����ȣ, �̸�,job,hiredate �˻��ϱ�");
		pso.selectAll();
		
		System.out.println("empno�� �μ��� �޾� empno�� �ش��ϴ� ��� �����ϱ�");
		pso.deleteEmp(7979);
		
		System.out.println(" empno�� �ش��ϴ� ����� ename,job,sal �����ϱ�");
		pso.changeEmp(new Emp(7878, "����", "singer", 5000,null));
		
		
		System.out.println(" empno�� �ش��ϴ� ��� �˻��ϱ�");
		pso.searchEmp(7979);
		pso.searchEmp(9000);
		
		System.out.println("emp���̺� ���ڵ� �߰��ϱ�");
		pso.insertEmp(new Emp(0203,"����","manager",0,null));
	}

}
