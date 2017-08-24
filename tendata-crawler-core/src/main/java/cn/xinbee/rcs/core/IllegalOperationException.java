package cn.xinbee.rcs.core;

public class IllegalOperationException extends BasicErrorCodeException {

    private static final long serialVersionUID = 880086313189021473L;
    
    public static final String ILLEGAL_OPERATION = "ILLEGAL_OPERATION";
    
    public IllegalOperationException() {
        super(ILLEGAL_OPERATION);
    }
    
    public IllegalOperationException(Object[] args){
        super(ILLEGAL_OPERATION, args);
    }
}
