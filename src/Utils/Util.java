package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Util {
	public static void l(String k) {
		System.out.println(k);
	}
	
	public static String modifyInputQuery(String inputQuery){
		inputQuery = inputQuery.replace("?","").replace("!", "");
		return inputQuery;
	}
	public static String squeeze(String s) {
		StringBuffer buf = new StringBuffer(s.length());

		// 空白以外の文字を取り出す
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\t')
				continue;
			buf.append(s.charAt(i));
		}
		return buf.toString();
	}

	public static boolean check(String target, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(target);

		if (m.find()) {
			// System.out.println("マッチします");
			return false;
		} else {
			// System.out.println("マッチしません");
			return true;
		}
	}

	public static String extractFromURLToId(String url) {
		String strArticleId = "";
		strArticleId = url.replaceAll("http://news.livedoor.com/article/detail/", "").replace("/", "").trim();
		return strArticleId;
	}
}
