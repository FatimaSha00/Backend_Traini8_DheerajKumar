package com.traini8.registry.logging;


import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@Slf4j
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

        logRequest(wrappedRequest);

        chain.doFilter(wrappedRequest, wrappedResponse);

        logResponse(wrappedResponse);

        // Copy the response back to the original response
        wrappedResponse.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) throws IOException {
        StringBuilder requestLog = new StringBuilder();
        requestLog.append("\n--------------------- REQUEST ---------------------\n");
        requestLog.append("🟢 Incoming Request: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append("\n");

        // Log Request Headers
        requestLog.append("📥 Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            requestLog.append("   ➝ ").append(headerName).append(" = ").append(request.getHeader(headerName)).append("\n");
        }

        // Log Request Body
        String requestBody = new String(request.getContentAsByteArray());
        if (!requestBody.isEmpty()) {
            requestLog.append("📌 Body: ").append(requestBody).append("\n");
        } else {
            requestLog.append("📌 Body: [EMPTY]\n");
        }

        log.info(requestLog.toString());
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        StringBuilder responseLog = new StringBuilder();
        responseLog.append("\n--------------------- RESPONSE ---------------------\n");
        responseLog.append("🔵 Outgoing Response: Status = ").append(response.getStatus()).append("\n");

        // Log Response Headers
        responseLog.append("📤 Headers:\n");
        response.getHeaderNames().forEach(headerName ->
                responseLog.append("   ➝ ").append(headerName).append(" = ").append(response.getHeader(headerName)).append("\n")
        );

        // Log Response Body
        String responseBody = new String(response.getContentAsByteArray());
        if (!responseBody.isEmpty()) {
            responseLog.append("📌 Body: ").append(responseBody).append("\n");
        } else {
            responseLog.append("📌 Body: [EMPTY]\n");
        }

        log.info(responseLog.toString());
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
