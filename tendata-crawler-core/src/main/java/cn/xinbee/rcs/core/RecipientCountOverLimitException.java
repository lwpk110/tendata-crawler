package cn.xinbee.rcs.core;

public class RecipientCountOverLimitException extends BasicErrorCodeException {

    private static final long serialVersionUID = 8474265865586471986L;
    
    public static final String RECIPIENT_COUNT_OVER_LIMIT = "RECIPIENT_COUNT_OVER_LIMIT";
    
    public RecipientCountOverLimitException() {
        super(RECIPIENT_COUNT_OVER_LIMIT);
    }
    
    public RecipientCountOverLimitException(Object[] args){
        super(RECIPIENT_COUNT_OVER_LIMIT, args);
    }
}
