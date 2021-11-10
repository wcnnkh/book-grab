package io.github.wcnnkh.book.grab.controller;

import java.util.List;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.github.wcnnkh.book.grab.pojo.Book;
import io.github.wcnnkh.book.grab.pojo.BookChannel;
import io.github.wcnnkh.book.grab.pojo.Chapter;
import io.github.wcnnkh.book.grab.pojo.PageList;
import io.github.wcnnkh.book.grab.service.BookChannelService;
import io.github.wcnnkh.book.grab.service.impl.JsoupCacheManager;

@RequestMapping(value="book")
public class BookController {
	@Autowired
	private BookChannelService bookChannelService;
	@Autowired
	private JsoupCacheManager cacheManger;

	@RequestMapping(value="channel_list")
	public List<BookChannel> channelList(){
		return bookChannelService.getBookChannelList();
	}
	
	@RequestMapping(value="search")
	public PageList<Book> search(String name, int page, int channelId){
		return bookChannelService.searchBook(name, page, channelId);
	}
	
	@RequestMapping(value="chapter_list")
	public PageList<Chapter> chapterList(String bookId, int page, int channelId){
		return bookChannelService.getChapterPageList(bookId, page, channelId);
	}
	
	@RequestMapping(value="chapter_content")
	public String chapterContent(String chapterId, int channelId){
		return bookChannelService.getChapterContent(chapterId, channelId);
	}
	
	@RequestMapping(value="delete_cache")
	public void deleteCache(String key){
		cacheManger.delete(key);
	}
}
