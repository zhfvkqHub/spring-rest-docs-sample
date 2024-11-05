package com.raonsecure.sample.base.errorcode;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ResponseCode implements ErrorCodes {
    ERROR("9999", "error"),
    FEIGN_ERROR("9998", "feign.error"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "9997", "invalid.request"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "9996", "invalid.parameter"),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "9995", "authentication.failed"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "9994", "access.denied"),
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, "9993", "illegal.argument"),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "9992", "login.failed"),

    // 1000 ~ 1099 : Admin
    NOT_FOUND_ADMIN(HttpStatus.NOT_FOUND, "1000", "admin.not.found"),
    EXIST_LOGIN_ID(HttpStatus.BAD_REQUEST, "1001", "admin.already.exist"),
    // 1100 ~ 1199 : Admin Log
    ACTION_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "1101", "action.log.not.found"),
    EXCEL_DOWNLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "1102", "excel.download.failed"),

    // 1200 ~ 1299 : Notice
    NOT_FOUND_NOTICE(HttpStatus.NOT_FOUND, "1200", "notice.not.found"),
    // 1300 ~ 1399 : User
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "1300", "user.not.found"),
    EXIST_USER(HttpStatus.BAD_REQUEST, "1301", "user.already.exist"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "1302", "invalid.password"),
    // 1400 ~ 1499 : Api
    NOT_FOUND_GAUDI_API(HttpStatus.NOT_FOUND, "1400", "gaudi.api.not.found"),
    NOT_FOUND_EXTERNAL_API(HttpStatus.NOT_FOUND, "1401", "external.api.not.found"),
    EXIST_EXTERNAL_API(HttpStatus.BAD_REQUEST, "1402", "external.api.already.exist"),

    ;

    public final HttpStatus httpStatus;
    public final String code;
    public final String message;

    private static final Map<String, ResponseCode> BY_MESSAGE =
            Stream.of(values())
                    .collect(Collectors.toMap(ResponseCode::getMessage, Function.identity()));

    ResponseCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    ResponseCode(String code, String message) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public static ResponseCode findByMessage(String message) {
        return BY_MESSAGE.get(message);
    }
}
