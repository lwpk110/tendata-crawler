package cn.xinbee.rcs.core;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

public class DefaultMessageSource extends ResourceBundleMessageSource {

    public DefaultMessageSource() {
        setBasename("cn.tendata.mdcs.messages");
    }
    
    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new DefaultMessageSource());
    }
}
