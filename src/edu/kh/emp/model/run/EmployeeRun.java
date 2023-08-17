package edu.kh.emp.model.run;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.view.EmployeeView;

public class EmployeeRun {
	
	public static void main(String[] args) {
		
//		EmployeeService service = new EmployeeService();
		
//		EmployeeView view = new EmployeeView();
		
		new EmployeeView().displayMenu();
		
	}
}
