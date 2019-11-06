package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.HttpMessageCacheConvert;
import scw.net.HttpMessage;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int)(XTime.ONE_DAY * 30), new HttpMessageCacheConvert());
	}

	public Document getDocument(String url){
		HttpMessage httpMessage = getAndTouch(url);
		Document document = Jsoup.parse(httpMessage.toString());
		return document;
	}
	
	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
