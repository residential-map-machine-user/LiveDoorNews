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

public class SearchIndex {
	public List<String> searchIndex(String inputQuery) throws IOException,
			ParseException {
		// 検索文字列を解析するためのパーサーを生成する
		Analyzer analyzer = new GosenAnalyzer(Version.LUCENE_4_9);
		// 検索対象のフィールドを第二引数で指定している
		QueryParser parser = new QueryParser(Version.LUCENE_4_9, "contents",
				analyzer);

		// 検索文字列を解析する
		Query query = parser.parse(inputQuery);

		// 検索で使用する IndexSearcher を生成する
		Directory indexDir = FSDirectory.open(new File("index"));
		IndexReader indexReader = DirectoryReader.open(indexDir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		// 検索を実行する（第二引数は、検索結果の最大数）
		TopDocs results = indexSearcher.search(query, 10);

		// 検索の結果、該当した Document を１つずつ取得する
		List<String> pathList = new ArrayList<>();
		for (ScoreDoc scoreDoc : results.scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			// Document の path を取得して出力する
			String path = doc.get("path");
			pathList.add(path);
			System.out.println("検索にマッチした"+path);
		}
		indexReader.close();
		return pathList;
	}
}