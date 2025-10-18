package cn.xilio.joty.service;



import cn.xilio.joty.adapter.portal.dto.request.*;
import cn.xilio.joty.adapter.portal.dto.response.ShortUrlWithStats;
import cn.xilio.joty.adapter.portal.dto.response.SingleShortUrlResponse;
import cn.xilio.joty.domain.dataobject.ShortUrl;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;

import java.util.List;


public interface ShortUrlService {
    /**
     * Create a single short link
     *
     * @param request Create request
     * @return Short link information
     */
    SingleShortUrlResponse createSingle(CreateSingleShortUrlRequest request);

    /**
     * Batch create short links
     *
     * @param request Create request
     * @return Create Results
     */
    List<SingleShortUrlResponse> createBatchShortUrl(CreateBatchShortUrlRequest request);

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
    PageResponse<ShortUrlWithStats> getShortUrlsByUser(ShortUrlPageRequest request);

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

    /**
     * Fast create
     * @param request Create info
     * @return A short url info
     */
    SingleShortUrlResponse fastCreateSingle(FastCreateShortUrlRequest request);

    /**
     * Restore short link
     * @param request Link info
     * @return result
     */
    SingleShortUrlResponse restoreShortUrl(RestoreShortUrlRequest request);
}
