package io.github.wcnnkh.book.pojo;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list;
	private int page;
	private int maxPage;
	
	public PageList(){};

	public PageList(List<T> list, int page, int maxPage) {
		this.list = list;
		this.page = page;
		this.maxPage = maxPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
