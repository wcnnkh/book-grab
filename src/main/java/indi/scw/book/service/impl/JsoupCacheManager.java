package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.SerializableClientHttpInputMessageCacheConvert;
import scw.net.message.SerializableInputMessage;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int) (XTime.ONE_DAY * 30), new SerializableClientHttpInputMessageCacheConvert());
	}

	public Document getDocument(String url) {
		SerializableInputMessage message = getAndTouch(url);
		Document document = Jsoup.parse(message.toString());
		return document;
	}

	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
