package cn.xilio.leopard.service;



import cn.xilio.leopard.adapter.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.request.ShortUrlPageRequest;
import cn.xilio.leopard.adapter.portal.dto.request.UpdateShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.adapter.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.domain.dataobject.ShortUrl;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;

import java.util.List;


public interface ShortUrlService {
    /**
     * Create a single short link
     *
     * @param request Create request
     * @return Short link information
     */
    CreateSingleShortUrlResponse createSingle(CreateSingleShortUrlRequest request);

    /**
     * Batch create short links
     *
     * @param request Create request
     * @return Create Results
     */
    CreateBatchShortUrlResponse createBatchShortUrl(CreateBatchShortUrlRequest request);

    /**
     * Batch delete short links
     *
     * @param ids Link ID List
     */
    void deleteShortUrl(List<String> ids);

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    PageResponse<ShortUrl> getShortUrlsByUser(ShortUrlPageRequest request);

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    PageResponse<ShortUrl> getShortUrls(PageQuery request);
    /**
     * Get short link information
     *
     * @param id ID
     * @return Short link information
     */
    ShortUrl getShortUrlInfo(String id);

    /**
     * Get short link information
     *
     * @param code Short Code
     * @return Short link information
     */
    ShortUrl getByShortCode(String code);

    /**
     * Delete expired short links
     */
    long deleteExpiredUrls();

    /**
     * Update short link
     * @param request Update info
     */
    void update(UpdateShortUrlRequest request);
}
