package io.github.wcnnkh.book.grab.service.impl;

import org.jsoup.nodes.Document;

import io.basc.framework.context.ioc.annotation.Autowired;
import io.github.wcnnkh.book.grab.service.BookService;

public abstract class AbstractBookService implements BookService{
	private final String baseUri;
	@Autowired
	private JsoupCacheManager jsoupCacheManager;
	
	public AbstractBookService(String baseUri){
		this.baseUri = baseUri;
	}

	public final String getBaseUri() {
		return baseUri;
	}
	
	protected Document getDocument(String url) {
		Document document = jsoupCacheManager.getDocument(url);
		if(document != null){
			document.setBaseUri(baseUri);
		}
		return document;
	}
}
