package Utils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class ParseText {
	
	/**
	 * 入力されたXMLのURLにたいしてtitleとlinkのhashmapを返す
	 * @param url
	 * @return
	 */
	public HashMap<String, String> parseXml(String url) {
		HashMap<String, String> item = new HashMap<>();
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.visit(url);
			Elements titles = userAgent.doc.findEach("<item>").findEach(
					"<title>");
			Elements guids = userAgent.doc.findEach("<item>")
					.findEach("<guid>");
			int i = 0;
			for (Element title : titles) {
				item.put("title" + i, title.innerXML());
				System.out.println("title" + i + ":" +title.innerXML());
				i++;
			}
			int k = 0;
			for (Element guid : guids) {
				item.put("guid" + k, guid.innerXML());
				System.out.println("guid" + k + ":" +guid.innerXML());
				k++;
			}
		} catch (JauntException e) {
			System.err.println(e);
		}
		return item;
	}
	
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
				Util.l("title" + i + titleList.get(i).getText());
				Util.l("url" + i + guidList.get(i).getText());
				item.put(titleList.get(i).getText(), guidList.get(i).getText());
			}
		} catch (JauntException e) {
			System.err.println(e);
		}
		return item;
	}
	/**
	 * していされたURLの記事を文字返す
	 * @param url
	 * @return
	 */
//	public String parseHtml(String url) {
//		String text = "";
//		try {
//			UserAgent userAgent = new UserAgent();
//			userAgent.settings.charset = "EUC-JP";
//			userAgent
//					.visit(url);
//			Elements contents = userAgent.doc.findEach(
//					"<span itemprop=articleBody>").findEach("<p>");
//			List<Element> contentList = contents.toList();
//			for (int i = 2; i < contentList.size() - 2; i++) {
//				text += contentList.get(i).innerHTML().trim();
//			}
//		} catch (JauntException e) {
//			System.err.println(e);
//		}
//		return removeTag(text);
//	}
//	
	public String parseHtml(String url) {
		String text = "";
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.settings.charset = "EUC-JP";
			userAgent
					.visit(url);
			Elements contents = userAgent.doc.findEach("<span itemprop=articleBody>");
			for (Element content : contents) {
				text += content.innerHTML();
			}
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
