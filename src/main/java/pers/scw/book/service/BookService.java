package pers.scw.book.service;

import pers.scw.book.pojo.Book;
import pers.scw.book.pojo.Chapter;
import pers.scw.book.pojo.PageList;

public interface BookService {
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	String getChapterContent(String chapterId) throws Exception;
}
