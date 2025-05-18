package cn.xilio.leopard.service;

import jakarta.servlet.http.HttpServletRequest;

public interface DispatcherService {
    /**
     * Obtain long links
     * @param code short code
     * @return Long link
     */
    String getLongUrl(String code, HttpServletRequest request);
}
