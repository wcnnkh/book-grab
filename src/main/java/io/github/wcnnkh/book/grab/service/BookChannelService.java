package io.github.wcnnkh.book.grab.service;

import java.util.List;

import io.github.wcnnkh.book.grab.pojo.Book;
import io.github.wcnnkh.book.grab.pojo.BookChannel;
import io.github.wcnnkh.book.grab.pojo.Chapter;
import io.github.wcnnkh.book.grab.pojo.PageList;

public interface BookChannelService {
	List<BookChannel> getBookChannelList();
	
	PageList<Book> searchBook(String name, int page, int channelId);

	PageList<Chapter> getChapterPageList(String bookId, int page, int channelId);

	String getChapterContent(String chapterId, int channelId);
}
