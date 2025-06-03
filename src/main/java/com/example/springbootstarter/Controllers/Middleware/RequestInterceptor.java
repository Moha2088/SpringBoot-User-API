package com.example.springbootstarter.Controllers.Middleware;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final StopWatch watch = new StopWatch();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        watch.start();
        System.out.printf("Executing %s request%n", request.getMethod());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        watch.stop();
        System.out.printf("Executed %s request%nRequest took %s", request.getMethod(), watch.getTotalTimeSeconds());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}