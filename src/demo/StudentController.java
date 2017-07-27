package demo;

import org.json.JSONArray;

public class StudentController {
	
	public StudentController() {
		
	}
	
	public String Create() {
		return "SUCCESS";
	}
	
	public String Edit() {
		return "SUCCESS";
	}
	
	public String Delete() {
		return "SUCCESS";
	}
	
	public JSONArray Index() {
		return MySql.getClasses();
	}
}
