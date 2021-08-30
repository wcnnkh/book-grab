package io.github.wcnnkh.book.grab.service;

import io.github.wcnnkh.book.grab.pojo.Book;
import io.github.wcnnkh.book.grab.pojo.Chapter;
import io.github.wcnnkh.book.grab.pojo.PageList;

public interface BookService {
	PageList<Book> searchBook(String name, int page) throws Exception;
	
	PageList<Chapter> getChapterPageList(String bookId, int page) throws Exception;

	String getChapterContent(String chapterId) throws Exception;
}
