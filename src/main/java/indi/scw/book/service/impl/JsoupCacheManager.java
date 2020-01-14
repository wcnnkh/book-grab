package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.HttpMessageCacheConvert;
import scw.net.message.HttpInputMessage;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int)(XTime.ONE_DAY * 30), new HttpMessageCacheConvert());
	}

	public Document getDocument(String url){
		HttpInputMessage httpInputMessage = getAndTouch(url);
		Document document = Jsoup.parse(httpInputMessage.toString());
		return document;
	}
	
	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
