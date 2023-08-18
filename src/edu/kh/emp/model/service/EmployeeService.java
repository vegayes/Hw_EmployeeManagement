package edu.kh.emp.model.service;

import static edu.kh.emp.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/** 1. 전체 사원 정보 조회 서비스
	 */
	public List<Employee> selectAll() throws Exception {
		
		Connection con = getConnection();
		
		List<Employee> list = dao.selectAll(con);

		close(con);
		
		return list;
	}

	/** 2. 새로운 사원 추가 서비스
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
	public int insert(int empId, String empName, String empNo, String email, 
			String phone, String deptCode, String jobCode, String salLevel, 
			int salary, float bonus, String managerId, String hireDate, String entDate, 
			/*char*/ String entYN) throws Exception {

		Connection con = getConnection();
		
		int result = dao.insert(con, empId, empName, empNo, email, phone, deptCode, 
				jobCode, salLevel, salary,bonus,
				managerId, hireDate, entDate, entYN );

		if(result > 0 ) commit(con);
		else 			rollback(con);

		close(con);
	
		return result;
	}

	/*
	public List<Employee> selectSearchEmpId(int searchEmpId) throws Exception {

		Connection con = getConnection();
		
		List<Employee> searchList = dao.selectSearchEmpId(con, searchEmpId);
		
		close(con);
		
		
		return searchList;
	}
	
	*/
	
	/** 3. 사번이 일치하는 사원 정보 조회 서비스
	 * @param searchEmpId
	 * @return
	 * @throws Exception
	 */
	public Employee selectSearchEmpId(int searchEmpId) throws Exception {
		Connection con = getConnection();
		
		Employee emp = dao.selectSearchEmpId(con, searchEmpId);
		
		close(con);
		
		
		return emp;
	}

	
	/** 4. 사번이 일치하는 사원 정보 수정 서비스
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
	public int updateEmployee(int updateEmpId, String updateEmpName, String updateEmpNo, String updateEmail,
			String updatePhone, String updateDeptCode, String updateJobCode, String updateSalLevel, int updateSalary,
			float updateBonus, String updateManagerId, String updateHireDate, String updateEntDate,
			String updateEntYN) throws Exception {

		Connection con = getConnection();
		
		int result = dao.updateEmployee(con,updateEmpId, updateEmpName, updateEmpNo, updateEmail,  
				updatePhone,updateDeptCode, updateJobCode, updateSalLevel, updateSalary,
				updateBonus, updateManagerId,updateHireDate,  updateEntDate,updateEntYN );
		
		if(result > 0 ) commit (con);
		else 			rollback(con);
		
		close(con);
		
		return result;
	}

	/**  5. 사번이 일치하는 사원 정보 삭제 서비스
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public int deleteEmployee(int empId) throws Exception{
		
		Connection con = getConnection();
		
		int result = dao.deleteEmployee(con, empId);
		
		if(result > 0 ) commit(con);
		else 			rollback(con);
		
		close(con);
		
		return result;
	}

	/** 6. 입력 받은 부서와 일치하는 모든 사원 정보 조회 서비스
	 * @param searchDept
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectDeptEmp(String searchDept) throws Exception {
		
		Connection con = getConnection();
		
		List<Employee> empList = dao.selectDeptEmp(con, searchDept);

		close(con);
		
		return empList;
	}

	/** 7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회 서비스 [List]
	 * @param searchSalary
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalaryEmp(int searchSalary) throws Exception{

		Connection con = getConnection();
		
		List<Employee> empList = dao.selectSalaryEmp(con, searchSalary);
		
		close(con);
		
		return empList;
	}



	

	/**  7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회 서비스 [Map]
	 * @param searchSalary
	 * @return
	 */
/*	public Map<String, Object> selectSalaryEmp(int searchSalary)throws Exception  {
		
		Connection con = getConnection();
		
		Map<String,Object> empMap = dao.selectSalaryEmp(con,searchSalary);
		
		close(con);
		
		return empMap;
	}	
	
*/






	/**  8. 부서별 급여 합 전체 조회 서비스[List]
	 * @return
	 * @throws Exception
	 */
/*	public List<Employee> selectDeptTotalSalary() throws Exception{

		Connection con = getConnection();
		
		List<Employee> deptList = dao.selectDeptTotalSalary(con);
		
		close(con);
		
		return deptList;
	}
*/
	
	
	/** 8. 부서별 급여 합 전체 조회 서비스[Map]
	 * @return
	 */
	public Map<String, Integer> selectDeptTotalSalary() throws Exception {

		Connection con = getConnection();
		
		Map<String, Integer> deptMap = dao.selectDeptTotalSalary(con);
		
		close(con);
		
		return deptMap;
	}

	
	
	
	
	/** 9. 주민등록번호가 일치하는 사원 정보 조회 서비스
	 * @param searchNo
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpNo(String searchNo) throws Exception{
		
		 Connection con = getConnection();
		 
		 Employee emp = dao.selectEmpNo(con,searchNo);
		 
		 close(con);

		return emp;
	}



	/** 10. 직급별 급여 평균 조회 서비스
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectJobAvgSalary() throws Exception{

		Connection con = getConnection();
		
		List<Employee> jobList = dao.selectJobAvgSalary(con);
		
		close(con);
		
		return jobList;
	}

	public int questionStmt() throws Exception {
		
		Connection con = getConnection();
		
		int result = dao.questionStmt(con);
		
		if(result >0) commit(con);
		else 		  rollback(con);
		
		close(con);
		
		return result;
	}



	
	
	/*

	public int updateEmployee(int updateEmpId, String updateEmpName, String updateEmpNo, String updateEmail,
			String updatePhone, String updateDeptTitle, String updateJobName, String updateSalLevel, int updateSalary, 
			float updateBonus, String updateManagerId, String updateHireDate, String updateEntDate, String updateEntYN) throws Exception{

		Connection con = getConnection();
		
		int result = dao.updateEmployee(con,updateEmpId, updateEmpName, updateEmpNo, updateEmail,  
				updatePhone,updateDeptTitle, updateJobName, updateSalLevel, updateSalary,
				updateBonus, updateManagerId,updateHireDate,  updateEntDate,updateEntYN );
		
		if(result > 0 ) commit (con);
		else 			rollback(con);
		
		close(con);
		
		return result;
	}

*/

	
}
