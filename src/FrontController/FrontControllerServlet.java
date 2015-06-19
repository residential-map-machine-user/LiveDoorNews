package FrontController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.BaseController;
import Beans.RequestURIBean;
import Utils.Util;

public class FrontControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setCharacterEncode(request);
		RequestURIBean uriObj = null;
		uriObj = spliteURIToArray(request);
		// リクエストされたコントロラーを取得
		@SuppressWarnings("rawtypes")
		Class controllerClass = getClass(uriObj);
		if (controllerClass != null) {
			try {
				BaseController controller = null;
				// 取得したコントローラーをインスタンス化
				controller = (BaseController) controllerClass.newInstance();
				doAction(controllerClass, controller, request, response, uriObj);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @throws IOException
	 */
	private void setCharacterEncode(HttpServletRequest request)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * 
	 * @param controllerClass
	 * @param controller
	 * @param request
	 * @param response
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void doAction(Class<?> controllerClass, BaseController controller,
			HttpServletRequest request, HttpServletResponse response,
			RequestURIBean uriObj) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 実行するメソッドの型指定
		request.setAttribute("ACTION", uriObj.getActionPath());
		Method actionMethod = controllerClass.getMethod(
				convertToActionName(uriObj.getActionPath()),
				HttpServletRequest.class, HttpServletResponse.class);
		// メソッドの実行
		actionMethod.invoke(controller, request, response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Class getClass(RequestURIBean uriObj) {
		String className = convertToControllerName(uriObj.getControllerPath());
		try {
			// クラスを取得して返す
			if (className.equals("NewsController")
					|| className.equals("IndexController")) {
				return Class.forName("Controllers." + className);
			}
		} catch (ClassNotFoundException notFoundException) {
			notFoundException.printStackTrace();
		}
		return null;
	}

	/**
	 * コントローラーネームのチェックメソッド
	 * 
	 * @param request
	 * @param uri
	 * @return
	 */
	private String convertToControllerName(String controllerPath) {
		if (controllerPath == null || controllerPath.equals("*")) {
			controllerPath = "IndexController";
			Util.l("コントローラーの名前>>>>>" + controllerPath);
			return controllerPath;
		}
		controllerPath += "Controller";
		Util.l("コントローラーの名前>>>>>" + controllerPath);
		return controllerPath;
	}

	/**
	 * アクションネームのチェックメソッド
	 * 
	 * @param request
	 * @param uri
	 * @return
	 */
	private String convertToActionName(String actionPath) {
		if (actionPath == null || actionPath.equals("")) {
			actionPath = "execute";
			Util.l("アクションの名前>>>>>" + actionPath);
			return actionPath;
		}
		actionPath += "Action";
		Util.l("アクションの名前>>>>>" + actionPath);
		return actionPath;
	}

	/**
	 * リクエストされたURIをコマンド名にして返すメソッド
	 * 
	 * @param request
	 * @return RequestURIBean パスの入ったインスタンス
	 */
	private RequestURIBean spliteURIToArray(HttpServletRequest request) {
		// リクエストURI
		String uriPath = request.getRequestURI();
		Util.l("URIパス>>>>>" + uriPath);
		// 現在のプロジェクトのルートパス
		String contextPath = request.getContextPath();
		Util.l("コンテキストパス>>>>>" + contextPath);
		// パスを分解
		String[] splitedPath = uriPath.replace(contextPath + "/front/", "")
				.split("/");
		// デバッグ
		for (int i = 0; i < splitedPath.length; i++) {
			Util.l("スプライト後の結果>>>>" + "[" + i + "]" + splitedPath[i]);
		}
		RequestURIBean uriObj = new RequestURIBean();
		uriObj.setControllerPath(splitedPath[0].substring(0, 1).toUpperCase()
				+ splitedPath[0].substring(1));
		if (splitedPath.length > 1) {
			uriObj.setActionPath(splitedPath[1].toLowerCase());
		}
		return uriObj;
	}
}
