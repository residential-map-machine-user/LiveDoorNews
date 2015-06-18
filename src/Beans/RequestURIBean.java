package Beans;

public class RequestURIBean {
	private String controllerPath = "";
	private String actionPath = "";
	private String requestPath= "";
	public String getControllerPath() {
		return controllerPath;
	}
	public void setControllerPath(String controllerPath) {
		this.controllerPath = controllerPath;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	public void setRequestPath(String requestPath){
		this.requestPath  = requestPath;
	}
	public String getRequestPath(){
		return requestPath;
	}
}
