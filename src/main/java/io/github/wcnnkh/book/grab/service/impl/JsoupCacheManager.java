package io.github.wcnnkh.book.grab.service.impl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.basc.framework.data.file.AutoRefreshDirectoryStorage;
import io.basc.framework.data.file.HttpGetBodyCacheConvert;

public final class JsoupCacheManager extends AutoRefreshDirectoryStorage {
	protected JsoupCacheManager() {
		super(30, TimeUnit.DAYS, new HttpGetBodyCacheConvert());
	}

	public Document getDocument(String url) {
		String body = getAndTouch(String.class, url);
		if (body == null) {
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
