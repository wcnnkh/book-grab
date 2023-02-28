package io.github.wcnnkh.book.grab;

import io.basc.framework.boot.support.MainApplication;
import io.basc.framework.context.annotation.Bean;
import io.basc.framework.http.client.SimpleClientHttpRequestFactory;
import io.basc.framework.web.resource.StaticResourceRegistry;

public class BookApplication {
	public static void main(String[] args) {
		SimpleClientHttpRequestFactory.truseAll();
		MainApplication.run(BookApplication.class, args);
	}

	@Bean
	public StaticResourceRegistry getStaticResourceRegistry() {
		StaticResourceRegistry registry = new StaticResourceRegistry();
		registry.add("/**.html", "/html/");
		return registry;
	}
}