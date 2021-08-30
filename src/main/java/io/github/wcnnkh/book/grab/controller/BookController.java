package io.github.wcnnkh.book.grab.controller;

import java.util.List;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.mvc.annotation.Controller;
import io.github.wcnnkh.book.grab.pojo.Book;
import io.github.wcnnkh.book.grab.pojo.BookChannel;
import io.github.wcnnkh.book.grab.pojo.Chapter;
import io.github.wcnnkh.book.grab.pojo.PageList;
import io.github.wcnnkh.book.grab.service.BookChannelService;
import io.github.wcnnkh.book.grab.service.impl.JsoupCacheManager;

@Controller(value="book")
public class BookController {
	@Autowired
	private BookChannelService bookChannelService;
	@Autowired
	private JsoupCacheManager cacheManger;

	@Controller(value="channel_list")
	public List<BookChannel> channelList(){
		return bookChannelService.getBookChannelList();
	}
	
	@Controller(value="search")
	public PageList<Book> search(String name, int page, int channelId){
		return bookChannelService.searchBook(name, page, channelId);
	}
	
	@Controller(value="chapter_list")
	public PageList<Chapter> chapterList(String bookId, int page, int channelId){
		return bookChannelService.getChapterPageList(bookId, page, channelId);
	}
	
	@Controller(value="chapter_content")
	public String chapterContent(String chapterId, int channelId){
		return bookChannelService.getChapterContent(chapterId, channelId);
	}
	
	@Controller(value="delete_cache")
	public void deleteCache(String key){
		cacheManger.delete(key);
	}
}
