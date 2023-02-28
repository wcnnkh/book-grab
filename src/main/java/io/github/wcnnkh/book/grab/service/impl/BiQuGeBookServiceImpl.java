package io.github.wcnnkh.book.grab.service.impl;

import java.util.HashMap;
import java.util.Map;

import io.basc.framework.codec.support.URLCodec;
import io.basc.framework.net.uri.UriUtils;

public class BiQuGeBookServiceImpl extends AbstractBiQuBookService {
	public BiQuGeBookServiceImpl() {
		super("https://www.xbiqugexx.com/search.php");
	}

	@Override
	protected String getSearchBookUrl(String name, int page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", name);
		return UriUtils.appendQueryParams(getBaseUri() + "?s=/web/index/search", map, URLCodec.UTF_8);
	}
}
