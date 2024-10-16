package myaong.popolog.psservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiCode {

	OK(HttpStatus.OK, "COMMON_2000", "OK"),
	INVALID_DATA(HttpStatus.BAD_REQUEST, "COMMON_4000", "Request data missing or invalid"),
	READ_ONLY_ACCESS(HttpStatus.FORBIDDEN, "COMMON_4031", "You can only read"),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_4050", "Method not allowed"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5000", "Internal Server Error"),
	DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5001", "DB Error"),

	PS_NOT_FOUND(HttpStatus.NOT_FOUND, "PS_4040", "존재하지 않는 자기소개서입니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
