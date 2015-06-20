package BaseClasses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.ErrorController;
public abstract class BaseController{
	public void execute(HttpServletRequest request, HttpServletResponse response){
	}	
	
	
	//エラーページへ飛ぶ
	public void goError(HttpServletRequest request, HttpServletResponse response){
		BaseController controller = new ErrorController();
		controller.execute(request, response);
	}
}
