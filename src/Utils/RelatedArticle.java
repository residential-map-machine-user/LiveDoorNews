package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import Beans.ItemBean;

public class RelatedArticle {
	public ArrayList<ItemBean> run(String url) {
		SearchIndex searchObj = new SearchIndex();
		MakeIndex indexObj = new MakeIndex();
		ArrayList<ItemBean> itemList = null;
		List<List<ItemBean>> searchResult = null;
		try {
			itemList = runIndex(url);
			indexObj.makeIndexFromBeanList(itemList);
			searchResult = runSearch(itemList, searchObj);
			for (int i = 0; i < searchResult.size(); i++) {
				itemList.get(i).setRelatedLink(searchResult.get(i));
				for (int j = 0; j < searchResult.get(i).size(); j++) {
					Util.l(searchResult.get(i).get(j).getUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public ArrayList<ItemBean> runIndex(String url) {
		ParseText parseObj = new ParseText();
		HashMap<String, String> item = null;
		ArrayList<ItemBean> itemList = new ArrayList<>();
		item = parseObj.parseXmlmod(url);
		for (String key : item.keySet()) {
			if (key != null && !key.equals("null") && item.get(key) != null
					&& !item.get(key).equals("null") && Util.check(key, "^「")
					&& Util.check(parseObj.parseHtml(item.get(key)), "^「")) {
				ItemBean itemObj = new ItemBean();
				itemObj.setUrl(item.get(key));
				itemObj.setTitle(key);
				itemObj.setArticle(parseObj.parseHtml(item.get(key)));
				Util.l("______________________________________________");
				Util.l("タイトル:" + key);
				Util.l("リンク:" + item.get(key));
				Util.l("記事内容:" + parseObj.parseHtml(item.get(key)));
				itemList.add(itemObj);
			}
		}
		return itemList;
	}

	public List<List<ItemBean>> runSearch(List<ItemBean> itemList,
			SearchIndex searchObj) throws IOException, ParseException {
		List<List<ItemBean>> nestedResult = new ArrayList<>();
		List<ItemBean> resultList = null;
		for (int i = 0; i < itemList.size(); i++) {
			Util.l("検索に渡す中身:" + Util.squeeze(itemList.get(i).getTitle()));
			resultList = searchObj.searchIndex(Util.squeeze(itemList.get(i)
					.getTitle()));
			nestedResult.add(resultList);
		}
		// for (ItemBean item : itemList) {
		// Util.l("検索に渡す中身:" + item.getArticle());
		// resultUrlList = searchObj.searchIndex(item.getArticle());
		// nestedResult.add(resultUrlList);
		// }
		return nestedResult;
	}
}
