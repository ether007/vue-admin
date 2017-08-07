package vip.ace.admin.configuration;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties
@ConditionalOnClass({AbstractSecurityWebApplicationInitializer.class,SessionCreationPolicy.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class SecurityFilterAutoConfiguration implements ApplicationContextAware {

    private static final String DEFAULT_FILTER_NAME = AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
    private ApplicationContext applicationContext = null;

    @Bean
    @ConditionalOnBean(name = DEFAULT_FILTER_NAME)
    public DelegatingFilterProxyRegistrationBean securityFilterChainRegistration(
            SecurityProperties securityProperties) {
        //FilterChainProxy
        Object dfn = applicationContext.getBean(DEFAULT_FILTER_NAME);
        System.out.println(dfn);
        DelegatingFilterProxyRegistrationBean registration = new DelegatingFilterProxyRegistrationBean(DEFAULT_FILTER_NAME);
        registration.setOrder(securityProperties.getFilterOrder());
        registration.setDispatcherTypes(getDispatcherTypes(securityProperties));
        registration.setUrlPatterns(Arrays.asList(new String[]{"/admin/*"}));
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    private EnumSet<DispatcherType> getDispatcherTypes(
            SecurityProperties securityProperties) {
        if (securityProperties.getFilterDispatcherTypes() == null) {
            return null;
        }
        Set<DispatcherType> dispatcherTypes = new HashSet<DispatcherType>();
        for (String dispatcherType : securityProperties.getFilterDispatcherTypes()) {
            dispatcherTypes.add(DispatcherType.valueOf(dispatcherType));
        }
        return EnumSet.copyOf(dispatcherTypes);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
