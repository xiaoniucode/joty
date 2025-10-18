package cn.xilio.joty.repository.impl;


import cn.xilio.joty.adapter.portal.dto.request.ShortUrlPageRequest;
import cn.xilio.joty.adapter.portal.dto.response.AccessStats;
import cn.xilio.joty.adapter.portal.dto.response.ShortUrlWithStats;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.dataobject.ShortUrl;
import cn.xilio.joty.repository.ShortUrlRepository;
import cn.xilio.joty.repository.dao.AccessRecordEntityRepository;
import cn.xilio.joty.repository.dao.ShortUrlEntityRepository;
import jakarta.annotation.Resource;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class JpaShortUrlRepository implements ShortUrlRepository {
    @Resource
    private ShortUrlEntityRepository shortUrlEntityRepository;
    @Autowired
    private AccessRecordEntityRepository accessRecordEntityRepository;


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
        // 3. 动态条件组装
        Specification<ShortUrl> spec = (root, query, cb) -> {
            Predicate predicate = cb.equal(root.get("createdBy"), userId);
            return predicate;
        };
        PageRequest pageRequest = PageRequest.of(page < 1 ? 0 : (page - 1), size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<ShortUrl> entityPage = shortUrlEntityRepository.findAll(spec, pageRequest);
        return PageResponse.of(
                entityPage.getContent(),
                (int) entityPage.getTotalElements(),
                entityPage.getNumber() + 1,
                entityPage.getSize(),
                entityPage.hasNext()
        );
    }
    @Override
    public PageResponse<ShortUrlWithStats> pageWithStats(ShortUrlPageRequest request, String userId) {
        int page = request.getPage();
        int size = request.getSize();
        // 1. 查询短链接分页数据
        Specification<ShortUrl> spec = (root, query, cb) -> {
            Predicate predicate = cb.equal(root.get("createdBy"), userId);
            return predicate;
        };

        PageRequest pageRequest = PageRequest.of(page < 1 ? 0 : (page - 1), size,
                Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<ShortUrl> entityPage = shortUrlEntityRepository.findAll(spec, pageRequest);

        // 2. 获取所有shortCode用于批量查询访问统计
        List<String> shortCodes = entityPage.getContent().stream()
                .map(ShortUrl::getShortCode)
                .collect(Collectors.toList());

        if (shortCodes.isEmpty()) {
            return PageResponse.of(
                    Collections.emptyList(),
                    0,
                    entityPage.getNumber() + 1,
                    entityPage.getSize(),
                    entityPage.hasNext()
            );
        }

        // 3. 批量查询访问统计数据
        Map<String, AccessStats> statsMap = getAccessStats(shortCodes);

        // 4. 合并结果
        List<ShortUrlWithStats> result = entityPage.getContent().stream()
                .map(shortUrl -> {
                    AccessStats stats = statsMap.getOrDefault(shortUrl.getShortCode(),
                            new AccessStats(0L, 0L, 0L, 0L));
                    ShortUrlWithStats shortUrlWithStats = new ShortUrlWithStats(
                            stats.getTotalVisits(),
                            stats.getTodayVisits(),
                            stats.getTotalVisitors(),
                            stats.getTodayVisitors());
                    BeanUtils.copyProperties(shortUrl, shortUrlWithStats);
                    return shortUrlWithStats;
                })
                .collect(Collectors.toList());

        return PageResponse.of(
                result,
                (int) entityPage.getTotalElements(),
                entityPage.getNumber() + 1,
                entityPage.getSize(),
                entityPage.hasNext()
        );
    }

    private Map<String, AccessStats> getAccessStats(List<String> shortCodes) {
        LocalDate today = LocalDate.now();
        Map<String, AccessStats> statsMap = new HashMap<>();

        // 查询总统计
        List<Object[]> totalResults = accessRecordEntityRepository.findTotalStatsByShortCodes(shortCodes);
        for (Object[] result : totalResults) {
            String code = (String) result[0];
            Long totalVisits = ((Number) result[1]).longValue();
            Long totalVisitors = ((Number) result[2]).longValue();
            statsMap.put(code, new AccessStats(totalVisits, 0L, totalVisitors, 0L));
        }

        // 查询今日统计
        List<Object[]> todayResults = accessRecordEntityRepository.findTodayStatsByShortCodes(shortCodes, today);
        for (Object[] result : todayResults) {
            String code = (String) result[0];
            Long todayVisits = ((Number) result[1]).longValue();
            Long todayVisitors = ((Number) result[2]).longValue();
            AccessStats stats = statsMap.getOrDefault(code, new AccessStats(0L, 0L, 0L, 0L));
            stats.setTodayVisits(todayVisits);
            stats.setTodayVisitors(todayVisitors);
            statsMap.put(code, stats);
        }

        return statsMap;
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
}


