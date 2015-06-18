package BaseClasses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controllers.ErrorController;
import Controllers.IndexController;
public abstract class BaseController{
	public void execute(HttpServletRequest request, HttpServletResponse response){
	}	
	
	//インデックスページへ飛ぶ
	public void goIndex(HttpServletRequest request, HttpServletResponse response){
		BaseController controller = new IndexController();
		controller.execute(request, response);
	}
	
	//エラーページへ飛ぶ
	public void goError(HttpServletRequest request, HttpServletResponse response){
		BaseController controller = new ErrorController();
		controller.execute(request, response);
	}
}
