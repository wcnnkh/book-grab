package indi.scw.book;

import java.util.HashMap;
import java.util.Map;

import scw.beans.annotation.Service;
import scw.logger.Logger;
import scw.logger.LoggerFactory;
import scw.mvc.Channel;
import scw.mvc.ExceptionHandler;
import scw.mvc.ExceptionHandlerChain;

@Service
public class ExceptionHandlerImpl implements ExceptionHandler{
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerImpl.class);
	
	public Object handler(Channel channel, Throwable throwable,
			ExceptionHandlerChain chain) {
		logger.error(throwable, channel.toString());
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("code", 1);
		map.put("msg", throwable.getMessage());
		return map;
	}

}
