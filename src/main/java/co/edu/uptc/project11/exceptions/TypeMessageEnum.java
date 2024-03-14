package co.edu.uptc.project11.exceptions;

import org.springframework.http.HttpStatus;

public enum TypeMessageEnum {
    NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Not found", 404),
    DONT_SAVE(HttpStatus.BAD_REQUEST.value(), "No saved", 410),
    FILE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "File not found", 420),
    INCOMPLETE_INFORMATION(HttpStatus.BAD_REQUEST.value(), "Incomplete Infomration", 430),
    SAVE(HttpStatus.OK.value(), "save", 210);

    public final int httpStatus;
    public final String message;
    public final int code;

    private TypeMessageEnum(int httpStatus, String message, int code){
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}
