package cn.xilio.leopard.domain.shorturl.repository;

import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;

public interface ShortUrlRepository {
    ShortUrl save(ShortUrl newShortUrl);

    /**
     * 分页查询用户的短链接列表
     * @param request 查询请求
     * @param userId 用户ID
     * @return 分页查询结果
     */
    PageResponse<ShortUrl> page(PageQuery request, String userId);
}
