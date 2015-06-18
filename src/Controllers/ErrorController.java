package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Util;
import BaseClasses.BaseController;
import Constants.AppConstants;

public class ErrorController extends BaseController {
	/**
	 *エラーページへ飛ぶ 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		Util.l("デフォルトアクション");
		try {
			request.getServletContext().getRequestDispatcher(AppConstants.FOWARD_PATH.CONST_ERROR_JSP).forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
