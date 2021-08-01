package by.dach.app;

import by.dach.app.exception.AuthorisationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {
    //    @Value("${security.firstKey}")
    //    private String key;

    final SecuritySettings securitySettings;

    public AuthorizationFilter(SecuritySettings securitySettings) {
        this.securitySettings = securitySettings;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.equals(securitySettings.getFirstKey())
        | authorizationHeader.equals(securitySettings.getSecondKey())) {
            filterChain.doFilter(request, response);
        } else {
           // throw new AuthorisationException("Bad authorization key");
           // response.setStatus(HttpStatus.FORBIDDEN.value());
            response.sendError(403);
        }

    }

    @Bean
    FilterRegistrationBean<AuthorizationFilter> setPatternForFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(securitySettings));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
