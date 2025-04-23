package cn.xilio.leopard.domain.shorturl.service;

import cn.xilio.leopard.api.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.api.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;

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
    PageResponse<ShortUrl> getShortUrls(PageQuery request);



}
