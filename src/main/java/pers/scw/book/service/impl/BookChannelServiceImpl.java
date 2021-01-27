package pers.scw.book.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pers.scw.book.pojo.Book;
import pers.scw.book.pojo.BookChannel;
import pers.scw.book.pojo.BookChannelConfig;
import pers.scw.book.pojo.Chapter;
import pers.scw.book.pojo.PageList;
import pers.scw.book.service.BookChannelService;
import pers.scw.book.service.BookService;
import scw.beans.annotation.Service;
import scw.beans.annotation.Value;
import scw.beans.ioc.value.XmlFileToBeansValueProcesser;
import scw.instance.InstanceFactory;
import scw.lang.NotFoundException;
import scw.mapper.Copy;

@Service
public class BookChannelServiceImpl implements BookChannelService {
	private Map<Integer, BookService> bookServiceMap = new HashMap<Integer, BookService>();
	private Map<Integer, BookChannel> bookChannelMap = new LinkedHashMap<Integer, BookChannel>();
	private InstanceFactory instanceFactory;

	public BookChannelServiceImpl(InstanceFactory instanceFactory) {
		this.instanceFactory = instanceFactory;
	}

	@Value(value = "classpath:/book-channel.xml", processer = XmlFileToBeansValueProcesser.class)
	public synchronized void setBookChannelConfigList(List<BookChannelConfig> bookChannelConfigs) {
		for (BookChannelConfig config : bookChannelConfigs) {
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
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
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
