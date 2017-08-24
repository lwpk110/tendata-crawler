package cn.xinbee.rcs.core.io;

public interface BeanFieldResolver {
    
    Object resolveValue(Object bean, String field);
}
