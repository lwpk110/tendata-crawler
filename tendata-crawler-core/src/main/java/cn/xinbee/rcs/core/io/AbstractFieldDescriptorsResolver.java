package cn.xinbee.rcs.core.io;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import cn.xinbee.rcs.core.DefaultMessageSource;

public abstract class AbstractFieldDescriptorsResolver implements FieldDescriptorsResolver, MessageSourceAware {
	
	private MessageSourceAccessor messages = DefaultMessageSource.getAccessor();
	
	private final Properties configProps;
	
	public Properties getConfigProps() {
		return configProps;
	}
	
	public AbstractFieldDescriptorsResolver(Properties configProps) {
		this.configProps = configProps;
	}
	
	protected MessageSourceAccessor getMessages(){
		return messages;
	}
	
	public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
