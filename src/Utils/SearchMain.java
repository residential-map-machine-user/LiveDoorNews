package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import Beans.ItemBean;
import Constants.AppConstants;
import DAOs.ArticleDAO;

public class SearchMain {
	public static void main(String[] args) {
		SearchIndex searchObj = new SearchIndex();
		ArticleDAO daoObj = new ArticleDAO();
		ArrayList<ItemBean> contentsList = daoObj.selectArticleByCategoryId(AppConstants.CATEGORY_MAP.get("top"));
		List<List<ItemBean>> relatedArticle;
		try {
			relatedArticle = searchObj.runSearch(contentsList, searchObj);
			for (int i = 0; i < contentsList.size(); i++) {
				contentsList.get(i).setRelatedLink(relatedArticle.get(i));
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
