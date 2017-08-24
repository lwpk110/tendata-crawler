package cn.xinbee.rcs.core;

public class BasicErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = 4121465718832647415L;
    
    private final String errorCode;
    private final Object[] args;

    public BasicErrorCodeException(String errorCode) {
        this(errorCode, errorCode);
    }
    
    public BasicErrorCodeException(String errorCode, Throwable cause){
        this(errorCode, errorCode, cause);
    }

    public BasicErrorCodeException(String errorCode, String message) {
        this(errorCode, null, message);
    }
    
    public BasicErrorCodeException(String errorCode, String message, Throwable cause){
        this(errorCode, null, message, cause);
    }
    
    public BasicErrorCodeException(String errorCode, Object[] args) {
        this(errorCode, errorCode);
    }
    
    public BasicErrorCodeException(String errorCode, Object[] args, Throwable cause) {
        this(errorCode, args, errorCode, cause);
    }

    public BasicErrorCodeException(String errorCode, Object[] args, String message) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }
    
    public BasicErrorCodeException(String errorCode, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }


    public Object[] getArgs() {
        return args;
    }
}
