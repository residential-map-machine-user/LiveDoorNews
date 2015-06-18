package Constants;

import java.util.HashMap;

public class AppConstants {
	// データベース
	public static class DATABASE {
		public static final String DB_URL = "jdbc:mysql://localhost/dokopro_db";
		public static final String DB_USER = "dokopro";
		public static final String DB_PASS = "dokopro";
	}

	// フォワードパス
	public static class FOWARD_PATH {
		public static final String CONST_INDEX_JSP = "/WEB-INF/jsp/index.jsp";
		public static final String CONST_ERROR_JSP = "/WEB-INF/jsp/error.jsp";
		public static final String CONST_DETAIL_ARTICLE_JSP = "/WEB-INF/jsp/detailArticle.jsp";
		public static final String CONST_DETAIL_CATEGORY_JSP = "/WEB-INF/jsp/detailCategory.jsp";
	}

	// エラーメッセージ
	public static class ERROR_MESSAGE {
		public static final String CONST_PATH_ERROR = "そのURLは存在しません。";
	}

	public static String[] XML_ARRAY = {
			"http://news.livedoor.com/topics/rss/top.xml",
			"http://news.livedoor.com/topics/rss/dom.xml",
			"http://news.livedoor.com/topics/rss/int.xml",
			"http://news.livedoor.com/topics/rss/ent.xml",
			"http://news.livedoor.com/topics/rss/spo.xml",
			"http://news.livedoor.com/rss/summary/52.xml",
			"http://news.livedoor.com/topics/rss/gourmet.xml",
			"http://news.livedoor.com/topics/rss/love.xml",
			"http://news.livedoor.com/topics/rss/trend.xml" };

	public static final HashMap<String, String> URL_MAP = new HashMap<String, String>() {
		{
			put("top", "http://news.livedoor.com/topics/rss/top.xml");
			put("national", "http://news.livedoor.com/topics/rss/dom.xml");
			put("international", "http://news.livedoor.com/topics/rss/int.xml");
			put("economic", "http://news.livedoor.com/topics/rss/ent.xml");
			put("sports", "http://news.livedoor.com/topics/rss/spo.xml");
			put("entertainment","http://news.livedoor.com/topics/rss/ent.xml");
			put("movie","http://news.livedoor.com/topics/rss/spo.xml");
			put("food","http://news.livedoor.com/topics/rss/gourmet.xml");
			put("women","http://news.livedoor.com/topics/rss/love.xml");
			put("trend","http://news.livedoor.com/topics/rss/trend.xml");
		}
	};
	
	public static final String SEARCH_TEXT = "ニュース";
}
