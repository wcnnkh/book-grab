package indi.scw.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import scw.core.utils.CollectionUtils;

public abstract class AbstractBiQuBookService extends AbstractBookService {

	public AbstractBiQuBookService(String baseUri) {
		super(baseUri);
	}

	protected abstract String getSearchBookUrl(String name, int page) throws Exception;

	public PageList<Book> searchBook(String name, int page) throws Exception {
		Document document = getDocument(getSearchBookUrl(name, page));
		Elements elements = document.select("#main div ul li");
		List<Book> list = new ArrayList<Book>();
		for (Element element : elements) {
			Element s2 = element.selectFirst("span.s2>a");
			if (s2 == null) {
				continue;
			}

			Book book = new Book();
			book.setId(s2.absUrl("href"));
			book.setName(s2.text());
			book.setAuthor(element.selectFirst("span.s4>a").text());
			book.setLastChapter(element.selectFirst("span.s3>a").text());
			book.setLastUpdateTime(element.selectFirst("span.s6").text());
			list.add(book);
		}
		return new PageList<Book>(list, page, 1);
	}

	public PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception {
		Document document = getDocument(bookId);
		Element element = document.selectFirst("#list dl");
		if (element == null) {
			return new PageList<Chapter>(null, page, 1);
		}

		List<Node> nodes = element.childNodes();
		ListIterator<Node> iterator = nodes.listIterator(nodes.size());
		List<Chapter> list = new ArrayList<Chapter>();
		while (iterator.hasPrevious()) {
			Node node = iterator.previous();
			if (node.nodeName().equals("dt")) {
				break;
			}

			if (!(node instanceof Element)) {
				continue;
			}

			Element e = (Element) node;
			Element a = e.selectFirst("a");
			Chapter chapter = new Chapter();
			chapter.setName(a.text());
			chapter.setId(a.absUrl("href"));
			list.add(chapter);
		}
		return new PageList<Chapter>(CollectionUtils.reversal(list), page, 1);
	}

	public String getChapterContent(String chapterId) throws Exception {
		return getDocument(chapterId).selectFirst("#content").html();
	}
}
