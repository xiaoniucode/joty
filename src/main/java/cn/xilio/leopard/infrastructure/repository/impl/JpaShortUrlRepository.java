package cn.xilio.leopard.infrastructure.repository.impl;


import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.repository.ShortUrlRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class JpaShortUrlRepository implements ShortUrlRepository {
    private final ShortUrlEntityRepository shortUrlEntityRepository;


    @Override
    public ShortUrl save(ShortUrl newShortUrl) {
        return shortUrlEntityRepository.save(newShortUrl);
    }

    /**
     * 分页查询用户的短链接列表
     *
     * @param request 查询请求
     * @param userId  用户ID
     * @return 分页查询结果
     */
    @Override
    public PageResponse<ShortUrl> page(PageQuery request, String userId) {
        int page = request.getPage();
        int size = request.getSize();
        // 3. 动态条件组装
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.equal(root.get("createdBy"), userId);
        PageRequest pageRequest = PageRequest.of(page < 1 ? 0 : (page - 1), size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ShortUrl> entityPage = shortUrlEntityRepository.findAll(spec, pageRequest);
        return PageResponse.of(
                entityPage.getContent(),
                (int) entityPage.getTotalElements(),
                entityPage.getNumber() + 1,
                entityPage.getSize(),
                entityPage.hasNext()
        );
    }
}

@Repository
interface ShortUrlEntityRepository extends JpaRepository<ShortUrl, String>, JpaSpecificationExecutor<ShortUrl> {

}

