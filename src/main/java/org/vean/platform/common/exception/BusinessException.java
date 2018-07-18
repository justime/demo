package org.vean.platform.common.exception;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
