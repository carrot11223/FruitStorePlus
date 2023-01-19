package com.carrot.thymeleaf;

import org.thymeleaf.context.AbstractContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.web.IWebExchange;

import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class WebContext extends AbstractContext implements IWebContext {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    public WebContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public WebContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, Locale locale) {
        super(locale);
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public WebContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, Locale locale, Map<String, Object> variables) {
        super(locale, variables);
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpSession getSession() {
        return this.request.getSession(false);
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public IWebExchange getExchange() {
        return null;
    }
}

