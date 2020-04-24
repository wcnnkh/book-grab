package indi.scw.book.service.impl;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import scw.core.utils.XTime;
import scw.data.file.AutoRefreshFileCache;
import scw.data.file.SerializableHttpInputMessageCacheConvert;
import scw.net.message.SerializableInputMessage;

public final class JsoupCacheManager extends AutoRefreshFileCache {
	protected JsoupCacheManager() {
		super((int) (XTime.ONE_DAY * 30), new SerializableHttpInputMessageCacheConvert());
	}

	public Document getDocument(String url) {
		SerializableInputMessage message = getAndTouch(url);
		if(message == null){
			return null;
		}
		Document document = Jsoup.parse(message.getTextBody());
		return document;
	}

	@Override
	protected void expireExecute(File file, long currentTimeMillis) {
		file.delete();
	}
}
