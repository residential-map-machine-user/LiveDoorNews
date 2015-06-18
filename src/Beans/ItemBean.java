package Beans;

import java.util.List;

public class ItemBean {
	private String title;
	private String description;
	private String link;
	private List<String> relatedLinks;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<String> getRelatedLinks() {
		return relatedLinks;
	}
	public void setRelatedLinks(List<String> relatedLinkss) {
		this.relatedLinks = relatedLinks;
	}
}
