package pers.scw.book.service.impl;

import java.util.HashMap;
import java.util.Map;

import scw.net.uri.UriUtils;

public class BiQuGeBookServiceImpl extends AbstractBiQuBookService {
	public BiQuGeBookServiceImpl() {
		super("https://www.biqugexx.com/index.php");
	}

	@Override
	protected String getSearchBookUrl(String name, int page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return UriUtils.appendQueryParams(getBaseUri() + "?s=/web/index/search", map, "UTF-8");
	}
}
