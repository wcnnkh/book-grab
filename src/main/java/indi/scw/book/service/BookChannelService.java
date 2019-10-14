package indi.scw.book.service;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.BookChannel;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;

import java.util.List;

public interface BookChannelService {
	List<BookChannel> getBookChannelList();
	
	/**
	 * 搜索书籍
	 * 
	 * @param name
	 * @param page
	 * @param channelId
	 * @return
	 */
	PageList<Book> searchBook(String name, int page, int channelId);

	/**
	 * 获取章节
	 * 
	 * @param bookId
	 * @param page
	 * @param channelId
	 * @return
	 */
	PageList<Chapter> getChapterPageList(String bookId, int page, int channelId);

	/**
	 * 获取章节内容
	 * 
	 * @param chapterId
	 * @param channelId
	 * @return
	 */
	String getChapterContent(String chapterId, int channelId);
}
