package indi.scw.book.service;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;

public interface BookService {
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	String getChapterContent(String chapterId) throws Exception;
}
