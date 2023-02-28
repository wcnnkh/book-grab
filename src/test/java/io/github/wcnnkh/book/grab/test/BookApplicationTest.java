package io.github.wcnnkh.book.grab.test;

import io.basc.framework.core.Members;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.Fields;
import io.github.wcnnkh.book.grab.BookApplication;
import io.github.wcnnkh.book.grab.service.impl.BiQuGeBookServiceImpl;

public class BookApplicationTest {
	public static void main(String[] args) {
		Members<Field> fields = Fields.getFields(BiQuGeBookServiceImpl.class).map((e) -> e).all();
		System.out.println(fields.stream().count());
		BookApplication.main(args);
	}
}