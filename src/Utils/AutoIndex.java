package Utils;

import java.io.IOException;

import Constants.AppConstants;

public class AutoIndex {
	/**
	 * 取得してきた記事の内容を形態素解析してインデックスを作成
	 * @param args
	 */
	public static void main(String[] args) {
		ParseText parseObj = new ParseText();
		MakeIndex indexObj = new MakeIndex();
		try {
			for (int i = 0; i < AppConstants.XML_ARRAY.length; i++) {
				indexObj.makeIndexFromBeanList(parseObj.runScrape(AppConstants.XML_ARRAY[i],i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
