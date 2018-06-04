package org.vean.platform.common.exception;

public class SystemException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SystemException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
