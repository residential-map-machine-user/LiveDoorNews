package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import Beans.ItemBean;
import Constants.AppConstants;

public class IndexMain {
	public static  void main(String[] args) {
		// MakeNewsIndex mni = new MakeNewsIndex();
		RSSReader rssObj = new RSSReader();
		List<String> pathList = new ArrayList<>();
		try {
			MakeIndex indexObj = new MakeIndex();
			for (int i = 0; i < AppConstants.XML_ARRAY.length; i++) {
				List<ItemBean> itemList = new ArrayList<>();
				rssObj.setURL(AppConstants.XML_ARRAY[i]);
				// itemList0:title 1:description 2:link
				itemList = rssObj.writeFeed();
				indexObj.makeIndexFromBeanList(itemList, AppConstants.XML_ARRAY[i]);
			}
			indexObj.finishAddDoc();
			SearchIndex searchObj = new SearchIndex();
			pathList = searchObj.searchIndex(AppConstants.SEARCH_TEXT);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<String>  run(String searchText) {
		// MakeNewsIndex mni = new MakeNewsIndex();
		RSSReader rssObj = new RSSReader();
		SearchIndex searchObj = new SearchIndex();
		List<String> pathList = new ArrayList<>();
		try {
			MakeIndex indexObj = new MakeIndex();
			for (int i = 0; i < AppConstants.XML_ARRAY.length; i++) {
				List<ItemBean> itemList = new ArrayList<>();
				rssObj.setURL(AppConstants.XML_ARRAY[i]);
				// itemList0:title 1:description 2:link
				itemList = rssObj.writeFeed();
				indexObj.makeIndexFromBeanList(itemList, AppConstants.XML_ARRAY[i]);
			}
			indexObj.finishAddDoc();
			pathList = searchObj.searchIndex(searchText);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathList;
	}
}
