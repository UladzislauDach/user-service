package by.dach.app;

import by.dach.app.filter.AuthorizationFilter;
import by.dach.app.service.UserSecurityService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationFilterConfiguration {
    private final UserSecurityService userSecurityService;

    public AuthorizationFilterConfiguration(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(userSecurityService));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

}
