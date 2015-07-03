package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;

import BaseClasses.BaseController;
import Beans.ItemBean;
import Constants.AppConstants;
import DAOs.ArticleDAO;
import Utils.SearchIndex;

public class NewsController extends BaseController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String action = (String) request.getAttribute("ACTION");
		try {
			if (action == null || action.equals("") || action.equals("null")|| action.equals("execute")) {
				action = AppConstants.TOP;
				List<ItemBean> contentsList = new ArrayList<>();
				contentsList = searchFromAction(action);
				request.setAttribute(AppConstants.TOP, contentsList);
				request.setAttribute("ACTION", action);
				request.getServletContext()
						.getRequestDispatcher(
								AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
						.forward(request, response);
			} else {
				List<ItemBean> contentsList = new ArrayList<>();
				contentsList = searchFromAction(action);
				request.setAttribute(action, contentsList);
				request.getServletContext()
						.getRequestDispatcher(
								AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
						.forward(request, response);
			}
		} catch (ServletException | IOException | ParseException e) {
			e.printStackTrace();
			goError(request, response);
		}
	}

	public List<ItemBean> searchFromAction(String action) throws IOException,
			ParseException {
		SearchIndex searchObj = new SearchIndex();
		ArticleDAO daoObj = new ArticleDAO();
		List<ItemBean> contentsList = new ArrayList<>();
		contentsList = daoObj
				.selectArticleByCategoryId(AppConstants.CATEGORY_MAP
						.get(action));
		List<List<ItemBean>> relatedArticle = searchObj.runSearch(contentsList,
				searchObj);
		for (int i = 0; i < contentsList.size(); i++) {
			contentsList.get(i).setRelatedLink(relatedArticle.get(i));
		}
//		contentsList = daoObj.selectRelateArticleByContentsList(daoObj
//				.selectArticleByCategoryId(AppConstants.CATEGORY_MAP
//						.get(action)));
		return contentsList;
	}
}
