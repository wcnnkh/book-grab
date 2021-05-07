package pers.scw.book.pojo;

import scw.orm.annotation.PrimaryKey;

public class BookChannelConfig extends BookChannel {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String serviceName;
	private boolean disable;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
}
