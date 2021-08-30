package io.github.wcnnkh.book.service;

import io.github.wcnnkh.book.pojo.Book;
import io.github.wcnnkh.book.pojo.Chapter;
import io.github.wcnnkh.book.pojo.PageList;

public interface BookService {
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	String getChapterContent(String chapterId) throws Exception;
}
