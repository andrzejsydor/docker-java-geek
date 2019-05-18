package com.sydor.factorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ExecutionTimeInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(ExecutionTimeInterceptor.class);
	private static final String START_TIME_ATTR_NAME = "startTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute(START_TIME_ATTR_NAME, startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute(START_TIME_ATTR_NAME);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;

		String n = request.getParameter("number");
		logger.info("[primes=" + n + "]:[executeTime=" + executionTime + "ms]");
	}
}
