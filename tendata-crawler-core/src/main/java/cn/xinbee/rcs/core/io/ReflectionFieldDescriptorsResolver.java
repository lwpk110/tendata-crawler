package cn.xinbee.rcs.core.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.MessageSourceAccessor;

public class ReflectionFieldDescriptorsResolver extends AbstractFieldDescriptorsResolver {
	
	public ReflectionFieldDescriptorsResolver(Properties configProps) {
		super(configProps);
	}
	
	@Override
	public List<FieldDescriptor> resolve(Object bean, Locale locale) {
		List<FieldDescriptor> descriptors = new ArrayList<FieldDescriptor>();
		Class<?> beanClass = bean.getClass();
		if(getConfigProps().containsKey(beanClass.getName())){
			String val = getConfigProps().getProperty(beanClass.getName());
			String[] fields = StringUtils.split(val, ',');
            MessageSourceAccessor messages = getMessages();
            for (String field : fields) {
                String caption = field;
                if(messages != null){
                    caption = messages.getMessage(beanClass.getSimpleName() + "." + field, field.toUpperCase(), locale);
                }
                descriptors.add(new FieldDescriptor(field, caption));
            }
		}
		return descriptors;
	}
}
