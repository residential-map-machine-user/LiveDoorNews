package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Beans.ItemBean;
import Constants.AppConstants;

public class RSSReader {
	private String strURL;

	public RSSReader() {
	}

	public void setURL(String strURL) {
		this.strURL = strURL;
	}

	public List<ItemBean> writeFeed() {
		List<ItemBean> itemList = new ArrayList<>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			URL rssURL = new URL(strURL);
			Document doc = builder.parse(rssURL.openStream());
			NodeList items = doc.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				ItemBean itemObj = new ItemBean();
				Element item = (Element) items.item(i);
				itemObj.setTitle(tagRemoveFromText(getValue(item, "title")).trim());
				itemObj.setDescription(tagRemoveFromText(getValue(item, "description")).trim());
				itemObj.setLink(tagRemoveFromText(getValue(item, "link")).trim());
				System.out.println("title>>>>>>>" + tagRemoveFromText(getValue(item, "title")).trim());
				System.out.println("description>>>>>>>>>"+ tagRemoveFromText(getValue(item, "description")).trim());
				System.out.println("link>>>>>>>>>" + tagRemoveFromText(getValue(item, "link")).trim());
				System.out.println();
				itemList.add(itemObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
	
	public List<ItemBean> writeFeed(String key) {
		List<ItemBean> itemList = new ArrayList<>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			URL rssURL = new URL(AppConstants.URL_MAP.get(key));
			Document doc = builder.parse(rssURL.openStream());
			NodeList items = doc.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				ItemBean itemObj = new ItemBean();
				Element item = (Element) items.item(i);
				itemObj.setTitle(tagRemoveFromText(getValue(item, "title")).trim());
				itemObj.setDescription(tagRemoveFromText(getValue(item, "description")).trim());
				itemObj.setLink(tagRemoveFromText(getValue(item, "link")).trim());
				System.out.println("title>>>>>>>" + tagRemoveFromText(getValue(item, "title")).trim());
				System.out.println("description>>>>>>>>>"+ tagRemoveFromText(getValue(item, "description")).trim());
				System.out.println("link>>>>>>>>>" + tagRemoveFromText(getValue(item, "link")).trim());
				System.out.println();
				itemList.add(itemObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
	public String getValue(Element parent, String nodeName) {
		return parent.getElementsByTagName(nodeName).item(0).getFirstChild()
				.getNodeValue();
	}

	public void tagRemoveFromUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "文字コード"));
			Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
			String line;
			while ((line = reader.readLine()) != null) {
				line = pattern.matcher(line).replaceAll("");
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public  String tagRemoveFromText(String text) {
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		String fixedText = pattern.matcher(text).replaceAll("");
		return fixedText;
	}
}