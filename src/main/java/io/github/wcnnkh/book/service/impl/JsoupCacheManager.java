package io.github.wcnnkh.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.basc.framework.data.file.AutoRefreshDirectoryStorage;
import io.basc.framework.data.file.HttpGetBodyCacheConvert;
import io.basc.framework.util.XTime;

public final class JsoupCacheManager extends AutoRefreshDirectoryStorage {
	protected JsoupCacheManager() {
		super((int) (XTime.ONE_DAY/1000 * 30), new HttpGetBodyCacheConvert());
	}

	public Document getDocument(String url) {
		String body = getAndTouch(url);
		if(body == null){
			return null;
		}
		Document document = Jsoup.parse(body);
		return document;
	}

	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
