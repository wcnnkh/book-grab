package pers.scw.book.controller;

import java.util.List;

import pers.scw.book.pojo.Book;
import pers.scw.book.pojo.BookChannel;
import pers.scw.book.pojo.Chapter;
import pers.scw.book.pojo.PageList;
import pers.scw.book.service.BookChannelService;
import pers.scw.book.service.impl.JsoupCacheManager;
import scw.beans.annotation.Autowired;
import scw.mvc.annotation.Controller;

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
