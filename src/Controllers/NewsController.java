package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;

import BaseClasses.BaseController;
import Beans.ItemBean;
import Constants.AppConstants;
import Utils.ParseText;
import Utils.SearchIndex;

public class NewsController extends BaseController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = (String)request.getAttribute("ACTION");
			if(action == null || action.equals("")||action.equals("null")){
				ParseText parseObj = new ParseText();
				HashMap<String,String>contentsMap = new HashMap<String, String>();
				contentsMap = parseObj.parseXmlmod(AppConstants.XML_ARRAY[0]);
				request.setAttribute(AppConstants.TOP, contentsMap);
			}else{
				List<ItemBean>contentsList = new ArrayList<>();
				contentsList = search(request,response);
				request.setAttribute(action,contentsList);
			}
			request.getServletContext()
					.getRequestDispatcher(
							AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP)
					.forward(request, response);
		} catch (ServletException | IOException | ParseException e) {
			e.printStackTrace();
			goError(request,response);
		}
	}
	
	public List<ItemBean> search(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		SearchIndex searchObj = new SearchIndex();
		ParseText parseObj = new ParseText();
		String action =(String)request.getAttribute("ACTION");
		List<ItemBean>contentsList = parseObj.runScrape(AppConstants.URL_MAP.get(action));
		List<List<ItemBean>>relatedArticle =searchObj.runSearch(contentsList, searchObj);
		for(int i=0; i<contentsList.size();i++){
			contentsList.get(i).setRelatedLink(relatedArticle.get(i));
		}
		return contentsList;
	}
}
