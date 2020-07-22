package pers.scw.book.service;

import java.util.List;

import pers.scw.book.pojo.Book;
import pers.scw.book.pojo.BookChannel;
import pers.scw.book.pojo.Chapter;
import pers.scw.book.pojo.PageList;

public interface BookChannelService {
	List<BookChannel> getBookChannelList();
	
	PageList<Book> searchBook(String name, int page, int channelId);

	PageList<Chapter> getChapterPageList(String bookId, int page, int channelId);

	String getChapterContent(String chapterId, int channelId);
}
