package indi.scw.book.service.impl;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import indi.scw.book.service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import scw.core.utils.CollectionUtils;
import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.HttpMessageCacheConvert;
import scw.net.HttpMessage;
import scw.net.http.HttpUtils;

public class BiQuGeBookServiceImpl extends AutoRefreshFileCache implements
		BookService {
	private String searchBook;
	private String queryKey;

	public BiQuGeBookServiceImpl() {
		this("https://www.biqugexx.com/index.php?s=/web/index/search", "name");
	}

	public BiQuGeBookServiceImpl(String searchBook, String queryKey) {
		super((int) (XTime.ONE_HOUR / 1000L), new HttpMessageCacheConvert(true));
		this.searchBook = searchBook;
		this.queryKey = queryKey;
	}

	public PageList<Book> searchBook(String name, int page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(queryKey, name);
		String host = HttpUtils.appendParameters(searchBook, map, "UTF-8");
		HttpMessage httpMessage = get(host);
		Document document = Jsoup.parse(httpMessage.toString());
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
			list.add(book);
		}
		return new PageList<Book>(list, page, 1);
	}

	public PageList<Chapter> getChapterPageList(String bookId, int page)
			throws Exception {
		HttpMessage httpMessage = get(bookId);
		Document document = Jsoup.parse(httpMessage.toString());
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
		HttpMessage httpMessage = get(chapterId);
		return Jsoup.parse(httpMessage.toString()).selectFirst("#content")
				.html();
	}

}
