package cn.xinbee.rcs.util;

public final class UserQuery {

    private final long targetUserId;
    private final long parentUserId;
    
    public UserQuery(long targetUserId, long parentUserId){
        this.targetUserId = targetUserId;
        this.parentUserId = parentUserId;
    }
    
    public long getTargetUserId() {
        return targetUserId;
    }

    public long getParentUserId() {
        return parentUserId;
    }
}
