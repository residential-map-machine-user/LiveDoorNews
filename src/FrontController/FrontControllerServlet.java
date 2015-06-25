package FrontController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.BaseController;
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
		List<String> splitedURI = spliteURIToList(request);
		// リクエストされたコントロラーを取得
		@SuppressWarnings("rawtypes")
		Class controllerClass = getClass(splitedURI.get(0));
		if (controllerClass != null) {
			try {
				BaseController controller = null;
				// 取得したコントローラーをインスタンス化
				controller = (BaseController) controllerClass.newInstance();
				doAction(controllerClass, controller, request, response,
						splitedURI.get(1));
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
			String actionName) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		actionName = actionName.toLowerCase();
		request.setAttribute("ACTION", actionName);
		Util.l("アクションの名前>>>>>" + actionName);
		Method actionMethod = controllerClass.getMethod("execute",
				HttpServletRequest.class, HttpServletResponse.class);
		// メソッドの実行
		actionMethod.invoke(controller, request, response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Class getClass(String className) {
		className = convertToControllerName(className);
		try {
			// クラスを取得して返す
			Util.l("コントローラの名前>>>>>" + className);
			return Class.forName("Controllers." + className);
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
			controllerPath = "NewsController";
			return controllerPath;
		}
		controllerPath = controllerPath.substring(0, 1).toUpperCase()
				+ controllerPath.substring(1);
		controllerPath += "Controller";
		return controllerPath;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private List<String> spliteURIToList(HttpServletRequest request) {
		// リクエストURI
		String uriPath = request.getRequestURI();
		Util.l("URIパス>>>>>" + uriPath);
		// 現在のプロジェクトのルートパス
		String contextPath = request.getContextPath();
		Util.l("コンテキストパス>>>>>" + contextPath);
		// パスを分解
		String[] splitedPath = uriPath.replace(contextPath + "/front/", "")
				.split("/");
		List<String> splitedURI = new ArrayList<>();
		if(splitedPath ==null){
			splitedPath = new String[2];
		}
		// デバッグ
		for (int i = 0; i < splitedPath.length; i++) {
			splitedURI.add(splitedPath[i]);
			Util.l("スプライト後の結果>>>>" + "[" + i + "]" + splitedPath[i]);
		}
		if (splitedURI.size() < 2) {
			splitedURI.add("execute");
		}
		return splitedURI;
	}
}
