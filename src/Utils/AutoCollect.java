package Utils;

import java.util.ArrayList;
import java.util.List;

import Beans.ItemBean;
import Constants.AppConstants;
import DAOs.ArticleDAO;

public class AutoCollect {
	/**
	 *　記事を取得してくるメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		ParseText parseObj = new ParseText();
		ArticleDAO daoObj = new ArticleDAO();
		AutoSearch searchObj = new AutoSearch();
		//データベースの初期化　デバッグ用
		daoObj.deleteArticle();
		for (int i = 0; i < AppConstants.XML_ARRAY.length; i++) {
			List<ItemBean> itemList = null;
			//カテゴリ記事の記事一覧を取得
			itemList = parseObj.runScrape(AppConstants.XML_ARRAY[i], i);
			for (ItemBean item : itemList) {
				//データベースに記事を保存
				int successNum = daoObj.addArticle(item.getTitle(), item.getUrl(), item.getArticle(), item.getIdCategory());
				//記事に対して関連の記事を検索してデータベースに保存
//				searchObj.autoSearch(itemList);
			}
		}
	}
	
	/**
	 * 記事のパラメータがヌルでないかをチェック
	 * @param item
	 * @return
	 */
	public static boolean checkParameter(ItemBean item) {
		if (item.getTitle() != null && item.getUrl() != null && item.getArticle() != null) {
			return true;
		}
		return false;
	}
}
