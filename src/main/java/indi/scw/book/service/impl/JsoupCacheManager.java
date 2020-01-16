package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.SerializableClientHttpInputMessageCacheConvert;
import scw.net.http.client.SerializableClientHttpInputMessage;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int) (XTime.ONE_DAY * 30), new SerializableClientHttpInputMessageCacheConvert());
	}

	public Document getDocument(String url) {
		SerializableClientHttpInputMessage message = getAndTouch(url);
		Document document = Jsoup.parse(message.toString());
		return document;
	}

	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
