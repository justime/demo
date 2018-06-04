package org.vean.platform.dao.dataobject;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class BaseDO {

	private Date gmtCreate;

	private Date gmtModified;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
