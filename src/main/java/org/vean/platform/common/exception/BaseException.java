package org.vean.platform.common.exception;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final ErrorEnum errorEnum;

    protected BaseException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
