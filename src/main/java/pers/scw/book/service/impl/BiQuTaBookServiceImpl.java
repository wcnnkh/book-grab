package pers.scw.book.service.impl;

import java.util.HashMap;
import java.util.Map;

import scw.net.uri.UriUtils;

public class BiQuTaBookServiceImpl extends AbstractBiQuBookService {

	public BiQuTaBookServiceImpl() {
		super("https://www.biquta.com/searchbook.php");
	}

	@Override
	protected String getSearchBookUrl(String name, int page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", name);
		return UriUtils.appendQueryParams(getBaseUri(), map, "UTF-8");
	}

}
