package edu.kh.emp.model.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

// 화면용 클래스 ( 입력 출력 할 수 있는 것 )
public class EmployeeView {
// View -> Service -> DAO -> query -> DAO-> Service-> View
	
	private Scanner sc = new Scanner(System.in);
	
	private EmployeeService service = new EmployeeService();
	
	// 메인 메뉴
	/**
	 * 메인 메뉴
	 */
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 전체 사원 정보 조회");
				System.out.println("2. 새로운 사원 추가");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				System.out.println("8. 부서별 급여 합 전체 조회");
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				System.out.println("10. 직급별 급여 평균 조회");		
				System.out.println("11. stmt 궁금한거 진행");		
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); //  추가!
				
				
				System.out.println();				
					
				switch(input) {
				case 1:  selectAll();   break;
				case 2:  insertEmployee();  break;
				case 3:  selectEmpId();   break;
				case 4:  updateEmployee();   break;
				case 5:  deleteEmployee();   break;
				case 6:  selectDeptEmp();   break;
				case 7:  selectSalaryEmp();   break;
				case 8:  selectDeptTotalSalary();   break;
				case 9:  selectEmpNo();   break;
				case 10:  selectJobAvgSalary();   break;
				case 11:  questionStmt();   break;
				case 0:  System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}while(input != 0);
		

		
	}
	

	// 주 기능 메서드
	/** 1. 전체 사원 정보 조회 
	 */
	public void selectAll() throws Exception  {  //1. 전체 사원 정보 조회
		
		System.out.println("<전체 사원 정보 조회>");

		List<Employee> empList = service.selectAll();
		
		printAll(empList);
	}
	
	/** 2. 새로운 사원 추가
	 * @throws Exception
	 */
	private void insertEmployee()  throws Exception { // 2. 새로운 사원 추가
	
		System.out.println("<새로운 사원 추가>");

		System.out.print("사번 : ");
		int empId = sc.nextInt();
		
		System.out.print("이름 :");
		String empName = sc.next();
		
		System.out.print("주민등록번호 :");
		String empNo = sc.next();
		
		System.out.print("이메일 :");
		String email = sc.next();
		
		System.out.print("전화번호 :");
		String phone = sc.next();
		
		System.out.print("부서 코드 :");
		String deptCode = sc.next();
		
		System.out.print("직책 코드:");
		String jobCode = sc.next();
		
		System.out.print("급여 등급:");
		String salLevel = sc.next();
		
		System.out.print("급여 :");
		int salary = sc.nextInt();
		
		System.out.print("보너스 :");
		float bonus = sc.nextFloat();
	
		System.out.print("상사 코드:");
		String managerId = sc.next();
		 
		sc.nextLine();
		
		System.out.print("입사일:");
		String hireDate = sc.nextLine();
		
		System.out.print("퇴사일:");
		String entDate = sc.nextLine();
		
		System.out.print("퇴직여부:");
		String entYN = sc.next();
		
		int result = service.insert(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary,bonus,
				managerId, hireDate, entDate, entYN );
		
		if(result > 0 ) {
			System.out.println("추가 성공");
		}else{
			System.out.println("추가 실패");
		}		
	}
	

	/** 3. 사번이 일치하는 사원 정보 조회
	 * @throws Exception
	 */
	private void selectEmpId() throws Exception { // 3. 사번이 일치하는 사원 정보 조회
		
		System.out.println("<사원 정보 조회>");
		

		int searchEmpId = inputEmpId();
		
		/*
		System.out.print("찾을 사번을 입력하세요 :");
		int searchEmpId = sc.nextInt();
		
		List<Employee> empList = service.selectSearchEmpId(searchEmpId);
		
		printAll(empList);
*/
		
		// emp로 바꾸기
		Employee emp = service.selectSearchEmpId(searchEmpId);
		printOne(emp);
		
		
	}
	

	/** 4. 사번이 일치하는 사원 정보 수정
	 * @throws Exception
	 */
	private void updateEmployee() throws Exception{ // 4. 사번이 일치하는 사원 정보 수정 
		
		System.out.println("<사원 정보 수정>");
		
		System.out.print("수정할 사번 :");
		int updateEmpId = sc.nextInt();
		
		System.out.print("수정할 이름:");
		String updateEmpName = sc.next();
		
		System.out.print("수정할 주민 등록 번호:");
		String updateEmpNo = sc.next();
		
		System.out.print("수정할 이메일:");
		String updateEmail = sc.next();
		
		System.out.print("수정할 전화번호:");
		String updatePhone = sc.next();
	
		System.out.print("수정할 부서 코드 :");
		String updateDeptCode = sc.next();
		
		System.out.print("수정할 직책 코드:");
		String updateJobCode = sc.next();
		
		System.out.print("수정할 급여 등급:");
		String updateSalLevel = sc.next();
		
		System.out.print("수정할 급여 : ");
		int updateSalary = sc.nextInt();
		
		System.out.print("수정할 보너스 :");
		float updateBonus = sc.nextFloat();
	
		System.out.print("수정할 상사 코드:");
		String updateManagerId = sc.next();
		 
		sc.nextLine();
		
		System.out.print("수정할 입사일:");
		String updateHireDate = sc.nextLine();
		
		System.out.print("수정할 퇴사일:");
		String updateEntDate = sc.nextLine();
		
		System.out.print("수정할 퇴직여부:");
		String updateEntYN = sc.next();
		
		
		int result = service.updateEmployee(updateEmpId, updateEmpName,
				updateEmpNo,updateEmail, updatePhone, updateDeptCode, updateJobCode, updateSalLevel, 
				updateSalary, updateBonus, updateManagerId, updateHireDate, updateEntDate,updateEntYN );
		
		if(result > 0 ) {
			System.out.println("수정 성공");
		}else {
			System.out.println("수정 실패");
		}
		
		
	}

	
	/**  5. 사번이 일치하는 사원 정보 삭제
	 * @throws Exception
	 */
	private void deleteEmployee() throws Exception{ // 5. 사번이 일치하는 사원 정보 삭제
		
		System.out.println("<사원 정보 삭제>");
		
		int empId = inputEmpId();
		
		System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
		char input = sc.next().toUpperCase().charAt(0);
		
		if(input == 'Y') {
			// 삭제 수행 서비스 호출
			
			int result = service.deleteEmployee(empId);
			
			if(result > 0 ) {
				System.out.println("삭제 성공");
			}else{
				System.out.println("삭제 실패");
			}	
			
		}else {
			System.out.println("취소되었습니다.");
		}
	}


	/**  6. 입력 받은 부서와 일치하는 모든 사원 정보 조회
	 * @throws Exception
	 */
	private void selectDeptEmp() throws Exception{  // 6. 입력 받은 부서와 일치하는 모든 사원 정보 조회

		System.out.println("<입력한 부서와 일치한 사원 정보 조회>");
		
		System.out.print("부서를 입력해주세요:");
		String searchDept = sc.nextLine();
		
		/*
		List<Employee> empList = service.selectDeptEmp(searchDept);
		printAll(empList);
		*/
		printAll(service.selectDeptEmp(searchDept));
		
	}

	
	/**  7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 * @throws Exception
	 */
	private void selectSalaryEmp() throws Exception{ // 7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회
		
		System.out.println("<입력한 급여 이상을 받는 사원 정보 조회>");
		
		System.out.print("급여를 입력해주세요:");
		int searchSalary = sc.nextInt();
		

		//List 사용
		List<Employee> empList = service.selectSalaryEmp(searchSalary);
		printAll(empList);

		
		// Map사용
//		Map<String, Object> empMap = service.selectSalaryEmp(searchSalary);
//		printAll(empMap);

		
	}
	
	/** 8. 부서별 급여 합 전체 조회
	 * @throws Exception
	 */
	private void selectDeptTotalSalary() throws Exception{ // 8. 부서별 급여 합 전체 조회

		System.out.println("<부서별 급여 합 전체 조회>");
		
		/*// List 사용
		List<Employee>deptList = service.selectDeptTotalSalary();
		printDept(deptList); 
		*/
		
		
		// Map 사용
		Map<String, Integer> deptMap = service.selectDeptTotalSalary();

		for( String temp : deptMap.keySet()){ // key만 뽑아옴. 
			System.out.println(temp + "\t | " + deptMap.get(temp));		
		}

		
	}

	
	/** 9. 주민등록번호가 일치하는 사원 정보 조회
	 * @throws Exception
	 */
	private void selectEmpNo() throws Exception { // 9. 주민등록번호가 일치하는 사원 정보 조회
		System.out.println("<주민등록번호가 일치하는 사원 정보 조회>");
		
		System.out.print("주민등록번호를 입력해주세요:");
		String searchNo = sc.nextLine();
		
		Employee emp = service.selectEmpNo(searchNo);
		
		printOne(emp);
	}

	/** 10. 직급별 급여 평균 조회
	 * @throws Exception
	 */
	private void selectJobAvgSalary()throws Exception { // 10. 직급별 급여 평균 조회

		System.out.println("<직급별 급여 평균 조회>");
		
		List<Employee> job = service.selectJobAvgSalary();
		
		printJob(job);
		
	}
	
	
	/** 11.question Stmt 의문 진행
	 * @throws Exception
	 */
	private void questionStmt() throws Exception {
		
		System.out.println("<query로도 int 받을 수 있나?>");
		
		int result = service.questionStmt();
		
		if(result > 0 ) {
			System.out.println(result);
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		
		
	}

	
	/**보조 메서드
	/* 전달 받은 사원 List 모두 출력
	 * @param empList
	 */
	public void printAll(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
						emp.getPhone(), emp.getDeptTitle(), emp.getJobName(), emp.getSalary());
			}
		
		}
		return;
	}
	
	
	/** 사원 1명 정보 출력
	 * @param emp
	 */
	public void printOne(Employee emp) {
		
		if(emp == null) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
					emp.getPhone(), emp.getDeptTitle(), emp.getJobName(), emp.getSalary());
		}
		
	}
	
	
	/** 부서별 출력
	 * @param empList
	 */
	public void printDept(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 부서 정보가 없습니다.");
			
		} else {
			System.out.println("부서명 |   합계  " );
			System.out.println("------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %s \t| %5d원\n",
						 emp.getDeptTitle(),  emp.getSalary());
			}
		
		}
		return;
	}
	
	
	/**직급별 출력 
	 * @param empList
	 */
	public void printJob(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 직급 정보가 없습니다.");
			
		} else {
			System.out.println("직급명 |   급여 평균  " );
			System.out.println("------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %s\t| %5d원\n",
						 emp.getJobName(),  emp.getSalary());
			}
		
		}
		return;
	}
	
	
	/** 사번을 입력받아 반환하는 메서드
	 * @return
	 */
	public int inputEmpId() {		
		System.out.print("사번 입력 :");
		int searchEmpId = sc.nextInt();

		sc.nextLine();
		
		return searchEmpId;
	}
	
	

}
