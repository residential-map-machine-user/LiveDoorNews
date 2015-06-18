package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.BaseController;
import Constants.AppConstants;

public class IndexController extends BaseController {
	
	/**
	 * インデックスページへ飛ぶ
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		try {
			request.getServletContext().getRequestDispatcher(AppConstants.FOWARD_PATH.CONST_INDEX_JSP).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
