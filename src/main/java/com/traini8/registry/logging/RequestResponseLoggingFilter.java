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

/**
 * This filter logs the details of incoming HTTP requests and outgoing responses.
 * It captures request and response headers along with their bodies for debugging and monitoring purposes.
 */
@Component
@Slf4j
public class RequestResponseLoggingFilter implements Filter {

    /**
     * Initializes the filter.
     *
     * @param filterConfig Filter configuration object.
     * @throws ServletException if an exception occurs during initialization.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    /**
     * Intercepts and logs HTTP requests and responses.
     *
     * @param request  The incoming servlet request.
     * @param response The outgoing servlet response.
     * @param chain    The filter chain to continue processing.
     * @throws IOException      If an input or output error occurs.
     * @throws ServletException If the request cannot be handled.
     */
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

    /**
     * Logs details of the incoming request.
     *
     * @param request The request wrapper containing cached request data.
     * @throws IOException If an error occurs while reading the request body.
     */
    private void logRequest(ContentCachingRequestWrapper request) throws IOException {
        StringBuilder requestLog = new StringBuilder();
        requestLog.append("\n--------------------- REQUEST ---------------------\n");
        requestLog.append("\uD83D\uDFE2 Incoming Request: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append("\n");

        // Log Request Headers
        requestLog.append("\uD83D\uDCE5 Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            requestLog.append("   ➝ ").append(headerName).append(" = ").append(request.getHeader(headerName)).append("\n");
        }

        log.info(requestLog.toString());
    }

    /**
     * Logs details of the outgoing response.
     *
     * @param response The response wrapper containing cached response data.
     * @throws IOException If an error occurs while reading the response body.
     */
    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        StringBuilder responseLog = new StringBuilder();
        responseLog.append("\n--------------------- RESPONSE ---------------------\n");
        responseLog.append("\uD83D\uDD35 Outgoing Response: Status = ").append(response.getStatus()).append("\n");

        // Log Response Headers
        responseLog.append("\uD83D\uDCE4 Headers:\n");
        response.getHeaderNames().forEach(headerName ->
                responseLog.append("   ➝ ").append(headerName).append(" = ").append(response.getHeader(headerName)).append("\n")
        );

        // Log Response Body
        String responseBody = new String(response.getContentAsByteArray());
        if (!responseBody.isEmpty()) {
            responseLog.append("\uD83D\uDCCC Body: ").append(responseBody).append("\n");
        } else {
            responseLog.append("\uD83D\uDCCC Body: [EMPTY]\n");
        }

        log.info(responseLog.toString());
    }

    /**
     * Cleans up resources if necessary.
     */
    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
