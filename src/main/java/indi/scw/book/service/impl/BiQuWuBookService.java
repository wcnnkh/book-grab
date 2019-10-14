package indi.scw.book.service.impl;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import indi.scw.book.service.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import scw.core.utils.StringUtils;
import scw.net.http.HttpUtils;

public class BiQuWuBookService implements BookService {

	public PageList<Book> searchBook(String name, int page) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("https://m.biquwu.cc/search.php?keyword=");
		sb.append(HttpUtils.encode(name, "utf-8"));
		sb.append("&page=" + page);

		Document document = null;
		try {
			document = Jsoup.connect(sb.toString()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document == null) {
			return null;
		}

		Elements element = document.select("div.result-item");
		Iterator<Element> iterator = element.iterator();
		List<Book> list = new ArrayList<Book>();
		while (iterator.hasNext()) {
			Element ele = iterator.next();
			Book book = new Book();

			String bookName = ele.select("h3.result-item-title a span").first()
					.html();
			String titleImg = ele.select("div.result-game-item-pic img")
					.first().attr("src");
			String desc = ele.select("p.result-game-item-desc").first().html();
			String url = ele.select("a.result-game-item-title-link").first()
					.attr("href");
			String author = ele.select("p.result-game-item-info-tag").first()
					.select("span").last().text();

			book.setName(bookName);
			book.setBlurb(desc);
			book.setTitleImg(titleImg);
			book.setId(url);
			book.setAuthor(author);
			list.add(book);
		}

		String pageInfo = document.select("div.search-result-page-main")
				.first().text();
		String[] arr = pageInfo.split(" ");
		String maxPage = arr[arr.length - 1];
		maxPage = maxPage.substring(2, maxPage.length() - 1);
		return new PageList<Book>(list, page, Integer.valueOf(maxPage));
	}

	public PageList<Chapter> getChapterPageList(String bookId, int page)
			throws Exception {
		String bookUrl;
		if (page == 1) {
			bookUrl = bookId;
		} else {
			bookUrl = bookId + "index_" + page + ".html";
		}

		Document document = null;
		try {
			document = Jsoup.connect(bookUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document == null) {
			return null;
		}

		Elements elements = document.select("div.cover>ul.chapter").last()
				.select("li");
		if (elements == null) {
			return null;
		}

		Iterator<Element> iterator = elements.iterator();
		List<Chapter> bookChapters = new ArrayList<Chapter>();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			element = element.select("a").first();
			String name = element.text();
			String uri = element.absUrl("href");

			Chapter bookChapter = new Chapter();
			bookChapter.setName(name);
			bookChapter.setId(uri);
			bookChapters.add(bookChapter);
		}

		int maxPage = document.select("div.listpage select option").size();
		return new PageList<Chapter>(bookChapters, page, maxPage);
	}

	public String getChapterContent(String chapterId) throws Exception {
		Document document = null;
		try {
			document = Jsoup.connect(chapterId).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document == null) {
			return null;
		}

		ContentInfo contentInfo = new ContentInfo(document);
		StringBuilder sb = new StringBuilder();
		sb.append(contentInfo.getContent());
		while (contentInfo.getPage() < contentInfo.getMaxPage()) {
			contentInfo = new ContentInfo(document = Jsoup.connect(
					contentInfo.getNextUrl()).get());
			sb.append(contentInfo.getContent());
		}
		return sb.toString();
	}
}

class ContentInfo {
	private int page;
	private int maxPage;
	private String content;
	private String nextUrl;
	private Document document;

	public ContentInfo(Document document) {
		this.document = document;
		Elements elements = document.select("p.chapter-page-info");
		if (elements == null) {
			return;
		}

		Element element = elements.first();
		if (element == null) {
			return;
		}

		String content = element.text();
		if (StringUtils.isNull(content)) {
			return;
		}

		int begin = content.lastIndexOf("(");
		int end = content.lastIndexOf(")");
		if (begin < 0 || end < 0 || begin >= end) {
			return;
		}

		content = content.substring(begin + 1, end);

		String[] arr = content.split("/");
		if (arr.length != 2) {
			return;
		}

		this.page = Integer.parseInt(arr[0]);
		this.maxPage = Integer.parseInt(arr[1]);
		this.nextUrl = document.select("#pb_next").first().absUrl("href");
	}

	public int getPage() {
		return page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public String getContent() {
		if (content == null) {
			Element element = document.select("#nr1").first();
			if (element == null) {
				return null;
			}

			Element rem = element.select("p").last();
			if (rem != null) {
				rem.remove();
			}
			this.content = element.html();
		}
		return content;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
}
