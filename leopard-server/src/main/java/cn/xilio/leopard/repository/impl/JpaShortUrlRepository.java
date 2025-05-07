package cn.xilio.leopard.repository.impl;


import cn.xilio.leopard.adapter.portal.dto.request.ShortUrlPageRequest;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.ShortUrl;
import cn.xilio.leopard.repository.ShortUrlRepository;
import cn.xilio.leopard.repository.dao.ShortUrlEntityRepository;
import jakarta.annotation.Resource;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
    public PageResponse<ShortUrl> page(ShortUrlPageRequest request, String userId) {
        int page = request.getPage();
        int size = request.getSize();
        String groupId = request.getGroupId();
        // 3. 动态条件组装
        Specification<ShortUrl> spec = (root, query, cb) -> {
            Predicate predicate = cb.equal(root.get("createdBy"), userId);
            if (StringUtils.hasText(groupId)) {
                predicate = cb.and(predicate, cb.equal(root.get("groupId"), groupId));
            }
            return predicate;
        };
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
     * Page wise query of short link list
     *
     * @param request queryRequest
     * @return Paging query results
     */
    @Override
    public PageResponse<ShortUrl> page(PageQuery request) {
        int page = request.getPage();
        int size = request.getSize();

        PageRequest pageRequest = PageRequest.of(page < 1 ? 0 : (page - 1), size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ShortUrl> entityPage = shortUrlEntityRepository.findAll(pageRequest);
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

    /**
     * Delete expired short links
     */
    @Override
    public long deleteExpiredUrls() {
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.lessThan(root.get("expiredAt"), new java.util.Date());
        return shortUrlEntityRepository.delete(spec);
    }

    /**
     * Get short link information by short code
     *
     * @param ids    List of short link IDs
     * @param userId UserId
     * @return Short link information
     */
    @Override
    public List<ShortUrl> findByIds(List<String> ids, String userId) {
        Specification<ShortUrl> spec = (root, query, cb) ->
                cb.and(
                        cb.equal(root.get("createdBy"), userId),
                        root.get("id").in(ids)
                );
        return shortUrlEntityRepository.findAll(spec);
    }

    /**
     * Update group to new group
     *
     * @param groupIds List of group IDs
     * @param newGroup New group
     */
    @Override
    public void updateGroupToNew(List<String> groupIds, String newGroup) {
        shortUrlEntityRepository.updateGroupId(groupIds, newGroup);
    }
}


