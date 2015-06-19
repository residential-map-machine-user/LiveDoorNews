package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import Beans.ItemBean;
import Constants.AppConstants;

public class IndexMainTest {
	public static void main(String[] args) {
		SearchIndex searchObj = new SearchIndex();
		MakeIndex indexObj = new MakeIndex();
		List<ItemBean> itemList = null;
		List<List<ItemBean>> searchResult = null;
		try {
			itemList = runIndex();
			indexObj.makeIndexFromBeanList(itemList);
			searchResult = runSearch(itemList, searchObj);
			for(List<ItemBean> itemObjList :searchResult){
				for(ItemBean itemObj: itemObjList){
					Util.l(itemObj.getUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<ItemBean> runIndex() {
		ParseText parseObj = new ParseText();
		HashMap<String, String> item = null;
		List<ItemBean> itemList = new ArrayList<>();
		item = parseObj.parseXml(AppConstants.XML_ARRAY[3]);
		for (int i = 0; i < item.size(); i++) {
			if (item.get("title" + i) != null
					&& !item.get("title" + i).equals("")
					&& !parseObj.parseHtml(item.get("guid" + i)).equals("")
					&& parseObj.parseHtml(item.get("guid" + i)) != null) {
				ItemBean itemObj = new ItemBean();
				itemObj.setUrl(item.get("guid" + i));
				itemObj.setTitle(item.get("title" + i));
				itemObj.setArticle(parseObj.parseHtml(item.get("guid" + i)));
				Util.l("______________________________________________");
				Util.l("タイトル:" + item.get("title" + i));
				Util.l("リンク:" + item.get("guid" + i));
				Util.l("記事内容:" + parseObj.parseHtml(item.get("guid" + i)));
				itemList.add(itemObj);
			}
		}
		return itemList;
	}

	public static List<List<ItemBean>> runSearch(List<ItemBean> itemList,
			SearchIndex searchObj) throws IOException, ParseException {
		List<List<ItemBean>> nestedResult = new ArrayList<>();
		List<ItemBean> resultUrlList = null;
		for (ItemBean item : itemList) {
			Util.l("検索に渡す中身:" +item.getArticle());
			resultUrlList = searchObj.searchIndex(item.getArticle());
			nestedResult.add(resultUrlList);
		}
		return nestedResult;
	}
}
