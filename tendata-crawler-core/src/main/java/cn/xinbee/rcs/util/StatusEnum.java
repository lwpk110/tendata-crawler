package cn.xinbee.rcs.util;

/**
 * Created by jeashi on 2016/9/21.
 */
public enum StatusEnum {
    WAITING_APPROVE(1,"审核中"),
    PASS_APPROVE_SYS(4,"已通过"),  //系统审核通过
    PASS_APPROVE(5,"已通过"),  //人工审核工作
    REFUSE_APPROVE(6,"未通过"),

    ;
    int status;
    String name;
    private StatusEnum(int status,String name){
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public static StatusEnum getStatusNameByStatus(int status){
        for(StatusEnum statusEnum: StatusEnum.values()){
            if(statusEnum.getStatus()==status){
                return statusEnum;
            }
        }
        return null;
    }
}
