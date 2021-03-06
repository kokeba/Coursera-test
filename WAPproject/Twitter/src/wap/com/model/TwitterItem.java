 package wap.com.model;

import java.io.Serializable;

public class TwitterItem implements Serializable{
	private static final long serialVersionUID = 2L;
	
	// Define image, content, header, .... for this 
	private String image;
	private String content;
	private String link;
	
	
	public TwitterItem(String image, String content, String link) {
		super();
		this.image = image;
		this.content = content;
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
	public TwitterItem() {
		
	}
	
	
}
