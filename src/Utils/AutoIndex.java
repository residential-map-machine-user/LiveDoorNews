package Utils;

import java.io.IOException;

import Constants.AppConstants;

public class AutoIndex {
	public static void main(String[] args) {
		ParseText parseObj = new ParseText();
		MakeIndex indexObj = new MakeIndex();
		try {
			for (int i = 0; i < AppConstants.XML_ARRAY.length; i++) {
				indexObj.makeIndexFromBeanList(parseObj.runScrape(AppConstants.XML_ARRAY[i],i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
