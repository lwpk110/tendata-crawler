package cn.xinbee.rcs.audit.spi;

import org.aspectj.lang.JoinPoint;
import org.jasig.inspektr.common.spi.PrincipalResolver;

public class AnonymousPrincipalResolver implements PrincipalResolver {

    @Override
    public String resolveFrom(JoinPoint joinPoint, Object o) {
        return PrincipalResolver.ANONYMOUS_USER;
    }

    @Override
    public String resolveFrom(JoinPoint joinPoint, Exception e) {
        return PrincipalResolver.ANONYMOUS_USER;
    }

    @Override
    public String resolve() {
        return PrincipalResolver.ANONYMOUS_USER;
    }
}
