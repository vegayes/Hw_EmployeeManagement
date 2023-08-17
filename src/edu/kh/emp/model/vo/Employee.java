package edu.kh.emp.model.vo;

// VO : 값 저장용 객체 ( == DB 조회 결과 한 행을 저장)
public class Employee {
	
	private int empId; // 사원번호
	private String empName; // 이름
	private String empNo; // 주민번호
	private String email; // 이메일
	private String phone; // 전화번호
	
	private String deptCode; // 부서코드
	private String jobCode; // 직급코드
	private String salLevel; // 급여등급
	private int salary; // 급여
	private int bonus; // 보너스
	private String managerId; // 사수번호
	
	private String deptTitle; // 부서명
	private String jobName ; // 직급명
	
	
	// 기본 생성자
	public Employee() {}



	public Employee(int empId, String empName, String empNo, String email, String phone, String deptTitle,
			String jobName, String deptCode, String jobCode, String salLevel, int salary, int bonus, String managerId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptTitle = deptTitle;
		this.jobName = jobName;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
	}



	public Employee(int empId, String empName, String empNo, String email, String phone,  String deptTitle,
			String jobName, int salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptTitle = deptTitle;
		this.jobName = jobName;
		this.salary = salary;
	}



	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
		this.empId = empId;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getEmpNo() {
		return empNo;
	}



	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getDeptTitle() {
		return deptTitle;
	}



	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}



	public String getJobName() {
		return jobName;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



	public String getDeptCode() {
		return deptCode;
	}



	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}



	public String getJobCode() {
		return jobCode;
	}



	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}



	public String getSalLevel() {
		return salLevel;
	}



	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}



	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}



	public int getBonus() {
		return bonus;
	}



	public void setBonus(int bonus) {
		this.bonus = bonus;
	}



	public String getManagerId() {
		return managerId;
	}



	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}



	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", deptTitle=" + deptTitle + ", jobName=" + jobName + ", deptCode=" + deptCode
				+ ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary=" + salary + ", bonus=" + bonus
				+ ", managerId=" + managerId + "]";
	}
	
	

}
