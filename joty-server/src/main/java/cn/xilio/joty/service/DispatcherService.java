package cn.xilio.joty.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DispatcherService {
    /**
     * Obtain long links
     * @param code short code
     * @return Long link
     */
    String getLongUrl(String code, HttpServletRequest request, HttpServletResponse response);
}
