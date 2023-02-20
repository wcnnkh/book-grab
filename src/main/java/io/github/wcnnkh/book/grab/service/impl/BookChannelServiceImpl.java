package io.github.wcnnkh.book.grab.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.ioc.annotation.Value;
import io.basc.framework.factory.InstanceFactory;
import io.basc.framework.lang.NotFoundException;
import io.basc.framework.mapper.Copy;
import io.github.wcnnkh.book.grab.pojo.Book;
import io.github.wcnnkh.book.grab.pojo.BookChannel;
import io.github.wcnnkh.book.grab.pojo.BookChannelConfig;
import io.github.wcnnkh.book.grab.pojo.Chapter;
import io.github.wcnnkh.book.grab.pojo.PageList;
import io.github.wcnnkh.book.grab.service.BookChannelService;
import io.github.wcnnkh.book.grab.service.BookService;

@Service
public class BookChannelServiceImpl implements BookChannelService {
	private Map<Integer, BookService> bookServiceMap = new HashMap<Integer, BookService>();
	private Map<Integer, BookChannel> bookChannelMap = new LinkedHashMap<Integer, BookChannel>();
	private InstanceFactory instanceFactory;

	public BookChannelServiceImpl(InstanceFactory instanceFactory) {
		this.instanceFactory = instanceFactory;
	}

	@Value(value = "classpath:/book-channel.xml")
	public synchronized void setBookChannelConfigList(List<BookChannelConfig> bookChannelConfigs) {
		for (BookChannelConfig config : bookChannelConfigs) {
			if (config.isDisable()) {
				continue;
			}

			bookServiceMap.put(config.getId(), (BookService) instanceFactory.getInstance(config.getServiceName()));
			bookChannelMap.put(config.getId(), Copy.copy(config, BookChannel.class));
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
