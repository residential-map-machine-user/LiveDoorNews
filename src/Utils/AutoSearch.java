package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import Beans.ItemBean;
import Constants.AppConstants;
import DAOs.ArticleDAO;

public class AutoSearch {
	public void autoSearch(List<ItemBean> contentsList) {
		SearchIndex searchObj = new SearchIndex();
		ArticleDAO daoObj = new ArticleDAO();
		// カテゴリでまとまった記事に対して関連する記事を保持
		List<List<ItemBean>> relatedArticle;
		try {
			// カテゴリの記事のリストにたいして関連する記事を取得
			relatedArticle = searchObj.runSearch(contentsList, searchObj);
			for (int i = 0; i < contentsList.size(); i++) {
				// 記事一つ一つに対して関連する記事をリストで付け足す
				contentsList.get(i).setRelatedLink(relatedArticle.get(i));
				// ここでデータベースに保存する
				for (int k = 0; k < contentsList.get(i).getRelatedLink().size(); k++) {
					Util.l("カテゴリーの記事" + i + "関連記事" + k + ">>>>>>>>>>>>>" + contentsList.get(i).getRelatedLink().get(k).getUrl());
					Util.l(contentsList.get(i).getRelatedLink().get(k).getTitle());
					Util.l(contentsList.get(i).getRelatedLink().get(k).getArticle());
					int successNum = daoObj.addRelatedArticle(contentsList.get(i).getIdArticle(),
							contentsList.get(i).getRelatedLink().get(k).getTitle(),
							contentsList.get(i).getRelatedLink().get(k).getUrl(),
							contentsList.get(i).getRelatedLink().get(k).getArticle());
					Util.l("成功件数" + successNum);
				}

			}

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
