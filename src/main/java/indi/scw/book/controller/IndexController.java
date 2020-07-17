package indi.scw.book.controller;

import scw.beans.annotation.Autowired;
import scw.mvc.annotation.Controller;
import scw.mvc.page.Page;
import scw.mvc.page.PageFactory;

@Controller
public class IndexController {
	@Autowired
	private PageFactory pageFactory;
	
	@Controller
	public Page index(){
		return pageFactory.getPage("/html/index.html");
	}
}
