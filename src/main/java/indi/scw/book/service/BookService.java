package indi.scw.book.service;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;

public interface BookService {
	/**
	 * �����鼮
	 * @param name
	 * @param page
	 * @return
	 */
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	/**
	 * ��ȡ�½�
	 * @param bookId
	 * @return
	 */
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	/**
	 * ��ȡ�½�����
	 * @param chapterId
	 * @return
	 */
	String getChapterContent(String chapterId) throws Exception;
}
