package indi.scw.book.controller;

import indi.scw.book.pojo.Book;
import indi.scw.book.pojo.BookChannel;
import indi.scw.book.pojo.Chapter;
import indi.scw.book.pojo.PageList;
import indi.scw.book.service.BookChannelService;

import java.util.List;

import scw.beans.annotation.Autowired;
import scw.mvc.annotation.Controller;

@Controller(value="book")
public class BookController {
	@Autowired
	private BookChannelService bookChannelService;

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
}
