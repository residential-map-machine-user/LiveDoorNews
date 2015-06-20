package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.BaseController;
import Beans.ItemBean;
import Constants.AppConstants;
import Utils.ParseText;
import Utils.RelatedArticle;

public class NewsController extends BaseController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			ParseText parseObj = new ParseText();
			HashMap<String,String>contentsMap = new HashMap<String, String>();
			contentsMap = parseObj.parseXmlmod(AppConstants.XML_ARRAY[0]);
			request.setAttribute(AppConstants.TOP, contentsMap);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_INDEX_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void foodAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.FOOD));
			request.setAttribute(AppConstants.FOOD, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void topAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.TOP));
			request.setAttribute(AppConstants.TOP, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void nationalAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.NATIONAL));
			request.setAttribute(AppConstants.NATIONAL, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void internationalAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.INTERNATIONAL));
			request.setAttribute(AppConstants.INTERNATIONAL, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void economicAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.ECONOMIC));
			request.setAttribute(AppConstants.ECONOMIC, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void sportsAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.SPORTS));
			request.setAttribute(AppConstants.SPORTS, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void movieAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.MOVIE));
			request.setAttribute(AppConstants.MOVIE, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void entertainmentAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.ENTERTAINMENT));
			request.setAttribute(AppConstants.ENTERTAINMENT, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void womenAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.WOMEN));
			request.setAttribute(AppConstants.WOMEN, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}

	public void trendAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			RelatedArticle relatedObj = new RelatedArticle();
			ArrayList<ItemBean> contentsList = relatedObj
					.run(AppConstants.URL_MAP.get(AppConstants.TREND));
			request.setAttribute(AppConstants.TREND, contentsList);
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}
}
