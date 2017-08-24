package cn.xinbee.rcs.audit.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.inspektr.audit.AuditTrailManagementAspect;
import org.jasig.inspektr.audit.AuditTrailManager;
import org.jasig.inspektr.audit.spi.AuditActionResolver;
import org.jasig.inspektr.audit.spi.AuditResourceResolver;
import org.jasig.inspektr.audit.spi.support.DefaultAuditActionResolver;
import org.jasig.inspektr.audit.spi.support.ObjectCreationAuditActionResolver;
import org.jasig.inspektr.audit.spi.support.ParametersAsStringResourceResolver;
import org.jasig.inspektr.audit.spi.support.ReturnValueAsStringResourceResolver;
import org.jasig.inspektr.audit.spi.support.SpringSecurityAuditablePrincipalResolver;
import org.jasig.inspektr.audit.support.Slf4jLoggingAuditTrailManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.xinbee.rcs.audit.spi.AuditActionResolvers;
import cn.xinbee.rcs.audit.spi.AuditResourceResolvers;
import cn.xinbee.rcs.audit.spi.support.AnnotatedParamsAsStringResourceResolver;

@Configuration
public class AuditTrailContextConfig {

    @Value("${audit.applicationCode:TENDATA_MDCS}")
    private String applicationCode;
    
    @Bean
    public AuditTrailManager auditTrailManager(){
        return new Slf4jLoggingAuditTrailManager();
    }
    
    @Bean
    public SpringSecurityAuditablePrincipalResolver springSecurityAuditablePrincipalResolver(){
        return new SpringSecurityAuditablePrincipalResolver();
    }
    
    @Bean
    public AuditTrailManagementAspect auditTrailManagementAspect(List<AuditTrailManager> auditTrailManagers){
        Map<String, AuditActionResolver> auditActionResolverMap = new HashMap<String, AuditActionResolver>(2);
        auditActionResolverMap.put(AuditActionResolvers.DEFAULT_ACTION_RESOLVER, new DefaultAuditActionResolver("_SUCCEEDED", "_FAILED"));
        auditActionResolverMap.put(AuditActionResolvers.OBJECT_CREATION_ACTION_RESOLVER, new ObjectCreationAuditActionResolver("_SUCCEEDED", "_FAILED"));
        
        Map<String, AuditResourceResolver> auditResourceResolverMap = new HashMap<String, AuditResourceResolver>(3);
        auditResourceResolverMap.put(AuditResourceResolvers.RETURN_VALUE_AS_STRING_RESOURCE_RESOLVER, new ReturnValueAsStringResourceResolver());
        auditResourceResolverMap.put(AuditResourceResolvers.PARAMS_AS_STRING_RESOURCE_RESOLVER, new ParametersAsStringResourceResolver());
        auditResourceResolverMap.put(AuditResourceResolvers.ANNOTATED_PARAMS_AS_STRING_RESOURCE_RESOLVER, new AnnotatedParamsAsStringResourceResolver());
        
        return new AuditTrailManagementAspect(
                applicationCode, 
                springSecurityAuditablePrincipalResolver(), 
                auditTrailManagers, 
                auditActionResolverMap, 
                auditResourceResolverMap);
    }
}
