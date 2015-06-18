package Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.gosen.GosenAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import Beans.ItemBean;

public class MakeIndex {

	private Directory dir = null;
	private Analyzer analyzer = null;
	private IndexWriterConfig iws = null;
	private IndexWriter writer = null;
	public MakeIndex() throws IOException {
		Directory dir = FSDirectory.open(new File("index"));
		// テキストの解析方法（アナライザー）を定義
		Analyzer analyzer = new GosenAnalyzer(Version.LUCENE_4_9);
		// 解析方法の設定
		IndexWriterConfig iws = new IndexWriterConfig(Version.LUCENE_4_9,
				analyzer);

		// インデックスが既に存在する場合の動作を定義する（OpenMode.CREATE の場合、新規に作成して上書きする）
		iws.setOpenMode(OpenMode.CREATE);
		writer = new IndexWriter(dir, iws);
	}

	public void makeIndexFromText(String text, String path) throws IOException {
		Document doc = new Document();
		doc.add(new StringField("path", path, Store.YES));
		doc.add(new TextField("contents", text, Store.YES));
		// インデックスを書き出す
		writer.addDocument(doc);
	}

	public void makeIndexFromBeanList(List<ItemBean> itemList, String path)
			throws IOException {
		// make a new, empty document
		for (ItemBean item : itemList) {
			String contents = item.getDescription() + item.getTitle();
			makeIndexFromText(contents, path);
		}
	}

	public void finishAddDoc() throws IOException {
		writer.close();
	}
}
