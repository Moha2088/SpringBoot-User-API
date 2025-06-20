package com.example.springbootstarter.Controllers.Middleware;
import com.example.springbootstarter.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtValidatorInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    @Autowired
    public JwtValidatorInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.printf("Executing %s request%n", request.getMethod());
        String token = jwtService.extractToken(request);
        jwtService.validateToken(token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.printf("Executed %s request", request.getMethod());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}