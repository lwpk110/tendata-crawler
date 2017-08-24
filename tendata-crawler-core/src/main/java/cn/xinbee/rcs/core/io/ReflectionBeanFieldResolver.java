package cn.xinbee.rcs.core.io;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import cn.xinbee.rcs.core.DefaultMessageSource;

public class ReflectionBeanFieldResolver implements BeanFieldResolver, MessageSourceAware {
    
    private MessageSourceAccessor messages = DefaultMessageSource.getAccessor();
    
    @Override
    public Object resolveValue(Object bean, String field) {
        Object value = null;
        try {
            value = PropertyUtils.getSimpleProperty(bean, field);
            if(value instanceof Enum){
                value = messages.getMessage(bean.getClass().getSimpleName() + "." + field + "." + value.toString().toUpperCase(),
                        LocaleContextHolder.getLocale());
            }
        } catch (Exception e) {
            throw new FieldResolverException(field, e);
        }
        return value;
    }
    
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
