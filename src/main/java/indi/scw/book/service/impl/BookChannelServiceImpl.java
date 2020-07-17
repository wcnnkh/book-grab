package indi.scw.book.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.BookChannel;
import indi.scw.book.pojo.BookChannelConfig;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import indi.scw.book.service.BookChannelService;
import indi.scw.book.service.BookService;
import scw.beans.Init;
import scw.beans.annotation.Service;
import scw.core.instance.InstanceFactory;
import scw.lang.NotFoundException;
import scw.mapper.Copy;
import scw.util.ConfigUtils;

@Service
public class BookChannelServiceImpl implements BookChannelService, Init {
	private Map<Integer, BookService> bookServiceMap = new HashMap<Integer, BookService>();
	private Map<Integer, BookChannel> bookChannelMap = new LinkedHashMap<Integer, BookChannel>();
	private InstanceFactory instanceFactory;

	public BookChannelServiceImpl(InstanceFactory instanceFactory) {
		this.instanceFactory = instanceFactory;
	}

	public void init() {
		List<BookChannelConfig> list = ConfigUtils.xmlToList(BookChannelConfig.class, "classpath:/book-channel.xml");
		for (BookChannelConfig config : list) {
			if (config.isDisable()) {
				continue;
			}

			bookServiceMap.put(config.getId(), (BookService) instanceFactory.getInstance(config.getServiceName()));
			bookChannelMap.put(config.getId(), Copy.copy(BookChannel.class, config));
		}
	}

	public List<BookChannel> getBookChannelList() {
		return new ArrayList<BookChannel>(bookChannelMap.values());
	}

	private BookService getBookService(int channelId) {
		BookService bookService = bookServiceMap.get(channelId);
		if (bookService == null) {
			throw new NotFoundException("未知的渠道" + channelId);
		}
		return bookService;
	}

	public PageList<Book> searchBook(String name, int page, int channelId) {
		try {
			return getBookService(channelId).searchBook(name, page);
		} catch (Exception e) {
			if(e instanceof RuntimeException){
				throw (RuntimeException)e;
			}
			throw new RuntimeException(e);
		}
	}

	public PageList<Chapter> getChapterPageList(String bookId, int page, int channelId) {
		try {
			return getBookService(channelId).getChapterPageList(bookId, page);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public String getChapterContent(String chapterId, int channelId) {
		try {
			return getBookService(channelId).getChapterContent(chapterId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
