package cn.xilio.leopard.module.biz.repository.impl;



import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.module.biz.domain.dataobject.ShortUrl;
import cn.xilio.leopard.module.biz.repository.ShortUrlRepository;
import cn.xilio.leopard.module.biz.repository.dao.ShortUrlEntityRepository;
import jakarta.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JpaShortUrlRepository implements ShortUrlRepository {
    @Resource
    private ShortUrlEntityRepository shortUrlEntityRepository;


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

    /**
     * Batch delete short links
     *
     * @param ids    List of short link IDs
     * @param userId UserId
     */
    @Override
    public void deleteByIds(List<String> ids, String userId) {
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("createdBy"), userId),
                        root.get("id").in(ids)
                );
        shortUrlEntityRepository.delete(spec);
    }

    /**
     * Get short link information
     *
     * @param id     Short link ID
     * @param userId UserId
     * @return Short link information
     */
    @Override
    public ShortUrl findById(String id, String userId) {
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("createdBy"), userId),
                        cb.equal(root.get("id"), id)
                );
        return shortUrlEntityRepository.findOne(spec)
                .orElse(null);
    }

    /**
     * Get short link information by short code
     *
     * @param code Short code
     * @return Short link information
     */
    @Override
    public ShortUrl findByShortCode(String code) {
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.and(cb.equal(root.get("shortCode"), code));
        return shortUrlEntityRepository.findOne(spec).orElse(null);
    }
}


