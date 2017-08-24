package cn.xinbee.rcs.audit.spi.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.xinbee.rcs.audit.spi.AuditParam;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.jasig.inspektr.audit.spi.AuditResourceResolver;

public class AnnotatedParamsAsStringResourceResolver implements AuditResourceResolver{
	
	public final String[] resolveFrom(final JoinPoint joinPoint, final Object retVal) {
		return createResource(joinPoint.getArgs(), getArgumentAnnotations(joinPoint));
	}

	public final String[] resolveFrom(final JoinPoint joinPoint, final Exception e) {
		return createResource(joinPoint.getArgs(), getArgumentAnnotations(joinPoint));
	}
	
	private Annotation[][] getArgumentAnnotations(final JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getMethod().getName();
//		Object[] args = joinPoint.getArgs();
//		Class<?>[] parameterTypes = new Class<?>[args.length];
		Class<?>[] parameterTypes = signature.getParameterTypes();
//		for(int i=0; i< args.length; i++) {
//		    if(args[i] != null) {
//		        parameterTypes[i] = args[i].getClass();
//		    }
//		    else {
//		        parameterTypes[i] = null;
//		    }
//		}
		Class<?> targetClass = joinPoint.getTarget().getClass();
		Method method;
		try {
			method = targetClass.getMethod(methodName, parameterTypes);
			return method.getParameterAnnotations();
		} catch (NoSuchMethodException ex) {
			throw new IllegalStateException("Expected method not found: " + ex);
		}
	}
	
	private String[] createResource(final Object[] args, final Annotation[][] annotations){
		String[] resources = new String[0];
		final List<String> stringArgs = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			Object obj = args[i];
			if(annotations != null){
				Annotation[] argAnnotations = annotations[i];
				AuditParam auditParam = null;
				for (Annotation annotation : argAnnotations) {
					if(annotation instanceof AuditParam){
					    auditParam = (AuditParam)annotation;
						break;
					}
				}
				if(auditParam != null){
					String prefix = StringUtils.isBlank(auditParam.value()) ? StringUtils.EMPTY : auditParam.value() + ":";
					stringArgs.add(prefix + ArrayUtils.toString(obj));
				}
			}
		}
		if(stringArgs.size() > 0){
			resources = new String[]{ StringUtils.join(stringArgs, ",") };
		}
		return resources;
	}
}
