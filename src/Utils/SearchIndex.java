package Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.gosen.GosenAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import Beans.ItemBean;

public class SearchIndex {
	/**
	 * 検索の実行メソッド
	 * @param itemList
	 * @param searchObj
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<List<ItemBean>> runSearch(List<ItemBean> itemList,
			SearchIndex searchObj) throws IOException, ParseException {
		List<List<ItemBean>> nestedResult = new ArrayList<>();
		List<ItemBean> resultList = null;
		for (int i = 0; i < itemList.size(); i++) {
//			Util.l("検索に渡す中身:" + Util.squeeze(itemList.get(i).getTitle()));
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
	
	/**
	 * 
	 * @param inputQuery
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<ItemBean> searchIndex(String inputQuery) throws IOException,
			ParseException {
		// 検索文字列を解析するためのパーサーを生成する
		Analyzer analyzer = new GosenAnalyzer(Version.LUCENE_4_9);
		// 検索対象のフィールドを第二引数で指定している
		QueryParser parser = new QueryParser(Version.LUCENE_4_9, "article",
				analyzer);
		// 検索文字列を解析する
		Query query = parser.parse(inputQuery);
		// 検索で使用する IndexSearcher を生成する
		Directory indexDir = FSDirectory.open(new File("/opt/tomcat7/webapps/newsIndex"));
		IndexReader indexReader = DirectoryReader.open(indexDir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		// 検索を実行する（第二引数は、検索結果の最大数）
		TopDocs results = indexSearcher.search(query, 10);
		// 検索の結果、該当した Document を１つずつ取得する
		List<ItemBean> itemList = new ArrayList<>();
		for (ScoreDoc scoreDoc : results.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			// Document の path を取得して出力する
			ItemBean itemObj = new ItemBean();
			// Util.l("検索結果"+ doc.get("title")+ doc.get("url"));
			String resultTitle = doc.get("title");
			String resultUrl = doc.get("url");
			String resultArticle = doc.get("article");
			itemObj.setTitle(resultTitle);
			itemObj.setUrl(resultUrl);
			itemObj.setArticle(resultArticle);
//			if (resultTitle != null && resultUrl != null) {
//				itemObj.setTitle(resultTitle);
//				itemObj.setUrl(resultUrl);
//			} else {
//				Util.l("検索結果にnullが出た");
//			}
			itemList.add(itemObj);
		}
		// Util.l("検索完了");
		indexReader.close();
		return itemList;
	}
}