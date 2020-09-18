package kosta.view;

import kosta.dao.PrepareSqlQueryDAO;
import kosta.vo.Emp;

public class MainView {

	public static void main(String[] args) {
//		System.out.println("1. 전체 사원이름 검색하기");
//		
//		SqlQueryDAO dao=new SqlQueryDAO();
//		dao.selectEname();
//		
//		System.out.println("2. 사원번호, 이름,job,hiredate 검색하기");
//		dao.selectAll();
//		
////		System.out.println("empno를 인수로 받아 empno에 해당하는 사원 삭제하기");
////		dao.deleteEmp(7369);
//		
//		System.out.println(" empno에 해당하는 사원의 ename,job,sal 변경하기");
//		dao.changeEmp(7499, "소은", "singer", 5000);
//		
//		System.out.println("수정 변경버전");
//		dao.update(new Emp(7566,"소은","냐냐",3000,null));
//		
//		System.out.println(" empno에 해당하는 사원 검색하기");
//		dao.searchEmp(7499);
//		dao.searchEmp(7800);
//		
//		System.out.println("emp테이블에 레코드 추가하기");
//		dao.insertEmp(new Emp(7878,"하이","manager",0,null));
		
		
		
		
		
	System.out.println("1. 전체 사원이름 검색하기");
		
		PrepareSqlQueryDAO pso=new PrepareSqlQueryDAO();
		pso.selectEname();
		
		System.out.println("2. 사원번호, 이름,job,hiredate 검색하기");
		pso.selectAll();
		
		System.out.println("empno를 인수로 받아 empno에 해당하는 사원 삭제하기");
		pso.deleteEmp(7979);
		
		System.out.println(" empno에 해당하는 사원의 ename,job,sal 변경하기");
		pso.changeEmp(new Emp(7878, "소은", "singer", 5000,null));
		
		
		System.out.println(" empno에 해당하는 사원 검색하기");
		pso.searchEmp(7979);
		pso.searchEmp(9000);
		
		System.out.println("emp테이블에 레코드 추가하기");
		pso.insertEmp(new Emp(0203,"하이","manager",0,null));
	}

}
