package demo;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	Map<String,Action> controllerMap=new HashMap<String,Action>();
	
	public ActionConfig() {
		controllerMap.put("/Index", new Action("Student","Index","demo.StudentController"));
		controllerMap.put("/Create", new Action("Student","Create","demo.StudentController"));
		controllerMap.put("/Delte", new Action("Student","Delte","demo.StudentController"));
		controllerMap.put("/Edit", new Action("Student","Edit","demo.StudentController"));
	}
}
