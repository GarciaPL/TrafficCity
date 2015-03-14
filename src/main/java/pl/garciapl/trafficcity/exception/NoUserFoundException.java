package pl.garciapl.trafficcity.exception;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */
public class NoUserFoundException extends Exception {

    private String code;
    private String message;

    public NoUserFoundException(String message) {
        this.message = message;
    }

    public NoUserFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NoUserFoundException(String message, Throwable cause, String message1) {
        super(message, cause);
        message = message1;
    }

    public NoUserFoundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public NoUserFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        message = message1;
    }

    public NoUserFoundException(String message, String code, String message1) {
        super(message);
        this.code = code;
        message = message1;
    }

    public NoUserFoundException(String message, Throwable cause, String code, String message1) {
        super(message, cause);
        this.code = code;
        message = message1;
    }

    public NoUserFoundException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public NoUserFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        message = message1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
