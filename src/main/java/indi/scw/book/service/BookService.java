package indi.scw.book.service;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;

public interface BookService {
	/**
	 * 搜索书籍
	 * @param name
	 * @param page
	 * @return
	 */
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	/**
	 * 获取章节
	 * @param bookId
	 * @return
	 */
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	/**
	 * 获取章节内容
	 * @param chapterId
	 * @return
	 */
	String getChapterContent(String chapterId) throws Exception;
}
