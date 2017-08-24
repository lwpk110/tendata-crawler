package cn.xinbee.rcs.service.model;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jeashi on 2016/11/28.
 */
public class ServiceReponse extends HashMap implements Serializable {

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_ERRCODE = "errcode";
    public static final String MSG_ERRMSG = "errmsg";
    public static final String MSG_TIMES = "$times$";

    public ServiceReponse() {
        put(MSG_SUCCESS, false);
        put(MSG_ERRCODE, null);
        put(MSG_ERRMSG, null);
    }

    public void setSuccess(boolean success) {
        put(MSG_SUCCESS, success);
    }

    public void setErrcode(String errcode) {
        put(MSG_ERRCODE, errcode);
    }

    public void setErrmsg(String errmsg) {
        put(MSG_ERRMSG, errmsg);
    }

    public static ServiceReponse initServiceReponse(boolean success, String errcode, String errmsg) {
        ServiceReponse serviceOutput = new ServiceReponse();
        serviceOutput.setSuccess(success);
        serviceOutput.setErrcode(errcode);
        serviceOutput.setErrmsg(errmsg);
        return serviceOutput;
    }

    public static ServiceReponse initServiceReponseSuccess() {
        ServiceReponse serviceOutput = new ServiceReponse();
        serviceOutput.setSuccess(true);
        serviceOutput.setErrcode("0");
        serviceOutput.setErrmsg(null);
        return serviceOutput;
    }

    public static ServiceReponse initServiceReponseSuccessWithResult(Object object) {
        ServiceReponse serviceReponse = ServiceReponse.initServiceReponseSuccess();
        serviceReponse.put("result", object);
        return serviceReponse;
    }

    public static ServiceReponse initServiceReponseSuccessWithResult(String key, Object object) {
        ServiceReponse serviceReponse = ServiceReponse.initServiceReponseSuccess();
        if (StringUtils.isEmpty(key)) {
            key = "result";
        }
        serviceReponse.put(key, object);
        return serviceReponse;
    }

    public static ServiceReponse initServiceReponseSuccessWithResultList(String key, List<?> list) {
        ServiceReponse serviceReponse = ServiceReponse.initServiceReponseSuccess();
        if (StringUtils.isEmpty(key)) {
            key = "list";
        }
        serviceReponse.put(key, list);
        return serviceReponse;
    }
}
