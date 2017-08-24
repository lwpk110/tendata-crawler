package cn.xinbee.rcs.core.io;

import java.util.List;
import java.util.Locale;

public interface FieldDescriptorsResolver {
    
	List<FieldDescriptor> resolve(Object bean, Locale locale);
}
