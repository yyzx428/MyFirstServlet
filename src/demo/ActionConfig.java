package demo;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	Map<String,Action> controllerMap=new HashMap<String,Action>();
	
	public ActionConfig() {
		controllerMap.put("/Student/Index", new Action("Student","Index","demo.StudentController"));
		controllerMap.put("/Student/Create", new Action("Student","Create","demo.StudentController"));
		controllerMap.put("/Student/Delte", new Action("Student","Delte","demo.StudentController"));
		controllerMap.put("/Student/Edit", new Action("Student","Edit","demo.StudentController"));
	}
	
	public Action findAction(String key) {
		return controllerMap.get(key);
	}
}
