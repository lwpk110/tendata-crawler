package cn.xinbee.crawler.exception;


import cn.xinbee.crawler.service.model.ServiceReponse;

import java.io.Serializable;

/**
 * @author jeashi
 * @version 创建时间：2015-2-2 下午01:09:49
 */
public class MyException extends RuntimeException implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorKey;

    public MyException(String errorCode, String errorKey, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public MyException(String errorCode, String errorKey) {
        super(ServiceReponse.MSG_ERRCODE + ":" + errorCode);
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }


    public MyException(MyErrorEnum errorEnum) {
        this(errorEnum.getErrorCode(), errorEnum.getErrorMsg());
    }

    public MyErrorEnum getMyErrorEnum() {
        return MyErrorEnum.findByCode(errorCode);
    }

    public final String getErrorKey() {
        return errorKey;
    }

    public final void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }
}
