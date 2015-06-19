package Beans;

import java.util.List;


public class ItemBean {
	private String url;
	private String article;
	private String title;
	private List<ItemBean> relatedLink;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public List<ItemBean> getRelatedLink() {
		return relatedLink;
	}
	public void setRelatedLink(List<ItemBean> relatedLink) {
		this.relatedLink = relatedLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
