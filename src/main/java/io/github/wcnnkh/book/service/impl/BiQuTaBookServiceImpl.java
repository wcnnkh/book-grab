package io.github.wcnnkh.book.service.impl;

import java.util.HashMap;
import java.util.Map;

import io.basc.framework.codec.support.URLCodec;
import io.basc.framework.net.uri.UriUtils;

public class BiQuTaBookServiceImpl extends AbstractBiQuBookService {

	public BiQuTaBookServiceImpl() {
		super("https://www.biquta.com/searchbook.php");
	}

	@Override
	protected String getSearchBookUrl(String name, int page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", name);
		return UriUtils.appendQueryParams(getBaseUri(), map, URLCodec.UTF_8);
	}

}
