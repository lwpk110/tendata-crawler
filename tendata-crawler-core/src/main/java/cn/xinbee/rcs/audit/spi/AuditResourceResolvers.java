package cn.xinbee.rcs.audit.spi;

public abstract class AuditResourceResolvers {
    
    public static final String RETURN_VALUE_AS_STRING_RESOURCE_RESOLVER = "RETURN_VALUE_AS_STRING_RESOURCE_RESOLVER";
    public static final String PARAMS_AS_STRING_RESOURCE_RESOLVER = "PARAMS_AS_STRING_RESOURCE_RESOLVER";
    public static final String ANNOTATED_PARAMS_AS_STRING_RESOURCE_RESOLVER = "ANNOTATED_PARAMS_AS_STRING_RESOURCE_RESOLVER";
    
    private AuditResourceResolvers(){
        
    }
}
