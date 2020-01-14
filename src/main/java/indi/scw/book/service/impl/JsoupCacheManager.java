package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.ClientHttpResponseCacheConvert;
import scw.net.http.ClientHttpResponse;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int)(XTime.ONE_DAY * 30), new ClientHttpResponseCacheConvert());
	}

	public Document getDocument(String url){
		ClientHttpResponse clientHttpResponse = getAndTouch(url);
		Document document = Jsoup.parse(clientHttpResponse.toString());
		return document;
	}
	
	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
