package cn.xilio.leopard.domain.shorturl.repository;

import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;

import java.util.List;

public interface ShortUrlRepository {
    ShortUrl save(ShortUrl newShortUrl);

    /**
     * Page wise query of user's short link list
     * @param request queryRequest
     * @param userId UserId
     * @return Paging query results
     */
    PageResponse<ShortUrl> page(PageQuery request, String userId);

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

}
