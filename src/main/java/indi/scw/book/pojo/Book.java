package indi.scw.book.pojo;

import java.io.Serializable;

import scw.core.attribute.SimpleAttributes;

public class Book extends SimpleAttributes<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String titleImg;
	private String blurb;
	private String author;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
