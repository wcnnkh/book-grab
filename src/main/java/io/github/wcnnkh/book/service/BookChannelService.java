package io.github.wcnnkh.book.service;

import java.util.List;

import io.github.wcnnkh.book.pojo.Book;
import io.github.wcnnkh.book.pojo.BookChannel;
import io.github.wcnnkh.book.pojo.Chapter;
import io.github.wcnnkh.book.pojo.PageList;

public interface BookChannelService {
	List<BookChannel> getBookChannelList();
	
	PageList<Book> searchBook(String name, int page, int channelId);

	PageList<Chapter> getChapterPageList(String bookId, int page, int channelId);

	String getChapterContent(String chapterId, int channelId);
}
