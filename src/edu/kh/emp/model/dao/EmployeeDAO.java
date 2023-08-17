package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EmployeeDAO() {	
		try {		
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));			
		}catch(Exception e) {
			// IO Exception 날 수 있음.		
			e.printStackTrace();
		}
	}

	/** 1. 전체 사원 정보 조회 DAO 
	 * @param con
	 * @return
	 */
	public List<Employee> selectAll(Connection con) throws Exception {
		
		// 결과 저장용 변수 선언
		// ==> 여러개를 받아올거기 때문에 List 
		List<Employee> empList = new ArrayList<Employee>();
		try {
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = con.createStatement(); // 위치 홀더 필요 없으므로 stmt 사용! 
			
			// SQL을 수행 후 결과(ResultSet) 반환 받음
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 얻어와 한 행씩 접근하여 
			// Employee 객체 생성 후 컬럼값 담기
			// -> List 추가
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				// EMP_ID 컬럼은 문자열 컬럼이지만, 저장된 값들이 모두 숫자 형태
				// ->  DB에서 자동으로 형변환 진행해서 얻어옴		
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
				empList.add(emp); // List에 담기.
				
			}// while문 종료 
		}finally {
			
//			close(rs); // ???
			
			close(stmt);
			
		}
		return empList;
	}

	/** 2. 새로운 사원 추가 DAO
	 * @param con
	 * @param empId
	 * @param empName
	 * @param empNo
	 * @param email
	 * @param phone
	 * @param deptCode
	 * @param jobCode
	 * @param salLevel
	 * @param salary
	 * @param bonus
	 * @param managerId
	 * @param hireDate
	 * @param entDate
	 * @param entYN
	 * @return
	 * @throws Exception
	 */
	public int insert(Connection con, int empId, String empName, String empNo, String email, String phone,
			String deptCode, String jobCode, String salLevel, int salary, float bonus, String managerId, String hireDate,
			String entDate, /*char*/ String entYN) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insert");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			pstmt.setString(2, empName);
			pstmt.setString(3, empNo);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, deptCode);
			pstmt.setString(7, jobCode);
			pstmt.setString(8, salLevel);
			pstmt.setInt(9, salary);
			pstmt.setFloat(10, bonus);
			pstmt.setString(11, managerId);
			pstmt.setString(12, hireDate);
			pstmt.setString(13, entDate);
			pstmt.setString(14, entYN); 
			
			result = pstmt.executeUpdate();

		}finally {
			
			close(pstmt);

		}
		
		return result;
	}


	/* 리스트로 가져옴.
	public List<Employee> selectSearchEmpId(Connection con, int searchEmpId) throws Exception{

		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("selectSearchEmpId");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,searchEmpId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				

				int empId = rs.getInt("EMP_ID"); 	
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
				empList.add(emp); 
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return empList;
	}
	*/
	
	/** 3. 사번이 일치하는 사원 정보 조회 DAO
	 * @param con
	 * @param searchEmpId
	 * @return
	 * @throws Exception
	 */
	public Employee selectSearchEmpId(Connection con, int searchEmpId) throws Exception{
		
		Employee emp = null;
		
		try {
			String sql = prop.getProperty("selectSearchEmpId");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,searchEmpId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				

//				int empId = rs.getInt("EMP_ID"); 	// 굳이 안써도됨.
				int empId = rs.getInt("EMP_ID"); 	
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
				/*emp = new Employee(searchEmpId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );*/
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return emp;
	}

	/** 4. 사번이 일치하는 사원 정보 수정 DAO
	 * @param con
	 * @param updateEmpId
	 * @param updateEmpName
	 * @param updateEmpNo
	 * @param updateEmail
	 * @param updatePhone
	 * @param updateDeptCode
	 * @param updateJobCode
	 * @param updateSalLevel
	 * @param updateSalary
	 * @param updateBonus
	 * @param updateManagerId
	 * @param updateHireDate
	 * @param updateEntDate
	 * @param updateEntYN
	 * @return
	 * @throws Exception
	 */
	public int updateEmployee(Connection con, int updateEmpId, String updateEmpName, String updateEmpNo,
			String updateEmail, String updatePhone, String updateDeptCode, String updateJobCode, String updateSalLevel,
			int updateSalary, float updateBonus, String updateManagerId, String updateHireDate, String updateEntDate,
			String updateEntYN) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateEmployee");
			
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, updateEmpName);
			pstmt.setString(2, updateEmpNo);
			pstmt.setString(3, updateEmail);
			pstmt.setString(4, updatePhone);
			pstmt.setString(5, updateDeptCode);
			pstmt.setString(6, updateJobCode);
			pstmt.setString(7, updateSalLevel);
			pstmt.setInt(8, updateSalary);
			pstmt.setFloat(9, updateBonus);
			pstmt.setString(10, updateManagerId);
			pstmt.setString(11, updateHireDate);
			pstmt.setString(12, updateEntDate);
			pstmt.setString(13, updateEntYN); 
			pstmt.setInt(14, updateEmpId);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			
			close(pstmt);
			
		}
		
		
		return result;
	}

	/** 5. 사번이 일치하는 사원 정보 삭제 DAO
	 * @param con
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public int deleteEmployee(Connection con, int empId) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteEmployee");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate();


		}finally {
			
			close(pstmt);
	
		return result;
		}
	
    }

	/** 6. 입력 받은 부서와 일치하는 모든 사원 정보 조회 DAO
	 * @param con
	 * @param searchDept
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectDeptEmp(Connection con, String searchDept) throws Exception{

		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectDeptEmp");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,searchDept);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 	
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
				empList.add(emp);
				
			}
			
			
			
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return empList;
	}

	/** 7. 입력 받은 급여 이상을 받는 모든 사원 정보 DAO 
	 * @param con
	 * @param searchSalary
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalaryEmp(Connection con, int searchSalary) throws Exception {
		
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectSalaryEmp");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, searchSalary);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 	
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
				empList.add(emp);
			}
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		return empList;
	}

	/** 8. 부서별 급여 합 전체 조회 DAO
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectDeptTotalSalary(Connection con) throws Exception {

		List<Employee> deptList = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("selectDeptTotalSalary");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String deptTitle = rs.getString("DEPT_TITLE");
				int sumDept = rs.getInt("SUM_SALARY");
				
				Employee emp = new Employee();
				
				emp.setDeptTitle(deptTitle);
				emp.setSalary(sumDept);
				
				deptList.add(emp);
			}
	
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return deptList;
	}

	
	
	
	/** 9. 주민등록번호가 일치하는 사원 정보 조회 DAO
	 * @param con
	 * @param searchNo
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpNo(Connection con, String searchNo) throws Exception {
		
		Employee emp = null;
		
		try {
			
			String sql = prop.getProperty("selectEmpNo");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,searchNo);
			
			
			rs = pstmt.executeQuery();
	
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 	
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, empNo, 
						email, phone, deptTitle, jobName, salary );
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return emp;
	}

	/** 10. 직급별 급여 평균 조회 DAO
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectJobAvgSalary(Connection con) throws Exception{
		
		List<Employee> jobList = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectJobAvgSalary");
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String jobName = rs.getString("JOB_NAME");
				int avgSalary = rs.getInt("AVG_SALARY");
				
				Employee emp = new Employee();
				
				emp.setJobName(jobName);
				emp.setSalary(avgSalary);
				
				jobList.add(emp);
			}
					
		}finally {
			close(rs);
			close(stmt);
		}

		return jobList;
	}



	
	
	
	
	
	/*

	public int updateEmployee(Connection con, int updateEmpId, String updateEmpName, String updateEmpNo,
			String updateEmail, String updatePhone, String updateDeptTitle, String updateJobName, String updateSalLevel, 
			int updateSalary, float updateBonus, String updateManagerId, String updateHireDate, String updateEntDate,
			String updateEntYN) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateEmployee");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, updateEmpName);
			pstmt.setString(2, updateEmpNo);
			pstmt.setString(3, updateEmail);
			pstmt.setString(4, updatePhone);
			pstmt.setString(5, updateDeptTitle);
			pstmt.setString(6, updateJobName);
			pstmt.setInt(7, updateSalary);
			pstmt.setInt(8, updateEmpId);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}

*/
	
}	
	
	
	
	
	
	
	
	
	
	
	
	

