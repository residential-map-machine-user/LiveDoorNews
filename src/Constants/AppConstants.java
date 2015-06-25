package Constants;

import java.util.HashMap;

public class AppConstants {
	// データベース
	public static class DATABASE {
		public static final String DB_URL = "jdbc:mysql://localhost/db_rss";
		public static final String DB_USER = "rss";
		public static final String DB_PASS = "rss";
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
			"http://news.livedoor.com/topics/rss/eco.xml",
			"http://news.livedoor.com/topics/rss/spo.xml",
			"http://news.livedoor.com/topics/rss/ent.xml",
			"http://news.livedoor.com/rss/summary/52.xml",
			"http://news.livedoor.com/topics/rss/gourmet.xml",
			"http://news.livedoor.com/topics/rss/love.xml",
			"http://news.livedoor.com/topics/rss/trend.xml" };
	public static final String TOP = "top";
	public static final String NATIONAL = "national";
	public static final String INTERNATIONAL = "international";
	public static final String ECONOMIC = "economic";
	public static final String SPORTS = "sports";
	public static final String ENTERTAINMENT = "entertainment";
	public static final String MOVIE = "movie";
	public static final String FOOD = "food";
	public static final String WOMEN = "women";
	public static final String TREND = "trend";

	public static final HashMap<String, String> URL_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(TOP, "http://news.livedoor.com/topics/rss/top.xml");
			put(NATIONAL, "http://news.livedoor.com/topics/rss/dom.xml");
			put(INTERNATIONAL, "http://news.livedoor.com/topics/rss/int.xml");
			put(ECONOMIC, "http://news.livedoor.com/topics/rss/eco.xml");
			put(SPORTS, "http://news.livedoor.com/topics/rss/spo.xml");
			put(ENTERTAINMENT, "http://news.livedoor.com/topics/rss/ent.xml");
			put(MOVIE, "http://news.livedoor.com/topics/rss/spo.xml");
			put(FOOD, "http://news.livedoor.com/topics/rss/gourmet.xml");
			put(WOMEN, "http://news.livedoor.com/topics/rss/love.xml");
			put(TREND, "http://news.livedoor.com/topics/rss/trend.xml");
		}
	};
	public static final HashMap<String,Integer> CATEGORY_MAP =new HashMap<String,Integer>(){
		{
			put(TOP, top);
			put(NATIONAL, national);
			put(INTERNATIONAL, international);
			put(ECONOMIC, economic);
			put(SPORTS, sports);
			put(ENTERTAINMENT, entertainment);
			put(MOVIE, movie);
			put(FOOD, food);
			put(WOMEN, women);
			put(TREND, trend);
		}
	};
	public static final int top = 0;
	public static final int national = 1;
	public static final int international = 2;
	public static final int economic = 3;
	public static final int sports = 4;
	public static final int entertainment = 5;
	public static final int movie = 6;
	public static final int food = 7;
	public static final int women = 8;
	public static final int trend = 9;
	
	public static final String INDEX_PATH = "/Users/yukimatsuyama/eclipse_projects/LiveDoorNews/newsIndex";
//	public static final String INDEX_PATH = "/opt";
	public static final String SEARCH_TEXT = "ニュース";
}
