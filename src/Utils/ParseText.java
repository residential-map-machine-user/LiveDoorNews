package Utils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class ParseText {	
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
