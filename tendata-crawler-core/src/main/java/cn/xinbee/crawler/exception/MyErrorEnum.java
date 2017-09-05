package cn.xinbee.crawler.exception;


/**
 * @author jeashi
 * @version 创建时间：2015-2-2 下午01:22:47
 */
public enum MyErrorEnum {
    errorParm("-1", "参数错误。"),
    queryError("1","查询错误。"),


    customError("-999", "");


    private final String errorCode;
    private final String errorMsg;

    private MyErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException getMyException() {
        return getMyException(errorMsg);
    }

    public MyException getMyException(String msg) {
        return new MyException(errorCode, "", msg);//  返回输入的错误信息
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static MyErrorEnum findByCode(String code) {
        for (MyErrorEnum value : MyErrorEnum.values()) {
            if (value.errorCode.equalsIgnoreCase(code)) {
                return value;
            }
        }
        return null;
    }
}
