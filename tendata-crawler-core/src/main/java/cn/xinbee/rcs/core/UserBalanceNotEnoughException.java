package cn.xinbee.rcs.core;

public class UserBalanceNotEnoughException extends BasicErrorCodeException {

    private static final long serialVersionUID = 8918015960590498719L;
    
    public static final String NOT_ENOUGH_BALANCE = "NOT_ENOUGH_BALANCE";
    
    public UserBalanceNotEnoughException(){
        super(NOT_ENOUGH_BALANCE);
    }
    
    public UserBalanceNotEnoughException(Object[] args){
        super(NOT_ENOUGH_BALANCE, args);
    }
}
