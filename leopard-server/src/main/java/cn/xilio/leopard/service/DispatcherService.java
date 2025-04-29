package cn.xilio.leopard.service;

public interface DispatcherService {
    /**
     * Obtain long links
     * @param code short code
     * @return Long link
     */
    String getLongUrl(String code);
}
