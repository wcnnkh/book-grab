package indi.scw.book.service;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.BookChannel;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;

import java.util.List;

public interface BookChannelService {
	List<BookChannel> getBookChannelList();
	
	PageList<Book> searchBook(String name, int page, int channelId);

	PageList<Chapter> getChapterPageList(String bookId, int page, int channelId);

	String getChapterContent(String chapterId, int channelId);
}
