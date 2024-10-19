package myaong.popolog.psservice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// 커스텀 예외 처리
	@ExceptionHandler
	public ResponseEntity<Object> HandleCustomException(ApiException ex) {
		return handleExceptionInternal(ex.getApiCode());
	}

	// @Valid 검증 예외 처리
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		List<Map<String, String>> errors = new ArrayList<>();
		for (FieldError fieldError : fieldErrors) {

			Map<String, String> error = new HashMap<>();
			error.put("field", fieldError.getField());
			error.put("message", fieldError.getDefaultMessage());

			errors.add(error);
		}

		return handleExceptionInternal(ApiCode.INVALID_DATA, errors);
	}

	// API 인자 불일치 예외 처리
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
		return handleExceptionInternal(ApiCode.METHOD_NOT_ALLOWED);
	}

	// DB 유효성 예외 처리
	@ExceptionHandler
	public ResponseEntity<Object> HandleDataIntegrityViolationException(DataIntegrityViolationException e) {
		return handleExceptionInternal(ApiCode.DB_ERROR);
	}

	// 그 외 모든 예외 처리
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception e) {
		log.error(e.getMessage(), e);

		return handleExceptionInternal(ApiCode.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	private ResponseEntity<Object> handleExceptionInternal (ApiCode apiCode) {
		return ResponseEntity.status(apiCode.getHttpStatus()).body(ApiResponse.onFailure(apiCode));
	}

	private ResponseEntity<Object> handleExceptionInternal (ApiCode apiCode, Object errors) {
		return ResponseEntity.status(apiCode.getHttpStatus()).body(ApiResponse.onFailure(apiCode, errors));
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		log.error(ex.getMessage(), ex);

		String message;
		if (ex instanceof ErrorResponse errorResponse)
			if (body == null)
				message = errorResponse.updateAndGetBody(this.getMessageSource(), LocaleContextHolder.getLocale()).getDetail();
			else
				message = ((ErrorResponse) body).getDetailMessageCode();
		else
			message = ex.getMessage();

		body = ApiResponse.onFailure(statusCode.value(), message);

		return ResponseEntity.status(statusCode.value()).body(body);
	}
}
