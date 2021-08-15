package by.dach.app.filters;

import by.dach.app.service.UserSecurityService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final UserSecurityService userSecurityService;

    public AuthorizationFilter(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String loginHeader = request.getHeader("login");
        String passwordHeader = request.getHeader("password");
        if (loginHeader == null | passwordHeader == null) {
            response.sendError(403);
        } else if (userSecurityService.existUserByLoginAndPassword(loginHeader, passwordHeader)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(401);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod();
        return "/api/users".equals(path) & method.equals("POST");
    }

    @Bean
    FilterRegistrationBean<AuthorizationFilter> setPatternForFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(userSecurityService));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns();
        return registrationBean;
    }
}
