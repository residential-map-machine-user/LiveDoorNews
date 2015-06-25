package Utils;

import java.util.ArrayList;
import java.util.List;

import Beans.ItemBean;
import Constants.AppConstants;
import DAOs.ArticleDAO;

public class AutoCollect {
	public static void main(String[] args){
		ParseText parseObj = new ParseText();
		
		ArticleDAO daoObj = new ArticleDAO();
		daoObj.deleteArticle();
		for(int i = 0;i < AppConstants.XML_ARRAY.length; i++){
			List<ItemBean> itemList = null;
			//この時点で記事は全部取得できているのでここにidCategoryをつけて記事に保存する.
			itemList = parseObj.runScrape(AppConstants.XML_ARRAY[i],i);
			for(ItemBean item:itemList){
				if(checkParameter(item)){
					daoObj.addArticle(item.getTitle(), item.getUrl(), item.getArticle(), item.getIdCategory());
				}else{
					continue;
				}
			}
			daoObj.finishConnection();
		}
	}
	
	public static boolean checkParameter(ItemBean item){
		if(item.getTitle() != null&& item.getUrl()!= null&& item.getArticle() != null){
			return true;
		}
		return false;
	}
}
