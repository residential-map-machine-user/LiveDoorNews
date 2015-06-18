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
import Utils.RSSReader;
import Utils.SearchIndex;

public class NewsController extends BaseController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		try {
			request.getServletContext().getRequestDispatcher(AppConstants.FOWARD_PATH.CONST_INDEX_JSP).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void foodAction(HttpServletRequest request, HttpServletResponse response){
		try {
			//[TOOD]ここでグルメという文字を取得する
			request.getRequestURI();
			//[TODO]文字を取得したらUtilのRssReaderを使ってデータを取得する.
			//[TODO]取得したらattributeに保存してあげるできればbeanのリストが望ましいと考えられる.
			RSSReader rssObj = new RSSReader();
			List<ItemBean> itemList = new ArrayList<>();
			itemList = rssObj.writeFeed("food");
//				try {
//					for(ItemBean item:itemList){
//					SearchIndex searchObj = new SearchIndex();
//					List<String> relatedLinks;
//					relatedLinks = searchObj.searchIndex("区");
//					item.setRelatedLinks(relatedLinks);
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			request.setAttribute("food",itemList);
			request.getServletContext().getRequestDispatcher(AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Action(HttpServletRequest request, HttpServletResponse response){
		try {
			//[TOOD]ここでグルメという文字を取得する
			request.getRequestURI();
			//[TODO]文字を取得したらUtilのRssReaderを使ってデータを取得する.
			//[TODO]取得したらattributeに保存してあげるできればbeanのリストが望ましいと考えられる.
			RSSReader rssObj = new RSSReader();
			List<ItemBean> itemList = new ArrayList<>();
			itemList = rssObj.writeFeed("food");
			request.setAttribute("food",itemList);
			request.getServletContext().getRequestDispatcher(AppConstants.FOWARD_PATH.CONST_DETAIL_CATEGORY_JSP).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
