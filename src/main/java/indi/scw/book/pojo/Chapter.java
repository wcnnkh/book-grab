package indi.scw.book.pojo;

import java.io.Serializable;

import scw.core.attribute.SimpleAttributes;

public class Chapter extends SimpleAttributes<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
