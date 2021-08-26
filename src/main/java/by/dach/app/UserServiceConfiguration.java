package by.dach.app;

import by.dach.app.filters.AuthorizationFilter;
import by.dach.app.service.UserSecurityService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {
    private final UserSecurityService userSecurityService;

    public UserServiceConfiguration(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(userSecurityService));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

}
