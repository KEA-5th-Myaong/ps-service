package myaong.popolog.psservice.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private final boolean isSuccess;
	private final String code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final T data;

	private ApiResponse(boolean isSuccess, String code, String message, T data) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	// 성공 응답
	public static <T> ApiResponse<T> onSuccess(T result) {
		return new ApiResponse<>(true, ApiCode.OK.getCode(), ApiCode.OK.getMessage(), result);
	}

	// 실패 응답
	public static <T> ApiResponse<T> onFailure(ApiCode status) {
		return new ApiResponse<>(false, status.getCode(), status.getMessage(), null);
	}

	// 실패 응답인데 errors가 필요한 경우
	public static <T> ApiResponse<T> onFailure(ApiCode status, T errors) {
		return new ApiResponse<>(false, status.getCode(), status.getMessage(), errors);
	}

	// handleExceptionInternal override에서 사용
	public static <T> ApiResponse<T> onFailure(int code, String message) {
		return new ApiResponse<>(false, "COMMON_"+code+"0", message, null);
	}
}
