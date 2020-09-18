package kosta.vo;

public class Emp {
	private int empno;
	private String ename;
	private String job;
	private int sal;
	private String hiredate;
	
	public Emp() {}

	public Emp(int empno, String ename, String job, int sal, String hiredate) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.hiredate = hiredate;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getHireate() {
		return hiredate;
	}

	public void setHireate(String hireate) {
		this.hiredate = hireate;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("empno = ");
		sb.append(empno);
		sb.append(" ename = ");
		sb.append(ename);
		sb.append(" job = ");
		sb.append(job);
		sb.append(" sal = ");
		sb.append(sal);
		sb.append(" hiredate = ");
		sb.append(hiredate);
		return sb.toString();
	}
	
	
}
