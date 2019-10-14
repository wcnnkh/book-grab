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

public class ShuQiBookServiceImpl implements BookService{
	public PageList<Book> searchBook(String name, int page)
			throws Exception {
		String url = "http://so.shuqi6.com/cse/search?click=1&entry=1&s=1243419093590560530&q="+name+"&p="+page;
		List<Book> list = new ArrayList<Book>();
			Document document = Jsoup.connect(url).get();
			Elements eles = document.select("div.result-item");
			Iterator<Element> iterator = eles.iterator();
			while(iterator.hasNext()){
				Element ele = iterator.next();
				try {
					Book book = new Book();
					book.setName(ele.select("h3.result-item-title a").first().html());
					book.setTitleImg(ele.select("div.result-game-item-pic img").first().attr("src"));
					book.setBlurb(ele.select("p.result-game-item-desc").first().html());
					book.setId(ele.select("a.result-game-item-title-link").first().attr("href"));
					list.add(book);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return new PageList<Book>(list, page, 1);
	}

	public PageList<Chapter> getChapterPageList(
			String bookId, int page) throws Exception {
		List<Chapter> list = new ArrayList<Chapter>();
			Document document = Jsoup.connect(bookId).get();
			Elements eles = document.select("#xslist ul li");
			Iterator<Element> iterator = eles.iterator();
			while(iterator.hasNext()){
				Element ele = iterator.next();
				try {
					Chapter chapter = new Chapter();
					chapter.setName(ele.text());
					chapter.setId(bookId + ele.getElementsByTag("a").first().attr("href"));
					list.add(chapter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return new PageList<Chapter>(list, page, 1);
	}

	public String getChapterContent(String chapterId) throws Exception {
			Element document = Jsoup.connect(chapterId).get().select("#booktext").first();
			return  document.text().replaceAll(" ", "<br/>");
	}
}
