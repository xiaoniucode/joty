package cn.xilio.leopard.repository.impl;



import cn.xilio.leopard.adapter.portal.dto.response.StatsResponse;
import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.repository.dao.AccessRecordEntityRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {
    @Resource
    private AccessRecordEntityRepository accessRecordEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<StatsResponse> findStatsCountByType(String shortCode, String type) {
                String sql = """
            SELECT 
                :type AS type,
                ANY_VALUE(CASE :type
                    WHEN 'IP' THEN ip_address
                    WHEN 'OS' THEN COALESCE(os,'Unknown')
                    WHEN 'BROWSER' THEN COALESCE(browser,'Unknown')
                    WHEN 'DEVICE' THEN COALESCE(device_type,'Unknown')
                    WHEN 'PROVINCE' THEN COALESCE(province,'未知')
                    ELSE 'unknown'
                END) AS name,
                COUNT(id) AS count
            FROM access_record
            WHERE short_code = :shortCode
            GROUP BY 
                CASE :type
                    WHEN 'IP' THEN ip_address
                    WHEN 'OS' THEN os
                    WHEN 'BROWSER' THEN browser
                    WHEN 'DEVICE' THEN device_type
                    WHEN 'PROVINCE' THEN province
                    ELSE 'unknown'
                END
            ORDER BY count DESC
            LIMIT 1000
        """;

                // 创建原生查询并手动映射结果
                List<Object[]> results = entityManager.createNativeQuery(sql)
                        .setParameter("type", type)
                        .setParameter("shortCode", shortCode)
                        .getResultList();

                // 转换为StatsResponse对象
                return results.stream()
                        .map(row -> new StatsResponse(
                                (String) row[0],  // type
                                (String) row[1],  // name
                                ((Number) row[2]).longValue()  // count
                        ))
                        .collect(Collectors.toList());
    }

}

