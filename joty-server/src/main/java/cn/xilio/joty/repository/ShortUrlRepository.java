package cn.xilio.joty.repository;



import cn.xilio.joty.adapter.portal.dto.request.ShortUrlPageRequest;
import cn.xilio.joty.adapter.portal.dto.response.ShortUrlWithStats;
import cn.xilio.joty.domain.dataobject.ShortUrl;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;

import java.util.List;

public interface ShortUrlRepository {
    ShortUrl save(ShortUrl newShortUrl);

    /**
     * Page wise query of user's short link list
     * @param request queryRequest
     * @param userId UserId
     * @return Paging query results
     */
    PageResponse<ShortUrl> page(ShortUrlPageRequest request, String userId);

    /**
     * Page wise query of short link list
     * @param request queryRequest
     * @return Paging query results
     */
    PageResponse<ShortUrl> page(PageQuery request);

    /**
     * Batch delete short links
     * @param ids List of short link IDs
     */
    void deleteByIds(List<String> ids,String userId);

    /**
     * Get short link information
     * @param id Short link ID
     * @param userId UserId
     * @return Short link information
     */
    ShortUrl findById(String id, String userId);

    /**
     * Get short link information by short code
     * @param code Short code
     * @return Short link information
     */
    ShortUrl findByShortCode(String code);

    /**
     * Delete expired short links
     */
    long deleteExpiredUrls();

    /**
     * Get short link information by short code
     * @param ids List of short link IDs
     * @param userId UserId
     * @return Short link information
     */
    List<ShortUrl> findByIds(List<String> ids, String userId);

    public PageResponse<ShortUrlWithStats> pageWithStats(ShortUrlPageRequest request, String userId);
}
