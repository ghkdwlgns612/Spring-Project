package com.github.prgrms.utils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

//		ApiUtils.ApiError apiError = new ApiUtils.ApiError("123",HttpStatus.CONTINUE);
//		ApiUtils.ApiResult apiResult = new ApiUtils.ApiResult<String>(true,HttpStatus.CONTINUE.getReasonPhrase(),apiError);
//		System.out.println("apiError = " + apiError);
//		System.out.println("apiResult = " + apiResult);
//      apiError = ApiUtils.ApiError[message=123,status=100]
//      apiResult = ApiUtils.ApiResult[success=true,response=Continue,error=ApiUtils.ApiError[message=123,status=100]]
public class ApiUtils {
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }
    public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }
    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message, status));
    }
    public static class ApiError {
        private final String message;
        private final int status;
        ApiError(Throwable throwable, HttpStatus status) {
            this(throwable.getMessage(), status);
        }
        public ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }
        public String getMessage() {
            return message;
        }
        public int getStatus() {
            return status;
        }
        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("message", message)
                    .append("status", status)
                    .toString();
        }//StringBuffer객체를 생성하여 정해진 Style에 맞게 값을 출력해준다. append에는 필드명과 값을 적어주면된다.
    }
    public static class ApiResult<T> {
        private final boolean success;
        private final T response; //Generic변수로 객체 생성 시 선언해주면된다. ApiResult<String>,ApiResult<Integer>
        private final ApiError error;
        public ApiResult(boolean success, T response, ApiError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }
        public boolean isSuccess() {
            return success;
        }
        public ApiError getError() {
            return error;
        }
        public T getResponse() {
            return response;
        }
        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("success", success)
                    .append("response", response)
                    .append("error", error)
                    .toString();
        }
    }
}