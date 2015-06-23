package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import Beans.ItemBean;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class ParseText {
	
	/**
	 * xml解析も含めたスクレイピングの実行メソッド
	 * @param url
	 * @return
	 */
	public ArrayList<ItemBean> runScrape(String url) {
		ParseText parseObj = new ParseText();
		HashMap<String, String> item = null;
		ArrayList<ItemBean> itemList = new ArrayList<>();
		item = parseObj.parseXmlmod(url);
		for (String title : item.keySet()) {
			String urlFromMap = item.get(title);
			if (title != null && !title.equals("null") && urlFromMap != null
					&& !urlFromMap.equals("null") && Util.check(title, "^「")) {
				String article = parseObj.parseHtml(urlFromMap);
				if (Util.check(article, "^「")) {
					ItemBean itemObj = new ItemBean();
					itemObj.setUrl(urlFromMap);
					itemObj.setTitle(title);
					itemObj.setArticle(parseObj.parseHtml(urlFromMap));
					 Util.l("______________________________________________");
					 Util.l("タイトル:" + title);
					 Util.l("リンク:" + urlFromMap);
					 Util.l("記事内容:" + parseObj.parseHtml(urlFromMap));
					itemList.add(itemObj);
				}
			}
		}
		return itemList;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public HashMap<String, String> parseXmlmod(String url) {
		HashMap<String, String> item = new HashMap<>();
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.visit(url);
			Elements titles = userAgent.doc.findEach("<item>").findEach(
					"<title>");
			Elements guids = userAgent.doc.findEach("<item>")
					.findEach("<guid>");
			List<Element>titleList = titles.toList();
			List<Element>guidList = guids.toList();
			for (int i = 0; i <titleList.size();i++) {
				item.put(titleList.get(i).getText(), guidList.get(i).getText());
			}
		} catch (JauntException e) {
			System.err.println(e);
		}
		return item;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public String parseHtml(String url) {
		String text = "";
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.settings.charset = "EUC-JP";
			userAgent
					.visit(url);
			Elements contents = userAgent.doc.findEach("<span itemprop=articleBody>");
			StringBuilder strBuilder = new StringBuilder();
			for (Element content : contents) {
				strBuilder.append(content.innerHTML());
			}
			text = strBuilder.toString();
		} catch (JauntException e) {
			System.err.println(e);
		}
		return Util.squeeze(removeTag(text));
	}
	
	public String removeTag(String text) {
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		text = pattern.matcher(text).replaceAll("");
		return text;
	}
	
	
}
