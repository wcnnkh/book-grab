package indi.scw.book.service.impl;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import indi.scw.book.service.BookService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import scw.core.utils.FormatUtils;
import scw.net.http.HttpUtils;

public class QuanShuWangBookServiceImpl implements BookService {
	private static final String SEARCH_URL = "http://www.quanshu.net/modules/article/search.php?searchtype=articlename&searchbuttom_x=0&searchbuttom_y=0&page={}&searchkey={}";

	public PageList<Book> searchBook(String name, int page) throws Exception {
		String url = FormatUtils.formatPlaceholder(SEARCH_URL, "{}",
				HttpUtils.encode(name, "gbk"), page);
		List<Book> list = new ArrayList<Book>();
		Document document = Jsoup.connect(url).get();
		Elements eles = document.select("#navList ul li");
		Iterator<Element> iterator = eles.iterator();
		while (iterator.hasNext()) {
			Element ele = iterator.next();
			Book book = new Book();
			book.setName(ele.select("span a").first().text());
			book.setId(ele.select("a").first().attr("href"));
			book.setBlurb(ele.select("em").first().text());
			book.setTitleImg(ele.select("a img").first().attr("src"));
			list.add(book);
		}
		return new PageList<Book>(list, page, 1);
	}

	public PageList<Chapter> getChapterPageList(String bookId, int page)
			throws Exception {
		List<Chapter> list = new ArrayList<Chapter>();
		Document document = Jsoup.connect(bookId).get();
		String chapterUrl = document.select("a.reader").first().attr("href");

		document = Jsoup.connect(chapterUrl).get();
		Elements eles = document.select("div.dirconone li");
		Iterator<Element> iterator = eles.iterator();
		while (iterator.hasNext()) {
			Element ele = iterator.next();
			Chapter chapter = new Chapter();
			chapter.setId(chapterUrl + "/"
					+ ele.getElementsByTag("a").first().attr("href"));
			chapter.setName(ele.text());
			list.add(chapter);
		}
		return new PageList<Chapter>(list, page, 1);
	}

	public String getChapterContent(String chapterId) throws Exception {
		return Jsoup.connect(chapterId).get().getElementById("content").html();
	}
}
