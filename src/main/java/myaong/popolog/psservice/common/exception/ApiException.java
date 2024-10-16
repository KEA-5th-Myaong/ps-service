package myaong.popolog.psservice.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

	private final ApiCode apiCode;

	public ApiException(ApiCode apiCode, String message) {
		super(message);
		this.apiCode = apiCode;
	}

	public ApiException(ApiCode apiCode) {
		super(apiCode.getMessage());
		this.apiCode = apiCode;
	}
}
