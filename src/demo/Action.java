package demo;

public class Action {
	
	public Action(String controllerName, String name, String className) {
		this.controllerName = controllerName;
		this.name = name;
		this.className = className;
	}
	
	private String controllerName;
	private String name;
	private String className;
	
	public String getControllerName() {
		return controllerName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getClassName() {
		return className;
	}
}
