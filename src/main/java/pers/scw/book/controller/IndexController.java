package pers.scw.book.controller;

import scw.io.ResourceUtils;
import scw.mvc.annotation.Controller;

/**
 * 因StaticResourceLoader存在BUG，先暂时这样
 * @author shuchaowen
 *
 */
@Controller
public class IndexController{
	@Controller
	public Object test(){
		return ResourceUtils.getResourceOperations().getContent("classpath:html/index.html", "UTF-8").getResource();
	}
	
	@Controller("index.html")
	public Object index(){
		return test();
	}
}
